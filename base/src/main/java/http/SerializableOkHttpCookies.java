package http;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import okhttp3.Cookie;

/**
 * 序列化Cookies
 *
 * @author Create by Yin Luo Fei
 * @version 0.1
 * @Time 2019/7/28 23 : 44
 */
public class SerializableOkHttpCookies implements Serializable {

    //该字段不参与序列化
    private transient final Cookie cookies;
    //该字段不参与序列化
    private transient Cookie clientCookies;
    //初始化Cookies
    public SerializableOkHttpCookies(Cookie cookies) {
        this.cookies = cookies;
    }

    /**
     * 获取Cookie
     * @return Cookie
     */
    public Cookie getCookies() {
        Cookie bestCookies = cookies;
        if (clientCookies != null) {
            bestCookies = clientCookies;
        }
        return bestCookies;
    }

    /**
     * 将Cookie写入流中
     * @param out Object输出流
     * @throws IOException 可能发生IO Exception
     */
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.writeObject(cookies.name());
        out.writeObject(cookies.value());
        out.writeLong(cookies.expiresAt());
        out.writeObject(cookies.domain());
        out.writeObject(cookies.path());
        out.writeBoolean(cookies.secure());
        out.writeBoolean(cookies.httpOnly());
        out.writeBoolean(cookies.hostOnly());
        out.writeBoolean(cookies.persistent());
    }

    /**
     * 读取Object Cookie
     * @param in Object 输入流
     * @throws IOException IO异常
     * @throws ClassNotFoundException 找不到类文件异常
     */
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
        clientCookies = builder.build();
    }

}
