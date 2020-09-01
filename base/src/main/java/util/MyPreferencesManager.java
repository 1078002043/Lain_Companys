package util;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.shawnlin.preferencesmanager.PreferencesManager;

import java.util.ArrayList;
import java.util.List;

/**
 * SharePreferences 封装
 *
 * @author Create by Yin Luo Fei
 * @version 0.1
 * @Time 2019/8/5 22 : 47
 */
public class MyPreferencesManager extends PreferencesManager {
    private Context context;

    /**
     * Initial the preferences manager.
     *
     * @param context The context of the application.
     */
    public MyPreferencesManager(Context context) {
        super(context);
        this.context = context;
    }

    /**
     * 设置文件名
     *
     * @param context        Context
     * @param preferenceName 文件名
     */
    public MyPreferencesManager(Context context, String preferenceName) {
        super(context);
        setName(preferenceName);
    }

    /**
     * 保存List
     *
     * @param key       保存List 的 KEY
     * @param listShare 需要保存的List数据
     * @param <T>       泛型
     */
    public static <T> void putList(String key, List<T> listShare) {

        //如果传入的数据是空的，直接返回
        if (listShare == null || listShare.size() <= 0) {
            return;
        }
        //获取Gson
        Gson gson = new Gson();
        //将List集合转成 JSON
        String strJson = gson.toJson(listShare);

        //清空文件
        //clear();
        //清空KEY
        PreferencesManager.remove(key);
        //储存集合
        PreferencesManager.putString(key, strJson);

    }

    /**
     * 获取保存的List数据集合
     *
     * @param key 保存List数据时的KEY
     * @param t   反射泛型名
     * @param <T> 反射泛型
     * @return 泛型List
     */
    public static <T> List<T> getList(String key, Class<T> t) {
        //获取Gson
        Gson gson = new Gson();
        //创建新的 List
        List<T> dataList = new ArrayList<>();
        //获取保存的 List 数据
        String listData = PreferencesManager.getString(key, "");

        //如果集合为空，直接返回
        if (listData.isEmpty()) {
            return dataList;
        }
        //JSON解析
        JsonParser parser = new JsonParser();
        //通过解析拿到返回的 JSONArray
        JsonArray jsonArray = parser.parse(listData).getAsJsonArray();
        //遍历集合数据中的 Element，添加到 数据List 中
        for (JsonElement element : jsonArray)
            dataList.add(gson.fromJson(element, t));
        //返回 获取到的数据
        return dataList;
    }

}
