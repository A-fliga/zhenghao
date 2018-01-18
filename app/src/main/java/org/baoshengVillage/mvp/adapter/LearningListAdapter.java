package org.baoshengVillage.mvp.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;

import org.baoshengVillage.R;
import org.baoshengVillage.mvp.model.bean.LearningListBean;
import org.baoshengVillage.mvp.viewholder.BaseViewHolder;
import org.baoshengVillage.utils.InitDateUtil;

import java.util.List;

/**
 * Created by www on 2018/1/18.
 */

public class LearningListAdapter extends BaseQuickAdapter<LearningListBean.LearnListBean, BaseViewHolder> {
    public LearningListAdapter(@LayoutRes int layoutResId, @Nullable List<LearningListBean.LearnListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, LearningListBean.LearnListBean item) {
        helper.setText(R.id.learning_list_title, item.getTitle())
                .setText(R.id.learning_list_time, InitDateUtil.getDate2(item.getPublishTime()) + "  " + InitDateUtil.getTime(item.getPublishTime()));
        if (item.getIsEnd() == 1) {
            helper.setVisible(R.id.learning_list_end, true);
        } else helper.setVisible(R.id.learning_list_end, false);
    }
}
