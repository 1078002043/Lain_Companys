package computer_room.bean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * 安防监控 BEAN
 *
 * @author Create by Yin Luo Fei
 * @version 0.1
 * @Time 2019/8/7 22 : 24
 */
public class SecurityBean {


    /**
     * iconUrl : /resources/icon/72x72/security/infrared.png
     * id : 1
     * isShow : 1
     * name : 红外监控
     * number : 1
     */

    private String iconUrl;
    private int id;
    private int isShow;
    private String name;
    private int number;

    public static SecurityBean objectFromData(String str) {

        return new Gson().fromJson(str, SecurityBean.class);
    }

    public static SecurityBean objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), SecurityBean.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<SecurityBean> arrayOperationalBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<SecurityBean>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public static List<SecurityBean> arrayOperationalBeanFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<SecurityBean>>() {
            }.getType();

            return new Gson().fromJson(jsonObject.getString(str), listType);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new ArrayList();


    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIsShow() {
        return isShow;
    }

    public void setIsShow(int isShow) {
        this.isShow = isShow;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
