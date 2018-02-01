package org.zhenghao.http.cookie;

import android.content.Context;

import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

/**
 * @author www
 *         create time：16:18 2016-08-12
 *         email：tanzhiqiang@todayoffice.cn
 *         desc:管理cookie的
 */
public class CookiesManager implements CookieJar {

    private final PersistentCookieStore mPersistentCookieStore;

    public CookiesManager(Context context) {
        mPersistentCookieStore = PersistentCookieStore.getInstance(context);
    }

    @Override
    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
        if (cookies != null && cookies.size() > 0) {
            for (Cookie item : cookies) {
                mPersistentCookieStore.add(url, item);
            }
        }
    }

    @Override
    public List<Cookie> loadForRequest(HttpUrl url) {
        List<Cookie> cookies = mPersistentCookieStore.get(url);
        for (int i = 0; i < cookies.size(); i++) {
            Cookie cookie = cookies.get(i);
        }
        return cookies;
    }
}