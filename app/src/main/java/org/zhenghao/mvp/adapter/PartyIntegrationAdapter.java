package org.zhenghao.mvp.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;

import org.zhenghao.R;
import org.zhenghao.mvp.model.bean.PartyIntegrationBean;
import org.zhenghao.mvp.viewholder.BaseViewHolder;
import org.zhenghao.utils.InitDateUtil;

import java.util.List;

/**
 * Created by www on 2018/1/18.
 */

public class PartyIntegrationAdapter<T> extends BaseQuickAdapter<T, BaseViewHolder> {
    public PartyIntegrationAdapter(@LayoutRes int layoutResId, @Nullable List<T> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, T item) {
        if (item instanceof PartyIntegrationBean.HyqdBean) {
            PartyIntegrationBean.HyqdBean bean = (PartyIntegrationBean.HyqdBean) item;
            helper.setText(R.id.int_item_name, "会议签到")
                    .setText(R.id.int_item_time, InitDateUtil.getDate2(bean.getCreateDate()) + "  " +
                            InitDateUtil.getTime(bean.getCreateDate()))
                    .setText(R.id.int_item_score, "积分+" + bean.getIntegral());
        }
        if (item instanceof PartyIntegrationBean.FpjlBean) {
            PartyIntegrationBean.FpjlBean bean = (PartyIntegrationBean.FpjlBean) item;
            helper.setText(R.id.int_item_name, "扶贫互助")
                    .setText(R.id.int_item_time, InitDateUtil.getDate2(bean.getCreateDate()) + "  " +
                            InitDateUtil.getTime(bean.getCreateDate()))
                    .setText(R.id.int_item_score, "积分+" + bean.getIntegral());
        }
    }
}
