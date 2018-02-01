package org.zhenghao.mvp.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;

import org.zhenghao.R;
import org.zhenghao.mvp.model.bean.LearningRecordBean;
import org.zhenghao.mvp.viewholder.BaseViewHolder;
import org.zhenghao.utils.ContextUtil;
import org.zhenghao.utils.InitDateUtil;

import java.util.List;

/**
 * Created by www on 2018/1/18.
 * 党员积分adapter
 */

public class LearningRecordAdapter extends BaseQuickAdapter<LearningRecordBean.ResultBean, BaseViewHolder> {
    public LearningRecordAdapter(@LayoutRes int layoutResId, @Nullable List<LearningRecordBean.ResultBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, LearningRecordBean.ResultBean item) {
        helper.setText(R.id.learn_item_name, item.getTitle());
        helper.setText(R.id.learn_item_time, InitDateUtil.getDate2(item.getCreateTime()) + "  " +
                InitDateUtil.getTime(item.getCreateTime()));
        if (item.getIsEnd() == 0) {
            helper.setTextColor(R.id.learn_item_status, ContextUtil.getContext().getResources().getColor(R.color.color_ef0e0e))
                    .setText(R.id.learn_item_status, "未完成");
        }
        if (item.getIsEnd() == 1) {
            helper.setTextColor(R.id.learn_item_status, ContextUtil.getContext().getResources().getColor(R.color.color_03b318))
                    .setText(R.id.learn_item_status, "已完成");
        }
    }
}
