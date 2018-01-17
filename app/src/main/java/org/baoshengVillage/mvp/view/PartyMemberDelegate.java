package org.baoshengVillage.mvp.view;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.baoshengVillage.R;
import org.baoshengVillage.application.UserManager;
import org.baoshengVillage.mvp.model.bean.UserBean;
import org.baoshengVillage.utils.InitDateUtil;
import org.baoshengVillage.utils.LoadImgUtil;

/**
 * Created by www on 2018/1/8.
 */

public class PartyMemberDelegate extends ViewDelegate {
    private TextView user_party_name, user_party_gender, user_party_birth, user_party_info;

    @Override
    public void onDestroy() {

    }

    @Override
    public int getRootLayoutId() {
        return R.layout.activity_party_member;
    }

    @Override
    public void initWidget() {
        initView();
        getTitleView().setText("个人信息");
        UserBean userBean = UserManager.getInstance().getUserBean();
        UserBean.PartyMemberInformationBean partyBean = userBean.getPartyMemberInformation();
        if (userBean != null) {
            if (partyBean.getHeadPortraitUrl() != null && !partyBean.getHeadPortraitUrl().isEmpty()) {
                LoadImgUtil.loadCirclePic(this.getActivity(), partyBean.getHeadPortraitUrl(), (ImageView) get(R.id.user_party_icon), R.mipmap.normal_head);
            }
            user_party_name.setText(partyBean.getNames());
            String gender = "";
            if (Integer.parseInt(partyBean.getSex()) == 1) {
                user_party_gender.setText("男");
                gender = "男";
            }
            if (Integer.parseInt(partyBean.getSex()) == 2) {
                {
                    user_party_gender.setText("女");
                    gender = "女";
                }
            }
            user_party_birth.setText(InitDateUtil.getDate(partyBean.getDateBirth()));
            user_party_info.setText(
                    "姓名：" + partyBean.getNames() + "\n" + "性别：" + gender + "\n" + "民族：" + partyBean.getNation() +
                            "\n籍贯：" + partyBean.getPlaceOfOrigin() + "\n出生日期：" + InitDateUtil.getDate(partyBean.getDateBirth()) + "\n入党时间：" +
                            InitDateUtil.getDate(Long.valueOf(partyBean.getTimeToJoinTheParty()).longValue()) + "\n文化水平：" +
                            partyBean.getEducation() + "\n住址：" + partyBean.getAddress() + "\n工作单位：" + partyBean.getWorkUnit()
            );
        }
    }

    private void initView() {
        user_party_name = get(R.id.user_party_name);
        user_party_gender = get(R.id.user_party_gender);
        user_party_birth = get(R.id.user_party_birth);
        user_party_info = get(R.id.user_party_info);
    }
}
