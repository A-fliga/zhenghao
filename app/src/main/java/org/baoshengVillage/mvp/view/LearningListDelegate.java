package org.baoshengVillage.mvp.view;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import org.baoshengVillage.ItemClickListener.NoticeItemClickListener;
import org.baoshengVillage.R;
import org.baoshengVillage.mvp.adapter.LearningListAdapter;
import org.baoshengVillage.mvp.model.bean.LearningListBean;
import org.baoshengVillage.mvp.presenter.activity.LearningDetailActivity;

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
        LearningListAdapter adapter = new LearningListAdapter(this.getActivity(), beanList);
        adapter.setOnItemClickListener(new NoticeItemClickListener() {
            @Override
            public void onItemClick(int position, int id) {
                Bundle bundle = new Bundle();
                bundle.putInt("LearningListId", id);
                startMyActivity(LearningDetailActivity.class, bundle);
            }
        });
        setRecycler((RecyclerView) get(R.id.learning_list_recycler), adapter, true);
    }

}
