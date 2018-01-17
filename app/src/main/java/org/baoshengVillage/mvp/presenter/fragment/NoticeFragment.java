package org.baoshengVillage.mvp.presenter.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import org.baoshengVillage.R;
import org.baoshengVillage.application.UserManager;
import org.baoshengVillage.mvp.model.PublicModel;
import org.baoshengVillage.mvp.model.bean.NoticeListBean;
import org.baoshengVillage.mvp.presenter.activity.LoginActivity;
import org.baoshengVillage.mvp.view.NoticeFragmentDelegate;
import org.baoshengVillage.utils.ProgressDialogUtil;
import org.baoshengVillage.utils.ToastUtil;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


import rx.Subscriber;

import static org.baoshengVillage.constants.Constants.IS_INIT;
import static org.baoshengVillage.constants.Constants.IS_LOAD_MORE;
import static org.baoshengVillage.constants.Constants.IS_REFRESH;

/**
 * Created by www on 2017/12/29.
 */

public class NoticeFragment extends FragmentPresenter<NoticeFragmentDelegate> {
    private static NoticeFragment fragment;

    @Override
    public Class<NoticeFragmentDelegate> getDelegateClass() {
        return NoticeFragmentDelegate.class;
    }

    @Override
    protected void onFragmentVisible() {

    }

    public static NoticeFragment getFragment() {
        if (fragment != null)
            return fragment;
        else return new NoticeFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);//订阅
        fragment = this;
    }

    @Override
    protected void onFragmentHidden() {

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initClickEvent();
        initData(1, IS_INIT);
    }

    public void initData(int page, int type) {
        if (UserManager.getInstance().alreadyLogin()) {
            Boolean isPartyMember = UserManager.getInstance().isPartyMember();
            if (isPartyMember) {
                getNoticeList(1, page, type);
            } else getNoticeList(2, page, type);
        } else {
            ProgressDialogUtil.instance().stopLoad();
            viewDelegate.showRefresh(false);
            viewDelegate.get(R.id.swipe_refresh).setVisibility(View.GONE);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);//订阅
    }

    private void getNoticeList(int informFor, final int pageIndex, final int type) {
        PublicModel.getInstance().getNoticeList(new Subscriber<NoticeListBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                ProgressDialogUtil.instance().stopLoad();
                ToastUtil.s("未知错误，请联系管理员");
                afterLoadOrRefresh(type);
            }

            @Override
            public void onNext(NoticeListBean noticeListBean) {
                ProgressDialogUtil.instance().stopLoad();
                if (noticeListBean.getCode() != 0) {
                    ToastUtil.s(noticeListBean.getMsg());
                } else {
                    viewDelegate.initList(noticeListBean.getResult(), type);
                    if ((noticeListBean.getResult() == null || noticeListBean.getResult().size() == 0)) {
                        if (type == IS_LOAD_MORE) {
                            ToastUtil.s("没有更多了");
                            int page = pageIndex - 1;
                            viewDelegate.setPage(page);
                            viewDelegate.recyclerView.scrollToPosition((page - 1) * 10);
                        } else {
                            viewDelegate.showRefresh(true);
                        }

                    } else if (type == IS_LOAD_MORE) {
                        viewDelegate.recyclerView.scrollToPosition((pageIndex - 1) * 10);
                    } else {
                        viewDelegate.showRefresh(false);
                    }
                }
                afterLoadOrRefresh(type);
            }
        }, informFor, pageIndex);
    }

    private void afterLoadOrRefresh(int type) {
        if (type == IS_REFRESH) {
            viewDelegate.onPullFinished();
        }
        if (type == IS_LOAD_MORE) {
            viewDelegate.onLoadFinished();
        }
    }


    private void initClickEvent() {
        viewDelegate.setOnClickListener(onClickListener, R.id.toLogin_img, R.id.refresh_img);
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.toLogin_img:
                    if (!UserManager.getInstance().alreadyLogin()) {
                        startMyActivity(LoginActivity.class, null);
                    }
                    break;
                case R.id.refresh_img:
                    ProgressDialogUtil.instance().startLoad();
                    refreshView();
                    break;
            }
        }
    };

    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
    public void loginChange(Boolean isChange) {
        if (isChange) {
            refreshView();
        }
    }

    private void refreshView() {
        viewDelegate.initWidget();
        initClickEvent();
        initData(1, IS_REFRESH);
        viewDelegate.setPage(1);
    }

    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
    public void receiveNotice(String refresh) {
        if (refresh.equals("refresh")) {
            refreshView();
        }
    }
}
