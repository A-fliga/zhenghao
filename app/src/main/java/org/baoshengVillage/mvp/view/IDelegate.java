package org.baoshengVillage.mvp.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * View delegate base class
 * 视图层代理的接口协议
 *
 * @author kymjs (http://www.kymjs.com/) on 10/23/15.
 */
public interface IDelegate {

    void create(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    void onDestroy();

    int getOptionsMenuId();

    View getRootView();

    void initWidget();

    TextView getTitleView();

}