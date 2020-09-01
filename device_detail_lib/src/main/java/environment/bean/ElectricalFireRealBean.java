package environment.bean;

import base.BaseBean;

/**
 * @ClassName: ElectricalFireRealBean
 * @Description:
 * @Author: YIN LUO FEI
 * @Date: 2020/3/28 13:12
 */
public class ElectricalFireRealBean extends BaseBean {


    /**
     * electricalFireId : 2
     * electricalFireName : 剩余电流
     * electricalFireAddress : 0
     * temp : 12.0
     * maxTemp : 0.0
     * minTemp : 0.0
     * current : 22.0
     * maxCurrent : 0.0
     * minCurrent : 0.0
     * electricalFireStatus : 2
     * intervalTime : 0
     * diId : 0
     * gId : 0
     */

    private int electricalFireId;
    private String electricalFireName;
    private int electricalFireAddress;
    private double temp;
    private double maxTemp;
    private double minTemp;
    private double current;
    private double maxCurrent;
    private double minCurrent;
    private int electricalFireStatus;
    private int intervalTime;
    private int diId;
    private int gId;

    public int getElectricalFireId() {
        return electricalFireId;
    }

    public void setElectricalFireId(int electricalFireId) {
        this.electricalFireId = electricalFireId;
    }

    public String getElectricalFireName() {
        return electricalFireName;
    }

    public void setElectricalFireName(String electricalFireName) {
        this.electricalFireName = electricalFireName;
    }

    public int getElectricalFireAddress() {
        return electricalFireAddress;
    }

    public void setElectricalFireAddress(int electricalFireAddress) {
        this.electricalFireAddress = electricalFireAddress;
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

    public double getCurrent() {
        return current;
    }

    public void setCurrent(double current) {
        this.current = current;
    }

    public double getMaxCurrent() {
        return maxCurrent;
    }

    public void setMaxCurrent(double maxCurrent) {
        this.maxCurrent = maxCurrent;
    }

    public double getMinCurrent() {
        return minCurrent;
    }

    public void setMinCurrent(double minCurrent) {
        this.minCurrent = minCurrent;
    }

    public int getElectricalFireStatus() {
        return electricalFireStatus;
    }

    public void setElectricalFireStatus(int electricalFireStatus) {
        this.electricalFireStatus = electricalFireStatus;
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
