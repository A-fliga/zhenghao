package org.baoshengVillage.mvp.presenter.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.baoshengVillage.R;
import org.baoshengVillage.application.UserManager;
import org.baoshengVillage.mvp.model.PublicModel;
import org.baoshengVillage.mvp.model.bean.BannerBean;
import org.baoshengVillage.mvp.model.bean.BaseEntity;
import org.baoshengVillage.mvp.model.bean.InfoListBean;
import org.baoshengVillage.mvp.model.bean.SignBean;
import org.baoshengVillage.mvp.presenter.activity.AllInfoDetailActivity;
import org.baoshengVillage.mvp.presenter.activity.AllInfoListActivity;
import org.baoshengVillage.mvp.presenter.activity.CertificateActivity;
import org.baoshengVillage.mvp.presenter.activity.LearningRecordActivity;
import org.baoshengVillage.mvp.presenter.activity.LoginActivity;
import org.baoshengVillage.mvp.presenter.activity.PartyIntegrationActivity;
import org.baoshengVillage.mvp.presenter.activity.PartyLearningActivity;
import org.baoshengVillage.mvp.presenter.activity.ScanActivity;
import org.baoshengVillage.mvp.presenter.activity.SpiritActivity;
import org.baoshengVillage.mvp.presenter.activity.WebViewActivity;
import org.baoshengVillage.mvp.view.HomeFragmentDelegate;
import org.baoshengVillage.utils.ProgressDialogUtil;
import org.baoshengVillage.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;

import static org.baoshengVillage.constants.Constants.BANNER_ID;
import static org.baoshengVillage.constants.Constants.FROM_BANNER;
import static org.baoshengVillage.constants.Constants.FROM_WHERE;

/**
 * Created by www on 2017/12/29.
 */

public class HomeFragment extends FragmentPresenter<HomeFragmentDelegate> {
    private static HomeFragment fragment;

    @Override
    public Class<HomeFragmentDelegate> getDelegateClass() {
        return HomeFragmentDelegate.class;
    }

    @Override
    protected void onFragmentVisible() {

    }

    @Override
    protected void onFragmentHidden() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragment = this;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initHome();
    }

    public static HomeFragment getInstance() {
        return fragment;
    }

    public void initHome() {
        initView();
        //获取首页轮播图
        getBanner();
        getInfoList();
    }

    private void getBanner() {
        PublicModel.getInstance().getBanner(new Subscriber<BannerBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                ToastUtil.s("未知错误，请联系管理员");
                viewDelegate.onPullFinished();
            }

            @Override
            public void onNext(BannerBean bannerBean) {
                viewDelegate.onPullFinished();
                if (bannerBean.getCode() != 0) {
                    ToastUtil.s(bannerBean.getMsg());
                } else {
                    showBanner(bannerBean.getResult());
                }
            }
        });
    }

    private void showBanner(final List<BannerBean.ResultBean> result) {
        List<String> bannerUrl = new ArrayList<>();
        for (int i = 0; i < result.size(); i++) {
            bannerUrl.add(result.get(i).getThumbnailUrl());
        }
        viewDelegate.showImgBanner(bannerUrl);
        viewDelegate.getConvenientBanner().setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Bundle bundle = new Bundle();
                bundle.putInt(FROM_WHERE, FROM_BANNER);
                bundle.putInt(BANNER_ID, result.get(position).getId());
                startMyActivity(AllInfoDetailActivity.class, bundle);
            }
        });
    }

    private void getInfoList() {
        PublicModel.getInstance().getInfoList(new Subscriber<BaseEntity<InfoListBean>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                viewDelegate.onPullFinished();
            }

            @Override
            public void onNext(BaseEntity<InfoListBean> infoListBeanBaseEntity) {
                viewDelegate.onPullFinished();
                if (infoListBeanBaseEntity.getCode() != 0) {
                    ToastUtil.s(infoListBeanBaseEntity.getMsg());
                } else {
                    viewDelegate.initInfoList(infoListBeanBaseEntity.getResult().getList());
                }
            }
        }, 28, 1);
    }


    private void initView() {
        viewDelegate.setOnClickListener(mOnclickListener, R.id.toolBar_img_left_rl, R.id.spirit_ll, R.id.learn_ll, R.id.guide_ll,
                R.id.dangjian_ll, R.id.home_info_more_tv);

    }

    private void startScanner() {
        IntentIntegrator integrator = IntentIntegrator.forSupportFragment(this);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
        integrator.setCaptureActivity(ScanActivity.class);
        integrator.setPrompt("请正对二维码进行扫描"); //底部的提示文字，设为""可以置空
        integrator.setCameraId(0); //前置或者后置摄像头
        integrator.setBeepEnabled(false); //扫描成功的「哔哔」声，默认开启
        integrator.initiateScan();
    }


    private View.OnClickListener mOnclickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                //十九大精神
                case R.id.spirit_ll:
                    toSpirit();
                    break;
                //党员学习
                case R.id.learn_ll:
                    toLearning();
                    break;
                //办证指南
                case R.id.guide_ll:
                    toGuide();
                    break;
                //智慧党建
                case R.id.dangjian_ll:
                    toParty();
                    break;
                //点击更多
                case R.id.home_info_more_tv:
                    toMoreInfo();
                    break;

                case R.id.toolBar_img_left_rl:
                    //开始扫描签到
                    toSign();
                    break;
            }
        }
    };

    private void toMoreInfo() {
        Bundle bundle = new Bundle();
        bundle.putInt("typeId", 28);
        startMyActivity(AllInfoListActivity.class, bundle);
    }

    private void toParty() {
        Bundle bundle = new Bundle();
        bundle.putInt("WebViewType", 1);
        startMyActivity(WebViewActivity.class, bundle);
    }

    private void toGuide() {
        startMyActivity(CertificateActivity.class, null);
    }

    private void toLearning() {
        if (!UserManager.getInstance().alreadyLogin()) {
            startMyActivity(LoginActivity.class, null);
        } else if (!UserManager.getInstance().isPartyMember()) {
            ToastUtil.s("请先认证党员");
        } else {
            startMyActivity(PartyLearningActivity.class, null);
        }

    }

    private void toSpirit() {
        startMyActivity(SpiritActivity.class, null);
    }

    private void toSign() {
        if (!UserManager.getInstance().alreadyLogin()) {
            startMyActivity(LoginActivity.class, null);
        } else if (!UserManager.getInstance().isPartyMember()) {
            ToastUtil.s("请先认证党员");
        } else {
            //去签到
            startScanner();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() != null) {
                toSign(result.getContents());
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void toSign(String code) {
        PublicModel.getInstance().toSign(new Subscriber<BaseEntity<SignBean>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                ToastUtil.s("未知错误，请联系管理员");
            }

            @Override
            public void onNext(BaseEntity<SignBean> signBeanBaseEntity) {
                if (signBeanBaseEntity.getCode() != 0) {
                    ToastUtil.l(signBeanBaseEntity.getMsg());
                } else {
                    ProgressDialogUtil.instance().startLoadWithView(signBeanBaseEntity.getResult().getTitle(), signBeanBaseEntity.getResult().getIntegral());
                }
            }
        }, String.valueOf(UserManager.getInstance().getUserBean().getPartyMemberInformation().getId()), code);
    }
}
