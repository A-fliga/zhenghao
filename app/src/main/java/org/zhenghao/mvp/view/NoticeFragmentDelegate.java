package org.zhenghao.mvp.view;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;

import org.zhenghao.R;
import org.zhenghao.application.UserManager;
import org.zhenghao.mvp.adapter.NoticeListAdapter;
import org.zhenghao.mvp.model.bean.NoticeListBean;
import org.zhenghao.mvp.presenter.activity.NoticeDetailActivity;
import org.zhenghao.mvp.presenter.fragment.NoticeFragment;

import java.util.ArrayList;
import java.util.List;

import static org.zhenghao.constants.Constants.IS_INIT;
import static org.zhenghao.constants.Constants.IS_LOAD_MORE;
import static org.zhenghao.constants.Constants.IS_REFRESH;

/**
 * Created by www on 2017/12/29.
 */

public class NoticeFragmentDelegate extends SwipeDelegate {
    public RecyclerView recyclerView;
    private List<NoticeListBean.ResultBean> list = new ArrayList<>();
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
        getTitleView().setText("我的消息");
        recyclerView = get(R.id.main_notice_recycler);
        if (!UserManager.getInstance().alreadyLogin()) {
            get(R.id.notice_login).setVisibility(View.VISIBLE);
        } else get(R.id.notice_login).setVisibility(View.GONE);
    }

    @Override
    public void onPullStarted() {
        super.pullStarted();
        page = 1;
        NoticeFragment.getFragment().initData(page, IS_REFRESH);
    }

    @Override
    public void onPullFinished() {
        super.pullFinished();
    }

    @Override
    public void onLoadStarted() {
        super.loadStarted();
        page++;
        NoticeFragment.getFragment().initData(page, IS_LOAD_MORE);
    }

    public void setPage(int page) {
        this.page = page;
    }

    @Override
    public void onLoadFinished() {
        super.loadFinished();
    }

    public void initList(final List<NoticeListBean.ResultBean> result, int type) {
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
        NoticeListAdapter adapter = new NoticeListAdapter(R.layout.item_notice_list, list);
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putInt("notice_id", result.get(position).getId());
                startMyActivity(NoticeDetailActivity.class, bundle);
            }
        });
        adapter.openLoadAnimation();
        setRecycler(recyclerView, adapter, true);
    }

    public void showRefresh(Boolean show) {
        if (show) {
            get(R.id.notice_no_data).setVisibility(View.VISIBLE);
            get(R.id.swipe_refresh).setVisibility(View.GONE);
        } else {
            get(R.id.swipe_refresh).setVisibility(View.VISIBLE);
            get(R.id.notice_no_data).setVisibility(View.GONE);
        }
    }
}
