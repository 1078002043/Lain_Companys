package bean;

import base.BaseBean;

/**
 * 盐度
 */
public class SalinityRealBean extends BaseBean {

    /**
     * salinityId : 1
     * salinityName : 盐度1号
     * salinityAddress : 2
     * salinityData : 30.0
     * salinityMaxData : 50.0
     * salinityMinData : 10.0
     * intervalTime : 30
     * salinityStatus : 1
     * diId : 1
     * gId : 1
     */

    private int salinityId;
    private String salinityName;
    private int salinityAddress;
    private double salinityData;
    private double salinityMaxData;
    private double salinityMinData;
    private int intervalTime;
    private int salinityStatus;
    private int diId;
    private int gId;

    public int getSalinityId() {
        return salinityId;
    }

    public void setSalinityId(int salinityId) {
        this.salinityId = salinityId;
    }

    public String getSalinityName() {
        return salinityName;
    }

    public void setSalinityName(String salinityName) {
        this.salinityName = salinityName;
    }

    public int getSalinityAddress() {
        return salinityAddress;
    }

    public void setSalinityAddress(int salinityAddress) {
        this.salinityAddress = salinityAddress;
    }

    public double getSalinityData() {
        return salinityData;
    }

    public void setSalinityData(double salinityData) {
        this.salinityData = salinityData;
    }

    public double getSalinityMaxData() {
        return salinityMaxData;
    }

    public void setSalinityMaxData(double salinityMaxData) {
        this.salinityMaxData = salinityMaxData;
    }

    public double getSalinityMinData() {
        return salinityMinData;
    }

    public void setSalinityMinData(double salinityMinData) {
        this.salinityMinData = salinityMinData;
    }

    public int getIntervalTime() {
        return intervalTime;
    }

    public void setIntervalTime(int intervalTime) {
        this.intervalTime = intervalTime;
    }

    public int getSalinityStatus() {
        return salinityStatus;
    }

    public void setSalinityStatus(int salinityStatus) {
        this.salinityStatus = salinityStatus;
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
