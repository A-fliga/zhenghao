package org.zhenghao.http;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.zhenghao.BuildConfig;
import org.zhenghao.application.MyApplication;
import org.zhenghao.constants.Constants;
import org.zhenghao.http.cookie.CookiesManager;
import org.zhenghao.mvp.model.bean.AboutBaoShengBean;
import org.zhenghao.mvp.model.bean.BannerAndInfoDetailBean;
import org.zhenghao.mvp.model.bean.BannerBean;
import org.zhenghao.mvp.model.bean.BaseEntity;
import org.zhenghao.mvp.model.bean.InfoListBean;
import org.zhenghao.mvp.model.bean.LearningDetailBean;
import org.zhenghao.mvp.model.bean.LearningListBean;
import org.zhenghao.mvp.model.bean.LearningRecordBean;
import org.zhenghao.mvp.model.bean.NoticeDetailBean;
import org.zhenghao.mvp.model.bean.NoticeListBean;
import org.zhenghao.mvp.model.bean.PartyIntegrationBean;
import org.zhenghao.mvp.model.bean.PartyLearningBean;
import org.zhenghao.mvp.model.bean.SignBean;
import org.zhenghao.mvp.model.bean.UpdateIconBean;
import org.zhenghao.mvp.model.bean.UserBean;
import org.zhenghao.mvp.model.bean.VerifyCodeBean;
import org.zhenghao.utils.MD5Util;

import java.io.File;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by www on 2017/11/13.
 */

public final class HttpClient {

    /**
     * HttpClient 对象
     */
    private static volatile HttpClient sHttpClient;

    /**
     * mmApi 接口
     */
    private final Api mApi;
    private Gson mGson;

