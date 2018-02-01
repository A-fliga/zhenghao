package org.zhenghao.constants;

/**
 * Created by www on 2017/11/13.
 * 常量类
 */

public final class Constants {

    public static String PARTY_URL = "http://119.23.209.82:8080/baoshengcun/mobile.jsp?phone=1";

    //极光别名，不可修改
    public static final String JPUSH_NAME = "USER_ID";

    public static final String SP_COOKIE = "BAOSHENG_VILLAGE";
    public static final String BAOSHENG_SHARED_NAME = "BAOSHENG";
    public static final String CONTENT_TYPE = "application/json;charset=UTF-8";

    //调用相机请求码
    public static final int TAKE_PHOTO_REQUEST_CODE = 2000;

    //从图库中选择图片
    public static final int CHOOSE_PHOTO_FROM_GALLERY_CODE = 2001;

    public static final long DEFAULT_ID = -1000;

    //选择配置信息的code
    public static final int CHOOSE_SETTINGS_REQUEST_CODE = 1000;

    public static final int CHOOSE_SETTINGS_RESULT_CODE = 1001;

    //SharedPreference的key值，不要重复

    public static final String JI_GUANG_TAG = "JI_GUANG_TAG";//极光别名

    public static final String EQUIPMENT_ID = "EQUIPMENT_ID";//保存设备ID

    public static final String IS_APP_ALIVE = "IS_APP_ALIVE";//极光别名


    //登录类型
    public static final String LOGIN_TYPE = "LOGIN_TYPE";

    public static class LoginType {
        //党员
        public static final String IS_PARTY_MEMBER = "IS_PARTY_MEMBER";
        //群众
        public static final String IS_PUBLIC = "IS_PUBLIC";
        //未登录
        public static final String IS_VISITOR = "IS_VISITOR";
    }

    //用户的登录手机和密码
    public static final String USER_PHONE = "USER_PHONE";
    public static final String USER_PWD = "USER_PWD";


    public static final String FROM_CHANGE_PWD = "FROM_CHANGE_PWD";
    public static final String FROM_NORMAL = "FROM_NORMAL";

    public static final int IS_INIT = 0;
    public static final int IS_REFRESH = 1;
    public static final int IS_LOAD_MORE = 2;

    public static final int FROM_BANNER = 1000;
    public static final int FROM_INFO = 1001;
    public static final int FROM_SPIRIT = 1002;
    public static final String FROM_WHERE = "FROM_WHERE";
    public static final String INFO_ID = "INFO_ID";
    public static final String BANNER_ID = "BANNER_ID";


}
