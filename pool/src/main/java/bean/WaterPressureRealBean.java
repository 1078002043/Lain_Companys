package bean;

import base.BaseBean;

public class WaterPressureRealBean extends BaseBean {

    /**
     * waterPressureId : 1
     * waterPressureName : 水压力
     * waterPressureAddress : 1
     * waterPressureData : 0.0
     * waterPressureMaxData : 22.0
     * waterPressureMinData : 1.0
     * intervalTime : 21
     * waterPressureStatus : 2
     * diId : 70
     * gId : 2
     */

    private int waterPressureId;
    private String waterPressureName;
    private int waterPressureAddress;
    private double waterPressureData;
    private double waterPressureMaxData;
    private double waterPressureMinData;
    private int intervalTime;
    private int waterPressureStatus;
    private int diId;
    private int gId;

    public int getWaterPressureId() {
        return waterPressureId;
    }

    public void setWaterPressureId(int waterPressureId) {
        this.waterPressureId = waterPressureId;
    }

    public String getWaterPressureName() {
        return waterPressureName;
    }

    public void setWaterPressureName(String waterPressureName) {
        this.waterPressureName = waterPressureName;
    }

    public int getWaterPressureAddress() {
        return waterPressureAddress;
    }

    public void setWaterPressureAddress(int waterPressureAddress) {
        this.waterPressureAddress = waterPressureAddress;
    }

    public double getWaterPressureData() {
        return waterPressureData;
    }

    public void setWaterPressureData(double waterPressureData) {
        this.waterPressureData = waterPressureData;
    }

    public double getWaterPressureMaxData() {
        return waterPressureMaxData;
    }

    public void setWaterPressureMaxData(double waterPressureMaxData) {
        this.waterPressureMaxData = waterPressureMaxData;
    }

    public double getWaterPressureMinData() {
        return waterPressureMinData;
    }

    public void setWaterPressureMinData(double waterPressureMinData) {
        this.waterPressureMinData = waterPressureMinData;
    }

    public int getIntervalTime() {
        return intervalTime;
    }

    public void setIntervalTime(int intervalTime) {
        this.intervalTime = intervalTime;
    }

    public int getWaterPressureStatus() {
        return waterPressureStatus;
    }

    public void setWaterPressureStatus(int waterPressureStatus) {
        this.waterPressureStatus = waterPressureStatus;
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
