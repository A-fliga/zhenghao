package org.baoshengVillage.mvp.view;

import android.support.v7.widget.RecyclerView;

import org.baoshengVillage.R;
import org.baoshengVillage.mvp.adapter.LearningRecordAdapter;
import org.baoshengVillage.mvp.model.bean.LearningRecordBean;
import org.baoshengVillage.utils.ToastUtil;

import java.util.List;

/**
 * Created by www on 2018/1/8.
 */

public class LearningRecordDelegate extends ViewDelegate {
    @Override
    public void onDestroy() {

    }

    @Override
    public int getRootLayoutId() {
        return R.layout.activity_learning_record;
    }

    @Override
    public void initWidget() {
        getTitleView().setText("学习记录");
    }

    public void initView(List<LearningRecordBean.ResultBean> beanList) {
        if (beanList.size() == 0) {
            ToastUtil.l("暂无记录");
        } else {
            LearningRecordAdapter adapter = new LearningRecordAdapter(getActivity(), beanList);
            setRecycler((RecyclerView) get(R.id.learning_recycler), adapter, true);
        }
    }
}
