package org.baoshengVillage.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;


import org.baoshengVillage.mvp.presenter.activity.ActivityPresenter;
import org.baoshengVillage.mvp.presenter.activity.MainActivity;
import org.baoshengVillage.mvp.presenter.activity.NoticeDetailActivity;
import org.baoshengVillage.utils.LogUtil;
import org.baoshengVillage.utils.MD5Util;
import org.baoshengVillage.utils.SharedPreferencesUtil;
import org.baoshengVillage.utils.SystemUtil;
import org.greenrobot.eventbus.EventBus;
import org.json.JSONObject;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by Administrator on 2017/10/8 0008.
 */

public class JpushCustomerReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        //接收发送下来的通知
        if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {
            EventBus.getDefault().post("refresh");
        }
        if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
            int noticeId = getInfoId(intent);
            //打开自定义的Activity
            Boolean isAppAlive = SharedPreferencesUtil.getAppStatus();
            if (!isAppAlive || ActivityPresenter.getActivityStack() == null || ActivityPresenter.getActivityCount() == 0) {
                firstActivity(context, noticeId);
            } else {
                Bundle noticeBundle = new Bundle();
                noticeBundle.putInt("notice_id", noticeId);
                Intent noticeIntent = new Intent(ActivityPresenter.getTopActivity(), NoticeDetailActivity.class);
                noticeIntent.putExtras(noticeBundle);
                ActivityPresenter.getTopActivity().startActivity(noticeIntent);
            }
        }
    }

    private void firstActivity(Context context, int noticeId) {
        Bundle bundle = new Bundle();
        bundle.putBoolean("from_notification", true);
        bundle.putInt("notice_id", noticeId);
        Intent i = new Intent(context, MainActivity.class);
        i.putExtras(bundle);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }

    private int getInfoId(Intent intent) {
        int noticeId = 0;
        Bundle bundleClick = intent.getExtras();
        String extras = bundleClick.getString(JPushInterface.EXTRA_EXTRA);
        try {
            JSONObject jsonObject = new JSONObject(new JSONObject(extras).getString("androidNotification_extras_key"));
            String action = jsonObject.getString("action");
            if (action.equals("pushInform")) {
                noticeId = jsonObject.getInt("informId");
            }
        } catch (Exception e) {
            LogUtil.d("解析异常");
        }
        return noticeId;
    }
}
