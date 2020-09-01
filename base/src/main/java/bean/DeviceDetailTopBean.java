package bean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * 机房监控 设备详细信息中的 顶部导航栏
 *
 * @author Create by Yin Luo Fei
 * @version 0.1
 * @Time 2019/8/7 00 : 28
 */
public class DeviceDetailTopBean {
    /**
     * id : 1
     * name : 实时数据
     */

    private int id;
    private String name;

    public static DeviceDetailTopBean objectFromData(String str) {

        return new Gson().fromJson(str, DeviceDetailTopBean.class);
    }

    public static DeviceDetailTopBean objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), DeviceDetailTopBean.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<DeviceDetailTopBean> arrayTemperatureBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<DeviceDetailTopBean>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public static List<DeviceDetailTopBean> arrayTemperatureBeanFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<DeviceDetailTopBean>>() {
            }.getType();

            return new Gson().fromJson(jsonObject.getString(str), listType);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new ArrayList();


    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
