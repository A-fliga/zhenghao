package org.baoshengVillage.mvp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhy.autolayout.utils.AutoUtils;

import org.baoshengVillage.ItemClickListener.NoticeItemClickListener;
import org.baoshengVillage.R;
import org.baoshengVillage.mvp.model.bean.LearningListBean;
import org.baoshengVillage.utils.InitDateUtil;

import java.util.List;

/**
 * Created by www on 2018/1/10.
 */

public class LearningListAdapter extends RecyclerView.Adapter<LearningListAdapter.LearningViewHolder> {
    private Context context;
    private List<LearningListBean.LearnListBean> beanList;
    private NoticeItemClickListener itemClickListener;

    public LearningListAdapter(Context context, List<LearningListBean.LearnListBean> beanList) {
        this.context = context;
        this.beanList = beanList;
    }

    @Override
    public LearningViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new LearningViewHolder(LayoutInflater.from(context).inflate(R.layout.item_learning_list, parent, false));
    }

    @Override
    public void onBindViewHolder(LearningViewHolder holder, final int position) {
        final LearningListBean.LearnListBean bean = beanList.get(position);
        holder.learning_list_title.setText(bean.getTitle());
        holder.learning_list_time.setText(InitDateUtil.getDate2(bean.getPublishTime()) + "  " + InitDateUtil.getTime(bean.getPublishTime()));
        if (bean.getIsEnd() == 1) {
            holder.learning_list_end.setVisibility(View.VISIBLE);
        } else holder.learning_list_end.setVisibility(View.GONE);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
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

    class LearningViewHolder extends RecyclerView.ViewHolder {
        private TextView learning_list_title, learning_list_time, learning_list_end;

        public LearningViewHolder(View itemView) {
            super(itemView);
            AutoUtils.autoSize(itemView);
            learning_list_title = (TextView) itemView.findViewById(R.id.learning_list_title);
            learning_list_time = (TextView) itemView.findViewById(R.id.learning_list_time);
            learning_list_end = (TextView) itemView.findViewById(R.id.learning_list_end);
        }
    }
}
