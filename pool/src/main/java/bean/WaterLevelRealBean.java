package bean;

import base.BaseBean;

/**
 * 水位检测
 */
public class WaterLevelRealBean extends BaseBean {


    /**
     * waterLevelId : 1
     * waterLevelName : 水位计1
     * waterLevelAddress : 1
     * waterLevelData : 12.0
     * waterLevelMaxData : 45.0
     * waterLevelMinData : 9.1
     * intervalTime : 12
     * waterLevelStatus : 2
     * diId : 49
     * gId : 2
     */

    private int waterLevelId;
    private String waterLevelName;
    private int waterLevelAddress;
    private double waterLevelData;
    private double waterLevelMaxData;
    private double waterLevelMinData;
    private int intervalTime;
    private int waterLevelStatus;
    private int diId;
    private int gId;

    public int getWaterLevelId() {
        return waterLevelId;
    }

    public void setWaterLevelId(int waterLevelId) {
        this.waterLevelId = waterLevelId;
    }

    public String getWaterLevelName() {
        return waterLevelName;
    }

    public void setWaterLevelName(String waterLevelName) {
        this.waterLevelName = waterLevelName;
    }

    public int getWaterLevelAddress() {
        return waterLevelAddress;
    }

    public void setWaterLevelAddress(int waterLevelAddress) {
        this.waterLevelAddress = waterLevelAddress;
    }

    public double getWaterLevelData() {
        return waterLevelData;
    }

    public void setWaterLevelData(double waterLevelData) {
        this.waterLevelData = waterLevelData;
    }

    public double getWaterLevelMaxData() {
        return waterLevelMaxData;
    }

    public void setWaterLevelMaxData(double waterLevelMaxData) {
        this.waterLevelMaxData = waterLevelMaxData;
    }

    public double getWaterLevelMinData() {
        return waterLevelMinData;
    }

    public void setWaterLevelMinData(double waterLevelMinData) {
        this.waterLevelMinData = waterLevelMinData;
    }

    public int getIntervalTime() {
        return intervalTime;
    }

    public void setIntervalTime(int intervalTime) {
        this.intervalTime = intervalTime;
    }

    public int getWaterLevelStatus() {
        return waterLevelStatus;
    }

    public void setWaterLevelStatus(int waterLevelStatus) {
        this.waterLevelStatus = waterLevelStatus;
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
