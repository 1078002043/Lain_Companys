package computer_room.bean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * 设备管理中的 IP 设置
 *
 * @author Create by Yin Luo Fei
 * @version 0.1
 * @Time 2019/8/15 15 : 59
 */
public class DeviceManageIP_Bean {

    /**
     * diAddress : 192.168.1.54
     * diDeviceNumber : 1
     * diId : 1
     * diIsConnect : 0
     * diName : 温湿度
     * diOperate : 0
     * diPort : 6002
     * sdsn : 0
     */

    private String diAddress;
    private int diDeviceNumber;
    private int diId;
    private int diIsConnect;
    private String diName;
    private int diOperate;
    private int diPort;
    private int sdsn;

    public static DeviceManageIP_Bean objectFromData(String str) {

        return new Gson().fromJson(str, DeviceManageIP_Bean.class);
    }

    public static DeviceManageIP_Bean objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), DeviceManageIP_Bean.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<DeviceManageIP_Bean> arrayDeviceManageIP_BeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<DeviceManageIP_Bean>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public static List<DeviceManageIP_Bean> arrayDeviceManageIP_BeanFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<DeviceManageIP_Bean>>() {
            }.getType();

            return new Gson().fromJson(jsonObject.getString(str), listType);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new ArrayList();


    }

    public String getDiAddress() {
        return diAddress;
    }

    public void setDiAddress(String diAddress) {
        this.diAddress = diAddress;
    }

    public int getDiDeviceNumber() {
        return diDeviceNumber;
    }

    public void setDiDeviceNumber(int diDeviceNumber) {
        this.diDeviceNumber = diDeviceNumber;
    }

    public int getDiId() {
        return diId;
    }

    public void setDiId(int diId) {
        this.diId = diId;
    }

    public int getDiIsConnect() {
        return diIsConnect;
    }

    public void setDiIsConnect(int diIsConnect) {
        this.diIsConnect = diIsConnect;
    }

    public String getDiName() {
        return diName;
    }

    public void setDiName(String diName) {
        this.diName = diName;
    }

    public int getDiOperate() {
        return diOperate;
    }

    public void setDiOperate(int diOperate) {
        this.diOperate = diOperate;
    }

    public int getDiPort() {
        return diPort;
    }

    public void setDiPort(int diPort) {
        this.diPort = diPort;
    }

    public int getSdsn() {
        return sdsn;
    }

    public void setSdsn(int sdsn) {
        this.sdsn = sdsn;
    }
}
