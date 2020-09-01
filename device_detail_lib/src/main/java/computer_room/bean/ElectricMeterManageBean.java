package computer_room.bean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * 电量仪 设备管理 Bean
 *
 * @author Create by Yin Luo Fei
 * @version 0.1
 * @Time 2019/8/22 11 : 13
 */
public class ElectricMeterManageBean {


    /**
     * deviceLocationPojo : {"dlId":0,"dlName":"12"}
     * emmAddress : 1
     * emmId : 5
     * emmName : 5号电量仪
     * emmOrder :
     * intervaltime : 0
     * ip : 7
     * ipPort : 192.168.1.254:1031
     * maxABvol : 400
     * maxAcur : 100
     * maxApap : 120
     * maxAppf : 100
     * maxAprp : 120
     * maxAvol : 240
     * maxBCvol : 400
     * maxBcur : 100
     * maxBpap : 120
     * maxBppf : 100
     * maxBprp : 120
     * maxBvol : 240
     * maxCAvol : 400
     * maxCcur : 100
     * maxCpap : 120
     * maxCppf : 100
     * maxCprp : 120
     * maxCvol : 240
     * maxTpap : 300
     * maxTppf : 100
     * maxTprp : 300
     * minABvol : 350
     * minAcur : 0
     * minApap : 0
     * minAppf : 0
     * minAprp : 0
     * minAvol : 200
     * minBCvol : 350
     * minBcur : 0
     * minBpap : 0
     * minBppf : 0
     * minBprp : 0
     * minBvol : 200
     * minCAvol : 350
     * minCcur : 0
     * minCpap : 0
     * minCppf : 0
     * minCprp : 0
     * minCvol : 200
     * minTpap : 0
     * minTppf : 0
     * minTprp : 0
     * number : 0
     * sn : 0
     */

    private DeviceLocationPojoBean deviceLocationPojo;
    private int emmAddress;
    private int emmId;
    private String emmName;
    private String emmOrder;
    private int intervaltime;
    private int ip;
    private String ipPort;
    private int maxABvol;
    private int maxAcur;
    private int maxApap;
    private int maxAppf;
    private int maxAprp;
    private int maxAvol;
    private int maxBCvol;
    private int maxBcur;
    private int maxBpap;
    private int maxBppf;
    private int maxBprp;
    private int maxBvol;
    private int maxCAvol;
    private int maxCcur;
    private int maxCpap;
    private int maxCppf;
    private int maxCprp;
    private int maxCvol;
    private int maxTpap;
    private int maxTppf;
    private int maxTprp;
    private int minABvol;
    private int minAcur;
    private int minApap;
    private int minAppf;
    private int minAprp;
    private int minAvol;
    private int minBCvol;
    private int minBcur;
    private int minBpap;
    private int minBppf;
    private int minBprp;
    private int minBvol;
    private int minCAvol;
    private int minCcur;
    private int minCpap;
    private int minCppf;
    private int minCprp;
    private int minCvol;
    private int minTpap;
    private int minTppf;
    private int minTprp;
    private int number;
    private int sn;

    public static ElectricMeterManageBean objectFromData(String str) {

        return new Gson().fromJson(str, ElectricMeterManageBean.class);
    }

