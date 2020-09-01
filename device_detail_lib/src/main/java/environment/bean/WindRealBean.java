package environment.bean;

import base.BaseBean;

/**
 * @ClassName: WindRealBean
 * @Description:
 * @Author: YIN LUO FEI
 * @Date: 2020/3/28 13:31
 */
public class WindRealBean extends BaseBean {


    /**
     * windSpeedId : 1
     * windSpeedName : 风速1
     * windSpeedAddress : 0
     * windSpeedData : 0.0
     * windSpeedMaxData : 0.0
     * windSpeedMinData : 0.0
     * intervalTime : 0
     * windSpeedStatus : 2
     * diId : 0
     * gId : 0
     */

    private int windSpeedId;
    private String windSpeedName;
    private int windSpeedAddress;
    private double windSpeedData;
    private double windSpeedMaxData;
    private double windSpeedMinData;
    private int intervalTime;
    private int windSpeedStatus;
    private int diId;
    private int gId;

    public int getWindSpeedId() {
        return windSpeedId;
    }

    public void setWindSpeedId(int windSpeedId) {
        this.windSpeedId = windSpeedId;
    }

    public String getWindSpeedName() {
        return windSpeedName;
    }

    public void setWindSpeedName(String windSpeedName) {
        this.windSpeedName = windSpeedName;
    }

    public int getWindSpeedAddress() {
        return windSpeedAddress;
    }

    public void setWindSpeedAddress(int windSpeedAddress) {
        this.windSpeedAddress = windSpeedAddress;
    }

    public double getWindSpeedData() {
        return windSpeedData;
    }

    public void setWindSpeedData(double windSpeedData) {
        this.windSpeedData = windSpeedData;
    }

    public double getWindSpeedMaxData() {
        return windSpeedMaxData;
    }

    public void setWindSpeedMaxData(double windSpeedMaxData) {
        this.windSpeedMaxData = windSpeedMaxData;
    }

    public double getWindSpeedMinData() {
        return windSpeedMinData;
    }

    public void setWindSpeedMinData(double windSpeedMinData) {
        this.windSpeedMinData = windSpeedMinData;
    }

    public int getIntervalTime() {
        return intervalTime;
    }

    public void setIntervalTime(int intervalTime) {
        this.intervalTime = intervalTime;
    }

    public int getWindSpeedStatus() {
        return windSpeedStatus;
    }

    public void setWindSpeedStatus(int windSpeedStatus) {
        this.windSpeedStatus = windSpeedStatus;
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
