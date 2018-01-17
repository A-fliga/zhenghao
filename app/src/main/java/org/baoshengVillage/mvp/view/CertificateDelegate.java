package org.baoshengVillage.mvp.view;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import org.baoshengVillage.ItemClickListener.CertificateItemClickListener;
import org.baoshengVillage.R;
import org.baoshengVillage.mvp.adapter.CertificateAdapter;
import org.baoshengVillage.mvp.model.bean.CertificateBean;
import org.baoshengVillage.mvp.presenter.activity.CertificateDetailActivity;

import java.util.List;

/**
 * Created by www on 2018/1/11.
 */

public class CertificateDelegate extends ViewDelegate {

    @Override
    public void onDestroy() {

    }

    @Override
    public int getRootLayoutId() {
        return R.layout.activity_certificate;
    }

    @Override
    public void initWidget() {
        getTitleView().setText("办证指南");
    }

    public void initRecycler(List<CertificateBean> bean) {
        CertificateAdapter adapter = new CertificateAdapter(this.getActivity(), bean);
        setRecycler((RecyclerView) get(R.id.certificate_recycler), adapter, true);
        adapter.setOnItemClickListener(new CertificateItemClickListener() {
            @Override
            public void onItemClick(int position, String url, String title) {
                Bundle bundle = new Bundle();
                bundle.putString("certificateUrl", url);
                bundle.putString("certificateTitle", title);
                startMyActivity(CertificateDetailActivity.class, bundle);
            }
        });
    }
}
