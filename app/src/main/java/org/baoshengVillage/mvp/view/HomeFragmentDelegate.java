package org.baoshengVillage.mvp.view;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;

import org.baoshengVillage.ItemClickListener.RecyclerItemClickListener;
import org.baoshengVillage.R;
import org.baoshengVillage.mvp.adapter.AllInfoListAdapter;
import org.baoshengVillage.mvp.adapter.InfoBannerAdapter;
import org.baoshengVillage.mvp.model.bean.InfoListBean;
import org.baoshengVillage.mvp.presenter.activity.AllInfoDetailActivity;
import org.baoshengVillage.mvp.presenter.fragment.HomeFragment;
import org.baoshengVillage.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import static com.bigkoo.convenientbanner.ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL;
import static org.baoshengVillage.constants.Constants.FROM_INFO;
import static org.baoshengVillage.constants.Constants.FROM_WHERE;
import static org.baoshengVillage.constants.Constants.INFO_ID;

/**
 * Created by www on 2017/12/29.
 */

public class HomeFragmentDelegate extends SwipeDelegate {
    private ConvenientBanner convenientBanner;

    @Override
    public void onDestroy() {

    }

    @Override
    public int getRootLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initWidget() {
        getTitleView().setText("宝胜村");
        setToolBarLeftImg(R.mipmap.saoyisao);
    }


    public void showImgBanner(List<String> imgUrl) {
        convenientBanner = get(R.id.convenientBanner);
        convenientBanner.setScrollDuration(3000);
        if (imgUrl.size() == 1) {
            convenientBanner.setCanLoop(false);
        }
        if (imgUrl.size() > 1) {
            convenientBanner.setCanLoop(true);
            convenientBanner.setManualPageable(true);
        }
        convenientBanner.setPages(new CBViewHolderCreator<InfoBannerAdapter>() {
            @Override
            public InfoBannerAdapter createHolder() {
                return new InfoBannerAdapter();
            }
        }, imgUrl);
        //第二个为选中的点
        int[] point = new int[]{R.mipmap.banner_normol, R.mipmap.banner_selected};
        convenientBanner.setPageIndicator(point)
                .setPointViewVisible(true)
                .setPageIndicatorAlign(CENTER_HORIZONTAL)
                .startTurning(5000);
    }

    public ConvenientBanner getConvenientBanner() {
        return convenientBanner;
    }

    public void initInfoList(final List<InfoListBean.ListBean> beanList) {
        AllInfoListAdapter adapter = new AllInfoListAdapter(beanList, this.getActivity());
        setRecycler((RecyclerView) get(R.id.home_info_recycler), adapter, false);
        adapter.setOnItemClickListener(new RecyclerItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Bundle bundle = new Bundle();
                bundle.putInt(FROM_WHERE, FROM_INFO);
                bundle.putInt(INFO_ID, beanList.get(position).getId());
                startMyActivity(AllInfoDetailActivity.class, bundle);
            }
        });
    }

    @Override
    public void onPullStarted() {
        super.pullStarted();
        HomeFragment fragment = HomeFragment.getInstance();
        fragment.initHome();
    }

    @Override
    public void onPullFinished() {
        super.pullFinished();
    }

    @Override
    public void onLoadStarted() {
        super.loadStarted();
        onLoadFinished();

    }

    @Override
    public void onLoadFinished() {
        super.loadFinished();
    }
}
