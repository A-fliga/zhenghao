package org.baoshengVillage.mvp.presenter.activity;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.baoshengVillage.R;
import org.baoshengVillage.mvp.view.SettingUrlDelegate;

import static org.baoshengVillage.constants.Constants.PARTY_URL;

/**
 * Created by www on 2018/1/15.
 */

public class SettingUrlActivity extends ActivityPresenter<SettingUrlDelegate> {
    private EditText et;

    @Override
    public Class<SettingUrlDelegate> getDelegateClass() {
        return SettingUrlDelegate.class;
    }

    @Override
    public boolean isSetDisplayHomeAsUpEnabled() {
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        et = get(R.id.setting_et);
        Button btn = get(R.id.setting_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PARTY_URL = "http://" + et.getText().toString().replaceAll(" ", "");
            }
        });
    }
}
