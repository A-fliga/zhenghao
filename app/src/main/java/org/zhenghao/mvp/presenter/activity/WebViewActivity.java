package org.zhenghao.mvp.presenter.activity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import org.zhenghao.R;
import org.zhenghao.mvp.model.PublicModel;
import org.zhenghao.mvp.model.bean.AboutBaoShengBean;
import org.zhenghao.mvp.model.bean.BaseEntity;
import org.zhenghao.mvp.view.WebViewDelegate;
import org.zhenghao.utils.ProgressDialogUtil;
import org.zhenghao.utils.ToastUtil;

import rx.Subscriber;

import static org.zhenghao.constants.Constants.PARTY_URL;

/**
 * Created by www on 2018/1/9.
 * 所有的webView页面
 */

public class WebViewActivity extends ActivityPresenter<WebViewDelegate> {
    private WebView webView;
    private static final int ABOUT = 0, PARTY = 1;
    private int WHAT = -1;

    @Override
    public Class<WebViewDelegate> getDelegateClass() {
        return WebViewDelegate.class;
    }

    @Override
    public boolean isSetDisplayHomeAsUpEnabled() {
        return WHAT != PARTY;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            WHAT = bundle.getInt("WebViewType");
            super.onCreate(savedInstanceState);
            ProgressDialogUtil.instance().startLoad();
            switch (WHAT) {
                case ABOUT:
                    viewDelegate.setTitle("关于宝胜村");
                    showAboutBaoSheng();
                    break;
                case PARTY:
                    initPartyView();
                    initWebView(PARTY_URL);
                    break;
            }
        }

    }

    private void initPartyView() {
        viewDelegate.setTitle("宝胜智慧党建");
        viewDelegate.setTitleColor(R.color.color_ffffff);
        viewDelegate.setToolBarColor(R.color.color_b93837);
        viewDelegate.setToolBarLeftImg(R.mipmap.return_arrow);
        viewDelegate.getToolBarLeftImg().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    private void showAboutBaoSheng() {
        PublicModel.getInstance().aboutBaoSheng(new Subscriber<BaseEntity<AboutBaoShengBean>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                ToastUtil.s("未知错误，请联系管理员");
            }

            @Override
            public void onNext(BaseEntity<AboutBaoShengBean> aboutBaoShengBeanBaseEntity) {
                if (aboutBaoShengBeanBaseEntity.getCode() != 0) {
                    ToastUtil.s(aboutBaoShengBeanBaseEntity.getMsg());
                } else {
                    initWebView(aboutBaoShengBeanBaseEntity.getResult().getContentUrl());
                }
            }
        });
    }

    private void initWebView(String contentUrl) {
        webView = get(R.id.webView);
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
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            webView.setWebContentsDebuggingEnabled(true);
        }
        webView.loadUrl(contentUrl);
        webView.setWebViewClient(new webViewClient());
    }


    //Web视图
    private class webViewClient extends WebViewClient {
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            ProgressDialogUtil.instance().stopLoad();
        }

        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            super.onReceivedError(view, request, error);
            ProgressDialogUtil.instance().stopLoad();
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
