package org.baoshengVillage.mvp.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhy.autolayout.utils.AutoUtils;

import org.baoshengVillage.ItemClickListener.NoticeItemClickListener;
import org.baoshengVillage.ItemClickListener.RecyclerItemClickListener;
import org.baoshengVillage.R;
import org.baoshengVillage.mvp.model.bean.InfoListBean;
import org.baoshengVillage.utils.InitDateUtil;
import org.baoshengVillage.utils.LoadImgUtil;

import java.util.List;

/**
 * Created by www on 2018/1/2.
 */

public class AllInfoListAdapter extends RecyclerView.Adapter<AllInfoListAdapter.MainInfoViewHolder> {
    private List<InfoListBean.ListBean> beanList;
    private Activity context;
    private RecyclerItemClickListener itemClickListener;
    private NoticeItemClickListener noticeItemClickListener;

    public AllInfoListAdapter(List<InfoListBean.ListBean> beanList, Activity context) {
        this.beanList = beanList;
        this.context = context;
    }

    @Override
    public MainInfoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MainInfoViewHolder(LayoutInflater.from(context).inflate(R.layout.item_all_info, parent, false));
    }

    @Override
    public void onBindViewHolder(MainInfoViewHolder holder, final int position) {
        final InfoListBean.ListBean bean = beanList.get(position);
        if (position == beanList.size() - 1) {
            holder.item_main_line.setVisibility(View.GONE);
        }
        LoadImgUtil.loadRoundImage(context, bean.getThumbnailUrl(), holder.item_main_img, R.mipmap.info_default);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemClickListener != null)
                    itemClickListener.onItemClick(position);
                if (noticeItemClickListener != null) {
                    noticeItemClickListener.onItemClick(position, bean.getId());
                }
            }
        });
        holder.item_main_title.setText(bean.getTitle());
        holder.item_main_time.setText(InitDateUtil.getDate2(bean.getCreateDate()) + " " + InitDateUtil.getTime(bean.getCreateDate()));
        holder.item_main_content.setText(bean.getText());
    }

    @Override
    public int getItemCount() {
        return beanList.size();
    }


    public void setOnItemClickListener(RecyclerItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public void setOnItemClickListener(NoticeItemClickListener itemClickListener) {
        this.noticeItemClickListener = itemClickListener;
    }

    class MainInfoViewHolder extends RecyclerView.ViewHolder {
        private View item_main_line;
        private ImageView item_main_img;
        private TextView item_main_title, item_main_content, item_main_time;

        public MainInfoViewHolder(View itemView) {
            super(itemView);
            AutoUtils.autoSize(itemView);
            item_main_img = (ImageView) itemView.findViewById(R.id.item_main_img);
            item_main_title = (TextView) itemView.findViewById(R.id.item_main_title);
            item_main_content = (TextView) itemView.findViewById(R.id.item_main_content);
            item_main_time = (TextView) itemView.findViewById(R.id.item_main_time);
            item_main_line = itemView.findViewById(R.id.item_main_line);
        }
    }
}
