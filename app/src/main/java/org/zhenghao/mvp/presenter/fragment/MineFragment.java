package org.zhenghao.mvp.presenter.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.zhenghao.R;
import org.zhenghao.application.UserManager;
import org.zhenghao.mvp.model.PublicModel;
import org.zhenghao.mvp.model.bean.BaseEntity;
import org.zhenghao.mvp.model.bean.SignBean;
import org.zhenghao.mvp.presenter.activity.IdentifyPartyActivity;
import org.zhenghao.mvp.presenter.activity.LearningRecordActivity;
import org.zhenghao.mvp.presenter.activity.LoginActivity;
import org.zhenghao.mvp.presenter.activity.PartyIntegrationActivity;
import org.zhenghao.mvp.presenter.activity.PartyMemberActivity;
import org.zhenghao.mvp.presenter.activity.ScanActivity;
import org.zhenghao.mvp.presenter.activity.SettingUrlActivity;
import org.zhenghao.mvp.presenter.activity.SettingsActivity;
import org.zhenghao.mvp.presenter.activity.UserInfoActivity;
import org.zhenghao.mvp.view.MineFragmentDelegate;
import org.zhenghao.utils.ProgressDialogUtil;
import org.zhenghao.utils.ToastUtil;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import rx.Subscriber;


/**
 * Created by www on 2017/12/29.
 */

public class MineFragment extends FragmentPresenter<MineFragmentDelegate> {
    @Override
    public Class<MineFragmentDelegate> getDelegateClass() {
        return MineFragmentDelegate.class;
    }

    @Override
    protected void onFragmentVisible() {

    }

    @Override
    protected void onFragmentHidden() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);//订阅
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        viewDelegate.setOnClickListener(mOnclickListener, R.id.mine_to_login, R.id.mine_show_detail,
                R.id.party_member, R.id.mine_settings_img, R.id.mine_sign_rl, R.id.mine_integration_rl,
                R.id.mine_record_rl, R.id.mine_settings_rl);
    }

    private View.OnClickListener mOnclickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                //去登录
                case R.id.mine_to_login:
                    startMyActivity(LoginActivity.class, null);
                    break;
                //查看个人信息
                case R.id.mine_show_detail:
                    showUserInfo();
                    break;
                //非党员可以去认证
                case R.id.party_member:
                    startMyActivity(IdentifyPartyActivity.class, null);
                    break;
                //设置
                case R.id.mine_settings_img:
                    startMyActivity(SettingsActivity.class, null);
                    break;
                //会议签到
                case R.id.mine_sign_rl:
                    toNext(0);
                    break;
                //党员积分
                case R.id.mine_integration_rl:
                    toNext(1);
                    break;
                //学习记录
                case R.id.mine_record_rl:
                    toNext(2);
                    break;

                case R.id.mine_settings_rl:
                    startMyActivity(SettingUrlActivity.class, null);
            }
        }
    };

    private void showUserInfo() {
        if (!UserManager.getInstance().isPartyMember())
            startMyActivity(UserInfoActivity.class, null);
        else startMyActivity(PartyMemberActivity.class, null);
    }

    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
    public void loginChange(Boolean isChange) {
        if (isChange) {
            viewDelegate.initWidget();
        }
    }

    private void toNext(int position) {
        if (!UserManager.getInstance().alreadyLogin()) {
            startMyActivity(LoginActivity.class, null);
        } else if (!UserManager.getInstance().isPartyMember()) {
            ToastUtil.s("请先认证党员");
        } else {
            if (position == 0) {
                //去签到
                startScanner();
            }
            if (position == 1) {
                //党员积分
                startMyActivity(PartyIntegrationActivity.class, null);
            }
            if (position == 2) {
                //学习记录
                startMyActivity(LearningRecordActivity.class, null);
            }
        }
    }

    private void startScanner() {
        IntentIntegrator integrator = IntentIntegrator.forSupportFragment(this);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
        integrator.setCaptureActivity(ScanActivity.class);
        integrator.setPrompt("请正对二维码进行扫描"); //底部的提示文字，设为""可以置空
        integrator.setCameraId(0); //前置或者后置摄像头
        integrator.setBeepEnabled(false); //扫描成功的「哔哔」声，默认开启
        integrator.initiateScan();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() != null) {
                toSign(result.getContents());
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void toSign(String code) {
        PublicModel.getInstance().toSign(new Subscriber<BaseEntity<SignBean>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                ToastUtil.s("未知错误，请联系管理员");
            }

            @Override
            public void onNext(BaseEntity<SignBean> signBeanBaseEntity) {
                if (signBeanBaseEntity.getCode() != 0) {
                    ToastUtil.l(signBeanBaseEntity.getMsg());
                } else {
                    ProgressDialogUtil.instance().startLoadWithView(signBeanBaseEntity.getResult().getTitle(), signBeanBaseEntity.getResult().getIntegral());
                }
            }
        }, String.valueOf(UserManager.getInstance().getUserBean().getPartyMemberInformation().getId()), code);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);//取消订阅
    }
}
