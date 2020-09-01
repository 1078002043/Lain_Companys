package computer_room.bean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * 温湿度-设备 管理
 *
 * @author Create by Yin Luo Fei
 * @version 0.1
 * @Time 2019/9/29 10 : 27
 */
public class TemperatureManageBean {


    /**
     * deviceLocationPojo : {"dlId":1,"dlName":"12"}
     * ehmAddress : 1
     * ehmHum : 0
     * ehmId : 41
     * ehmInterval : 30
     * ehmMaxHum : 90
     * ehmMaxTemp : 35
     * ehmMinHum : 20
     * ehmMinTemp : 10
     * ehmName : 温湿度
     * ehmTemp : 0
     * ip : 1
     * ipPort : 192.168.1.54:6002
     * number : 0
     * sn : 0
     */

    private DeviceLocationPojoBean deviceLocationPojo;
    private int ehmAddress;
    private int ehmHum;
    private int ehmId;
    private int ehmInterval;
    private int ehmMaxHum;
    private int ehmMaxTemp;
    private int ehmMinHum;
    private int ehmMinTemp;
    private String ehmName;
    private int ehmTemp;
    private int ip;
    private String ipPort;
    private int number;
    private int sn;

    public static TemperatureManageBean objectFromData(String str) {

        return new Gson().fromJson(str, TemperatureManageBean.class);
    }

    public static TemperatureManageBean objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), TemperatureManageBean.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<TemperatureManageBean> arrayTemperatureManageBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<TemperatureManageBean>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public static List<TemperatureManageBean> arrayTemperatureManageBeanFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<TemperatureManageBean>>() {
            }.getType();

            return new Gson().fromJson(jsonObject.getString(str), listType);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new ArrayList();


    }

    public DeviceLocationPojoBean getDeviceLocationPojo() {
        return deviceLocationPojo;
    }

    public void setDeviceLocationPojo(DeviceLocationPojoBean deviceLocationPojo) {
        this.deviceLocationPojo = deviceLocationPojo;
    }

    public int getEhmAddress() {
        return ehmAddress;
    }

    public void setEhmAddress(int ehmAddress) {
        this.ehmAddress = ehmAddress;
    }

    public int getEhmHum() {
        return ehmHum;
    }

    public void setEhmHum(int ehmHum) {
        this.ehmHum = ehmHum;
    }

    public int getEhmId() {
        return ehmId;
    }

    public void setEhmId(int ehmId) {
        this.ehmId = ehmId;
    }

    public int getEhmInterval() {
        return ehmInterval;
    }

    public void setEhmInterval(int ehmInterval) {
        this.ehmInterval = ehmInterval;
    }

    public int getEhmMaxHum() {
        return ehmMaxHum;
    }

    public void setEhmMaxHum(int ehmMaxHum) {
        this.ehmMaxHum = ehmMaxHum;
    }

    public int getEhmMaxTemp() {
        return ehmMaxTemp;
    }

    public void setEhmMaxTemp(int ehmMaxTemp) {
        this.ehmMaxTemp = ehmMaxTemp;
    }

    public int getEhmMinHum() {
        return ehmMinHum;
    }

    public void setEhmMinHum(int ehmMinHum) {
        this.ehmMinHum = ehmMinHum;
    }

    public int getEhmMinTemp() {
        return ehmMinTemp;
    }

    public void setEhmMinTemp(int ehmMinTemp) {
        this.ehmMinTemp = ehmMinTemp;
    }

    public String getEhmName() {
        return ehmName;
    }

    public void setEhmName(String ehmName) {
        this.ehmName = ehmName;
    }

    public int getEhmTemp() {
        return ehmTemp;
    }

    public void setEhmTemp(int ehmTemp) {
        this.ehmTemp = ehmTemp;
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
