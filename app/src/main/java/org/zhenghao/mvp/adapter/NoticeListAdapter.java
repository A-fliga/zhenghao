package org.zhenghao.mvp.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;

import org.zhenghao.R;
import org.zhenghao.mvp.model.bean.NoticeListBean;
import org.zhenghao.mvp.viewholder.BaseViewHolder;
import org.zhenghao.utils.InitDateUtil;

import java.util.List;

/**
 * Created by www on 2018/1/9.
 * 通知列表页面
 */

public class NoticeListAdapter extends BaseQuickAdapter<NoticeListBean.ResultBean, BaseViewHolder> {

    public NoticeListAdapter(@LayoutRes int layoutResId, @Nullable List<NoticeListBean.ResultBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, NoticeListBean.ResultBean item) {
        if (item.getInformFor() == 1) {
            helper.setVisible(R.id.notice_isPartyMember, true);
        } else helper.setVisible(R.id.notice_isPartyMember, false);
        helper.setText(R.id.notice_title, item.getTitle())
                .setText(R.id.notice_synopsis, item.getSynopsis())
                .setText(R.id.notice_time, InitDateUtil.getDate2(item.getPublishTime()) + " " + InitDateUtil.getTime(item.getPublishTime()));
        helper.addOnClickListener(R.id.notice_show_detail);
    }
}
