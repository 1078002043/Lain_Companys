package bean;

import base.BaseBean;

/**
 * 水温检测 实时数据
 */
public class WaterTempRealBean extends BaseBean {


    /**
     * waterTempId : 3
     * waterTempName : 水温1
     * waterTempAddress : 1
     * waterTempData : 13.0
     * waterTempMaxData : 20.0
     * waterTempMinData : 11.1
     * intervalTime : 21
     * waterTempStatus : 2
     * diId : 52
     * gId : 2
     */

    private int waterTempId;
    private String waterTempName;
    private int waterTempAddress;
    private double waterTempData;
    private double waterTempMaxData;
    private double waterTempMinData;
    private int intervalTime;
    private int waterTempStatus;
    private int diId;
    private int gId;

    public int getWaterTempId() {
        return waterTempId;
    }

    public void setWaterTempId(int waterTempId) {
        this.waterTempId = waterTempId;
    }

    public String getWaterTempName() {
        return waterTempName;
    }

    public void setWaterTempName(String waterTempName) {
        this.waterTempName = waterTempName;
    }

    public int getWaterTempAddress() {
        return waterTempAddress;
    }

    public void setWaterTempAddress(int waterTempAddress) {
        this.waterTempAddress = waterTempAddress;
    }

    public double getWaterTempData() {
        return waterTempData;
    }

    public void setWaterTempData(double waterTempData) {
        this.waterTempData = waterTempData;
    }

    public double getWaterTempMaxData() {
        return waterTempMaxData;
    }

    public void setWaterTempMaxData(double waterTempMaxData) {
        this.waterTempMaxData = waterTempMaxData;
    }

    public double getWaterTempMinData() {
        return waterTempMinData;
    }

    public void setWaterTempMinData(double waterTempMinData) {
        this.waterTempMinData = waterTempMinData;
    }

    public int getIntervalTime() {
        return intervalTime;
    }

    public void setIntervalTime(int intervalTime) {
        this.intervalTime = intervalTime;
    }

    public int getWaterTempStatus() {
        return waterTempStatus;
    }

    public void setWaterTempStatus(int waterTempStatus) {
        this.waterTempStatus = waterTempStatus;
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
