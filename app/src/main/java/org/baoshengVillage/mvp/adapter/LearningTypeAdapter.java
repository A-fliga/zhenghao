package org.baoshengVillage.mvp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhy.autolayout.utils.AutoUtils;

import org.baoshengVillage.ItemClickListener.NoticeItemClickListener;
import org.baoshengVillage.R;
import org.baoshengVillage.mvp.model.bean.PartyLearningBean;

import java.util.List;

/**
 * Created by www on 2018/1/10.
 * 党员学习的类型adapter
 */

public class LearningTypeAdapter extends RecyclerView.Adapter<LearningTypeAdapter.LearningListViewHolder> {
    private NoticeItemClickListener itemClickListener;
    private Context context;
    private List<PartyLearningBean.ResultBean> beanList;
    private int[] imgList;

    public LearningTypeAdapter(Context context, List<PartyLearningBean.ResultBean> beanList, int[] imgList) {
        this.context = context;
        this.beanList = beanList;
        this.imgList = imgList;
    }

    @Override
    public LearningListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new LearningListViewHolder(LayoutInflater.from(context).inflate(R.layout.item_learning_type, parent, false));
    }

    @Override
    public void onBindViewHolder(LearningListViewHolder holder, final int position) {
        final PartyLearningBean.ResultBean bean = beanList.get(position);
        int imgIndex = position % imgList.length;
        holder.learning_list_img.setImageResource(imgList[imgIndex]);
        holder.learning_list_name.setText(bean.getTypeName());
        holder.learning_list_count.setText(String.valueOf(bean.getTypeCount()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemClickListener != null) {
                    itemClickListener.onItemClick(position, bean.getTypeId());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return beanList.size();
    }

    public void setOnItemClickListener(NoticeItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    class LearningListViewHolder extends RecyclerView.ViewHolder {
        private ImageView learning_list_img;
        private TextView learning_list_name, learning_list_count;

        public LearningListViewHolder(View itemView) {
            super(itemView);
            AutoUtils.autoSize(itemView);
            learning_list_img = (ImageView) itemView.findViewById(R.id.learning_list_img);
            learning_list_name = (TextView) itemView.findViewById(R.id.learning_list_name);
            learning_list_count = (TextView) itemView.findViewById(R.id.learning_list_count);
        }
    }
}
