package org.baoshengVillage.mvp.view;

import android.view.View;

import org.baoshengVillage.R;

/**
 * Created by www on 2018/1/8.
 */

public class IdentifyPartyDelegate extends ViewDelegate {
    @Override
    public void onDestroy() {

    }

    @Override
    public int getRootLayoutId() {
        return R.layout.activity_identify_party;
    }

    @Override
    public void initWidget() {
        getTitleView().setText("党员认证");
    }
}
