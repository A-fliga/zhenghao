package org.baoshengVillage.mvp.presenter.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import org.baoshengVillage.R;
import org.baoshengVillage.application.MyApplication;
import org.baoshengVillage.application.UserManager;
import org.baoshengVillage.mvp.model.PublicModel;
import org.baoshengVillage.mvp.model.bean.BaseEntity;
import org.baoshengVillage.mvp.model.bean.UserBean;
import org.baoshengVillage.mvp.view.LoginDelegate;
import org.baoshengVillage.utils.NetUtil;
import org.baoshengVillage.utils.ToastUtil;
import org.greenrobot.eventbus.EventBus;

import java.util.Map;

import rx.Subscriber;

import static org.baoshengVillage.constants.Constants.USER_PHONE;

/**
 * Created by www on 2018/1/3.
 */

public class LoginActivity extends ActivityPresenter<LoginDelegate> {
    private Boolean fromNormal = true;
    private EditText num_et, pwd_et;

    @Override
    public Class<LoginDelegate> getDelegateClass() {
        return LoginDelegate.class;
    }

    @Override
    public boolean isSetDisplayHomeAsUpEnabled() {
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getType();
        initView();
        viewDelegate.setOnClickListener(onClickListener, R.id.to_register, R.id.change_pwd, R.id.login_btn);
    }

    private void getType() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            fromNormal = bundle.getBoolean("fromNormal", true);
        }
    }

    private void initView() {
        num_et = get(R.id.login_num_et);
        pwd_et = get(R.id.login_pwd_et);
        Map<String, String> map = UserManager.getInstance().getUserPhonePwd();
        if (map.containsKey(USER_PHONE)) {
            num_et.setText(map.get(USER_PHONE));
        }
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.to_register:
                    startMyActivity(RegisterActivity.class, null);
                    break;
                case R.id.change_pwd:
                    startMyActivity(ChangePwdActivity.class, null);
                    break;
                case R.id.login_btn:
                    toLogin();
                    break;
            }

        }
    };

    private void toLogin() {
        if (num_et.getText().toString().length() != 11) {
            ToastUtil.s("请输入11位手机号");
        } else if (!(pwd_et.getText().length() > 5 && pwd_et.getText().length() < 21)) {
            ToastUtil.s("请输入6-21位密码");
        } else {
            if (NetUtil.isConnect()) {
                PublicModel.getInstance().login(new Subscriber<BaseEntity<UserBean>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(BaseEntity<UserBean> userBeanBaseEntity) {
                        if (userBeanBaseEntity.getCode() != 0) {
                            ToastUtil.s(userBeanBaseEntity.getMsg());
                        } else {
                            MyApplication.getAppContext().setAlisa("bsc" + userBeanBaseEntity.getResult().getUuid());
                            ToastUtil.s("登录成功");
                            UserManager.getInstance().setUserBean(userBeanBaseEntity.getResult());
                            UserManager.getInstance().setUserPhonePwd(num_et.getText().toString(), pwd_et.getText().toString());
                            if (fromNormal) {
                                finish();
                                EventBus.getDefault().post(true);
                            } else {
                                startMyActivityWithFinish(MainActivity.class, null);
                            }
                        }
                    }
                }, num_et.getText().toString(), pwd_et.getText().toString());
            }
        }
    }
}