    /**
     * 私有的构造方法
     */
    private HttpClient(String host) {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(
                new HttpLoggingInterceptor.Logger() {
                    @Override
                    public void log(String message) {
                        HttpLoggingInterceptor.Logger.DEFAULT.log(message);
//                        FileUtil.writeLog(message);
                    }
                });
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .retryOnConnectionFailure(true)
                .connectTimeout(30, TimeUnit.SECONDS)
                .hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                })
                //是否设置cookie
                .cookieJar(new CookiesManager(MyApplication.getContext()))
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(host)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .build();
        mApi = retrofit.create(Api.class);
    }

    private HashMap<String, String> getBodyMap(String[] key, String[] value) {
        HashMap<String, String> queryMap = new HashMap<>();
        for (int i = 0; i < key.length; i++) {
            queryMap.put(key[i], value[i]);
        }
        return queryMap;
    }

    private String[] getStrings(String content) {
        return content.split(",");
    }

    private String[] getStrings(String... strings) {
        String[] content = new String[strings.length];
        for (int i = 0; i < strings.length; i++) {
            content[i] = strings[i];
        }
        return content;
    }

    /**
     * @return return {@link HttpClient} 单例
     */
    public static HttpClient getInstance() {
        if (sHttpClient == null) {
            synchronized (HttpClient.class) {
                if (sHttpClient == null) {
                    sHttpClient = new HttpClient(BuildConfig.HOST + "baoshengcun/appApi/");
                }
            }
        }
        return sHttpClient;
    }

    /**
     *
     */
    public static HttpClient getNewInstance() {
        return new HttpClient(BuildConfig.HOST + "baoshengcun/api/");
    }

    /**
     * 线程切换
     *
     * @param o   {@link Observable}
     * @param s   {@link Subscriber}
     * @param <T> 可变类型
     */
    private <T> void toSubscribe(Observable<T> o, Subscriber<T> s) {
        o
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s);
    }

    private RequestBody getMapRequestBody(HashMap<String, String> params) {
        return RequestBody.create(MediaType.parse(Constants.CONTENT_TYPE), getGson().toJson(params));
    }

    private RequestBody getObjRequestBody(Object o) {
        return RequestBody.create(MediaType.parse(Constants.CONTENT_TYPE), getGson().toJson(o));
    }

    private MultipartBody.Part getFileRequestBody(File file) {
        String token = "ASDDSKKK19990SDDDSS";//用户token
//        MultipartBody.Builder builder = new MultipartBody.Builder()
//                .setType(MultipartBody.FORM)//表单类型
//                .addFormDataPart(ParamKey.TOKEN, token);//ParamKey.TOKEN 自定义参数key常量类，即参数名
//        RequestBody imageBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
//        builder.addFormDataPart("imgfile", file.getName(), imageBody);//imgfile 后台接收图片流的参数名


        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        return MultipartBody.Part.createFormData("file", file.getName(), requestBody);
    }

    private Gson getGson() {
        if (mGson == null)
            mGson = new GsonBuilder().disableHtmlEscaping().create();
        return mGson;
    }


    /**
     * 注册
     */
    public void register(Subscriber<BaseEntity> subscriber, String phone, String password, String verifyCode) {
        String result = MD5Util.md5crypt(password);
        Observable observable = mApi.register(
                getMapRequestBody(getBodyMap(getStrings("phone,verifyCode,password"), getStrings(phone, verifyCode, result))));
        toSubscribe(observable, subscriber);
    }

    /**
     * 获取验证码
     */
    public void getVerifyCode(Subscriber<BaseEntity<VerifyCodeBean>> subscriber, String phone, String type) {
        HashMap<String, String> codeMap = new HashMap<>();
        codeMap.put("phone", phone);
        codeMap.put("type", type);
        Observable observable = mApi.getVerifyCode(getMapRequestBody(codeMap));
        toSubscribe(observable, subscriber);
    }

    /**
     * 登录
     */
    public void login(Subscriber<BaseEntity<UserBean>> subscriber, String phone, String pwd) {
        String result = MD5Util.md5crypt(pwd);
        Observable observable = mApi.login(getMapRequestBody
                (getBodyMap(getStrings("phone,password"), getStrings(phone, result))));
        toSubscribe(observable, subscriber);
    }

    /**
     * 注册
     */
    public void updatePassword(Subscriber<BaseEntity> subscriber, String phone, String password, String verifyCode) {
        String result = MD5Util.md5crypt(password);
        Observable observable = mApi.updatePassword(
                getMapRequestBody(getBodyMap(getStrings("phone,verifyCode,password"), getStrings(phone, verifyCode, result))));
        toSubscribe(observable, subscriber);
    }

    /**
     * 去认证党员
     */
    public void toIdentify(Subscriber<BaseEntity> subscriber, String userId, String userIdCard) {
        Observable observable = mApi.toIdentify(getMapRequestBody(getBodyMap(getStrings("userId,idCard"), getStrings(userId, userIdCard))));
        toSubscribe(observable, subscriber);
    }

    /**
     * 获取党员积分
     */
    public void getPartyIntegration(Subscriber<BaseEntity<PartyIntegrationBean>> subscriber, String userId) {
        Observable observable = mApi.getPartyIntegration(getMapRequestBody(getBodyMap(getStrings("userId"), getStrings(userId))));
        toSubscribe(observable, subscriber);
    }

    /**
     * 获取学习记录
     */
    public void getLearningRecord(Subscriber<LearningRecordBean> subscriber, int userId) {
        Observable observable = mApi.getLearningRecord(getMapRequestBody(getBodyMap(getStrings("userId"), getStrings(String.valueOf(userId)))));
        toSubscribe(observable, subscriber);
    }

    /**
     * 意见反馈
     */
    public void feedBack(Subscriber<BaseEntity> subscriber, int userId, String content) {
        Observable observable = mApi.feedBack(getMapRequestBody(getBodyMap(getStrings("userId,content"), getStrings(String.valueOf(userId), content))));
        toSubscribe(observable, subscriber);
    }

    /**
     * 获取学习记录
     */
    public void aboutBaoSheng(Subscriber<BaseEntity<AboutBaoShengBean>> subscriber) {
        Observable observable = mApi.aboutBaoSheng();
        toSubscribe(observable, subscriber);
    }

    /**
     * 会议签到
     */
    public void toSign(Subscriber<BaseEntity<SignBean>> subscriber, String pmId, String code) {
        Observable observable = mApi.toSign(getMapRequestBody(getBodyMap(getStrings("pmId,code"), getStrings(pmId, code))));
        toSubscribe(observable, subscriber);
    }

    /**
     * 通知列表
     */
    public void getNoticeList(Subscriber<NoticeListBean> subscriber, int informFor, int pageIndex) {
        Observable observable = mApi.getNoticeList(getMapRequestBody(getBodyMap(getStrings("informFor,pageIndex,pageSize"),
                getStrings(String.valueOf(informFor), String.valueOf(pageIndex), "10"))));
        toSubscribe(observable, subscriber);

    }

    /**
     * 获取通知详情
     */
    public void getNoticeDetail(Subscriber<BaseEntity<NoticeDetailBean>> subscriber, int id) {
        Observable observable = mApi.getNoticeDetail(getMapRequestBody(getBodyMap(getStrings("id"), getStrings(String.valueOf(id)))));
        toSubscribe(observable, subscriber);
    }

    /**
     * 退出登录
     */
    public void exit(Subscriber<BaseEntity> subscriber, int id) {
        Observable observable = mApi.exit(getMapRequestBody(getBodyMap(getStrings("id"), getStrings(String.valueOf(id)))));
        toSubscribe(observable, subscriber);
    }

    /**
     * 首页轮播图
     */
    public void getBanner(Subscriber<BannerBean> subscriber) {
        Observable observable = mApi.getBanner();
        toSubscribe(observable, subscriber);
    }

    /**
     * 获取资讯列表
     */
    public void getInfoList(Subscriber<BaseEntity<InfoListBean>> subscriber, String typeId, String pageIndex) {
        Observable observable = mApi.getInfoList(getMapRequestBody(getBodyMap(getStrings("typeId,pageIndex"), getStrings(typeId, pageIndex))));
        toSubscribe(observable, subscriber);
    }


    /**
     * 轮播图详情
     */
    public void showBannerDetail(Subscriber<BaseEntity<BannerAndInfoDetailBean>> subscriber, String id) {
        Observable observable = mApi.showBannerDetail(getMapRequestBody(getBodyMap(getStrings("id"), getStrings(id))));
        toSubscribe(observable, subscriber);
    }

    /**
     * 资讯详情
     */
    public void showInfoDetail(Subscriber<BaseEntity<BannerAndInfoDetailBean>> subscriber, String nsId) {
        Observable observable = mApi.showInfoDetail(getMapRequestBody(getBodyMap(getStrings("nsId"), getStrings(nsId))));
        toSubscribe(observable, subscriber);
    }

    /**
     * 获取党员学习类型
     */
    public void getLearningType(Subscriber<PartyLearningBean> subscriber) {
        Observable observable = mApi.getLearningType();
        toSubscribe(observable, subscriber);
    }

    /**
     * 获取党员学习列表
     */
    public void getLearningList(Subscriber<BaseEntity<LearningListBean>> subscriber, String userId, int learningTypeId) {
        Observable observable = mApi.getLearningList(getMapRequestBody(getBodyMap(getStrings("userId,learningTypeId"),
                getStrings(userId, String.valueOf(learningTypeId)))));
        toSubscribe(observable, subscriber);
    }

    /**
     * 获取党员学习详情
     */
    public void getLearningDetail(Subscriber<BaseEntity<LearningDetailBean>> subscriber, String userId, String learningId) {
        Observable observable = mApi.getLearningDetail(getMapRequestBody(getBodyMap(getStrings("userId,learningId"),
                getStrings(userId, learningId))));
        toSubscribe(observable, subscriber);
    }


    /**
     * 增加学习完成记录
     */
    public void updateStatus(Subscriber<BaseEntity> subscriber, String userId, String learningId) {
        Observable observable = mApi.updateStatus(getMapRequestBody(getBodyMap(getStrings("userId,learningId"),
                getStrings(userId, learningId))));
        toSubscribe(observable, subscriber);
    }


    /**
     * 修改个人信息
     */
    public void saveUserInfo(Subscriber<BaseEntity> subscriber, UserBean userBean) {
        Observable observable = mApi.saveUserInfo(getObjRequestBody(userBean));
        toSubscribe(observable, subscriber);
    }

    /**
     * 修改头像
     */
    public void updateIcon(Subscriber<UpdateIconBean> subscriber, File path) {
        Observable observable = mApi.updateIcon(getFileRequestBody(path));
        toSubscribe(observable, subscriber);
    }
}
