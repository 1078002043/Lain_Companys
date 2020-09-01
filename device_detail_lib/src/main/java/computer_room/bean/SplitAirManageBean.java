package computer_room.bean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * 分体空调 设备管理
 *
 * @author Create by Yin Luo Fei
 * @version 0.1
 * @Time 2019/8/21 17 : 38
 */
public class SplitAirManageBean {


    /**
     * address : 1
     * deviceLocationPojo : {"dlId":0,"dlName":"12"}
     * id : 1
     * ip : 1
     * ipPort : 192.168.1.54:6002
     * name : 1号空调
     * number : 0
     * offOrder :
     * onOrder :
     * sn : 0
     * status : 0
     * studyOffOrder :
     * studyOnOrder :
     */

    private int address;
    private DeviceLocationPojoBean deviceLocationPojo;
    private int id;
    private int ip;
    private String ipPort;
    private String name;
    private int number;
    private String offOrder;
    private String onOrder;
    private int sn;
    private int status;
    private String studyOffOrder;
    private String studyOnOrder;

    public static SplitAirManageBean objectFromData(String str) {

        return new Gson().fromJson(str, SplitAirManageBean.class);
    }

    public static SplitAirManageBean objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), SplitAirManageBean.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<SplitAirManageBean> arraySplitAirManageBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<SplitAirManageBean>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public static List<SplitAirManageBean> arraySplitAirManageBeanFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<SplitAirManageBean>>() {
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

    public String getOffOrder() {
        return offOrder;
    }

    public void setOffOrder(String offOrder) {
        this.offOrder = offOrder;
    }

    public String getOnOrder() {
        return onOrder;
    }

    public void setOnOrder(String onOrder) {
        this.onOrder = onOrder;
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

    public String getStudyOffOrder() {
        return studyOffOrder;
    }

    public void setStudyOffOrder(String studyOffOrder) {
        this.studyOffOrder = studyOffOrder;
    }

    public String getStudyOnOrder() {
        return studyOnOrder;
    }

    public void setStudyOnOrder(String studyOnOrder) {
        this.studyOnOrder = studyOnOrder;
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
