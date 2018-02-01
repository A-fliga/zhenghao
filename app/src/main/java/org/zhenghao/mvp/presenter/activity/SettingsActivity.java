package org.zhenghao.mvp.presenter.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.zhenghao.R;
import org.zhenghao.application.UserManager;
import org.zhenghao.mvp.model.PublicModel;
import org.zhenghao.mvp.model.bean.BaseEntity;
import org.zhenghao.mvp.view.SettingsDelegate;
import org.zhenghao.utils.ToastUtil;
import org.greenrobot.eventbus.EventBus;

import rx.Subscriber;

/**
 * Created by www on 2018/1/8.
 * 设置页面
 */

public class SettingsActivity extends ActivityPresenter<SettingsDelegate> {
    @Override
    public Class<SettingsDelegate> getDelegateClass() {
        return SettingsDelegate.class;
    }

    @Override
    public boolean isSetDisplayHomeAsUpEnabled() {
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewDelegate.setOnClickListener(onClickListener, R.id.change_pwd_settings, R.id.feedback_settings, R.id.about_baosheng_settings, R.id.logoff_settings);
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.change_pwd_settings:
                    toChangePwd();
                    break;
                case R.id.feedback_settings:
                    startMyActivity(FeedBackActivity.class, null);
                    break;
                case R.id.about_baosheng_settings:
                    Bundle bundle = new Bundle();
                    bundle.putInt("WebViewType", 0);
                    startMyActivity(WebViewActivity.class, bundle);
                    break;
                case R.id.logoff_settings:
                    toLogOut();
                    break;
            }
        }
    };


    private void toLogOut() {
        PublicModel.getInstance().exit(new Subscriber<BaseEntity>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                ToastUtil.s("未知错误，请联系管理员");
            }

            @Override
            public void onNext(BaseEntity baseEntity) {
                if (baseEntity.getCode() != 0) {
                    ToastUtil.s(baseEntity.getMsg());
                } else {
                    UserManager.getInstance().setUserNull();
                    ToastUtil.s("您已退出登录");
                    EventBus.getDefault().post(true);
                    finish();
                }
            }
        }, UserManager.getInstance().getUserBean().getId());

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        ToastUtil.s("sss");
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void toChangePwd() {
        Bundle bundle = new Bundle();
        bundle.putBoolean("fromLogin", false);
        startMyActivity(ChangePwdActivity.class, bundle);
    }
}
