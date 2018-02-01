package org.zhenghao.mvp.presenter.activity;

import android.os.Bundle;

import org.zhenghao.application.UserManager;
import org.zhenghao.mvp.model.PublicModel;
import org.zhenghao.mvp.model.bean.LearningRecordBean;
import org.zhenghao.mvp.view.LearningRecordDelegate;
import org.zhenghao.utils.ToastUtil;

import rx.Subscriber;

/**
 * Created by www on 2018/1/8.
 * 学习记录页面
 */

public class LearningRecordActivity extends ActivityPresenter<LearningRecordDelegate> {
    @Override
    public Class<LearningRecordDelegate> getDelegateClass() {
        return LearningRecordDelegate.class;
    }

    @Override
    public boolean isSetDisplayHomeAsUpEnabled() {
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLearningRecord();
    }

    private void getLearningRecord() {
        PublicModel.getInstance().getLearningRecord(new Subscriber<LearningRecordBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(LearningRecordBean learningRecordBean) {
                if (learningRecordBean.getCode() != 0) {
                    ToastUtil.s(learningRecordBean.getMsg());
                } else viewDelegate.initView(learningRecordBean.getResult());
            }
        }, UserManager.getInstance().getUserBean().getId());
    }
}
