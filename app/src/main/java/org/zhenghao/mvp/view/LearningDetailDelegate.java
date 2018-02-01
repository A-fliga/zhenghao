package org.zhenghao.mvp.view;

import android.widget.TextView;

import org.zhenghao.R;
import org.zhenghao.mvp.model.bean.LearningDetailBean;
import org.zhenghao.utils.InitDateUtil;

/**
 * Created by www on 2018/1/10.
 */

public class LearningDetailDelegate extends ViewDelegate {
    public TextView learning_detail_title, learning_detail_source,
            learning_detail_time, learning_duration, learning_status;

    @Override
    public void onDestroy() {

    }

    @Override
    public int getRootLayoutId() {
        return R.layout.activity_learning_detail;
    }

    @Override
    public void initWidget() {
        learning_detail_title = get(R.id.learning_detail_title);
        learning_detail_source = get(R.id.learning_detail_source);
        learning_detail_time = get(R.id.learning_detail_time);
        learning_duration = get(R.id.learning_duration);
        learning_status = get(R.id.learning_status);
    }

    public void initView(LearningDetailBean result) {
        if (result.getIsEnd() == 0) {
            learning_status.setText("开始学习");
            learning_status.setEnabled(true);
            learning_status.setClickable(true);
        } else {
            learning_status.setEnabled(false);
            learning_status.setClickable(false);
            learning_status.setBackgroundColor(getActivity().getResources().getColor(R.color.color_c0c0c0));
            learning_status.setText("已完成");
        }
        learning_detail_title.setText(result.getPartyMemberLearning().getTitle());
        learning_detail_source.setText(result.getPartyMemberLearning().getContentSource());
        learning_duration.setText(result.getPartyMemberLearning().getLearningTime() + "分钟");
        learning_detail_time.setText(InitDateUtil.getDate2(result.getPartyMemberLearning().getCreateTime())
                + " " + InitDateUtil.getTime(result.getPartyMemberLearning().getCreateTime()));
    }

    public void setLearningStatus(final String text, final Boolean canClick) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (canClick) {
                    learning_status.setBackgroundColor(getActivity().getResources().getColor(R.color.color_e60000));
                    learning_status.setText("提交结果");
                } else {
                    learning_status.setBackgroundColor(getActivity().getResources().getColor(R.color.color_c0c0c0));
                    learning_status.setText(text);
                }
            }
        });

    }
}
