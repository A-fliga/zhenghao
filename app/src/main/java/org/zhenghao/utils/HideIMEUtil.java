package org.zhenghao.utils;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;

import org.zhenghao.mvp.view.CustomerView.AutoHideIMEFrameLayout;


/**
 * Created by www on 2017/6/12.
 * EditText失去焦点后自动隐藏软键盘的工具类
 */
public class HideIMEUtil {

    public static void wrap(Activity activity) {
        ViewGroup contentParent = (ViewGroup) activity.findViewById(android.R.id.content);
        wrap(contentParent);
    }

    public static void wrap(Fragment fragment) {
        ViewGroup contentParent = (ViewGroup) fragment.getView().getParent();
        wrap(contentParent);
    }

    public static void wrap(ViewGroup contentParent) {
        View content = contentParent.getChildAt(0);
        contentParent.removeView(content);
        ViewGroup.LayoutParams p = content.getLayoutParams();
        AutoHideIMEFrameLayout layout = new AutoHideIMEFrameLayout(content.getContext());
        contentParent.addView(layout, new ViewGroup.LayoutParams(p.width, p.height));
        layout.addView(content);
    }
}