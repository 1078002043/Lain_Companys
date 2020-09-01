package bean;

import base.BaseBean;

/**
 * 水流量
 */
public class DischargeRealBean extends BaseBean {


    /**
     * waterFlowId : 2
     * waterFlowName : 水流量检测
     * waterFlowAddress : 1
     * waterFlowData : 11.0
     * waterFlowMaxData : 20.0
     * waterFlowMinData : 111.0
     * intervalTime : 21
     * waterFlowStatus : 2
     * diId : 53
     * gId : 2
     */

    private int waterFlowId;
    private String waterFlowName;
    private int waterFlowAddress;
    private double waterFlowData;
    private double waterFlowMaxData;
    private double waterFlowMinData;
    private int intervalTime;
    private int waterFlowStatus;
    private int diId;
    private int gId;

    public int getWaterFlowId() {
        return waterFlowId;
    }

    public void setWaterFlowId(int waterFlowId) {
        this.waterFlowId = waterFlowId;
    }

    public String getWaterFlowName() {
        return waterFlowName;
    }

    public void setWaterFlowName(String waterFlowName) {
        this.waterFlowName = waterFlowName;
    }

    public int getWaterFlowAddress() {
        return waterFlowAddress;
    }

    public void setWaterFlowAddress(int waterFlowAddress) {
        this.waterFlowAddress = waterFlowAddress;
    }

    public double getWaterFlowData() {
        return waterFlowData;
    }

    public void setWaterFlowData(double waterFlowData) {
        this.waterFlowData = waterFlowData;
    }

    public double getWaterFlowMaxData() {
        return waterFlowMaxData;
    }

    public void setWaterFlowMaxData(double waterFlowMaxData) {
        this.waterFlowMaxData = waterFlowMaxData;
    }

    public double getWaterFlowMinData() {
        return waterFlowMinData;
    }

    public void setWaterFlowMinData(double waterFlowMinData) {
        this.waterFlowMinData = waterFlowMinData;
    }

    public int getIntervalTime() {
        return intervalTime;
    }

    public void setIntervalTime(int intervalTime) {
        this.intervalTime = intervalTime;
    }

    public int getWaterFlowStatus() {
        return waterFlowStatus;
    }

    public void setWaterFlowStatus(int waterFlowStatus) {
        this.waterFlowStatus = waterFlowStatus;
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
