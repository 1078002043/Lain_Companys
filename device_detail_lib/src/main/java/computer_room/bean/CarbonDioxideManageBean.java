package computer_room.bean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * 二氧化碳 设备管理
 *
 * @author Create by Yin Luo Fei
 * @version 0.1
 * @Time 2019/8/22 10 : 55
 */
public class CarbonDioxideManageBean {


    /**
     * address : 2
     * alarmData : 100
     * currentData : 56
     * deviceLocationPojo : {"dlId":1,"dlName":"12"}
     * id : 1
     * ip : 5
     * ipPort : 192.168.1.222:5100
     * name : 1号CO2
     * number : 0
     * sn : 0
     */

    private int address;
    private int alarmData;
    private int currentData;
    private DeviceLocationPojoBean deviceLocationPojo;
    private int id;
    private int ip;
    private String ipPort;
    private String name;
    private int number;
    private int sn;

    public static CarbonDioxideManageBean objectFromData(String str) {

        return new Gson().fromJson(str, CarbonDioxideManageBean.class);
    }

    public static CarbonDioxideManageBean objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), CarbonDioxideManageBean.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<CarbonDioxideManageBean> arrayCarbonDioxideManageBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<CarbonDioxideManageBean>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public static List<CarbonDioxideManageBean> arrayCarbonDioxideManageBeanFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<CarbonDioxideManageBean>>() {
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

    public int getAlarmData() {
        return alarmData;
    }

    public void setAlarmData(int alarmData) {
        this.alarmData = alarmData;
    }

    public int getCurrentData() {
        return currentData;
    }

    public void setCurrentData(int currentData) {
        this.currentData = currentData;
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

    public static class DeviceLocationPojoBean {
        /**
         * dlId : 1
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
