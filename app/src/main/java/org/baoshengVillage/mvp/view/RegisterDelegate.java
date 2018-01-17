package org.baoshengVillage.mvp.view;

import android.widget.EditText;
import android.widget.TextView;

import org.baoshengVillage.R;

/**
 * Created by www on 2018/1/3.
 */

public class RegisterDelegate extends ViewDelegate {
    @Override
    public void onDestroy() {

    }

    @Override
    public int getRootLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    public void initWidget() {
    }

    public void setTextColor(int id, int color) {
        TextView tv = get(id);
        tv.setTextColor(color);
    }

    public void setText(int id, String content) {
        TextView tv = get(id);
        tv.setText(content);

    }
}
