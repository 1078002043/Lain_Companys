package computer_room.bean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * 机房监控系统管理
 *
 * @author Create by Yin Luo Fei
 * @version 0.1
 * @Time 2019/8/7 23 : 41
 */
public class SystemManageBean {
    /**
     * iconUrl : /resources/icon/72x72/system/log.png
     * id : 0
     * isShow : 1
     * name : 日志管理
     * number : 0
     */

    private String iconUrl;
    private int id;
    private int isShow;
    private String name;
    private int number;

    public static SystemManageBean objectFromData(String str) {

        return new Gson().fromJson(str, SystemManageBean.class);
    }

    public static SystemManageBean objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), SystemManageBean.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<SystemManageBean> arraySystemManageBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<SystemManageBean>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public static List<SystemManageBean> arraySystemManageBeanFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<SystemManageBean>>() {
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
