package org.baoshengVillage.mvp.view;

import android.widget.TextView;


import org.baoshengVillage.R;
import org.baoshengVillage.mvp.model.bean.BannerAndInfoDetailBean;
import org.baoshengVillage.utils.InitDateUtil;

/**
 * Created by www on 2018/1/9.
 */

public class AllInfoDetailDelegate extends ViewDelegate {
    private TextView info_detail_title, info_detail_time;

    @Override
    public void onDestroy() {

    }

    @Override
    public int getRootLayoutId() {
        return R.layout.activity_all_info_detail;
    }

    @Override
    public void initWidget() {
        info_detail_title = get(R.id.info_detail_title);
        info_detail_time = get(R.id.info_detail_time);
    }

    public void initView(BannerAndInfoDetailBean bean) {
        info_detail_title.setText(bean.getTitle());
        info_detail_time.setText(InitDateUtil.getDate2(bean.getCreateDate()) + " " + InitDateUtil.getTime(bean.getCreateDate()));
    }
}
