package org.baoshengVillage.mvp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhy.autolayout.utils.AutoUtils;

import org.baoshengVillage.R;
import org.baoshengVillage.mvp.model.bean.PartyIntegrationBean;
import org.baoshengVillage.utils.InitDateUtil;

import java.util.List;

/**
 * Created by www on 2018/1/8.
 */

public class PartyIntegrationAdapter extends RecyclerView.Adapter<PartyIntegrationAdapter.PartyIntViewHolder> {
    private Context context;
    private List<PartyIntegrationBean.HyqdBean> hyqdBeanList;
    private List<PartyIntegrationBean.FpjlBean> fpjlBeanList;

    public PartyIntegrationAdapter(Context context, List<PartyIntegrationBean.HyqdBean> hyqdBeanList, List<PartyIntegrationBean.FpjlBean> fpjlBeanList) {
        this.context = context;
        this.hyqdBeanList = hyqdBeanList;
        this.fpjlBeanList = fpjlBeanList;
    }

    @Override
    public PartyIntViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PartyIntViewHolder(LayoutInflater.from(context).inflate(R.layout.item_party_integration, parent, false));
    }

    @Override
    public void onBindViewHolder(PartyIntViewHolder holder, int position) {
        if (hyqdBeanList.size() != 0 && position <= hyqdBeanList.size() - 1) {
            holder.int_item_name.setText("会议签到");
            holder.int_item_time.setText(InitDateUtil.getDate2(hyqdBeanList.get(position).getCreateDate()) + "  " +
                    InitDateUtil.getTime(hyqdBeanList.get(position).getCreateDate()));
            holder.int_item_score.setText("积分+" + hyqdBeanList.get(position).getIntegral());
        } else if (fpjlBeanList.size() != 0) {
            int index = position - hyqdBeanList.size();
            holder.int_item_name.setText("扶贫互助");
            holder.int_item_time.setText(InitDateUtil.getDate2(fpjlBeanList.get(index).getCreateDate()) + "  " +
                    InitDateUtil.getTime(fpjlBeanList.get(index).getCreateDate()));
            holder.int_item_score.setText("积分+" + fpjlBeanList.get(index).getIntegral());
        }
    }

    @Override
    public int getItemCount() {
        return hyqdBeanList.size() + fpjlBeanList.size();
    }

    class PartyIntViewHolder extends RecyclerView.ViewHolder {
        private TextView int_item_name, int_item_time, int_item_score;

        public PartyIntViewHolder(View itemView) {
            super(itemView);
            AutoUtils.autoSize(itemView);
            int_item_name = (TextView) itemView.findViewById(R.id.int_item_name);
            int_item_time = (TextView) itemView.findViewById(R.id.int_item_time);
            int_item_score = (TextView) itemView.findViewById(R.id.int_item_score);
        }
    }
}
