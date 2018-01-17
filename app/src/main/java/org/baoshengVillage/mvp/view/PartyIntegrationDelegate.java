package org.baoshengVillage.mvp.view;

import android.support.v7.widget.RecyclerView;
import android.widget.RatingBar;
import android.widget.TextView;

import org.baoshengVillage.R;
import org.baoshengVillage.mvp.adapter.PartyIntegrationAdapter;
import org.baoshengVillage.mvp.model.bean.PartyIntegrationBean;

/**
 * Created by www on 2018/1/8.
 */

public class PartyIntegrationDelegate extends ViewDelegate {
    private RatingBar ratingBar;
    private TextView party_int_name, party_int_score;

    @Override
    public void onDestroy() {

    }

    @Override
    public int getRootLayoutId() {
        return R.layout.activity_party_integration;
    }

    @Override
    public void initWidget() {
        getTitleView().setText("党员积分");
        initView();
    }

    private void initView() {
        ratingBar = get(R.id.party_int_star);
        party_int_name = get(R.id.party_int_name);
        party_int_score = get(R.id.party_int_score);
    }

    public void initViews(PartyIntegrationBean bean) {
        if (bean != null) {
//            ratingBar.setRating();
            party_int_name.setText(bean.getDyName());
            party_int_score.setText(bean.getGrades());
            PartyIntegrationAdapter adapter = new PartyIntegrationAdapter(this.getActivity(), bean.getHyqd(), bean.getFpjl());
            setRecycler((RecyclerView) get(R.id.party_int_recycler), adapter, true);
        }
    }
}
