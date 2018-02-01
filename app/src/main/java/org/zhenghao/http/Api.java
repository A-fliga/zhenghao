package org.zhenghao.http;


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

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import rx.Observable;

/**
 * Created by www on 2017/11/13.
 * 定义网络请求接口
 */

public interface Api {

    /**
     * 注册
     *
     * @return
     */
    @Headers({"Content-Type: application/json"})
    @POST("register")
    Observable<BaseEntity> register(@Body RequestBody register);

    /**
     * 发送验证码
     *
     * @return
     */
    @Headers({"Content-Type: application/json"})
    @POST("getVerifyCode")
    Observable<BaseEntity<VerifyCodeBean>> getVerifyCode(@Body RequestBody requestBody);


    /**
     * 登录
     *
     * @return
     */
    @Headers({"Content-Type: application/json"})
    @POST("login")
    Observable<BaseEntity<UserBean>> login(@Body RequestBody requestBody);


    /**
     * 修改密码
     *
     * @return
     */
    @Headers({"Content-Type: application/json"})
    @POST("updatePassword")
    Observable<BaseEntity> updatePassword(@Body RequestBody updatePassword);

    /**
     * 认证党员
     */
    @Headers({"Content-Type: application/json"})
    @POST("authPartyMember")
    Observable<BaseEntity> toIdentify(@Body RequestBody authPartyMember);

    /**
     * 党员积分
     */
    @Headers({"Content-Type: application/json"})
    @POST("partyManIntegral")
    Observable<BaseEntity<PartyIntegrationBean>> getPartyIntegration(@Body RequestBody partyManIntegral);

    /**
     * 获取学习记录
     */
    @Headers({"Content-Type: application/json"})
    @POST("learningRecord")
    Observable<LearningRecordBean> getLearningRecord(@Body RequestBody learningRecord);

    /**
     * 意见反馈
     */
    @Headers({"Content-Type: application/json"})
    @POST("opinionFeedback")
    Observable<BaseEntity> feedBack(@Body RequestBody feedBack);

    /**
     * 关于宝胜村
     */
    @Headers({"Content-Type: application/json"})
    @POST("aboutBS")
    Observable<BaseEntity<AboutBaoShengBean>> aboutBaoSheng();

    /**
     * 会议签到
     */
    @Headers({"Content-Type: application/json"})
    @POST("meetingSign")
    Observable<BaseEntity<SignBean>> toSign(@Body RequestBody sign);


    /**
     * 通知列表
     */
    @Headers({"Content-Type: application/json"})
    @POST("inform")
    Observable<NoticeListBean> getNoticeList(@Body RequestBody inform);

    /**
     * 通知详情
     */
    @Headers({"Content-Type: application/json"})
    @POST("informDetail")
    Observable<BaseEntity<NoticeDetailBean>> getNoticeDetail(@Body RequestBody informDetail);

    /**
     * 退出登录
     */
    @Headers({"Content-Type: application/json"})
    @POST("exit")
    Observable<BaseEntity> exit(@Body RequestBody exit);


    /**
     * 首页轮播图
     */
    @Headers({"Content-Type: application/json"})
    @POST("cycleInformation")
    Observable<BannerBean> getBanner();


    /**
     * 获取资讯列表
     */
    @Headers({"Content-Type: application/json"})
    @POST("getNineteenSpiritList")
    Observable<BaseEntity<InfoListBean>> getInfoList(@Body RequestBody getNineteenSpiritList);

    /**
     * 轮播图详情
     */
    @Headers({"Content-Type: application/json"})
    @POST("cycleInformationDetail")
    Observable<BaseEntity<BannerAndInfoDetailBean>> showBannerDetail(@Body RequestBody cycleInformationDetail);

    /**
     * 资讯详情
     */
    @Headers({"Content-Type: application/json"})
    @POST("getNineteenSpiritDetail")
    Observable<BaseEntity<BannerAndInfoDetailBean>> showInfoDetail(@Body RequestBody getNineteenSpiritDetail);

    /**
     * 获取党员学习类型
     */
    @Headers({"Content-Type: application/json"})
    @POST("learn")
    Observable<PartyLearningBean> getLearningType();

    /**
     * 获取党员学习列表
     */
    @Headers({"Content-Type: application/json"})
    @POST("learnList")
    Observable<BaseEntity<LearningListBean>> getLearningList(@Body RequestBody getLearningList);


    /**
     * 获取党员学习详情
     */
    @Headers({"Content-Type: application/json"})
    @POST("learningDetail")
    Observable<BaseEntity<LearningDetailBean>> getLearningDetail(@Body RequestBody learningDetail);


    /**
     * 增加学习完成记录
     */
    @Headers({"Content-Type: application/json"})
    @POST("addLearningRecord")
    Observable<BaseEntity> updateStatus(@Body RequestBody addLearningRecord);

    /**
     * 修改个人信息
     */
    @Headers({"Content-Type: application/json"})
    @POST("editUser")
    Observable<BaseEntity> saveUserInfo(@Body RequestBody editUser);

    /**
     * 修改个人头像
     */
    @Multipart
//    @Headers({"Content-Type: application/json"})
    @POST("uploadHeadPic")
    Observable<UpdateIconBean> updateIcon(@Part MultipartBody.Part body);
}
