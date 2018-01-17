package org.baoshengVillage.mvp.presenter.activity;

import android.os.Bundle;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import org.baoshengVillage.R;
import org.baoshengVillage.mvp.model.PublicModel;
import org.baoshengVillage.mvp.model.bean.BaseEntity;
import org.baoshengVillage.mvp.model.bean.NoticeDetailBean;
import org.baoshengVillage.mvp.view.NoticeDetailDelegate;
import org.baoshengVillage.utils.ToastUtil;

import rx.Subscriber;

/**
 * Created by www on 2018/1/2.
 */

public class NoticeDetailActivity extends ActivityPresenter<NoticeDetailDelegate> {
    private int noticeId = 0;
    private WebView webView;

    @Override
    public Class<NoticeDetailDelegate> getDelegateClass() {
        return NoticeDetailDelegate.class;
    }

    @Override
    public boolean isSetDisplayHomeAsUpEnabled() {
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getNoticeId();
        getNoticeDetail();
    }

    private void getNoticeDetail() {
        PublicModel.getInstance().getNoticeDetail(new Subscriber<BaseEntity<NoticeDetailBean>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                ToastUtil.s("未知错误，请联系管理员");
            }

            @Override
            public void onNext(BaseEntity<NoticeDetailBean> noticeDetailBeanBaseEntity) {
                if (noticeDetailBeanBaseEntity.getCode() != 0) {
                    ToastUtil.s(noticeDetailBeanBaseEntity.getMsg());
                } else {
                    showContent(noticeDetailBeanBaseEntity.getResult());
                }
            }
        }, noticeId);
    }

    private void showContent(NoticeDetailBean detailBean) {
        viewDelegate.initView(detailBean);
        initWebView(detailBean.getContentUrl());
    }

    private void getNoticeId() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            noticeId = bundle.getInt("notice_id");
        }
    }

    private void initWebView(String contentUrl) {
        webView = get(R.id.notice_detail_webView);
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
    }
}
