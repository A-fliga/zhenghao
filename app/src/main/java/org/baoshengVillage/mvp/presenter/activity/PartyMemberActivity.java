package org.baoshengVillage.mvp.presenter.activity;

import org.baoshengVillage.mvp.view.PartyMemberDelegate;

/**
 * Created by www on 2018/1/8.
 * 为党员时的个人中心页面
 */

public class PartyMemberActivity extends ActivityPresenter<PartyMemberDelegate> {
    @Override
    public Class<PartyMemberDelegate> getDelegateClass() {
        return PartyMemberDelegate.class;
    }

    @Override
    public boolean isSetDisplayHomeAsUpEnabled() {
        return true;
    }
}
