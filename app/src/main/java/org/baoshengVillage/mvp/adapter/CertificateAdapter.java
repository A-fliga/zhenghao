package org.baoshengVillage.mvp.adapter;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhy.autolayout.utils.AutoUtils;

import org.baoshengVillage.ItemClickListener.CertificateItemClickListener;
import org.baoshengVillage.R;
import org.baoshengVillage.mvp.model.bean.CertificateBean;

import java.util.List;

/**
 * Created by www on 2018/1/11.
 */

public class CertificateAdapter extends RecyclerView.Adapter<CertificateAdapter.CertificateViewHolder> {
    private CertificateItemClickListener itemClickListener;
    private Context context;
    private List<CertificateBean> beanList;

    public CertificateAdapter(Context context, List<CertificateBean> beanList) {
        this.context = context;
        this.beanList = beanList;
    }

    @Override
    public CertificateViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CertificateViewHolder(LayoutInflater.from(context).inflate(R.layout.item_certificate, parent, false));
    }

    @Override
    public void onBindViewHolder(CertificateViewHolder holder, int position) {
        CertificateBean bean = beanList.get(position);
        holder.certificate_name.setText(bean.getTitle());
        CertificateItemAdapter adapter = new CertificateItemAdapter(context, bean.getBeanList());
        setRecycler(holder.certificate_item_recycler, adapter, 4, false);
        adapter.setOnItemClickListener(new CertificateItemClickListener() {
            @Override
            public void onItemClick(int position, String url, String title) {
                if (itemClickListener != null) {
                    itemClickListener.onItemClick(position, url, title);
                }
            }
        });
    }


    public void setOnItemClickListener(CertificateItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public int getItemCount() {
        return beanList.size();
    }

    public void setRecycler(final RecyclerView recycler, final RecyclerView.Adapter adapter, final int count, final boolean scroll) {
        GridLayoutManager gridLayoutManager;
        if (scroll) {
            gridLayoutManager = new GridLayoutManager(context, count);
        } else {
            gridLayoutManager = new GridLayoutManager(context, count) {
                @Override
                public boolean canScrollVertically() {
                    return false;
                }
            };
        }
        gridLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        recycler.setLayoutManager(gridLayoutManager);
        recycler.setHasFixedSize(true);
        recycler.setItemAnimator(new DefaultItemAnimator());
        recycler.setAdapter(adapter);
    }

    class CertificateViewHolder extends RecyclerView.ViewHolder {
        private TextView certificate_name;
        private RecyclerView certificate_item_recycler;

        public CertificateViewHolder(View itemView) {
            super(itemView);
            AutoUtils.autoSize(itemView);
            certificate_name = (TextView) itemView.findViewById(R.id.certificate_name);
            certificate_item_recycler = (RecyclerView) itemView.findViewById(R.id.certificate_item_recycler);
        }
    }
}
