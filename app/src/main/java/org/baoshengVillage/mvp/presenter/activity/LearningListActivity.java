package org.baoshengVillage.mvp.presenter.activity;

import android.os.Bundle;

import org.baoshengVillage.application.UserManager;
import org.baoshengVillage.mvp.model.PublicModel;
import org.baoshengVillage.mvp.model.bean.BaseEntity;
import org.baoshengVillage.mvp.model.bean.LearningListBean;
import org.baoshengVillage.mvp.view.LearningListDelegate;
import org.baoshengVillage.utils.ToastUtil;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import rx.Subscriber;

/**
 * Created by www on 2018/1/10.
 * 学习列表页面
 */

public class LearningListActivity extends ActivityPresenter<LearningListDelegate> {
    private int LearningTypeId = 0;

    @Override
    public Class<LearningListDelegate> getDelegateClass() {
        return LearningListDelegate.class;
    }

    @Override
    public boolean isSetDisplayHomeAsUpEnabled() {
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            viewDelegate.setTitle(bundle.getString("LearningTypeName"));
            LearningTypeId = bundle.getInt("LearningTypeId");
            getLeaningList(LearningTypeId);
        }
        EventBus.getDefault().register(this);
    }

    private void getLeaningList(int learningTypeId) {
        PublicModel.getInstance().getLearningList(new Subscriber<BaseEntity<LearningListBean>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                ToastUtil.s("未知错误，请联系管理员");
            }

            @Override
            public void onNext(BaseEntity<LearningListBean> learningListBeanBaseEntity) {
                if (learningListBeanBaseEntity.getCode() != 0) {
                    ToastUtil.s(learningListBeanBaseEntity.getMsg());
                } else showList(learningListBeanBaseEntity.getResult());
            }
        }, UserManager.getInstance().getUserBean().getId(), learningTypeId);
    }

    private void showList(LearningListBean result) {
        viewDelegate.initTop(result);
        if (result.getLearnList().size() == 0) {
            ToastUtil.s("暂无数据");
        } else
            viewDelegate.initList(result.getLearnList());
    }

    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
    public void learningFinish(String learning) {
        if (learning.equals("learningFinished")) {
            getLeaningList(LearningTypeId);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
