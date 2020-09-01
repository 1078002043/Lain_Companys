package http;

import com.google.gson.Gson;

/**
 * 获取GSON的单例
 *
 * @author Create by Yin Luo Fei
 * @version 0.1
 * @Time 2019/8/1 15 : 15
 */
public class MyGson {

    //Gson引用
    private volatile static Gson gson = null;

    //私有化Gong构造方法
    private MyGson() {
    }

    /**
     * 获取Gson的单例
     *
     * @return Gson
     */
    public static Gson getInstance() {
        if (gson == null) {
            synchronized (MyGson.class) {
                if (gson == null) {
                    gson = new Gson();
                    return gson;
                }
            }

        } else return gson;

        return null;
    }

}
