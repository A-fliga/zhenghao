package org.baoshengVillage.mvp.presenter.activity;

import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.baoshengVillage.R;
import org.baoshengVillage.mvp.model.PublicModel;
import org.baoshengVillage.mvp.model.bean.BaseEntity;
import org.baoshengVillage.mvp.model.bean.VerifyCodeBean;
import org.baoshengVillage.mvp.view.RegisterDelegate;
import org.baoshengVillage.utils.LogUtil;
import org.baoshengVillage.utils.ToastUtil;

import java.util.Timer;
import java.util.TimerTask;

import rx.Subscriber;

/**
 * Created by www on 2018/1/3.
 */

public class RegisterActivity extends ActivityPresenter<RegisterDelegate> {
    //倒计时用
    private Timer timer = new Timer();
    private TimerTask task;
    private int mCount;
    private EditText num_et, pwd_et, code_et;

    @Override
    public Class<RegisterDelegate> getDelegateClass() {
        return RegisterDelegate.class;
    }

    @Override
    public boolean isSetDisplayHomeAsUpEnabled() {
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        viewDelegate.setOnClickListener(onClickListener, R.id.code_btn,R.id.confirm_btn);
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.code_btn:
                    getVerifyCode();
                    break;
                case R.id.confirm_btn:
                    //去注册
                    toRegister();
            }
        }
    };

    private void toRegister() {
        if (num_et.getText().toString().length() != 11) {
            ToastUtil.s("请输入11位手机号");
        } else if (!(pwd_et.getText().length() > 5 && pwd_et.getText().length() < 21)) {
            ToastUtil.s("请输入6-21位密码");
        }
        else if (code_et.getText().toString().replaceAll(" ","").isEmpty()) {
            ToastUtil.s("验证码不能为空");
        }
        else {
            PublicModel.getInstance().register(new Subscriber<BaseEntity>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {
                    LogUtil.d("zhuce",e.toString());
                }

                @Override
                public void onNext(BaseEntity baseEntity) {
                    if(baseEntity.getCode() == 0){
                        if(task != null)
                            task.cancel();
                        ToastUtil.s("注册成功");
                        finish();
//                       startMyActivityWithFinish(LoginActivity.class,null);
                    }
                    else ToastUtil.s(baseEntity.getMsg());
                }
            },num_et.getText().toString(),pwd_et.getText().toString(),code_et.getText().toString());
        }
    }

    private void getVerifyCode() {
        if (num_et.getText().toString().length() != 11) {
            ToastUtil.s("请输入11位手机号");
        } else {
            PublicModel.getInstance().getVerifyCode(new Subscriber<BaseEntity<VerifyCodeBean>>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {
                    LogUtil.d("yanzheng", e.toString());
                }

                @Override
                public void onNext(BaseEntity<VerifyCodeBean> verifyCodeBeanBaseEntity) {
                    if (verifyCodeBeanBaseEntity.getCode() != 0) {
                        ToastUtil.s(verifyCodeBeanBaseEntity.getMsg());
                    } else {
                        ToastUtil.s("验证码已发送到：" + num_et.getText().toString());
                        //开始倒计时
                        startCountDown(60);
                    }
                }
            }, num_et.getText().toString(), "1");
        }
    }


    //发送验证码倒计时
    public boolean startCountDown(final int count) {
        this.mCount = count;
        task = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        get(R.id.code_btn).setEnabled(false);
                        mCount--;
                        viewDelegate.setTextColor(R.id.code_btn, getResources().getColor(R.color.color_999999));
                        viewDelegate.setText(R.id.code_btn, String.valueOf(mCount));
                        if (mCount == 0) {
                            findViewById(R.id.code_btn).setEnabled(true);
                            viewDelegate.setText(R.id.code_btn, "重发");
                            viewDelegate.setTextColor(R.id.code_btn, getResources().getColor(R.color.color_ffffff));
                            task.cancel();
                            mCount = count;
                        }
                    }
                });
            }
        };
        timer.schedule(task, 0, 1000);
        return true;
    }

    private void initView() {
        viewDelegate.getTitleView().setText("注册");
        Button btn = get(R.id.confirm_btn);
        btn.setText("注册");
        num_et = get(R.id.register_num_et);
        pwd_et = get(R.id.register_pwd_et);
        pwd_et.setHint("请输入密码");
        code_et = get(R.id.register_code_et);
    }


    //EditText
    public static void setEditTextInhibitInputSpace(EditText editText) {
        InputFilter filter = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                return " ".equals(source) ? "" : null;
            }
        };

        //保留原有的EditText的filter
        InputFilter[] oldFilters = editText.getFilters();
        int oldFiltersLength = oldFilters.length;
        InputFilter[] newFilters = new InputFilter[oldFiltersLength + 1];
        if (oldFiltersLength > 0) {
            System.arraycopy(oldFilters, 0, newFilters, 0, oldFiltersLength);
        }
        //添加新的过滤规则
        newFilters[oldFiltersLength] = filter;
        editText.setFilters(newFilters);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (task != null)
            task.cancel();
    }
}
