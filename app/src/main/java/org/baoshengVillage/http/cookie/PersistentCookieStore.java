package org.baoshengVillage.http.cookie;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import org.baoshengVillage.constants.Constants;
import org.baoshengVillage.utils.LogUtil;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import okhttp3.Cookie;
import okhttp3.HttpUrl;

/**
 * @author www
 *         create time：16:19 2016-08-12
 *         email：tanzhiqiang@todayoffice.cn
 *         desc:cookie持久化
 */
@SuppressWarnings("ALL")
public final class PersistentCookieStore {

    private static final String TAG = "DGG";
    /**
     * PersistentCookieStore 单例
     */
    private static volatile PersistentCookieStore sPersistentCookieStore;
    /**
     * cookies map
     */
    private final Map<String, ConcurrentHashMap<String, Cookie>> mCookies;
    /**
     * 保存cookies的SharedPreferences
     */
    private final SharedPreferences mCookiePrefs;

    /**
     * 私有构造方法
     *
     * @param context {@link Context}
     */
    private PersistentCookieStore(Context context) {
        mCookiePrefs = context.getSharedPreferences(Constants.SP_COOKIE, 0);
        mCookies = new HashMap<>();
        //将持久化的cookies缓存到内存中 即map mCookies
        Map<String, ?> prefsMap = mCookiePrefs.getAll();
        for (Map.Entry<String, ?> entry : prefsMap.entrySet()) {
            String[] cookieNames = TextUtils.split((String) entry.getValue(), ",");
            for (String name : cookieNames) {
                String encodedCookie = mCookiePrefs.getString(name, null);
                if (encodedCookie != null) {
                    Cookie decodedCookie = decodeCookie(encodedCookie);
                    if (decodedCookie != null) {
                        if (!mCookies.containsKey(entry.getKey())) {
                            mCookies.put(entry.getKey(), new ConcurrentHashMap<String, Cookie>());
                        }
                        mCookies.get(entry.getKey()).put(name, decodedCookie);
                    }
                }
            }
        }
    }

    /**
     * @param context Context
     * @return return {@link PersistentCookieStore}
     */
    public static PersistentCookieStore getInstance(Context context) {
        if (sPersistentCookieStore == null) {
            synchronized (PersistentCookieStore.class) {
                if (null == sPersistentCookieStore) {
                    sPersistentCookieStore = new PersistentCookieStore(context);
                }
            }
        }
        return sPersistentCookieStore;
    }

    /**
     * 获取cookie
     *
     * @param cookie {@link Cookie}
     * @return return cookie实体数据
     */
    private String getCookieToken(Cookie cookie) {
        return cookie.name() + "@" + cookie.domain();
    }

    /**
     * 添加cookie
     *
     * @param url    key
     * @param cookie value
     */
    public void add(HttpUrl url, Cookie cookie) {
        String name = getCookieToken(cookie);
        //将cookies缓存到内存中 如果缓存过期 就重置此cookie
        if (!cookie.persistent()) {
            if (!mCookies.containsKey(url.host())) {
                mCookies.put(url.host(), new ConcurrentHashMap<String, Cookie>());
            }
            mCookies.get(url.host()).put(name, cookie);
        } else {
            if (mCookies.containsKey(url.host())) {
                mCookies.get(url.host()).remove(name);
            }
        }
        //讲cookies持久化到本地
        SharedPreferences.Editor prefsWriter = mCookiePrefs.edit();
        prefsWriter.putString(url.host(), TextUtils.join(",", mCookies.get(url.host()).keySet()));
        prefsWriter.putString(name, encodeCookie(new SerializableOkHttpCookies(cookie)));
        prefsWriter.apply();
    }

    /**
     * 获取 cookie
     *
     * @param url cookie 存储对应的key
     * @return return cookie 集合
     */
    public List<Cookie> get(HttpUrl url) {
        ArrayList<Cookie> ret = new ArrayList<>();
        if (mCookies.containsKey(url.host())) {
            ConcurrentHashMap<String, Cookie> concurrentHashMap = mCookies.get(url.host());
            if (concurrentHashMap != null && !concurrentHashMap.isEmpty()) {
                ret.addAll(concurrentHashMap.values());
            }
        }
        return ret;
    }

    /**
     * 删除所有cookie
     *
     * @return return true 删除所有成功
     */
    public void removeAll() {
        SharedPreferences.Editor prefsWriter = mCookiePrefs.edit();
        prefsWriter.clear();
        prefsWriter.apply();
        mCookies.clear();
    }

    /**
     * 移除url 对应的cookie
     *
     * @param url    cookie 存储的key
     * @param cookie cookie数据
     * @return true 移除成功
     */
    public boolean remove(HttpUrl url, Cookie cookie) {
        String name = getCookieToken(cookie);
        if (mCookies.containsKey(url.host()) && mCookies.get(url.host()).containsKey(name)) {
            mCookies.get(url.host()).remove(name);

            SharedPreferences.Editor prefsWriter = mCookiePrefs.edit();
            if (mCookiePrefs.contains(name)) {
                prefsWriter.remove(name);
            }
            prefsWriter.putString(url.host(), TextUtils.join(",", mCookies.get(url.host()).keySet()));
            prefsWriter.apply();
            return true;
        } else {
            return false;
        }
    }

    /**
     * 获取所有cookie
     *
     * @return return all cookie
     */
    public List<Cookie> getCookies() {
        ArrayList<Cookie> ret = new ArrayList<>();
        for (String key : mCookies.keySet())
            ret.addAll(mCookies.get(key).values());
        return ret;
    }

    /**
     * mCookies 序列化成 string
     *
     * @param cookie 要序列化的cookie
     * @return 序列化之后的string
     */
    private String encodeCookie(SerializableOkHttpCookies cookie) {
        if (cookie == null)
            return null;
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(os);
            outputStream.writeObject(cookie);
        } catch (IOException e) {
            return null;
        }
        return byteArrayToHexString(os.toByteArray());
    }

    /**
     * 将字符串反序列化成cookies
     *
     * @param cookieString mCookies string
     * @return cookie object
     */
    private Cookie decodeCookie(String cookieString) {
        byte[] bytes = hexStringToByteArray(cookieString);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        Cookie cookie = null;
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            cookie = ((SerializableOkHttpCookies) objectInputStream.readObject()).getCookies();
        } catch (IOException e) {
            LogUtil.i(TAG, "IOException in decodeCookie" + e.getMessage());
        } catch (ClassNotFoundException e) {
            LogUtil.i(TAG, "ClassNotFoundException in decodeCookie" + e.getMessage());
        }
        return cookie;
    }

    /**
     * 二进制数组转十六进制字符串
     *
     * @param bytes byte array to be converted
     * @return string containing hex values
     */
    private String byteArrayToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder(bytes.length * 2);
        for (byte element : bytes) {
            int v = element & 0xff;
            if (v < 16) {
                sb.append('0');
            }
            sb.append(Integer.toHexString(v));
        }
        return sb.toString().toUpperCase(Locale.US);
    }

    /**
     * 十六进制字符串转二进制数组
     *
     * @param hexString string of hex-encoded values
     * @return decoded byte array
     */
    private byte[] hexStringToByteArray(String hexString) {
        int len = hexString.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(hexString.charAt(i), 16) << 4) +
                    Character.digit(hexString.charAt(i + 1), 16));
        }
        return data;
    }
}