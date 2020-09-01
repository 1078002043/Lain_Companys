package computer_room.bean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * 定位漏水设备管理 BEAN
 *
 * @author Create by Yin Luo Fei
 * @version 0.1
 * @Time 2019/8/21 15 : 54
 */
public class LocatingManageBean {


    /**
     * address : 1
     * deviceLocationPojo : {"dlId":0,"dlName":"12"}
     * id : 1
     * ip : 1
     * ipPort : 192.168.1.54:6002
     * length : 1
     * name : 定位漏水
     * number : 0
     * sn : 0
     * status : 0
     */

    private int address;
    private DeviceLocationPojoBean deviceLocationPojo;
    private int id;
    private int ip;
    private String ipPort;
    private int length;
    private String name;
    private int number;
    private int sn;
    private int status;

    public static LocatingManageBean objectFromData(String str) {

        return new Gson().fromJson(str, LocatingManageBean.class);
    }

    public static LocatingManageBean objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), LocatingManageBean.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<LocatingManageBean> arrayLocatingManageBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<LocatingManageBean>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public static List<LocatingManageBean> arrayLocatingManageBeanFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<LocatingManageBean>>() {
            }.getType();

            return new Gson().fromJson(jsonObject.getString(str), listType);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new ArrayList();


    }

    public int getAddress() {
        return address;
    }

    public void setAddress(int address) {
        this.address = address;
    }

    public DeviceLocationPojoBean getDeviceLocationPojo() {
        return deviceLocationPojo;
    }

    public void setDeviceLocationPojo(DeviceLocationPojoBean deviceLocationPojo) {
        this.deviceLocationPojo = deviceLocationPojo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIp() {
        return ip;
    }

    public void setIp(int ip) {
        this.ip = ip;
    }

    public String getIpPort() {
        return ipPort;
    }

    public void setIpPort(String ipPort) {
        this.ipPort = ipPort;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
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

    public int getSn() {
        return sn;
    }

    public void setSn(int sn) {
        this.sn = sn;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public static class DeviceLocationPojoBean {
        /**
         * dlId : 0
         * dlName : 12
         */

        private int dlId;
        private String dlName;

        public static DeviceLocationPojoBean objectFromData(String str) {

            return new Gson().fromJson(str, DeviceLocationPojoBean.class);
        }

        public static DeviceLocationPojoBean objectFromData(String str, String key) {

            try {
                JSONObject jsonObject = new JSONObject(str);

                return new Gson().fromJson(jsonObject.getString(str), DeviceLocationPojoBean.class);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        public static List<DeviceLocationPojoBean> arrayDeviceLocationPojoBeanFromData(String str) {

            Type listType = new TypeToken<ArrayList<DeviceLocationPojoBean>>() {
            }.getType();

            return new Gson().fromJson(str, listType);
        }

        public static List<DeviceLocationPojoBean> arrayDeviceLocationPojoBeanFromData(String str, String key) {

            try {
                JSONObject jsonObject = new JSONObject(str);
                Type listType = new TypeToken<ArrayList<DeviceLocationPojoBean>>() {
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
}
