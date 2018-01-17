package org.baoshengVillage.mvp.view;

import android.view.View;
import android.widget.TextView;

import org.baoshengVillage.R;
import org.baoshengVillage.application.UserManager;
import org.baoshengVillage.mvp.model.bean.NoticeDetailBean;
import org.baoshengVillage.utils.InitDateUtil;

/**
 * Created by www on 2018/1/2.
 */

public class NoticeDetailDelegate extends ViewDelegate {
    private TextView notice_detail_title, notice_detail_isParty, notice_detail_time;

    @Override
    public void onDestroy() {

    }

    @Override
    public int getRootLayoutId() {
        return R.layout.activity_notice_detail;
    }

    @Override
    public void initWidget() {
        notice_detail_title = get(R.id.notice_detail_title);
        notice_detail_isParty = get(R.id.notice_detail_isParty);
        notice_detail_time = get(R.id.notice_detail_time);
    }

    public void initView(NoticeDetailBean detailBean) {
        notice_detail_title.setText(detailBean.getTitle());
        if (detailBean.getInformFor() == 1) {
            notice_detail_isParty.setVisibility(View.VISIBLE);
        } else notice_detail_isParty.setVisibility(View.GONE);
        notice_detail_time.setText(InitDateUtil.getDate2(detailBean.getCreateTime()) + " " + InitDateUtil.getTime(detailBean.getCreateTime()));
    }
}
