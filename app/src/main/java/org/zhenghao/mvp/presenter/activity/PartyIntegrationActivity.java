package org.zhenghao.mvp.presenter.activity;

import android.os.Bundle;

import org.zhenghao.application.UserManager;
import org.zhenghao.mvp.model.PublicModel;
import org.zhenghao.mvp.model.bean.BaseEntity;
import org.zhenghao.mvp.model.bean.PartyIntegrationBean;
import org.zhenghao.mvp.view.PartyIntegrationDelegate;
import org.zhenghao.utils.ToastUtil;

import rx.Subscriber;

/**
 * Created by www on 2018/1/8.
 * 党员积分页面
 */

public class PartyIntegrationActivity extends ActivityPresenter<PartyIntegrationDelegate> {
    @Override
    public Class<PartyIntegrationDelegate> getDelegateClass() {
        return PartyIntegrationDelegate.class;
    }

    @Override
    public boolean isSetDisplayHomeAsUpEnabled() {
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        PublicModel.getInstance().getPartyIntegration(new Subscriber<BaseEntity<PartyIntegrationBean>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(BaseEntity<PartyIntegrationBean> partyIntegrationBeanBaseEntity) {
                if (partyIntegrationBeanBaseEntity.getCode() != 0) {
                    ToastUtil.s(partyIntegrationBeanBaseEntity.getMsg());
                } else {
                    viewDelegate.initViews(partyIntegrationBeanBaseEntity.getResult());

                }
            }
        }, String.valueOf(UserManager.getInstance().getUserBean().getId()));
    }
}
