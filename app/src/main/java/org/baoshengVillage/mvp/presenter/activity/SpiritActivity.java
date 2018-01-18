package org.baoshengVillage.mvp.presenter.activity;

import android.os.Bundle;
import android.view.View;

import org.baoshengVillage.R;
import org.baoshengVillage.mvp.view.SpiritDelegate;


/**
 * Created by www on 2018/1/10.
 * 十九大页面
 */

public class SpiritActivity extends ActivityPresenter<SpiritDelegate> {
    @Override
    public Class<SpiritDelegate> getDelegateClass() {
        return SpiritDelegate.class;
    }

    @Override
    public boolean isSetDisplayHomeAsUpEnabled() {
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewDelegate.setOnClickListener(onClickListener, R.id.gaishu_img, R.id.spirit_img, R.id.expert_img, R.id.bobao_img);
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                //十九大概述
                case R.id.gaishu_img:
                    onNext(4);
                    break;
                //十九大详解
                case R.id.spirit_img:
                    onNext(40);
                    break;
                //专家解读
                case R.id.expert_img:
                    onNext(41);
                    break;
                //全程播报
                case R.id.bobao_img:
                    onNext(43);
                    break;
            }
        }
    };

    private void onNext(int type) {
        Bundle bundle = new Bundle();
        bundle.putInt("typeId", type);
        startMyActivity(AllInfoListActivity.class, bundle);
    }
}
