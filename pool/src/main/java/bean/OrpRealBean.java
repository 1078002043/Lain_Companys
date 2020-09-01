package bean;

import base.BaseBean;

/**
 * @ClassName: OrpRealBean
 * @Description: ORP变送器-实时
 * @Author: YIN LUO FEI
 * @Date: 2020/3/28 0:40
 */
public class OrpRealBean extends BaseBean {


    /**
     * orpMeterId : 1
     * orpMeterName : ORP变送器
     * orpMeterAddress : 0
     * orpMeterData : 22.0
     * orpMeterMaxData : 0.0
     * orpMeterMinData : 0.0
     * orpMeterStatus : 2
     * intervalTime : 0
     * diId : 0
     * gId : 0
     */

    private int orpMeterId;
    private String orpMeterName;
    private int orpMeterAddress;
    private double orpMeterData;
    private double orpMeterMaxData;
    private double orpMeterMinData;
    private int orpMeterStatus;
    private int intervalTime;
    private int diId;
    private int gId;

    public int getOrpMeterId() {
        return orpMeterId;
    }

    public void setOrpMeterId(int orpMeterId) {
        this.orpMeterId = orpMeterId;
    }

    public String getOrpMeterName() {
        return orpMeterName;
    }

    public void setOrpMeterName(String orpMeterName) {
        this.orpMeterName = orpMeterName;
    }

    public int getOrpMeterAddress() {
        return orpMeterAddress;
    }

    public void setOrpMeterAddress(int orpMeterAddress) {
        this.orpMeterAddress = orpMeterAddress;
    }

    public double getOrpMeterData() {
        return orpMeterData;
    }

    public void setOrpMeterData(double orpMeterData) {
        this.orpMeterData = orpMeterData;
    }

    public double getOrpMeterMaxData() {
        return orpMeterMaxData;
    }

    public void setOrpMeterMaxData(double orpMeterMaxData) {
        this.orpMeterMaxData = orpMeterMaxData;
    }

    public double getOrpMeterMinData() {
        return orpMeterMinData;
    }

    public void setOrpMeterMinData(double orpMeterMinData) {
        this.orpMeterMinData = orpMeterMinData;
    }

    public int getOrpMeterStatus() {
        return orpMeterStatus;
    }

    public void setOrpMeterStatus(int orpMeterStatus) {
        this.orpMeterStatus = orpMeterStatus;
    }

    public int getIntervalTime() {
        return intervalTime;
    }

    public void setIntervalTime(int intervalTime) {
        this.intervalTime = intervalTime;
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
}
