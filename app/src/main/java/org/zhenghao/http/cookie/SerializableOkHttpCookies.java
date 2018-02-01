package org.zhenghao.http.cookie;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import okhttp3.Cookie;

/**
 * @author www
 *         create time：16:26 2016-08-12
 *         email：tanzhiqiang@todayoffice.cn
 *         desc: 序列化和反序列化cookies
 */
public class SerializableOkHttpCookies implements Serializable {

    /**
     * {@link Cookie} 对象
     */
    private final transient Cookie mCookies;
    /**
     *
     */
    private transient Cookie mClientCookies;

    /**
     * 创建一个{@link SerializableOkHttpCookies} 对象
     *
     * @param cookies {@link Cookie}
     */
    public SerializableOkHttpCookies(Cookie cookies) {
        this.mCookies = cookies;
    }

    /**
     * @return return {@link Cookie}对象
     */
    public Cookie getCookies() {
        Cookie bestCookies = mCookies;
        if (mClientCookies != null) {
            bestCookies = mClientCookies;
        }
        return bestCookies;
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.writeObject(mCookies.name());
        out.writeObject(mCookies.value());
        out.writeLong(mCookies.expiresAt());
        out.writeObject(mCookies.domain());
        out.writeObject(mCookies.path());
        out.writeBoolean(mCookies.secure());
        out.writeBoolean(mCookies.httpOnly());
        out.writeBoolean(mCookies.hostOnly());
        out.writeBoolean(mCookies.persistent());
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        String name = (String) in.readObject();
        String value = (String) in.readObject();
        long expiresAt = in.readLong();
        String domain = (String) in.readObject();
        String path = (String) in.readObject();
        boolean secure = in.readBoolean();
        boolean httpOnly = in.readBoolean();
        boolean hostOnly = in.readBoolean();
        boolean persistent = in.readBoolean();
        Cookie.Builder builder = new Cookie.Builder();
        builder = builder.name(name);
        builder = builder.value(value);
        builder = builder.expiresAt(expiresAt);
        builder = hostOnly ? builder.hostOnlyDomain(domain) : builder.domain(domain);
        builder = builder.path(path);
        builder = secure ? builder.secure() : builder;
        builder = httpOnly ? builder.httpOnly() : builder;
        mClientCookies = builder.build();
    }
}