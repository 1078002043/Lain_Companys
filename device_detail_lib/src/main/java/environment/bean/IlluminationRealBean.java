package environment.bean;

import base.BaseBean;

/**
 * @ClassName: IlluminationRealBean
 * @Description: 光照强度-实时
 * @Author: YIN LUO FEI
 * @Date: 2020/3/28 10:50
 */
public class IlluminationRealBean extends BaseBean {


    /**
     * lightId : 1
     * lightName : 照度传感器1
     * lightAddress : 3
     * lightData : 0.0
     * lightMaxData : 54.0
     * lightMinData : 12.0
     * intervalTime : 23
     * lightStatus : 0
     * diId : 56
     * gId : 2
     */

    private int lightId;
    private String lightName;
    private int lightAddress;
    private double lightData;
    private double lightMaxData;
    private double lightMinData;
    private int intervalTime;
    private int lightStatus;
    private int diId;
    private int gId;

    public int getLightId() {
        return lightId;
    }

    public void setLightId(int lightId) {
        this.lightId = lightId;
    }

    public String getLightName() {
        return lightName;
    }

    public void setLightName(String lightName) {
        this.lightName = lightName;
    }

    public int getLightAddress() {
        return lightAddress;
    }

    public void setLightAddress(int lightAddress) {
        this.lightAddress = lightAddress;
    }

    public double getLightData() {
        return lightData;
    }

    public void setLightData(double lightData) {
        this.lightData = lightData;
    }

    public double getLightMaxData() {
        return lightMaxData;
    }

    public void setLightMaxData(double lightMaxData) {
        this.lightMaxData = lightMaxData;
    }

    public double getLightMinData() {
        return lightMinData;
    }

    public void setLightMinData(double lightMinData) {
        this.lightMinData = lightMinData;
    }

    public int getIntervalTime() {
        return intervalTime;
    }

    public void setIntervalTime(int intervalTime) {
        this.intervalTime = intervalTime;
    }

    public int getLightStatus() {
        return lightStatus;
    }

    public void setLightStatus(int lightStatus) {
        this.lightStatus = lightStatus;
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
