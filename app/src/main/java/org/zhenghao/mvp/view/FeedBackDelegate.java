package org.zhenghao.mvp.view;

import org.zhenghao.R;

/**
 * Created by www on 2018/1/9.
 */

public class FeedBackDelegate extends ViewDelegate {
    @Override
    public void onDestroy() {

    }

    @Override
    public int getRootLayoutId() {
        return R.layout.activity_feedback;
    }

    @Override
    public void initWidget() {
        getTitleView().setText("意见反馈");
        setToolBarRightTv("提交");
        getToolBarRightTv().setTextColor(getActivity().getResources().getColor(R.color.color_e60000));
    }
}
