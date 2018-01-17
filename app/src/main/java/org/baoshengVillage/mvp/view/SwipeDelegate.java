package org.baoshengVillage.mvp.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.baoshengVillage.R;
import org.baoshengVillage.utils.NetUtil;
import org.baoshengVillage.widget.SuperSwipeRefreshLayout;


/**
 * Created by orchid on 2017/4/24.
 */
public abstract class SwipeDelegate extends ViewDelegate {

    protected boolean swipe = true;
    private SuperSwipeRefreshLayout swipeRefreshLayout;
    // Header View
    private TextView headerTextView;
    private ImageView headerImageView;
    private ProgressBar headerProgressBar;

    // Footer View
    private TextView footerTextView;
    private ImageView footerImageView;
    private ProgressBar footerProgressBar;

    @Override
    public void create(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.create(inflater, container, savedInstanceState);
        if (swipe) {
            initSwipe();
        }
    }

    private void initSwipe() {
        swipeRefreshLayout = get(R.id.swipe_refresh);
//        swipeRefreshLayout.setHeaderViewBackgroundColor(0xff888888);
        swipeRefreshLayout.setHeaderView(createHeaderView());// add headerView
        swipeRefreshLayout.setFooterView(createFooterView());
//        swipeRefreshLayout.setTargetScrollWithLayout(true);
        swipeRefreshLayout.setOnPullRefreshListener(new SuperSwipeRefreshLayout.OnPullRefreshListener() {
            @Override
            public void onRefresh() {
                onPullStarted();
            }

            @Override
            public void onPullDistance(int distance) {
//                LogUtil.e("debug:distance = " + distance);
            }

            @Override
            public void onPullEnable(boolean enable) {
                headerTextView.setText(enable ? "松开刷新" : "下拉刷新");
                headerImageView.setVisibility(View.VISIBLE);
                headerImageView.setRotation(enable ? 180 : 0);
            }
        });

        swipeRefreshLayout.setOnPushLoadMoreListener(new SuperSwipeRefreshLayout.OnPushLoadMoreListener() {
            @Override
            public void onLoadMore() {
                onLoadStarted();
            }

            @Override
            public void onPushDistance(int distance) {
            }

            @Override
            public void onPushEnable(boolean enable) {
                footerTextView.setText(enable ? "松开加载" : "上拉加载");
                footerImageView.setVisibility(View.VISIBLE);
                footerImageView.setRotation(enable ? 0 : 180);
            }
        });
    }

    private View createHeaderView() {
        View headerView = LayoutInflater.from(swipeRefreshLayout.getContext())
                .inflate(R.layout.swipe_header, null);
        headerTextView = (TextView) headerView.findViewById(R.id.swipe_header_text);
        headerTextView = (TextView) headerView.findViewById(R.id.swipe_header_text);
        headerImageView = (ImageView) headerView.findViewById(R.id.swipe_header_image);
        headerProgressBar = (ProgressBar) headerView.findViewById(R.id.swipe_header_pb);
        headerTextView.setText("下拉刷新");
        headerImageView.setImageResource(R.mipmap.down_arrow);
        headerImageView.setVisibility(View.VISIBLE);
        headerProgressBar.setVisibility(View.GONE);
        return headerView;
    }

    private View createFooterView() {
        View footerView = LayoutInflater.from(swipeRefreshLayout.getContext())
                .inflate(R.layout.swipe_footer, null);
        footerTextView = (TextView) footerView.findViewById(R.id.swipe_footer_text);
        footerImageView = (ImageView) footerView.findViewById(R.id.swipe_footer_image);
        footerProgressBar = (ProgressBar) footerView.findViewById(R.id.swipe_footer_pb);
        footerTextView.setText("上拉加载");
        footerImageView.setImageResource(R.mipmap.down_arrow);
        footerImageView.setVisibility(View.VISIBLE);
        footerProgressBar.setVisibility(View.GONE);
        return footerView;
    }

    public abstract void onPullStarted();

    public abstract void onPullFinished();

    public abstract void onLoadStarted();

    public abstract void onLoadFinished();

    protected void pullStarted() {
        headerTextView.setText("正在刷新...");
        headerImageView.setVisibility(View.GONE);
        headerProgressBar.setVisibility(View.VISIBLE);
        if (!NetUtil.isConnect()) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    onPullFinished();
                }
            }, 2000);
        }
    }

    protected void pullFinished() {
        headerImageView.setVisibility(View.VISIBLE);
        headerProgressBar.setVisibility(View.GONE);
        swipeRefreshLayout.setRefreshing(false);
    }

    protected void loadStarted() {
        footerTextView.setText("正在加载...");
        footerImageView.setVisibility(View.GONE);
        footerProgressBar.setVisibility(View.VISIBLE);
        if (NetUtil.isConnect()) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    onLoadFinished();
                }
            }, 2000);
        }
    }

    protected void loadFinished() {
        footerImageView.setVisibility(View.VISIBLE);
        footerProgressBar.setVisibility(View.GONE);
        swipeRefreshLayout.setLoadMore(false);
    }

}