package bean;

import base.BaseBean;

/**
 * @ClassName: CyaninRealBean
 * @Description: 蓝藻素-实时
 * @Author: YIN LUO FEI
 * @Date: 2020/3/27 23:20
 */
public class CyaninRealBean extends BaseBean {


    /**
     * cyaninId : 1
     * cyaninName : 蓝藻素
     * cyaninAddress : 0
     * cyaninData : 8.0
     * cyaninMaxData : 0.0
     * cyaninMinData : 0.0
     * temp : 10.0
     * maxTemp : 0.0
     * minTemp : 0.0
     * intervalTime : 0
     * cyaninStatus : 2
     * diId : 0
     * gId : 0
     */

    private int cyaninId;
    private String cyaninName;
    private int cyaninAddress;
    private double cyaninData;
    private double cyaninMaxData;
    private double cyaninMinData;
    private double temp;
    private double maxTemp;
    private double minTemp;
    private int intervalTime;
    private int cyaninStatus;
    private int diId;
    private int gId;

    public int getCyaninId() {
        return cyaninId;
    }

    public void setCyaninId(int cyaninId) {
        this.cyaninId = cyaninId;
    }

    public String getCyaninName() {
        return cyaninName;
    }

    public void setCyaninName(String cyaninName) {
        this.cyaninName = cyaninName;
    }

    public int getCyaninAddress() {
        return cyaninAddress;
    }

    public void setCyaninAddress(int cyaninAddress) {
        this.cyaninAddress = cyaninAddress;
    }

    public double getCyaninData() {
        return cyaninData;
    }

    public void setCyaninData(double cyaninData) {
        this.cyaninData = cyaninData;
    }

    public double getCyaninMaxData() {
        return cyaninMaxData;
    }

    public void setCyaninMaxData(double cyaninMaxData) {
        this.cyaninMaxData = cyaninMaxData;
    }

    public double getCyaninMinData() {
        return cyaninMinData;
    }

    public void setCyaninMinData(double cyaninMinData) {
        this.cyaninMinData = cyaninMinData;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public double getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(double maxTemp) {
        this.maxTemp = maxTemp;
    }

    public double getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(double minTemp) {
        this.minTemp = minTemp;
    }

    public int getIntervalTime() {
        return intervalTime;
    }

    public void setIntervalTime(int intervalTime) {
        this.intervalTime = intervalTime;
    }

    public int getCyaninStatus() {
        return cyaninStatus;
    }

    public void setCyaninStatus(int cyaninStatus) {
        this.cyaninStatus = cyaninStatus;
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
