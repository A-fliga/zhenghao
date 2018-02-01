package org.zhenghao.mvp.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;

import org.zhenghao.R;
import org.zhenghao.mvp.model.bean.PartyLearningBean;
import org.zhenghao.mvp.viewholder.BaseViewHolder;

import java.util.List;

/**
 * Created by www on 2018/1/19.
 * 党员学习的类型adapter
 */

public class LearningTypeAdapter extends BaseQuickAdapter<PartyLearningBean.ResultBean, BaseViewHolder> {
    private int[] imgList;

    public LearningTypeAdapter(@LayoutRes int layoutResId, @Nullable List<PartyLearningBean.ResultBean> data, int[] imgList) {
        super(layoutResId, data);
        this.imgList = imgList;
    }


    @Override
    protected void convert(BaseViewHolder helper, PartyLearningBean.ResultBean item) {
        int imgIndex = helper.getAdapterPosition() % imgList.length;
        helper.setImageResource(R.id.learning_list_img, imgList[imgIndex])
                .setText(R.id.learning_list_name, item.getTypeName())
                .setText(R.id.learning_list_count, String.valueOf(item.getTypeCount()));
    }
}
