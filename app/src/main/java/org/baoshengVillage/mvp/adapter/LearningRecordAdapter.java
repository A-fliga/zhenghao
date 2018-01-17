package org.baoshengVillage.mvp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhy.autolayout.utils.AutoUtils;

import org.baoshengVillage.R;
import org.baoshengVillage.mvp.model.bean.LearningRecordBean;
import org.baoshengVillage.utils.InitDateUtil;

import java.util.List;

/**
 * Created by www on 2018/1/8.
 */

public class LearningRecordAdapter extends RecyclerView.Adapter<LearningRecordAdapter.RecordViewHolder> {

    private List<LearningRecordBean.ResultBean> beanList;
    private Context context;

    public LearningRecordAdapter(Context context, List<LearningRecordBean.ResultBean> beanList) {
        this.beanList = beanList;
        this.context = context;
    }

    @Override
    public RecordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecordViewHolder(LayoutInflater.from(context).inflate(R.layout.item_learning_record, parent, false));
    }

    @Override
    public void onBindViewHolder(RecordViewHolder holder, int position) {
        LearningRecordBean.ResultBean resultBean = beanList.get(position);
        holder.learn_item_time.setText(InitDateUtil.getDate2(resultBean.getCreateTime()) + "  " +
                InitDateUtil.getTime(resultBean.getCreateTime()));

        holder.learn_item_name.setText(resultBean.getTitle());
        if (resultBean.getIsEnd() == 0) {
            holder.learn_item_status.setTextColor(context.getResources().getColor(R.color.color_ef0e0e));
            holder.learn_item_status.setText("未完成");
        }
        if (resultBean.getIsEnd() == 1) {
            holder.learn_item_status.setText("已完成");
            holder.learn_item_status.setTextColor(context.getResources().getColor(R.color.color_03b318));
        }
    }

    @Override
    public int getItemCount() {
        return beanList.size();
    }

    class RecordViewHolder extends RecyclerView.ViewHolder {
        private TextView learn_item_name, learn_item_time, learn_item_status;

        public RecordViewHolder(View itemView) {
            super(itemView);
            AutoUtils.autoSize(itemView);
            learn_item_name = (TextView) itemView.findViewById(R.id.learn_item_name);
            learn_item_time = (TextView) itemView.findViewById(R.id.learn_item_time);
            learn_item_status = (TextView) itemView.findViewById(R.id.learn_item_status);
        }
    }
}
