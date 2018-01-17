package org.baoshengVillage.mvp.presenter.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import org.baoshengVillage.R;
import org.baoshengVillage.application.UserManager;
import org.baoshengVillage.mvp.model.PublicModel;
import org.baoshengVillage.mvp.model.bean.BaseEntity;
import org.baoshengVillage.mvp.model.bean.LearningDetailBean;
import org.baoshengVillage.mvp.view.LearningDetailDelegate;
import org.baoshengVillage.utils.DialogUtil;
import org.baoshengVillage.utils.ToastUtil;
import org.greenrobot.eventbus.EventBus;

import java.util.Timer;
import java.util.TimerTask;

import rx.Subscriber;

/**
 * Created by www on 2018/1/10.
 */

public class LearningDetailActivity extends ActivityPresenter<LearningDetailDelegate> {
    private Boolean canUpLoad = false;
    private Boolean isLearning = false;
    private WebView webView;
    private int learningId = 0;
    private TimerTask task;
    private int count;
    private LearningDetailBean detailBean;

    @Override
    public Class<LearningDetailDelegate> getDelegateClass() {
        return LearningDetailDelegate.class;
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
            learningId = bundle.getInt("LearningListId");
            getDetail(learningId);
        }

        initToolBar();
    }

    private void initToolBar() {
        viewDelegate.getToolbar().setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isLearning) {
                    showMDialog("您的学习时长未满，退出后需要重新学习", "下次再学，退出", "继续学习");
                } else if (canUpLoad) {
                    showMDialog("您还未提交学习结果，退出后需要重新学习", "不提交，退出", "先去提交");
                } else finish();
            }
        });
    }

    private void getDetail(int learningListId) {
        PublicModel.getInstance().getLearningDetail(new Subscriber<BaseEntity<LearningDetailBean>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                ToastUtil.s("未知错误，请联系管理员");
            }

            @Override
            public void onNext(BaseEntity<LearningDetailBean> learningDetailBeanBaseEntity) {
                if (learningDetailBeanBaseEntity.getCode() != 0) {
                    ToastUtil.s(learningDetailBeanBaseEntity.getMsg());
                } else {
                    showDetail(learningDetailBeanBaseEntity.getResult());
                }

            }
        }, UserManager.getInstance().getUserBean().getId(), learningListId);
    }

    private void showDetail(LearningDetailBean result) {
        detailBean = result;
        viewDelegate.initView(result);
        initWebView(result.getPartyMemberLearning().getContentUrl());
        viewDelegate.setOnClickListener(onClickListener, R.id.learning_status);
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.learning_status:
                    if (canUpLoad) {
                        //如果学习完成，就可以上传状态
                        updateStatus();
                    } else {
                        //如果还没开始学习，就开始倒计时
                        startCountDown(detailBean.getPartyMemberLearning().getLearningTime() * 60);
                    }
                    break;
            }
        }
    };

    private void startCountDown(int i) {
        isLearning = true;
        count = i;
        viewDelegate.learning_status.setEnabled(false);
        viewDelegate.learning_status.setClickable(false);
        viewDelegate.setLearningStatus(String.valueOf(count), false);
        task = new TimerTask() {
            @Override
            public void run() {
                count--;
                viewDelegate.setLearningStatus("还需要学习" + String.valueOf(count) + "秒", false);
                if (count == 0) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            viewDelegate.learning_status.setClickable(true);
                            viewDelegate.learning_status.setEnabled(true);
                        }
                    });
                    task.cancel();
                    canUpLoad = true;
                    isLearning = false;
                    viewDelegate.setLearningStatus("提交结果", true);
                }
            }
        };
        Timer timer = new Timer();
        timer.schedule(task, 0, 1000);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return sure2Quit(keyCode, event);
    }

    private boolean sure2Quit(int keyCode, KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            if (isLearning) {
                showMDialog("您的学习时长未满，退出后需要重新学习", "下次再学，退出", "继续学习");
                return false;
            } else if (canUpLoad) {
                showMDialog("您还未提交学习结果，退出后需要重新学习", "不提交，退出", "先去提交");
                return false;
            } else return super.onKeyDown(keyCode, event);
        }
        return super.onKeyDown(keyCode, event);
    }

    private void showMDialog(String message, String sure, String cancel) {
        DialogUtil.showDialog(this, message, sure, cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case -2:
                        dialog.dismiss();
                        break;
                    case -1:
                        finish();
                        break;
                }
            }
        });
    }

    private void updateStatus() {
        PublicModel.getInstance().updateStatus(new Subscriber<BaseEntity>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                ToastUtil.s("发生未知错误，提交失败");
            }

            @Override
            public void onNext(BaseEntity baseEntity) {
                if (baseEntity.getCode() != 0) {
                    ToastUtil.s(baseEntity.getMsg() + "提交失败");
                } else {
                    showSuccess();
                }
            }
        }, UserManager.getInstance().getUserBean().getId(), learningId);
    }

    private void showSuccess() {
        ToastUtil.s("提交成功");
        canUpLoad = false;
        viewDelegate.setLearningStatus("已完成", false);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                viewDelegate.learning_status.setEnabled(false);
                viewDelegate.learning_status.setClickable(false);
            }
        });
        EventBus.getDefault().post("learningFinished");
    }

    private void initWebView(String contentUrl) {
        webView = get(R.id.learning_webView);
        WebSettings webSettings = webView.getSettings();
        webSettings.setSupportZoom(false);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setDefaultTextEncodingName("utf-8");
        webSettings.setLoadsImagesAutomatically(true);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);

        webSettings.setDomStorageEnabled(true);

        webSettings.setAppCacheMaxSize(1024 * 1024 * 8);

        String appCachePath = getApplicationContext().getCacheDir().getAbsolutePath();

        webSettings.setAppCachePath(appCachePath);

        webSettings.setAllowFileAccess(true);

        webSettings.setAppCacheEnabled(true);

        webView.setHorizontalScrollBarEnabled(false);//水平不显示
        webView.setVerticalScrollBarEnabled(false); //垂直不显示
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            webView.setWebContentsDebuggingEnabled(true);
//        }
        webView.loadUrl(contentUrl);
        webView.setWebViewClient(new webViewClient());
    }


    //Web视图
    private class webViewClient extends WebViewClient {
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (webView != null) {
            webView.clearHistory();
            ((ViewGroup) webView.getParent()).removeView(webView);
            webView.destroy();
            webView = null;
        }
        if (task != null) {
            task.cancel();
        }
    }
}
