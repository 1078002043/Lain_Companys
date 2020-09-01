package environment.bean;

import base.BaseBean;

/**
 * @ClassName: OutdoorTempRealBean
 * @Description: 室外温度变送器-实时
 * @Author: YIN LUO FEI
 * @Date: 2020/3/28 14:00
 */
public class OutdoorTempRealBean extends BaseBean {


    /**
     * dId : 39
     * name : null
     * isShow : 0
     * diId : 62
     * diAddress : 192.168.1.229
     * diPort : 6000
     * diIsConnect : 0
     * diOperate : 0
     * gId : 2
     * outdoorHumitureId : 1
     * outdoorHumitureDeviceAddress : 1
     * outdoorHumitureDeviceFunction : 1
     * outdoorHumitureDeviceName : 室外温湿度
     * outdoorHumitureTem : 12.0
     * outdoorHumitureHum : 22.0
     * outdoorHumitureMaxTem : 20.0
     * outdoorHumitureMinTem : 10.0
     * outdoorHumitureMaxHum : 20.0
     * outdoorHumitureMinHum : 10.0
     * outdoorHumitureStatus : 2
     * intervalTime : 1
     */

    private int dId;
    private Object name;
    private int isShow;
    private int diId;
    private String diAddress;
    private int diPort;
    private int diIsConnect;
    private int diOperate;
    private int gId;
    private int outdoorHumitureId;
    private int outdoorHumitureDeviceAddress;
    private int outdoorHumitureDeviceFunction;
    private String outdoorHumitureDeviceName;
    private double outdoorHumitureTem;
    private double outdoorHumitureHum;
    private double outdoorHumitureMaxTem;
    private double outdoorHumitureMinTem;
    private double outdoorHumitureMaxHum;
    private double outdoorHumitureMinHum;
    private int outdoorHumitureStatus;
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

    public String getDiAddress() {
        return diAddress;
    }

    public void setDiAddress(String diAddress) {
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

    public int getOutdoorHumitureId() {
        return outdoorHumitureId;
    }

    public void setOutdoorHumitureId(int outdoorHumitureId) {
        this.outdoorHumitureId = outdoorHumitureId;
    }

    public int getOutdoorHumitureDeviceAddress() {
        return outdoorHumitureDeviceAddress;
    }

    public void setOutdoorHumitureDeviceAddress(int outdoorHumitureDeviceAddress) {
        this.outdoorHumitureDeviceAddress = outdoorHumitureDeviceAddress;
    }

    public int getOutdoorHumitureDeviceFunction() {
        return outdoorHumitureDeviceFunction;
    }

    public void setOutdoorHumitureDeviceFunction(int outdoorHumitureDeviceFunction) {
        this.outdoorHumitureDeviceFunction = outdoorHumitureDeviceFunction;
    }

    public String getOutdoorHumitureDeviceName() {
        return outdoorHumitureDeviceName;
    }

    public void setOutdoorHumitureDeviceName(String outdoorHumitureDeviceName) {
        this.outdoorHumitureDeviceName = outdoorHumitureDeviceName;
    }

    public double getOutdoorHumitureTem() {
        return outdoorHumitureTem;
    }

    public void setOutdoorHumitureTem(double outdoorHumitureTem) {
        this.outdoorHumitureTem = outdoorHumitureTem;
    }

    public double getOutdoorHumitureHum() {
        return outdoorHumitureHum;
    }

    public void setOutdoorHumitureHum(double outdoorHumitureHum) {
        this.outdoorHumitureHum = outdoorHumitureHum;
    }

    public double getOutdoorHumitureMaxTem() {
        return outdoorHumitureMaxTem;
    }

    public void setOutdoorHumitureMaxTem(double outdoorHumitureMaxTem) {
        this.outdoorHumitureMaxTem = outdoorHumitureMaxTem;
    }

    public double getOutdoorHumitureMinTem() {
        return outdoorHumitureMinTem;
    }

    public void setOutdoorHumitureMinTem(double outdoorHumitureMinTem) {
        this.outdoorHumitureMinTem = outdoorHumitureMinTem;
    }

    public double getOutdoorHumitureMaxHum() {
        return outdoorHumitureMaxHum;
    }

    public void setOutdoorHumitureMaxHum(double outdoorHumitureMaxHum) {
        this.outdoorHumitureMaxHum = outdoorHumitureMaxHum;
    }

    public double getOutdoorHumitureMinHum() {
        return outdoorHumitureMinHum;
    }

    public void setOutdoorHumitureMinHum(double outdoorHumitureMinHum) {
        this.outdoorHumitureMinHum = outdoorHumitureMinHum;
    }

    public int getOutdoorHumitureStatus() {
        return outdoorHumitureStatus;
    }

    public void setOutdoorHumitureStatus(int outdoorHumitureStatus) {
        this.outdoorHumitureStatus = outdoorHumitureStatus;
    }

    public int getIntervalTime() {
        return intervalTime;
    }

    public void setIntervalTime(int intervalTime) {
        this.intervalTime = intervalTime;
    }
}
