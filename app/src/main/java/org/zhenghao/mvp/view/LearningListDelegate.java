package org.zhenghao.mvp.view;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;

import org.zhenghao.R;
import org.zhenghao.mvp.adapter.LearningListAdapter;
import org.zhenghao.mvp.model.bean.LearningListBean;
import org.zhenghao.mvp.presenter.activity.LearningDetailActivity;

import java.util.List;

/**
 * Created by www on 2018/1/10.
 */

public class LearningListDelegate extends ViewDelegate {

    @Override
    public void onDestroy() {

    }

    @Override
    public int getRootLayoutId() {
        return R.layout.activity_learning_list;
    }

    @Override
    public void initWidget() {
    }

    public void setTitle(String title) {
        getTitleView().setText(title);
    }

    public void initTop(LearningListBean bean) {
        TextView learning_list_allCount = get(R.id.learning_list_allCount);
        learning_list_allCount.setText(String.valueOf(bean.getCount()));
        TextView learning_list_endCount = get(R.id.learning_list_endCount);
        learning_list_endCount.setText(String.valueOf(bean.getLearnEndCount()));
    }

    public void initList(final List<LearningListBean.LearnListBean> beanList) {
        LearningListAdapter adapter = new LearningListAdapter(R.layout.item_learning_list, beanList);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putInt("LearningListId", beanList.get(position).getId());
                startMyActivity(LearningDetailActivity.class, bundle);
            }
        });
        adapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        setRecycler((RecyclerView) get(R.id.learning_list_recycler), adapter, true);
    }

}
