package org.baoshengVillage.mvp.view;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.baoshengVillage.ItemClickListener.NoticeItemClickListener;
import org.baoshengVillage.ItemClickListener.RecyclerItemClickListener;
import org.baoshengVillage.R;
import org.baoshengVillage.mvp.adapter.AllInfoListAdapter;
import org.baoshengVillage.mvp.adapter.NoticeListAdapter;
import org.baoshengVillage.mvp.model.bean.InfoListBean;
import org.baoshengVillage.mvp.model.bean.NoticeListBean;
import org.baoshengVillage.mvp.presenter.activity.AllInfoDetailActivity;
import org.baoshengVillage.mvp.presenter.activity.AllInfoListActivity;
import org.baoshengVillage.mvp.presenter.activity.NoticeDetailActivity;
import org.baoshengVillage.mvp.presenter.fragment.NoticeFragment;

import java.util.ArrayList;
import java.util.List;

import static org.baoshengVillage.constants.Constants.FROM_INFO;
import static org.baoshengVillage.constants.Constants.FROM_WHERE;
import static org.baoshengVillage.constants.Constants.INFO_ID;
import static org.baoshengVillage.constants.Constants.IS_INIT;
import static org.baoshengVillage.constants.Constants.IS_LOAD_MORE;
import static org.baoshengVillage.constants.Constants.IS_REFRESH;

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

        AllInfoListAdapter adapter = new AllInfoListAdapter(list, this.getActivity());
        adapter.setOnItemClickListener(new NoticeItemClickListener() {
            @Override
            public void onItemClick(int position, int id) {
                Bundle bundle = new Bundle();
                bundle.putInt(INFO_ID, id);
                bundle.putInt(FROM_WHERE,FROM_INFO);
                startMyActivity(AllInfoDetailActivity.class, bundle);
            }
        });
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
