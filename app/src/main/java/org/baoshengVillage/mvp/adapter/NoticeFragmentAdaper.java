package org.baoshengVillage.mvp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhy.autolayout.utils.AutoUtils;

import org.baoshengVillage.ItemClickListener.RecyclerItemClickListener;
import org.baoshengVillage.R;

/**
 * Created by www on 2018/1/2.
 */

public class NoticeFragmentAdaper extends RecyclerView.Adapter<NoticeFragmentAdaper.NoticeViewHolder> {
    private RecyclerItemClickListener itemClickListener;
    private Context context;

    public NoticeFragmentAdaper(Context context) {
        this.context = context;
    }

    @Override
    public NoticeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NoticeViewHolder(LayoutInflater.from(context).inflate(R.layout.item_notice_recycler, parent, false));
    }

    @Override
    public void onBindViewHolder(NoticeViewHolder holder, final int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemClickListener != null)
                    itemClickListener.onItemClick(position);
            }
        });


    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public void setItemClickListener(RecyclerItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    class NoticeViewHolder extends RecyclerView.ViewHolder {
        private TextView item_notice_title, item_notice_content, item_notice_time;

        public NoticeViewHolder(View itemView) {
            super(itemView);
            AutoUtils.autoSize(itemView);
            item_notice_title = (TextView) itemView.findViewById(R.id.item_notice_title);
            item_notice_content = (TextView) itemView.findViewById(R.id.item_notice_content);
            item_notice_time = (TextView) itemView.findViewById(R.id.item_notice_time);
        }

    }
}
