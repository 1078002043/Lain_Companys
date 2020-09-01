package bean;

import base.BaseBean;

/**
 * @ClassName: ChlorophyllRealBean
 * @Description: 叶绿素-实时
 * @Author: YIN LUO FEI
 * @Date: 2020/3/27 22:43
 */
public class ChlorophyllRealBean extends BaseBean {


    /**
     * chlorophyllId : 1
     * chlorophyllName : 叶绿素
     * chlorophyllAddress : 0
     * chlorophyllData : 5.0
     * chlorophyllMaxData : 0.0
     * chlorophyllMinData : 0.0
     * temp : 10.0
     * maxTemp : 0.0
     * minTemp : 0.0
     * intervalTime : 0
     * chlorophyllStatus : 2
     * diId : 0
     * gId : 0
     */

    private int chlorophyllId;
    private String chlorophyllName;
    private int chlorophyllAddress;
    private double chlorophyllData;
    private double chlorophyllMaxData;
    private double chlorophyllMinData;
    private double temp;
    private double maxTemp;
    private double minTemp;
    private int intervalTime;
    private int chlorophyllStatus;
    private int diId;
    private int gId;

    public int getChlorophyllId() {
        return chlorophyllId;
    }

    public void setChlorophyllId(int chlorophyllId) {
        this.chlorophyllId = chlorophyllId;
    }

    public String getChlorophyllName() {
        return chlorophyllName;
    }

    public void setChlorophyllName(String chlorophyllName) {
        this.chlorophyllName = chlorophyllName;
    }

    public int getChlorophyllAddress() {
        return chlorophyllAddress;
    }

    public void setChlorophyllAddress(int chlorophyllAddress) {
        this.chlorophyllAddress = chlorophyllAddress;
    }

    public double getChlorophyllData() {
        return chlorophyllData;
    }

    public void setChlorophyllData(double chlorophyllData) {
        this.chlorophyllData = chlorophyllData;
    }

    public double getChlorophyllMaxData() {
        return chlorophyllMaxData;
    }

    public void setChlorophyllMaxData(double chlorophyllMaxData) {
        this.chlorophyllMaxData = chlorophyllMaxData;
    }

    public double getChlorophyllMinData() {
        return chlorophyllMinData;
    }

    public void setChlorophyllMinData(double chlorophyllMinData) {
        this.chlorophyllMinData = chlorophyllMinData;
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

    public int getChlorophyllStatus() {
        return chlorophyllStatus;
    }

    public void setChlorophyllStatus(int chlorophyllStatus) {
        this.chlorophyllStatus = chlorophyllStatus;
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
