package bean;

import base.BaseBean;

/**
 * 温湿度实时数据
 *
 * @author Create by Yin Luo Fei
 * @version 0.1
 * @Time 2019/8/12 10 : 43
 */
public class TemperatureBean extends BaseBean {


    /**
     * dId : 0
     * diId : 76
     * diIsConnect : 0
     * diOperate : 0
     * diPort : 0
     * ehmDeviceAddress : 1
     * ehmDeviceFunction : 4
     * ehmDeviceName : 温湿度3
     * ehmHum : 70.4
     * ehmId : 124
     * ehmMaxHum : 90
     * ehmMaxTem : 70
     * ehmMinHum : 8
     * ehmMinTem : 10
     * ehmStatus : 1
     * ehmTem : 28.9
     * gId : 0
     * intervalTime : 30
     * isShow : 0
     */

    private int dId;
    private int diId;
    private int diIsConnect;
    private int diOperate;
    private int diPort;
    private int ehmDeviceAddress;
    private int ehmDeviceFunction;
    private String ehmDeviceName;
    private double ehmHum;
    private int ehmId;
    private float ehmMaxHum;
    private float ehmMaxTem;
    private float ehmMinHum;
    private float ehmMinTem;
    private int ehmStatus;
    private double ehmTem;
    private int gId;
    private int intervalTime;
    private int isShow;

    public int getDId() {
        return dId;
    }

    public void setDId(int dId) {
        this.dId = dId;
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

    public int getEhmDeviceAddress() {
        return ehmDeviceAddress;
    }

    public void setEhmDeviceAddress(int ehmDeviceAddress) {
        this.ehmDeviceAddress = ehmDeviceAddress;
    }

    public int getEhmDeviceFunction() {
        return ehmDeviceFunction;
    }

    public void setEhmDeviceFunction(int ehmDeviceFunction) {
        this.ehmDeviceFunction = ehmDeviceFunction;
    }

    public String getEhmDeviceName() {
        return ehmDeviceName;
    }

    public void setEhmDeviceName(String ehmDeviceName) {
        this.ehmDeviceName = ehmDeviceName;
    }

    public double getEhmHum() {
        return ehmHum;
    }

    public void setEhmHum(double ehmHum) {
        this.ehmHum = ehmHum;
    }

    public int getEhmId() {
        return ehmId;
    }

    public void setEhmId(int ehmId) {
        this.ehmId = ehmId;
    }

    public float getEhmMaxHum() {
        return ehmMaxHum;
    }

    public void setEhmMaxHum(int ehmMaxHum) {
        this.ehmMaxHum = ehmMaxHum;
    }

    public float getEhmMaxTem() {
        return ehmMaxTem;
    }

    public void setEhmMaxTem(int ehmMaxTem) {
        this.ehmMaxTem = ehmMaxTem;
    }

    public float getEhmMinHum() {
        return ehmMinHum;
    }

    public void setEhmMinHum(int ehmMinHum) {
        this.ehmMinHum = ehmMinHum;
    }

    public float getEhmMinTem() {
        return ehmMinTem;
    }

    public void setEhmMinTem(int ehmMinTem) {
        this.ehmMinTem = ehmMinTem;
    }

    public int getEhmStatus() {
        return ehmStatus;
    }

    public void setEhmStatus(int ehmStatus) {
        this.ehmStatus = ehmStatus;
    }

    public double getEhmTem() {
        return ehmTem;
    }

    public void setEhmTem(double ehmTem) {
        this.ehmTem = ehmTem;
    }

    public int getGId() {
        return gId;
    }

    public void setGId(int gId) {
        this.gId = gId;
    }

    public int getIntervalTime() {
        return intervalTime;
    }

    public void setIntervalTime(int intervalTime) {
        this.intervalTime = intervalTime;
    }

    public int getIsShow() {
        return isShow;
    }

    public void setIsShow(int isShow) {
        this.isShow = isShow;
    }
}
