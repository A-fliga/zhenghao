package org.zhenghao.mvp.view;

import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import org.zhenghao.R;
import org.zhenghao.application.UserManager;
import org.zhenghao.mvp.model.bean.UserBean;
import org.zhenghao.utils.InitDateUtil;
import org.zhenghao.utils.LoadImgUtil;

/**
 * Created by www on 2018/1/8.
 */

public class UserInfoDelegate extends ViewDelegate {
    private EditText name_et;
    private RadioButton radioMale, radioFemale;
    private TextView birth_tv;

    @Override
    public void onDestroy() {

    }

    @Override
    public int getRootLayoutId() {
        return R.layout.activity_user_info;
    }

    @Override
    public void initWidget() {
        initView();
        getTitleView().setText("个人信息");
        UserBean bean = UserManager.getInstance().getUserBean();
        if (bean != null) {
            name_et.setText(bean.getNames());
            if (bean.getGender() == 1) {
                radioMale.setChecked(true);
            }
            if (bean.getGender() == 2) {
                radioFemale.setChecked(true);
            }
            if (bean.getBirthTime() != null && !bean.getBirthTime().isEmpty()) {
                birth_tv.setText(InitDateUtil.getDate(Long.valueOf(bean.getBirthTime()).longValue()));
            }
            if (bean.getHeadUrl() != null && !bean.getHeadUrl().isEmpty()) {
                LoadImgUtil.loadCirclePic(this.getActivity(), bean.getHeadUrl(), (ImageView) get(R.id.user_info_icon), R.mipmap.normal_head);
            }
        }
        setToolBarRightTv("保存");
    }

    private void initView() {
        name_et = get(R.id.user_name_et);
        radioMale = get(R.id.radioMale);
        radioFemale = get(R.id.radioFemale);
        birth_tv = get(R.id.user_info_birth_tv);
    }
}
