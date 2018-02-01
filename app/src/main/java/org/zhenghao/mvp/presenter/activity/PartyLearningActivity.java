package org.zhenghao.mvp.presenter.activity;

import android.os.Bundle;

import org.zhenghao.mvp.model.PublicModel;
import org.zhenghao.mvp.model.bean.PartyLearningBean;
import org.zhenghao.mvp.view.PartyLearningDelegate;
import org.zhenghao.utils.ToastUtil;

import java.util.List;

import rx.Subscriber;

/**
 * Created by www on 2018/1/10.
 * 党员学习页面
 */

public class PartyLearningActivity extends ActivityPresenter<PartyLearningDelegate> {
    @Override
    public Class<PartyLearningDelegate> getDelegateClass() {
        return PartyLearningDelegate.class;
    }

    @Override
    public boolean isSetDisplayHomeAsUpEnabled() {
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLearningList();
    }

    private void getLearningList() {
        PublicModel.getInstance().getLearningType(new Subscriber<PartyLearningBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                ToastUtil.s("未知错误，请联系管理员");
            }

            @Override
            public void onNext(PartyLearningBean partyLearningBean) {
                if (partyLearningBean.getCode() != 0) {
                    ToastUtil.s(partyLearningBean.getMsg());
                } else {
                    showList(partyLearningBean.getResult());
                }
            }
        });
    }

    private void showList(List<PartyLearningBean.ResultBean> result) {
        viewDelegate.initList(result);
    }
}
