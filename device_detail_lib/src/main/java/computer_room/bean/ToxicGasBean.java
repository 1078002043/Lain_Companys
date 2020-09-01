package computer_room.bean;

import base.BaseBean;

public class ToxicGasBean extends BaseBean {

    /**
     * ecmId : 1
     * ecmAddress : 1
     * ecmName : 1号有毒气体
     * ecmAlarmData : 0.77
     * ecmCurrentData : 0.5
     * ecmStatus : 2
     * diId : 42
     * gId : 2
     * intervalTime : 30
     */

    private int ecmId;
    private int ecmAddress;
    private String ecmName;
    private double ecmAlarmData;
    private double ecmCurrentData;
    private int ecmStatus;
    private int diId;
    private int gId;
    private int intervalTime;

    public int getEcmId() {
        return ecmId;
    }

    public void setEcmId(int ecmId) {
        this.ecmId = ecmId;
    }

    public int getEcmAddress() {
        return ecmAddress;
    }

    public void setEcmAddress(int ecmAddress) {
        this.ecmAddress = ecmAddress;
    }

    public String getEcmName() {
        return ecmName;
    }

    public void setEcmName(String ecmName) {
        this.ecmName = ecmName;
    }

    public double getEcmAlarmData() {
        return ecmAlarmData;
    }

    public void setEcmAlarmData(double ecmAlarmData) {
        this.ecmAlarmData = ecmAlarmData;
    }

    public double getEcmCurrentData() {
        return ecmCurrentData;
    }

    public void setEcmCurrentData(double ecmCurrentData) {
        this.ecmCurrentData = ecmCurrentData;
    }

    public int getEcmStatus() {
        return ecmStatus;
    }

    public void setEcmStatus(int ecmStatus) {
        this.ecmStatus = ecmStatus;
    }

    public int getDiId() {
        return diId;
    }

    public void setDiId(int diId) {
        this.diId = diId;
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
}
