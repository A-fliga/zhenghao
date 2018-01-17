package org.baoshengVillage.application;


import org.baoshengVillage.mvp.model.bean.UserBean;
import org.baoshengVillage.utils.SharedPreferencesUtil;

import java.util.Map;

import static org.baoshengVillage.constants.Constants.LoginType.IS_PARTY_MEMBER;
import static org.baoshengVillage.constants.Constants.LoginType.IS_PUBLIC;
import static org.baoshengVillage.constants.Constants.LoginType.IS_VISITOR;

/**
 * 登录的用户信管理，只能有一个用户，所以用单例
 * <p>
 * Created by www on 3/21/17.
 */
public final class UserManager {

    private static volatile UserManager instance;
    private UserBean mUserBean;

    private UserManager() {
    }

    public static UserManager getInstance() {
        if (null == instance) {
            synchronized (UserManager.class) {
                if (null == instance) {
                    instance = new UserManager();
                }
            }
        }
        return instance;
    }

    public static String getUserName() {
        String username = null;
        UserBean data = getInstance().getUserBean();
        if (data != null) {
            username = data.getNames();
        }
        return username;
    }


    public String getUserStatus() {
        return SharedPreferencesUtil.getUserStatus();
    }

    public Boolean alreadyLogin() {
        return !(getUserStatus() == null || getUserStatus().equals(IS_VISITOR));
    }

    public Boolean isPartyMember() {
        return getUserStatus() != null && getUserStatus().equals(IS_PARTY_MEMBER);
    }

    public void setUserBean(UserBean userBean) {
        this.mUserBean = userBean;
        if (userBean.getPartyMemberInformation() != null) {
            SharedPreferencesUtil.saveUserStatus(IS_PARTY_MEMBER);
        } else SharedPreferencesUtil.saveUserStatus(IS_PUBLIC);
    }

    public void setUserNull() {
        this.mUserBean = null;
        SharedPreferencesUtil.saveUserStatus(IS_VISITOR);
    }

    public void setUserPhonePwd(String phone,String pwd){
        SharedPreferencesUtil.savePhonePwd(phone,pwd);
    }

    public Map<String,String> getUserPhonePwd(){
        return SharedPreferencesUtil.getPhonePwd();
    }

//
//    public void loadUser() {
//        mUserBean = new LoginData();
//        SharedPreferences sp = CloudApplication.getContext().getSharedPreferences();
//        mUserBean.username = sp.getString(Constants.LoginData.USERNAME, null);
//        mUserBean.organizer = sp.getString(Constants.LoginData.ORGANIZER, null);
//        mUserBean.token = sp.getString(Constants.LoginData.TOKEN, null);
//        mUserBean.gender = sp.getString(Constants.LoginData.GENDER, null);
//        mUserBean.mobile = sp.getString(Constants.LoginData.MOBILE, null);
//        mUserBean.email = sp.getString(Constants.LoginData.EMAIL, null);
//        mUserBean.description = sp.getString(Constants.LoginData.DESCRIPTION, null);
//        mUserBean.nickname = sp.getString(Constants.LoginData.NICKNAME, null);
//        mUserBean.avatarBucket = sp.getString(Constants.LoginData.AVATARBUCKET, null);
//        mUserBean.avatarName = sp.getString(Constants.LoginData.AVATARNAME, null);
//        mUserBean.language = sp.getString(Constants.LoginData.LANGUAGE, null);
//        mUserBean.rongyunToken = sp.getString(Constants.LoginData.RONGYUNTOKEN, null);
//        Set<String> role = sp.getStringSet(Constants.LoginData.ROLE, new HashSet<String>());
//        mUserBean.role = new ArrayList<>(role);
//        mUserBean.realName = sp.getString(Constants.LoginData.REALNAME, null);
//        mUserBean.avatarUrl = sp.getString(Constants.LoginData.AVATARURL, null);
//    }
//
//    public void setUser(LoginData pData, String type, String mobileAreaCode, String account, String pwd) {
//        this.mUserBean = pData;
//        SharedPreferences.Editor edit = CloudApplication.getContext().getSharedPreferences().edit();
//        if (!TextUtils.isEmpty(type)) {
//            edit.putString(Constants.LOGIN_TYPE, type);
//        }
//        edit.putString(Constants.LoginData.USERNAME, TextUtils.isEmpty(pData.username) ? "" : pData.username);
//        edit.putString(Constants.LoginData.ORGANIZER, TextUtils.isEmpty(pData.organizer) ? "" : pData.organizer);
//        edit.putString(Constants.LoginData.TOKEN, TextUtils.isEmpty(pData.token) ? "" : pData.token);
//        edit.putString(Constants.LoginData.GENDER, TextUtils.isEmpty(pData.gender) ? "" : pData.gender);
//        edit.putString(Constants.LoginData.MOBILE, TextUtils.isEmpty(pData.mobile) ? "" : pData.mobile);
//        edit.putString(Constants.LoginData.EMAIL, TextUtils.isEmpty(pData.email) ? "" : pData.email);
//        edit.putString(Constants.LoginData.DESCRIPTION, TextUtils.isEmpty(pData.description) ? "" : pData.description);
//        edit.putString(Constants.LoginData.NICKNAME, TextUtils.isEmpty(pData.nickname) ? "" : pData.nickname);
//        edit.putString(Constants.LoginData.AVATARBUCKET, TextUtils.isEmpty(pData.avatarBucket) ? "" : pData.avatarBucket);
//        edit.putString(Constants.LoginData.AVATARNAME, TextUtils.isEmpty(pData.avatarName) ? "" : pData.avatarName);
//        edit.putString(Constants.LoginData.LANGUAGE, TextUtils.isEmpty(pData.language) ? "" : pData.language);
//        edit.putString(Constants.LoginData.RONGYUNTOKEN, TextUtils.isEmpty(pData.rongyunToken) ? "" : pData.rongyunToken);
//        Set<String> role = new HashSet<>(pData.role);
//        edit.putStringSet(Constants.LoginData.ROLE, role);
//        edit.putString(Constants.LoginData.REALNAME, TextUtils.isEmpty(pData.realName) ? "" : pData.realName);
//        edit.putString(Constants.LoginData.AVATARURL, TextUtils.isEmpty(pData.avatarUrl) ? "" : pData.avatarUrl);
//        if (!TextUtils.isEmpty(mobileAreaCode)) {
//            edit.putString(Constants.SP_AREACODE, mobileAreaCode);
//        }
//        if (!TextUtils.isEmpty(account) && !TextUtils.isEmpty(pwd)) {
//            edit.putString(Constants.SP_ACCOUNT, account);
//            edit.putString(Constants.SP_PWD, pwd);
//        }
//        edit.apply();
//    }

    public UserBean getUserBean() {
        return mUserBean;
    }

//    public String getUserRole() {
//        if (mUserBean != null)
//            return mUserBean.role.get(0);
//        else return Constants.ROLE_VISITOR;
//    }
}