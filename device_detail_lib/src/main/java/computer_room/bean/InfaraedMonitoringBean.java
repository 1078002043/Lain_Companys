package computer_room.bean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * 红外监控
 *
 * @author Create by Yin Luo Fei
 * @version 0.1
 * @Time 2019/8/14 13 : 42
 */
public class InfaraedMonitoringBean {
    /**
     * status : 2
     * gallery : DI1
     * name : 1号红外
     */

    private int status;
    private String gallery;
    private String name;

    public static InfaraedMonitoringBean objectFromData(String str) {

        return new Gson().fromJson(str, InfaraedMonitoringBean.class);
    }

    public static InfaraedMonitoringBean objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), InfaraedMonitoringBean.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<InfaraedMonitoringBean> arrayInfaraedMonitoringBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<InfaraedMonitoringBean>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public static List<InfaraedMonitoringBean> arrayInfaraedMonitoringBeanFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<InfaraedMonitoringBean>>() {
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
