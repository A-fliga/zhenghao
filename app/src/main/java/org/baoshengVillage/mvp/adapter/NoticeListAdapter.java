package org.baoshengVillage.mvp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.baoshengVillage.ItemClickListener.NoticeItemClickListener;
import org.baoshengVillage.R;
import org.baoshengVillage.mvp.model.bean.NoticeListBean;
import org.baoshengVillage.utils.InitDateUtil;

import java.util.List;

/**
 * Created by www on 2018/1/9.
 */

public class NoticeListAdapter extends RecyclerView.Adapter<NoticeListAdapter.NoticeListViewHolder> {
    private NoticeItemClickListener itemClickListener;
    private Context context;
    private List<NoticeListBean.ResultBean> beanList;

    public NoticeListAdapter(Context context, List<NoticeListBean.ResultBean> beanList) {
        this.context = context;
        this.beanList = beanList;
    }

    @Override
    public NoticeListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NoticeListViewHolder(LayoutInflater.from(context).inflate(R.layout.item_notice_list, parent, false));
    }

    @Override
    public void onBindViewHolder(NoticeListViewHolder holder, final int position) {
        final NoticeListBean.ResultBean bean = beanList.get(position);
        if (bean.getInformFor() == 1) {
            holder.notice_isPartyMember.setVisibility(View.VISIBLE);
        } else holder.notice_isPartyMember.setVisibility(View.GONE);

        holder.notice_title.setText(bean.getTitle());
        holder.notice_synopsis.setText(bean.getSynopsis());
        holder.notice_time.setText(InitDateUtil.getDate2(bean.getPublishTime()) + " " + InitDateUtil.getTime(bean.getPublishTime()));
        holder.notice_show_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemClickListener != null) {
                    itemClickListener.onItemClick(position, bean.getId());
                }
            }
        });
    }

    public void setOnItemClickListener(NoticeItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public int getItemCount() {
        return beanList.size();
    }

    class NoticeListViewHolder extends RecyclerView.ViewHolder {
        private TextView notice_title, notice_synopsis, notice_isPartyMember, notice_time, notice_show_detail;

        public NoticeListViewHolder(View itemView) {
            super(itemView);
            notice_title = (TextView) itemView.findViewById(R.id.notice_title);
            notice_synopsis = (TextView) itemView.findViewById(R.id.notice_synopsis);
            notice_isPartyMember = (TextView) itemView.findViewById(R.id.notice_isPartyMember);
            notice_time = (TextView) itemView.findViewById(R.id.notice_time);
            notice_show_detail = (TextView) itemView.findViewById(R.id.notice_show_detail);
        }
    }
}
