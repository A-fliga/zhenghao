package org.zhenghao.mvp.view;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;

import org.zhenghao.R;
import org.zhenghao.mvp.adapter.AllInfoListAdapter;
import org.zhenghao.mvp.model.bean.InfoListBean;
import org.zhenghao.mvp.presenter.activity.AllInfoDetailActivity;
import org.zhenghao.mvp.presenter.activity.AllInfoListActivity;

import java.util.ArrayList;
import java.util.List;

import static org.zhenghao.constants.Constants.FROM_INFO;
import static org.zhenghao.constants.Constants.FROM_WHERE;
import static org.zhenghao.constants.Constants.INFO_ID;
import static org.zhenghao.constants.Constants.IS_INIT;
import static org.zhenghao.constants.Constants.IS_LOAD_MORE;
import static org.zhenghao.constants.Constants.IS_REFRESH;

/**
 * Created by www on 2018/1/10.
 */

public class AllInfoListDelegate extends SwipeDelegate {
    public RecyclerView recyclerView;
    private List<InfoListBean.ListBean> list = new ArrayList<>();
    private int page = 1;

    @Override
    public void onDestroy() {

    }

    @Override
    public int getRootLayoutId() {
        return R.layout.fragment_notice;
    }

    @Override
    public void initWidget() {
        get(R.id.recycler_line).setVisibility(View.VISIBLE);
        recyclerView = get(R.id.main_notice_recycler);

    }

    @Override
    public void onPullStarted() {
        super.pullStarted();
        page = 1;
        AllInfoListActivity activity = getActivity();
        activity.getInfoList(page, IS_REFRESH);
    }

    @Override
    public void onPullFinished() {
        super.pullFinished();
    }

    @Override
    public void onLoadStarted() {
        super.loadStarted();
        page++;
        AllInfoListActivity activity = getActivity();
        activity.getInfoList(page, IS_LOAD_MORE);
    }

    @Override
    public void onLoadFinished() {
        super.loadFinished();
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void initList(List<InfoListBean.ListBean> result, int type) {
        get(R.id.swipe_refresh).setVisibility(View.VISIBLE);
        if (type == IS_INIT) {
            list.clear();
            list.addAll(result);
        }
        if (type == IS_REFRESH) {
            list.clear();
            list.addAll(result);
        }
        if (type == IS_LOAD_MORE) {
            list.addAll(result);
        }

        AllInfoListAdapter adapter = new AllInfoListAdapter(R.layout.item_all_info, list);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putInt(INFO_ID, list.get(position).getId());
                bundle.putInt(FROM_WHERE, FROM_INFO);
                startMyActivity(AllInfoDetailActivity.class, bundle);
            }
        });
        adapter.openLoadAnimation();
        setRecycler(recyclerView, adapter, true);
    }

    public void initTitle(int type) {
        switch (type) {
            case 28:
                getTitleView().setText("资讯动态");
                break;
            case 4:
                getTitleView().setText("十九大概述");
                break;
            case 40:
                getTitleView().setText("十九大详解");
                break;
            case 41:
                getTitleView().setText("专家解读");
                break;
            case 43:
                getTitleView().setText("全程播报");
                break;
        }
    }
}
