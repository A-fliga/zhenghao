package org.baoshengVillage.mvp.presenter.activity;

import android.os.Bundle;

import org.baoshengVillage.application.UserManager;
import org.baoshengVillage.mvp.model.PublicModel;
import org.baoshengVillage.mvp.model.bean.LearningRecordBean;
import org.baoshengVillage.mvp.view.LearningRecordDelegate;
import org.baoshengVillage.utils.ToastUtil;

import rx.Subscriber;

/**
 * Created by www on 2018/1/8.
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
