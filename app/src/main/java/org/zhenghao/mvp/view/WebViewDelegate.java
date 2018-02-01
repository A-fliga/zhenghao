package org.zhenghao.mvp.view;

import org.zhenghao.R;

/**
 * Created by www on 2018/1/9.
 */

public class WebViewDelegate extends ViewDelegate {
    @Override
    public void onDestroy() {

    }

    @Override
    public int getRootLayoutId() {
        return R.layout.activity_webview;
    }

    @Override
    public void initWidget() {
    }

    public void setTitle(String title){
        getTitleView().setText(title);
    }
}
