package computer_room.bean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * 设备管理
 *
 * @author Create by Yin Luo Fei
 * @version 0.1
 * @Time 2019/8/14 16 : 26
 */
public class DevicesManageBean {
    /**
     * dlId : 1
     * dlName : 12
     */

    private int dlId;
    private String dlName;

    public static DevicesManageBean objectFromData(String str) {

        return new Gson().fromJson(str, DevicesManageBean.class);
    }

    public static DevicesManageBean objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), DevicesManageBean.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<DevicesManageBean> arrayDevicesManageBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<DevicesManageBean>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public static List<DevicesManageBean> arrayDevicesManageBeanFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<DevicesManageBean>>() {
            }.getType();

            return new Gson().fromJson(jsonObject.getString(str), listType);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new ArrayList();


    }

    public int getDlId() {
        return dlId;
    }

    public void setDlId(int dlId) {
        this.dlId = dlId;
    }

    public String getDlName() {
        return dlName;
    }

    public void setDlName(String dlName) {
        this.dlName = dlName;
    }
}
