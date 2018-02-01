package org.zhenghao.mvp.view;

import org.zhenghao.R;

/**
 * Created by www on 2018/1/8.
 */

public class SettingsDelegate extends ViewDelegate {
    @Override
    public void onDestroy() {

    }

    @Override
    public int getRootLayoutId() {
        return R.layout.activity_settings;
    }

    @Override
    public void initWidget() {
        getTitleView().setText("设置");
    }
}
