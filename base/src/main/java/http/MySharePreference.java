package http;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Map;

import base.Lain_Application;

/**
 * @ClassName: MySharePreference
 * @Description: SharePreference 工具类
 * @Author: YIN LUO FEI
 * @Date: 2020/7/24 15:30
 */
public class MySharePreference {

    //保存当前类实例，用于单例
    private volatile static MySharePreference sharePreference;
    private Context context;

    /**
     * 构建单例
     *
     * @return MySharePreference工具类的单例
     */
    public static MySharePreference getInstance() {

        if (sharePreference == null)
            synchronized (MySharePreference.class) {
                if (sharePreference == null)
                    sharePreference = new MySharePreference();
            }

        return sharePreference;
    }

    /**
     * 根据文件名来获取 SharedPreferences
     *
     * @param fileName 需要获取的文件名
     * @param context  上下文
     * @return 获取到的SharedPreferences
     */
    public SharedPreferences getSharePreference(String fileName, Context context) {

        SharedPreferences preferences = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        return preferences;

    }

    /**
     * 传入MAP集合和文件名，进行 Share 数据保存
     *
     * @param fileName 文件名称
     * @param dataMap  需要写入的数据
     */
    public void editPreference(String fileName, Map<String, String> dataMap) {

        SharedPreferences preferences = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);

        //获取编写类
        SharedPreferences.Editor editor = preferences.edit();

        //遍历获取数据
        for (Map.Entry<String, String> entryMap : dataMap.entrySet()) {

            //保存编辑的数据
            editor.putString(entryMap.getKey(), entryMap.getValue());

        }

        //应用编辑
        editor.apply();

    }

    /**
     * 返回获取 Preference 的数据
     *
     * @param fileName 文件名称
     * @param key      查询的KEY
     * @return 查询的结果
     */
    public String getPreference(String fileName, String key) {

        if (context == null)
            context = Lain_Application.getContext();

        SharedPreferences preferences = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);

        return preferences.getString(key, "null");

    }

    /**
     * 设置Content，如果传入的Context为NULL，则从 Application 中获取
     *
     * @param context 上下文
     */
    public void setContext(Context context) {
        this.context = context == null ? Lain_Application.getContext() : context;
    }
}
