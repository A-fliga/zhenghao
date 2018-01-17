package org.baoshengVillage.utils;

import android.content.Context;
import android.content.SharedPreferences;

import org.baoshengVillage.application.MyApplication;
import org.baoshengVillage.constants.Constants;

import java.util.HashMap;
import java.util.Map;

import static org.baoshengVillage.constants.Constants.USER_PHONE;
import static org.baoshengVillage.constants.Constants.USER_PWD;


/**
 * Created by Administrator on 2017/10/13 0013.
 */

public class SharedPreferencesUtil {
    private static SharedPreferences sharedPreference = null;
    private static SharedPreferences.Editor editor = null;


    /**
     * 保存极光别名
     */
    public static void saveJpushAlias(String id) {
        if (sharedPreference == null) {
            sharedPreference = MyApplication.getContext()
                    .getSharedPreferences(Constants.BAOSHENG_SHARED_NAME, Context.MODE_PRIVATE);
        }
        editor = sharedPreference.edit();
        editor.putString(Constants.JI_GUANG_TAG, id);
        editor.apply();
    }

    /**
     * App已退出
     */
    public static void saveAppStatus(Boolean isAlive) {
        if (sharedPreference == null) {
            sharedPreference = MyApplication.getContext()
                    .getSharedPreferences(Constants.BAOSHENG_SHARED_NAME, Context.MODE_PRIVATE);
        }
        editor = sharedPreference.edit();
        editor.putBoolean(Constants.IS_APP_ALIVE, isAlive);
        editor.apply();
    }

    public static Boolean getAppStatus() {
        if (sharedPreference == null) {
            sharedPreference = MyApplication.getContext()
                    .getSharedPreferences(Constants.BAOSHENG_SHARED_NAME, Context.MODE_PRIVATE);
        }
        return sharedPreference.getBoolean(Constants.IS_APP_ALIVE, true);
    }

    /**
     * 取出别名
     *
     * @param
     * @return
     */
    public static String getJpushAlias() {
        if (sharedPreference == null) {
            sharedPreference = MyApplication.getContext()
                    .getSharedPreferences(Constants.BAOSHENG_SHARED_NAME, Context.MODE_PRIVATE);
        }
        return sharedPreference.getString(Constants.JI_GUANG_TAG, null);
    }


    /**
     * 保存登录的用户信息
     */
    public static void saveUserStatus(String status) {
        if (sharedPreference == null) {
            sharedPreference = MyApplication.getContext()
                    .getSharedPreferences(Constants.BAOSHENG_SHARED_NAME, Context.MODE_PRIVATE);
        }
        editor = sharedPreference.edit();
        editor.putString(Constants.LOGIN_TYPE, status);
        editor.apply();
    }

    /**
     * 保存用户上次登录的手机号和密码
     *
     * @return
     */
    public static void savePhonePwd(String phone, String pwd) {
        if (sharedPreference == null) {
            sharedPreference = MyApplication.getContext()
                    .getSharedPreferences(Constants.BAOSHENG_SHARED_NAME, Context.MODE_PRIVATE);
        }
        editor = sharedPreference.edit();
        editor.putString(USER_PHONE, phone);
        editor.putString(USER_PWD, pwd);
        editor.apply();
    }

    /**
     * 取出用户上次登录的手机号和密码
     *
     * @return
     */
    public static Map<String, String> getPhonePwd() {
        if (sharedPreference == null) {
            sharedPreference = MyApplication.getContext()
                    .getSharedPreferences(Constants.BAOSHENG_SHARED_NAME, Context.MODE_PRIVATE);
        }
        HashMap<String, String> map = new HashMap<>();
        map.put(USER_PHONE, sharedPreference.getString(USER_PHONE, null));
        map.put(USER_PWD, sharedPreference.getString(USER_PWD, null));
        return map;
    }

    public static String getUserStatus() {
        if (sharedPreference == null) {
            sharedPreference = MyApplication.getContext()
                    .getSharedPreferences(Constants.BAOSHENG_SHARED_NAME, Context.MODE_PRIVATE);
        }
        return sharedPreference.getString(Constants.LOGIN_TYPE, null);
    }


}
