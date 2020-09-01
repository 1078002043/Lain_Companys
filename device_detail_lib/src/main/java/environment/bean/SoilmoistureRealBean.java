package environment.bean;

import base.BaseBean;

/**
 * @ClassName: SoilmoistureRealBean
 * @Description:
 * @Author: YIN LUO FEI
 * @Date: 2020/3/28 14:33
 */
public class SoilmoistureRealBean extends BaseBean {


    /**
     * dId : 0
     * name : null
     * isShow : 0
     * diId : 0
     * diAddress : null
     * diPort : 0
     * diIsConnect : 0
     * diOperate : 0
     * gId : 0
     * ehmId : 117
     * ehmDeviceAddress : 0
     * ehmDeviceFunction : 0
     * ehmDeviceName : 温湿度1
     * ehmTem : 34.0
     * ehmHum : 54.0
     * ehmMaxTem : 0.0
     * ehmMinTem : 0.0
     * ehmMaxHum : 0.0
     * ehmMinHum : 0.0
     * ehmStatus : 0
     * intervalTime : 0
     */

    private int dId;
    private Object name;
    private int isShow;
    private int diId;
    private Object diAddress;
    private int diPort;
    private int diIsConnect;
    private int diOperate;
    private int gId;
    private int ehmId;
    private int ehmDeviceAddress;
    private int ehmDeviceFunction;
    private String ehmDeviceName;
    private double ehmTem;
    private double ehmHum;
    private double ehmMaxTem;
    private double ehmMinTem;
    private double ehmMaxHum;
    private double ehmMinHum;
    private int ehmStatus;
    private int intervalTime;

    public int getDId() {
        return dId;
    }

    public void setDId(int dId) {
        this.dId = dId;
    }

    public Object getName() {
        return name;
    }

    public void setName(Object name) {
        this.name = name;
    }

    public int getIsShow() {
        return isShow;
    }

    public void setIsShow(int isShow) {
        this.isShow = isShow;
    }

    public int getDiId() {
        return diId;
    }

    public void setDiId(int diId) {
        this.diId = diId;
    }

    public Object getDiAddress() {
        return diAddress;
    }

    public void setDiAddress(Object diAddress) {
        this.diAddress = diAddress;
    }

    public int getDiPort() {
        return diPort;
    }

    public void setDiPort(int diPort) {
        this.diPort = diPort;
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

    public int getGId() {
        return gId;
    }

    public void setGId(int gId) {
        this.gId = gId;
    }

    public int getEhmId() {
        return ehmId;
    }

    public void setEhmId(int ehmId) {
        this.ehmId = ehmId;
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

    public double getEhmTem() {
        return ehmTem;
    }

    public void setEhmTem(double ehmTem) {
        this.ehmTem = ehmTem;
    }

    public double getEhmHum() {
        return ehmHum;
    }

    public void setEhmHum(double ehmHum) {
        this.ehmHum = ehmHum;
    }

    public double getEhmMaxTem() {
        return ehmMaxTem;
    }

    public void setEhmMaxTem(double ehmMaxTem) {
        this.ehmMaxTem = ehmMaxTem;
    }

    public double getEhmMinTem() {
        return ehmMinTem;
    }

    public void setEhmMinTem(double ehmMinTem) {
        this.ehmMinTem = ehmMinTem;
    }

    public double getEhmMaxHum() {
        return ehmMaxHum;
    }

    public void setEhmMaxHum(double ehmMaxHum) {
        this.ehmMaxHum = ehmMaxHum;
    }

    public double getEhmMinHum() {
        return ehmMinHum;
    }

    public void setEhmMinHum(double ehmMinHum) {
        this.ehmMinHum = ehmMinHum;
    }

    public int getEhmStatus() {
        return ehmStatus;
    }

    public void setEhmStatus(int ehmStatus) {
        this.ehmStatus = ehmStatus;
    }

    public int getIntervalTime() {
        return intervalTime;
    }

    public void setIntervalTime(int intervalTime) {
        this.intervalTime = intervalTime;
    }
}
