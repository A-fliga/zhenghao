package org.baoshengVillage.mvp.view;

import org.baoshengVillage.R;

/**
 * Created by www on 2018/1/3.
 */

public class LoginDelegate extends ViewDelegate {
    @Override
    public void onDestroy() {

    }

    @Override
    public int getRootLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initWidget() {
        getTitleView().setText("登录");
    }
}
