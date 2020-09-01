package bean;

import base.BaseBean;

public class Ammonia2RealBean extends BaseBean {


    /**
     * ammoniaId : 1
     * ammoniaName : 氨气1
     * ammoniaAddress : 0
     * ammoniaData : 5.3
     * ammoniaMaxData : 0.0
     * ammoniaMinData : 0.0
     * intervalTime : 0
     * ammoniaStatus : 0
     * diId : 0
     * gId : 0
     */

    private int ammoniaId;
    private String ammoniaName;
    private int ammoniaAddress;
    private double ammoniaData;
    private double ammoniaMaxData;
    private double ammoniaMinData;
    private int intervalTime;
    private int ammoniaStatus;
    private int diId;
    private int gId;

    public int getAmmoniaId() {
        return ammoniaId;
    }

    public void setAmmoniaId(int ammoniaId) {
        this.ammoniaId = ammoniaId;
    }

    public String getAmmoniaName() {
        return ammoniaName;
    }

    public void setAmmoniaName(String ammoniaName) {
        this.ammoniaName = ammoniaName;
    }

    public int getAmmoniaAddress() {
        return ammoniaAddress;
    }

    public void setAmmoniaAddress(int ammoniaAddress) {
        this.ammoniaAddress = ammoniaAddress;
    }

    public double getAmmoniaData() {
        return ammoniaData;
    }

    public void setAmmoniaData(double ammoniaData) {
        this.ammoniaData = ammoniaData;
    }

    public double getAmmoniaMaxData() {
        return ammoniaMaxData;
    }

    public void setAmmoniaMaxData(double ammoniaMaxData) {
        this.ammoniaMaxData = ammoniaMaxData;
    }

    public double getAmmoniaMinData() {
        return ammoniaMinData;
    }

    public void setAmmoniaMinData(double ammoniaMinData) {
        this.ammoniaMinData = ammoniaMinData;
    }

    public int getIntervalTime() {
        return intervalTime;
    }

    public void setIntervalTime(int intervalTime) {
        this.intervalTime = intervalTime;
    }

    public int getAmmoniaStatus() {
        return ammoniaStatus;
    }

    public void setAmmoniaStatus(int ammoniaStatus) {
        this.ammoniaStatus = ammoniaStatus;
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
