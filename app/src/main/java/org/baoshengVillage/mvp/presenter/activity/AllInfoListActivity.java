package org.baoshengVillage.mvp.presenter.activity;

import android.os.Bundle;

import org.baoshengVillage.mvp.model.PublicModel;
import org.baoshengVillage.mvp.model.bean.BaseEntity;
import org.baoshengVillage.mvp.model.bean.InfoListBean;
import org.baoshengVillage.mvp.view.AllInfoListDelegate;
import org.baoshengVillage.utils.ProgressDialogUtil;
import org.baoshengVillage.utils.ToastUtil;

import rx.Subscriber;

import static org.baoshengVillage.constants.Constants.IS_INIT;
import static org.baoshengVillage.constants.Constants.IS_LOAD_MORE;
import static org.baoshengVillage.constants.Constants.IS_REFRESH;

/**
 * Created by www on 2018/1/10.
 */

public class AllInfoListActivity extends ActivityPresenter<AllInfoListDelegate> {
    private int TYPE = 0;

    @Override
    public Class<AllInfoListDelegate> getDelegateClass() {
        return AllInfoListDelegate.class;
    }

    @Override
    public boolean isSetDisplayHomeAsUpEnabled() {
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            TYPE = bundle.getInt("typeId");
            viewDelegate.initTitle(TYPE);
            getInfoList(1, IS_INIT);
        }
    }

    public void getInfoList(final int pageIndex, final int action) {
        ProgressDialogUtil.instance().startLoad();
        PublicModel.getInstance().getInfoList(new Subscriber<BaseEntity<InfoListBean>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                ToastUtil.s("未知错误，请联系管理员");
                ProgressDialogUtil.instance().stopLoad();
                afterLoadOrRefresh(action);
            }

            @Override
            public void onNext(BaseEntity<InfoListBean> infoListBean) {
                ProgressDialogUtil.instance().stopLoad();
                if (infoListBean.getCode() != 0) {
                    ToastUtil.s(infoListBean.getMsg());
                } else {
                    viewDelegate.initList(infoListBean.getResult().getList(), action);
                    if ((infoListBean.getResult() == null || infoListBean.getResult().getList() == null || infoListBean.getResult().getList().size() == 0)
                            && action == IS_LOAD_MORE) {
                        ToastUtil.s("没有更多了");
                        int page = pageIndex - 1;
                        viewDelegate.setPage(page);
                        viewDelegate.recyclerView.scrollToPosition((page - 1) * 10);

                    } else if (action == IS_LOAD_MORE) {
                        viewDelegate.recyclerView.scrollToPosition((pageIndex - 1) * 10);
                    } else if (action == IS_INIT && infoListBean.getResult().getList().size() == 0) {
                        ToastUtil.s("暂无数据");
                    }
                }
                afterLoadOrRefresh(action);
            }
        }, TYPE, pageIndex);
    }

    private void afterLoadOrRefresh(int type) {
        if (type == IS_REFRESH) {
            viewDelegate.onPullFinished();
        }
        if (type == IS_LOAD_MORE) {
            viewDelegate.onLoadFinished();
        }
    }
}
