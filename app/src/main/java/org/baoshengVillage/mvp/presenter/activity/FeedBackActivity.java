package org.baoshengVillage.mvp.presenter.activity;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.view.View;

import org.baoshengVillage.R;
import org.baoshengVillage.application.UserManager;
import org.baoshengVillage.mvp.model.PublicModel;
import org.baoshengVillage.mvp.model.bean.BaseEntity;
import org.baoshengVillage.mvp.view.FeedBackDelegate;
import org.baoshengVillage.utils.ToastUtil;

import rx.Subscriber;

/**
 * Created by www on 2018/1/9.
 * 反馈
 */

public class FeedBackActivity extends ActivityPresenter<FeedBackDelegate> {
    private TextInputEditText feedback_et;

    @Override
    public Class<FeedBackDelegate> getDelegateClass() {
        return FeedBackDelegate.class;
    }

    @Override
    public boolean isSetDisplayHomeAsUpEnabled() {
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        feedback_et = get(R.id.feedback_et);
        viewDelegate.getToolBarRightTv().setOnClickListener(onClickListener);

    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.toolbar_right_tv) {
                if (!feedback_et.getText().toString().isEmpty()) {
                    toFeedBack();
                } else {
                    ToastUtil.s("内容不能为空");
                }
            }
        }
    };

    private void toFeedBack() {
        PublicModel.getInstance().feedBack(new Subscriber<BaseEntity>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                ToastUtil.s("操作失败，请联系管理员");
            }

            @Override
            public void onNext(BaseEntity baseEntity) {
                if (baseEntity.getCode() != 0) {
                    ToastUtil.s(baseEntity.getMsg());
                } else {
                    ToastUtil.s("感谢您的反馈！");
                    finish();
                }
            }
        }, UserManager.getInstance().getUserBean().getId(), feedback_et.getText().toString());
    }

}
