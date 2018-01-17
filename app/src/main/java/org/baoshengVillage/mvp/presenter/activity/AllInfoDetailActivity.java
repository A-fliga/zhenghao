package org.baoshengVillage.mvp.presenter.activity;

import android.os.Build;
import android.os.Bundle;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import org.baoshengVillage.R;
import org.baoshengVillage.mvp.model.PublicModel;
import org.baoshengVillage.mvp.model.bean.BannerAndInfoDetailBean;
import org.baoshengVillage.mvp.model.bean.BaseEntity;
import org.baoshengVillage.mvp.view.AllInfoDetailDelegate;
import org.baoshengVillage.utils.ToastUtil;

import rx.Subscriber;

import static org.baoshengVillage.constants.Constants.BANNER_ID;
import static org.baoshengVillage.constants.Constants.FROM_BANNER;
import static org.baoshengVillage.constants.Constants.FROM_INFO;
import static org.baoshengVillage.constants.Constants.FROM_SPIRIT;
import static org.baoshengVillage.constants.Constants.FROM_WHERE;
import static org.baoshengVillage.constants.Constants.INFO_ID;

/**
 * Created by www on 2018/1/9.
 */

public class AllInfoDetailActivity extends ActivityPresenter<AllInfoDetailDelegate> {
    private WebView webView;

    @Override
    public Class<AllInfoDetailDelegate> getDelegateClass() {
        return AllInfoDetailDelegate.class;
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
            switch (bundle.getInt(FROM_WHERE)) {
                //轮播图跳转
                case FROM_BANNER:
                    getBannerDetail(getIntent().getExtras().getInt(BANNER_ID));
                    break;
                //普通资讯跳转
                case FROM_INFO:
                    getInfoDetail(getIntent().getExtras().getInt(INFO_ID));
                    break;
            }
        }
    }

    private void getInfoDetail(int id) {
        PublicModel.getInstance().showInfoDetail(new Subscriber<BaseEntity<BannerAndInfoDetailBean>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                ToastUtil.s("未知错误，请联系管理员");
            }

            @Override
            public void onNext(BaseEntity<BannerAndInfoDetailBean> bannerAndInfoDetailBeanBaseEntity) {
                if (bannerAndInfoDetailBeanBaseEntity.getCode() != 0) {
                    ToastUtil.s(bannerAndInfoDetailBeanBaseEntity.getMsg());
                } else {
                    showInfoDetail(bannerAndInfoDetailBeanBaseEntity.getResult());
                }
            }
        }, id);
    }

    private void showInfoDetail(BannerAndInfoDetailBean result) {
        viewDelegate.initView(result);
        initWebView(result.getContent());
    }

    private void getBannerDetail(int bannerId) {
        PublicModel.getInstance().showBannerDetail(new Subscriber<BaseEntity<BannerAndInfoDetailBean>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                ToastUtil.s("未知错误，请联系管理员");
            }

            @Override
            public void onNext(BaseEntity<BannerAndInfoDetailBean> bannerAndInfoDetailBeanBaseEntity) {
                if (bannerAndInfoDetailBeanBaseEntity.getCode() != 0) {
                    ToastUtil.s(bannerAndInfoDetailBeanBaseEntity.getMsg());
                } else {
                    showBannerDetail(bannerAndInfoDetailBeanBaseEntity.getResult());
                }
            }
        }, bannerId);
    }

    private void showBannerDetail(BannerAndInfoDetailBean result) {
        viewDelegate.initView(result);
        initWebView(result.getContentUrl());
    }

    private void initWebView(String contentUrl) {
        webView = get(R.id.info_detail_webView);
        WebSettings webSettings = webView.getSettings();
        webView.setHorizontalScrollBarEnabled(false);//水平不显示
        webView.setVerticalScrollBarEnabled(false); //垂直不显示

        webSettings.setSupportZoom(true);
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
