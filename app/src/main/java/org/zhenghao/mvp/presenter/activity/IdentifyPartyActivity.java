package org.zhenghao.mvp.presenter.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import org.zhenghao.R;
import org.zhenghao.application.UserManager;
import org.zhenghao.mvp.model.PublicModel;
import org.zhenghao.mvp.model.bean.BaseEntity;
import org.zhenghao.mvp.model.bean.UserBean;
import org.zhenghao.mvp.view.IdentifyPartyDelegate;
import org.zhenghao.utils.SharedPreferencesUtil;
import org.zhenghao.utils.ToastUtil;
import org.greenrobot.eventbus.EventBus;

import java.util.Map;

import rx.Subscriber;

import static org.zhenghao.constants.Constants.USER_PHONE;
import static org.zhenghao.constants.Constants.USER_PWD;

/**
 * Created by www on 2018/1/8.
 * 认证页面
 */

public class IdentifyPartyActivity extends ActivityPresenter<IdentifyPartyDelegate> {
    private EditText name_et, idCart_et;

    @Override
    public Class<IdentifyPartyDelegate> getDelegateClass() {
        return IdentifyPartyDelegate.class;
    }

    @Override
    public boolean isSetDisplayHomeAsUpEnabled() {
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        get(R.id.identify_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toIdentify();
            }
        });
    }

    private void toIdentify() {
        if (name_et.getText().toString().replaceAll(" ", "").isEmpty()) {
            ToastUtil.s("请输入姓名");
        } else if (idCart_et.getText().toString().replaceAll(" ", "").isEmpty()) {
            ToastUtil.s("请输入身份证号");
        } else {
            PublicModel.getInstance().toIdentify(new Subscriber<BaseEntity>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {
                    ToastUtil.l("认证失败，请咨询管理员");
                }

                @Override
                public void onNext(BaseEntity baseEntity) {
                    if (baseEntity.getCode() != 0) {
                        ToastUtil.l(baseEntity.getMsg());
                    } else {
                        toAutoLogin();
                    }
                }
            }, String.valueOf(UserManager.getInstance().getUserBean().getId()), idCart_et.getText().toString());
        }
    }

    private void toAutoLogin() {
        Map<String, String> map = SharedPreferencesUtil.getPhonePwd();
        if (map != null && map.containsKey(USER_PHONE)) {
            PublicModel.getInstance().autoLogin(map.get(USER_PHONE), map.get(USER_PWD), new PublicModel.ReLoginListener() {
                @Override
                public void success(BaseEntity<UserBean> userBean) {
                    UserManager.getInstance().setUserBean(userBean.getResult());
                    EventBus.getDefault().post(true);
                    ToastUtil.l("认证成功，您隶属于" + userBean.getResult().getPartyMemberInformation().getWorkUnit());
                    finish();
                }

                @Override
                public void error() {
                    ToastUtil.l("认证失败，请重试");
                }

                @Override
                public void serverException(BaseEntity<UserBean> userBean) {
                    ToastUtil.s(userBean.getMsg());
                }
            });
        }
    }

    private void initView() {
        name_et = get(R.id.identify_name_et);
        idCart_et = get(R.id.identify_idCart_et);
    }

}
