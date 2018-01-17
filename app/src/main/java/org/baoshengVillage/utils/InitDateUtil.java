package org.baoshengVillage.utils;

import android.text.format.DateFormat;
import android.widget.TextView;

import java.util.Calendar;

/**
 * Created by www on 2017/11/17.
 */

public class InitDateUtil {
    //初始化日期
    public static void initDate(TextView dateTV) {
        long sysTime = System.currentTimeMillis();
        CharSequence sysTimeStr = DateFormat.format("yyyy年MM月dd日", sysTime);
        dateTV.setText(sysTimeStr);
    }

    //初始化日期
    public static CharSequence getDate(long time) {
        return DateFormat.format("yyyy年MM月dd日", time);
    }


    //初始化时钟
    public static CharSequence getTime(long time) {
        return DateFormat.format("HH:mm", time);
    }

    //初始化日期
    public static CharSequence getDate2(long time) {
        return DateFormat.format("yyyy-MM-dd", time);
    }


    //初始化时钟
    public static String initClock(TextView clockTv) {
        long sysTime = System.currentTimeMillis();
        CharSequence sysTimeStr = DateFormat.format("HH:mm:ss", sysTime);
        if (clockTv != null)
            clockTv.setText(sysTimeStr);
        return sysTimeStr.toString();
    }

}
