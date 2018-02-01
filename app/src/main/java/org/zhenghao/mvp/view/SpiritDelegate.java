package org.zhenghao.mvp.view;

import org.zhenghao.R;

/**
 * Created by www on 2018/1/10.
 */

public class SpiritDelegate extends ViewDelegate {
    @Override
    public void onDestroy() {

    }

    @Override
    public int getRootLayoutId() {
        return R.layout.activity_spirit;
    }

    @Override
    public void initWidget() {
        getTitleView().setText("十九大精神");
    }
}
