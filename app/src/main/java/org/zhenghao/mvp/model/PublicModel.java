package org.zhenghao.mvp.model;


import org.zhenghao.http.HttpClient;
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
import org.zhenghao.utils.NetUtil;

import java.io.File;

import rx.Subscriber;

/**
 * Created by www on 6/7/2017.
 * 公共Model类
 */
public class PublicModel implements IModel {


    private PublicModel() {
    }

    private static PublicModel model;

    public static PublicModel getInstance() {
        if (null == model)
            model = new PublicModel();
        return model;
    }

    public interface ReLoginListener {
        void success(BaseEntity<UserBean> userBean);

        void error();

        void serverException(BaseEntity<UserBean> userBean);
    }

    public void autoLogin(String phone, String pwd, final ReLoginListener listener) {
        if (NetUtil.isConnect()) {
            PublicModel.getInstance().login(new Subscriber<BaseEntity<UserBean>>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {
                    listener.error();
                }

                @Override
                public void onNext(BaseEntity<UserBean> userBeanBaseEntity) {
                    if (userBeanBaseEntity.getCode() != 0) {
                        listener.serverException(userBeanBaseEntity);

                    } else {
                        listener.success(userBeanBaseEntity);
                    }
                }
            }, phone, pwd);
        } else {
            listener.error();
        }
    }

    /**
     * 注册
     */
    public void register(Subscriber<BaseEntity> subscriber, String phone, String pwd, String verifyCode) {
        if (NetUtil.isConnect()) {
            HttpClient.getInstance().register(subscriber, phone, pwd, verifyCode);
        }
    }

    /**
     * 获取验证码
     */
    public void getVerifyCode(Subscriber<BaseEntity<VerifyCodeBean>> subscriber, String phone, String type) {
        if (NetUtil.isConnect()) {
            HttpClient.getInstance().getVerifyCode(subscriber, phone, type);
        }
    }

    /**
     * 登录
     */
    public void login(Subscriber<BaseEntity<UserBean>> subscriber, String phone, String pwd) {
        HttpClient.getInstance().login(subscriber, phone, pwd);
    }

    /**
     * 修改密码
     */
    public void updatePassword(Subscriber<BaseEntity> subscriber, String phone, String pwd, String verifyCode) {
        if (NetUtil.isConnect()) {
            HttpClient.getInstance().updatePassword(subscriber, phone, pwd, verifyCode);
        }
    }

    /**
     * 认证党员
     */
    public void toIdentify(Subscriber<BaseEntity> subscriber, String userId, String userIdCard) {
        if (NetUtil.isConnect()) {
            HttpClient.getInstance().toIdentify(subscriber, userId, userIdCard);
        }
    }

    /**
     * 获取党员积分
     */
    public void getPartyIntegration(Subscriber<BaseEntity<PartyIntegrationBean>> subscriber, String userId) {
        if (NetUtil.isConnect()) {
            HttpClient.getInstance().getPartyIntegration(subscriber, userId);
        }
    }

    /**
     * 获取学习记录
     */
    public void getLearningRecord(Subscriber<LearningRecordBean> subscriber, int userId) {
        if (NetUtil.isConnect()) {
            HttpClient.getInstance().getLearningRecord(subscriber, userId);
        }
    }


    /**
     * 获取学习记录
     */
    public void feedBack(Subscriber<BaseEntity> subscriber, int userId, String content) {
        if (NetUtil.isConnect()) {
            HttpClient.getInstance().feedBack(subscriber, userId, content);
        }
    }

    /**
     * 关于宝胜村
     */
    public void aboutBaoSheng(Subscriber<BaseEntity<AboutBaoShengBean>> subscriber) {
        if (NetUtil.isConnect()) {
            HttpClient.getInstance().aboutBaoSheng(subscriber);
        }
    }

    /**
     * 会议签到
     */
    public void toSign(Subscriber<BaseEntity<SignBean>> subscriber, String pmId, String code) {
        if (NetUtil.isConnect()) {
            HttpClient.getNewInstance().toSign(subscriber, pmId, code);
        }
    }

    /**
     * 通知列表
     */
    public void getNoticeList(Subscriber<NoticeListBean> subscriber, int informFor, int pageIndex) {
        if (NetUtil.isConnect()) {
            HttpClient.getInstance().getNoticeList(subscriber, informFor, pageIndex);
        }
    }

    /**
     * 获取通知详情
     */
    public void getNoticeDetail(Subscriber<BaseEntity<NoticeDetailBean>> subscriber, int id) {
        if (NetUtil.isConnect()) {
            HttpClient.getInstance().getNoticeDetail(subscriber, id);
        }
    }

    /**
     * 退出登录
     */
    public void exit(Subscriber<BaseEntity> subscriber, int id) {
        if (NetUtil.isConnect()) {
            HttpClient.getInstance().exit(subscriber, id);
        }
    }

    /**
     * 首页轮播图
     */
    public void getBanner(Subscriber<BannerBean> subscriber) {
        if (NetUtil.isConnect()) {
            HttpClient.getInstance().getBanner(subscriber);
        }
    }

    /**
     * 获取资讯列表
     */
    public void getInfoList(Subscriber<BaseEntity<InfoListBean>> subscriber, int typeId, int pageIndex) {
        if (NetUtil.isConnect()) {
            HttpClient.getInstance().getInfoList(subscriber, String.valueOf(typeId), String.valueOf(pageIndex));
        }
    }

    /**
     * 轮播图详情
     */
    public void showBannerDetail(Subscriber<BaseEntity<BannerAndInfoDetailBean>> subscriber, int id) {
        if (NetUtil.isConnect()) {
            HttpClient.getInstance().showBannerDetail(subscriber, String.valueOf(id));
        }
    }

    /**
     * 资讯详情
     */
    public void showInfoDetail(Subscriber<BaseEntity<BannerAndInfoDetailBean>> subscriber, int id) {
        if (NetUtil.isConnect()) {
            HttpClient.getInstance().showInfoDetail(subscriber, String.valueOf(id));
        }
    }

    /**
     * 获取党员学习类型
     */
    public void getLearningType(Subscriber<PartyLearningBean> subscriber) {
        if (NetUtil.isConnect()) {
            HttpClient.getInstance().getLearningType(subscriber);
        }
    }

    /**
     * 获取党员学习列表
     */
    public void getLearningList(Subscriber<BaseEntity<LearningListBean>> subscriber, int userId, int learningTypeId) {
        if (NetUtil.isConnect()) {
            HttpClient.getInstance().getLearningList(subscriber, String.valueOf(userId), learningTypeId);
        }
    }

    /**
     * 获取党员学习详情
     */
    public void getLearningDetail(Subscriber<BaseEntity<LearningDetailBean>> subscriber, int userId, int learningId) {
        if (NetUtil.isConnect()) {
            HttpClient.getInstance().getLearningDetail(subscriber, String.valueOf(userId), String.valueOf(learningId));
        }
    }

    /**
     * 增加学习完成记录
     */
    public void updateStatus(Subscriber<BaseEntity> subscriber, int userId, int learningId) {
        if (NetUtil.isConnect()) {
            HttpClient.getInstance().updateStatus(subscriber, String.valueOf(userId), String.valueOf(learningId));
        }
    }

    /**
     * 修改个人信息
     */
    public void saveUserInfo(Subscriber<BaseEntity> subscriber, UserBean userBean) {
        if (NetUtil.isConnect()) {
            HttpClient.getInstance().saveUserInfo(subscriber, userBean);
        }
    }


    /**
     * 修改头像
     */
    public void updateIcon(Subscriber<UpdateIconBean> subscriber, File path) {
        if (NetUtil.isConnect()) {
            HttpClient.getInstance().updateIcon(subscriber, path);
        }
    }
}