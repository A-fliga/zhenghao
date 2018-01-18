package org.baoshengVillage.mvp.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;

import org.baoshengVillage.R;
import org.baoshengVillage.mvp.model.bean.InfoListBean;
import org.baoshengVillage.mvp.presenter.activity.ActivityPresenter;
import org.baoshengVillage.mvp.viewholder.BaseViewHolder;
import org.baoshengVillage.utils.InitDateUtil;
import org.baoshengVillage.utils.LoadImgUtil;
import org.baoshengVillage.utils.LogUtil;
import org.baoshengVillage.utils.ToastUtil;

import java.util.List;

/**
 * Created by www on 2018/1/2.
 * 所有的资讯列表类adapter
 */

public class AllInfoListAdapter extends BaseQuickAdapter<InfoListBean.ListBean, BaseViewHolder> {
    public AllInfoListAdapter(@LayoutRes int layoutResId, @Nullable List<InfoListBean.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, InfoListBean.ListBean item) {
        if (helper.getAdapterPosition() == getItemCount() - 1) {
            helper.setVisible(R.id.item_main_line, false);
        }
        LoadImgUtil.loadRoundImage(ActivityPresenter.getTopActivity(), item.getThumbnailUrl(), (ImageView) helper.getView(R.id.item_main_img), R.mipmap.info_default);
        helper.setText(R.id.item_main_title, item.getTitle())
                .setText(R.id.item_main_time, InitDateUtil.getDate2(item.getCreateDate()) + " " + InitDateUtil.getTime(item.getCreateDate()))
                .setText(R.id.item_main_content, item.getText());

    }
}
