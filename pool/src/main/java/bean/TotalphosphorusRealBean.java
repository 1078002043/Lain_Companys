package bean;

import base.BaseBean;

/**
 * @ClassName: TotalphosphorusRealBean
 * @Description:
 * @Author: YIN LUO FEI
 * @Date: 2020/3/27 23:36
 */
public class TotalphosphorusRealBean extends BaseBean {


    /**
     * phosphorusId : 1
     * phosphorusName : 总磷水质
     * phosphorusAddress : 2
     * phosphorusData : 20.0
     * phosphorusMaxData : 50.0
     * phosphorusMinData : 10.0
     * intervalTime : 30
     * phosphorusStatus : 2
     * diId : 1
     * gId : 1
     */

    private int phosphorusId;
    private String phosphorusName;
    private int phosphorusAddress;
    private double phosphorusData;
    private double phosphorusMaxData;
    private double phosphorusMinData;
    private int intervalTime;
    private int phosphorusStatus;
    private int diId;
    private int gId;

    public int getPhosphorusId() {
        return phosphorusId;
    }

    public void setPhosphorusId(int phosphorusId) {
        this.phosphorusId = phosphorusId;
    }

    public String getPhosphorusName() {
        return phosphorusName;
    }

    public void setPhosphorusName(String phosphorusName) {
        this.phosphorusName = phosphorusName;
    }

    public int getPhosphorusAddress() {
        return phosphorusAddress;
    }

    public void setPhosphorusAddress(int phosphorusAddress) {
        this.phosphorusAddress = phosphorusAddress;
    }

    public double getPhosphorusData() {
        return phosphorusData;
    }

    public void setPhosphorusData(double phosphorusData) {
        this.phosphorusData = phosphorusData;
    }

    public double getPhosphorusMaxData() {
        return phosphorusMaxData;
    }

    public void setPhosphorusMaxData(double phosphorusMaxData) {
        this.phosphorusMaxData = phosphorusMaxData;
    }

    public double getPhosphorusMinData() {
        return phosphorusMinData;
    }

    public void setPhosphorusMinData(double phosphorusMinData) {
        this.phosphorusMinData = phosphorusMinData;
    }

    public int getIntervalTime() {
        return intervalTime;
    }

    public void setIntervalTime(int intervalTime) {
        this.intervalTime = intervalTime;
    }

    public int getPhosphorusStatus() {
        return phosphorusStatus;
    }

    public void setPhosphorusStatus(int phosphorusStatus) {
        this.phosphorusStatus = phosphorusStatus;
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
