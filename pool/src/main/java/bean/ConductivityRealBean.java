package bean;

import base.BaseBean;

/**
 *  电导率 实时数据
 */
public class ConductivityRealBean extends BaseBean {


    /**
     * conId : 1
     * conName : 电导1
     * conAddress : 1
     * conData : 10.0
     * conMaxData : 2.0
     * conMinData : 40.0
     * intervalTime : 12
     * conStatus : 2
     * diId : 48
     * gId : 2
     */

    private int conId;
    private String conName;
    private int conAddress;
    private double conData;
    private double conMaxData;
    private double conMinData;
    private int intervalTime;
    private int conStatus;
    private int diId;
    private int gId;

    public int getConId() {
        return conId;
    }

    public void setConId(int conId) {
        this.conId = conId;
    }

    public String getConName() {
        return conName;
    }

    public void setConName(String conName) {
        this.conName = conName;
    }

    public int getConAddress() {
        return conAddress;
    }

    public void setConAddress(int conAddress) {
        this.conAddress = conAddress;
    }

    public double getConData() {
        return conData;
    }

    public void setConData(double conData) {
        this.conData = conData;
    }

    public double getConMaxData() {
        return conMaxData;
    }

    public void setConMaxData(double conMaxData) {
        this.conMaxData = conMaxData;
    }

    public double getConMinData() {
        return conMinData;
    }

    public void setConMinData(double conMinData) {
        this.conMinData = conMinData;
    }

    public int getIntervalTime() {
        return intervalTime;
    }

    public void setIntervalTime(int intervalTime) {
        this.intervalTime = intervalTime;
    }

    public int getConStatus() {
        return conStatus;
    }

    public void setConStatus(int conStatus) {
        this.conStatus = conStatus;
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
