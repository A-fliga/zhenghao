package org.zhenghao.mvp.view;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import org.zhenghao.R;
import org.zhenghao.application.UserManager;
import org.zhenghao.mvp.model.bean.UserBean;
import org.zhenghao.utils.LoadImgUtil;

/**
 * Created by www on 2017/12/29.
 */

public class MineFragmentDelegate extends ViewDelegate {
    private ImageView user_icon;
    private UserBean userBean;

    @Override
    public void onDestroy() {

    }

    @Override
    public int getRootLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    public void initWidget() {
        TextView name = get(R.id.mine_name);
        ImageView party_member = get(R.id.party_member);
        user_icon = get(R.id.mine_icon);
        userBean = UserManager.getInstance().getUserBean();
        if (!UserManager.getInstance().alreadyLogin()) {
            visitorView();
        } else {
            AlreadyLogin(name, party_member);
        }
    }

    private void AlreadyLogin(TextView name, ImageView party_member) {
        get(R.id.mine_settings_img).setVisibility(View.VISIBLE);
        get(R.id.mine_to_login).setVisibility(View.GONE);
        get(R.id.mine_identify_rl).setVisibility(View.VISIBLE);
        if (UserManager.getInstance().isPartyMember() && userBean.getPartyMemberInformation() != null) {
            isPartyMember(name, party_member);

        } else {
            isPublic(name, party_member);
        }
    }

    private void isPublic(TextView name, ImageView party_member) {
        name.setText(userBean.getNames());
        get(R.id.mine_already_identification).setVisibility(View.GONE);
        party_member.setBackgroundResource(R.mipmap.to_identify);
        party_member.setVisibility(View.VISIBLE);
        party_member.setClickable(true);
        party_member.setEnabled(true);
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (userBean.getHeadUrl() != null && !userBean.getHeadUrl().isEmpty()) {
                    LoadImgUtil.loadCirclePic(getActivity(), userBean.getHeadUrl(), user_icon, R.mipmap.normal_head);
                } else user_icon.setImageResource(R.mipmap.normal_head);
            }
        });
    }

    private void isPartyMember(TextView name, ImageView party_member) {
        name.setText(userBean.getPartyMemberInformation().getNames());
        get(R.id.mine_already_identification).setVisibility(View.VISIBLE);
        party_member.setBackgroundResource(R.mipmap.party_member);
        party_member.setClickable(false);
        party_member.setEnabled(false);
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (userBean.getPartyMemberInformation().getHeadPortraitUrl() != null && !userBean.getPartyMemberInformation().getHeadPortraitUrl().isEmpty()) {
                    LoadImgUtil.loadCirclePic(getActivity(), userBean.getPartyMemberInformation().getHeadPortraitUrl(), user_icon, R.mipmap.normal_head);
                } else user_icon.setImageResource(R.mipmap.normal_head);
            }
        });
    }

    private void visitorView() {
        get(R.id.mine_to_login).setVisibility(View.VISIBLE);
        get(R.id.mine_identify_rl).setVisibility(View.GONE);
        get(R.id.mine_settings_img).setVisibility(View.GONE);
    }
}
