package org.zhenghao.mvp.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhy.autolayout.utils.AutoUtils;

import org.zhenghao.ItemClickListener.ItemClickListener;
import org.zhenghao.R;
import org.zhenghao.mvp.model.bean.InfoListBean;
import org.zhenghao.utils.InitDateUtil;
import org.zhenghao.utils.LoadImgUtil;

import java.util.List;

/**
 * Created by www on 2018/2/6.
 */

public class HomeListAdapter extends RecyclerView.Adapter<HomeListAdapter.MainInfoViewHolder> {
    private List<InfoListBean.ListBean> beanList;
    private Activity context;
    private ItemClickListener itemClickListener;

    public HomeListAdapter(List<InfoListBean.ListBean> beanList, Activity context) {
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
                if (itemClickListener != null) {
                    itemClickListener.onItemClick(position);
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


    public void setOnItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public class MainInfoViewHolder extends RecyclerView.ViewHolder {
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