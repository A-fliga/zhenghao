package org.zhenghao.mvp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhy.autolayout.utils.AutoUtils;

import org.zhenghao.ItemClickListener.CertificateItemClickListener;
import org.zhenghao.R;
import org.zhenghao.mvp.model.bean.CertificateBean;

import java.util.List;

/**
 * Created by www on 2018/1/11.
 */

public class CertificateItemAdapter extends RecyclerView.Adapter<CertificateItemAdapter.CertificateItemViewHolder> {

    private CertificateItemClickListener itemClickListener;
    private Context context;
    private List<CertificateBean.CertificateItemBean> beanList;

    public CertificateItemAdapter(Context context, List<CertificateBean.CertificateItemBean> beanList) {
        this.context = context;
        this.beanList = beanList;
    }

    @Override
    public CertificateItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CertificateItemViewHolder(LayoutInflater.from(context).inflate(R.layout.item_certificate_item, parent, false));
    }

    @Override
    public void onBindViewHolder(CertificateItemViewHolder holder, final int position) {
        final CertificateBean.CertificateItemBean bean = beanList.get(position);
        holder.certificate_item_img.setImageResource(bean.getImgId());
        holder.certificate_item_name.setText(bean.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemClickListener != null) {
                    itemClickListener.onItemClick(position, bean.getUrl(), bean.getName());
                }
            }
        });
    }

    public void setOnItemClickListener(CertificateItemClickListener onItemClickListener) {
        this.itemClickListener = onItemClickListener;
    }

    @Override
    public int getItemCount() {
        return beanList.size();
    }

    class CertificateItemViewHolder extends RecyclerView.ViewHolder {
        private ImageView certificate_item_img;
        private TextView certificate_item_name;

        public CertificateItemViewHolder(View itemView) {
            super(itemView);
            AutoUtils.autoSize(itemView);
            certificate_item_img = (ImageView) itemView.findViewById(R.id.certificate_item_img);
            certificate_item_name = (TextView) itemView.findViewById(R.id.certificate_item_name);
        }
    }
}
