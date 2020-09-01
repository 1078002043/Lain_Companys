package http;

import android.content.Context;
import android.util.Log;

import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

/**
 * Cookies管理，用于持久化Cookies
 *
 * @author Create by Yin Luo Fei
 * @version 0.1
 * @Time 2019/7/28 23 : 45
 */
public class CookiesManager implements CookieJar {
    //Context
    private static Context mContext;
    //保存Cookie的主要类
    private static PersistentCookieStore cookieStore;

    /**
     * 初始化Context和 cookieStore
     *
     * @param context 初始化Context
     */
    public CookiesManager(Context context) {

        mContext = context;

        if (cookieStore == null) {
            cookieStore = new PersistentCookieStore(mContext);
        }

    }

    /**
     * 保存发送网络请求的Cookies
     *
     * @param url     发送网络请求的URL
     * @param cookies 服务器返回的Cookies进行保存
     */
    @Override
    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
        Log.d("dfasdrrer", "saveFromResponse: " + cookies.size() + "----" + url.host());
        if (cookies != null && cookies.size() > 0) {
            for (Cookie item : cookies) {
                cookieStore.add(url, item);
            }
        }
    }

    /**
     * 获取Cookies
     *
     * @param url 网络请求的URL
     * @return 保存的Cookies
     */
    @Override
    public List<Cookie> loadForRequest(HttpUrl url) {
        List<Cookie> cookies = cookieStore.get(url);
        return cookies;
    }

    public List<Cookie> getCookies(String host) {

        return cookieStore.get(host);

    }

    /**
     * 清空所有的Cookies
     */
    public void removeAllCookies() {

        cookieStore.removeAll();

    }

    /**
     * 清空单个Cookie
     * @param httpUrl Cookie对应的请求链接
     * @param cookie Cookie 实例
     */
    public void removeCookie(HttpUrl httpUrl, Cookie cookie) {
        cookieStore.remove(httpUrl, cookie);
    }

}
