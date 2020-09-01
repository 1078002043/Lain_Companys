package computer_room.bean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * 烟感
 *
 * @author Create by Yin Luo Fei
 * @version 0.1
 * @Time 2019/8/14 14 : 40
 */
public class SmokeMonitoringBean {

    /**
     * status : 2
     * gallery : DI0
     * name : 1号烟感
     */

    private int status;
    private String gallery;
    private String name;

    public static SmokeMonitoringBean objectFromData(String str) {

        return new Gson().fromJson(str, SmokeMonitoringBean.class);
    }

    public static SmokeMonitoringBean objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), SmokeMonitoringBean.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<SmokeMonitoringBean> arraySmokeMonitoringBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<SmokeMonitoringBean>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public static List<SmokeMonitoringBean> arraySmokeMonitoringBeanFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<SmokeMonitoringBean>>() {
            }.getType();

            return new Gson().fromJson(jsonObject.getString(str), listType);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new ArrayList();


    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getGallery() {
        return gallery;
    }

    public void setGallery(String gallery) {
        this.gallery = gallery;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
