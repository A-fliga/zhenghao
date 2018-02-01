package org.zhenghao.mvp.view;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;

import org.zhenghao.R;
import org.zhenghao.mvp.adapter.LearningTypeAdapter;
import org.zhenghao.mvp.model.bean.PartyLearningBean;
import org.zhenghao.mvp.presenter.activity.LearningListActivity;

import java.util.List;

/**
 * Created by www on 2018/1/10.
 */

public class PartyLearningDelegate extends ViewDelegate {
    @Override
    public void onDestroy() {

    }

    @Override
    public int getRootLayoutId() {
        return R.layout.activity_party_learning;
    }

    @Override
    public void initWidget() {
        getTitleView().setText("党员学习");
    }

    public void initList(final List<PartyLearningBean.ResultBean> beanList) {
        int[] imgList = new int[]{R.mipmap.learningorange1, R.mipmap.learningorange2,
                R.mipmap.learningorange4, R.mipmap.learningyellow, R.mipmap.learninggreen1,
                R.mipmap.learninggreen2, R.mipmap.learninggreen3, R.mipmap.learningblue};
        LearningTypeAdapter adapter = new LearningTypeAdapter(R.layout.item_learning_type, beanList, imgList);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putInt("LearningTypeId", beanList.get(position).getTypeId());
                bundle.putString("LearningTypeName", beanList.get(position).getTypeName());
                startMyActivity(LearningListActivity.class, bundle);
            }
        });
        adapter.openLoadAnimation();
        setRecycler((RecyclerView) get(R.id.party_learning_recycler), adapter, true);
    }


}