    public static ElectricMeterManageBean objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), ElectricMeterManageBean.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<ElectricMeterManageBean> arrayElectricMeterManageBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<ElectricMeterManageBean>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public static List<ElectricMeterManageBean> arrayElectricMeterManageBeanFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<ElectricMeterManageBean>>() {
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

    public int getEmmAddress() {
        return emmAddress;
    }

    public void setEmmAddress(int emmAddress) {
        this.emmAddress = emmAddress;
    }

    public int getEmmId() {
        return emmId;
    }

    public void setEmmId(int emmId) {
        this.emmId = emmId;
    }

    public String getEmmName() {
        return emmName;
    }

    public void setEmmName(String emmName) {
        this.emmName = emmName;
    }

    public String getEmmOrder() {
        return emmOrder;
    }

    public void setEmmOrder(String emmOrder) {
        this.emmOrder = emmOrder;
    }

    public int getIntervaltime() {
        return intervaltime;
    }

    public void setIntervaltime(int intervaltime) {
        this.intervaltime = intervaltime;
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

    public int getMaxABvol() {
        return maxABvol;
    }

    public void setMaxABvol(int maxABvol) {
        this.maxABvol = maxABvol;
    }

    public int getMaxAcur() {
        return maxAcur;
    }

    public void setMaxAcur(int maxAcur) {
        this.maxAcur = maxAcur;
    }

    public int getMaxApap() {
        return maxApap;
    }

    public void setMaxApap(int maxApap) {
        this.maxApap = maxApap;
    }

    public int getMaxAppf() {
        return maxAppf;
    }

    public void setMaxAppf(int maxAppf) {
        this.maxAppf = maxAppf;
    }

    public int getMaxAprp() {
        return maxAprp;
    }

    public void setMaxAprp(int maxAprp) {
        this.maxAprp = maxAprp;
    }

    public int getMaxAvol() {
        return maxAvol;
    }

    public void setMaxAvol(int maxAvol) {
        this.maxAvol = maxAvol;
    }

    public int getMaxBCvol() {
        return maxBCvol;
    }

    public void setMaxBCvol(int maxBCvol) {
        this.maxBCvol = maxBCvol;
    }

    public int getMaxBcur() {
        return maxBcur;
    }

    public void setMaxBcur(int maxBcur) {
        this.maxBcur = maxBcur;
    }

    public int getMaxBpap() {
        return maxBpap;
    }

    public void setMaxBpap(int maxBpap) {
        this.maxBpap = maxBpap;
    }

    public int getMaxBppf() {
        return maxBppf;
    }

    public void setMaxBppf(int maxBppf) {
        this.maxBppf = maxBppf;
    }

    public int getMaxBprp() {
        return maxBprp;
    }

    public void setMaxBprp(int maxBprp) {
        this.maxBprp = maxBprp;
    }

    public int getMaxBvol() {
        return maxBvol;
    }

    public void setMaxBvol(int maxBvol) {
        this.maxBvol = maxBvol;
    }

    public int getMaxCAvol() {
        return maxCAvol;
    }

    public void setMaxCAvol(int maxCAvol) {
        this.maxCAvol = maxCAvol;
    }

    public int getMaxCcur() {
        return maxCcur;
    }

    public void setMaxCcur(int maxCcur) {
        this.maxCcur = maxCcur;
    }

    public int getMaxCpap() {
        return maxCpap;
    }

    public void setMaxCpap(int maxCpap) {
        this.maxCpap = maxCpap;
    }

    public int getMaxCppf() {
        return maxCppf;
    }

    public void setMaxCppf(int maxCppf) {
        this.maxCppf = maxCppf;
    }

    public int getMaxCprp() {
        return maxCprp;
    }

    public void setMaxCprp(int maxCprp) {
        this.maxCprp = maxCprp;
    }

    public int getMaxCvol() {
        return maxCvol;
    }

    public void setMaxCvol(int maxCvol) {
        this.maxCvol = maxCvol;
    }

    public int getMaxTpap() {
        return maxTpap;
    }

    public void setMaxTpap(int maxTpap) {
        this.maxTpap = maxTpap;
    }

    public int getMaxTppf() {
        return maxTppf;
    }

    public void setMaxTppf(int maxTppf) {
        this.maxTppf = maxTppf;
    }

    public int getMaxTprp() {
        return maxTprp;
    }

    public void setMaxTprp(int maxTprp) {
        this.maxTprp = maxTprp;
    }

    public int getMinABvol() {
        return minABvol;
    }

    public void setMinABvol(int minABvol) {
        this.minABvol = minABvol;
    }

    public int getMinAcur() {
        return minAcur;
    }

    public void setMinAcur(int minAcur) {
        this.minAcur = minAcur;
    }

    public int getMinApap() {
        return minApap;
    }

    public void setMinApap(int minApap) {
        this.minApap = minApap;
    }

    public int getMinAppf() {
        return minAppf;
    }

    public void setMinAppf(int minAppf) {
        this.minAppf = minAppf;
    }

    public int getMinAprp() {
        return minAprp;
    }

    public void setMinAprp(int minAprp) {
        this.minAprp = minAprp;
    }

    public int getMinAvol() {
        return minAvol;
    }

    public void setMinAvol(int minAvol) {
        this.minAvol = minAvol;
    }

    public int getMinBCvol() {
        return minBCvol;
    }

    public void setMinBCvol(int minBCvol) {
        this.minBCvol = minBCvol;
    }

    public int getMinBcur() {
        return minBcur;
    }

    public void setMinBcur(int minBcur) {
        this.minBcur = minBcur;
    }

    public int getMinBpap() {
        return minBpap;
    }

    public void setMinBpap(int minBpap) {
        this.minBpap = minBpap;
    }

    public int getMinBppf() {
        return minBppf;
    }

    public void setMinBppf(int minBppf) {
        this.minBppf = minBppf;
    }

    public int getMinBprp() {
        return minBprp;
    }

    public void setMinBprp(int minBprp) {
        this.minBprp = minBprp;
    }

    public int getMinBvol() {
        return minBvol;
    }

    public void setMinBvol(int minBvol) {
        this.minBvol = minBvol;
    }

    public int getMinCAvol() {
        return minCAvol;
    }

    public void setMinCAvol(int minCAvol) {
        this.minCAvol = minCAvol;
    }

    public int getMinCcur() {
        return minCcur;
    }

    public void setMinCcur(int minCcur) {
        this.minCcur = minCcur;
    }

    public int getMinCpap() {
        return minCpap;
    }

    public void setMinCpap(int minCpap) {
        this.minCpap = minCpap;
    }

    public int getMinCppf() {
        return minCppf;
    }

    public void setMinCppf(int minCppf) {
        this.minCppf = minCppf;
    }

    public int getMinCprp() {
        return minCprp;
    }

    public void setMinCprp(int minCprp) {
        this.minCprp = minCprp;
    }

    public int getMinCvol() {
        return minCvol;
    }

    public void setMinCvol(int minCvol) {
        this.minCvol = minCvol;
    }

    public int getMinTpap() {
        return minTpap;
    }

    public void setMinTpap(int minTpap) {
        this.minTpap = minTpap;
    }

    public int getMinTppf() {
        return minTppf;
    }

    public void setMinTppf(int minTppf) {
        this.minTppf = minTppf;
    }

    public int getMinTprp() {
        return minTprp;
    }

    public void setMinTprp(int minTprp) {
        this.minTprp = minTprp;
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
