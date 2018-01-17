package org.baoshengVillage.application;

import android.app.Application;
import android.content.Context;


import org.baoshengVillage.utils.SharedPreferencesUtil;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by Administrator on 2017/10/8 0008.
 */

public class MyApplication extends Application {
    private static Context context;
    private static MyApplication application;
    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        application = this;
        //极光推送
        JPushInterface.init(this);
        JPushInterface.setDebugMode(true);
    }

    public static Context getContext() {
        return context;
    }

    public static MyApplication getAppContext(){
        return application;
    }
    /**
     * 设置极光的别名或标签
     */
    public void setAlisa(String alias) {
        if(alias != null){
            JPushInterface.setAlias(getContext(), 1100, alias);
            SharedPreferencesUtil.saveJpushAlias(alias);
        }
    }

}
