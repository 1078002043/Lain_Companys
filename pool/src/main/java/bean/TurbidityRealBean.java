package bean;

import base.BaseBean;

/**
 * @ClassName: TurbidityRealBean
 * @Description: 浑浊度-实时
 * @Author: YIN LUO FEI
 * @Date: 2020/3/27 22:07
 */
public class TurbidityRealBean extends BaseBean {


    /**
     * muddyId : 1
     * muddyName : 浑浊度1好
     * muddyAddress : 3
     * muddyData : 19.3
     * muddyMaxData : 40.0
     * muddyMinData : 10.0
     * intervalTime : 20
     * muddyStatus : 2
     * diId : 1
     * gId : 1
     */

    private int muddyId;
    private String muddyName;
    private int muddyAddress;
    private double muddyData;
    private double muddyMaxData;
    private double muddyMinData;
    private int intervalTime;
    private int muddyStatus;
    private int diId;
    private int gId;

    public int getMuddyId() {
        return muddyId;
    }

    public void setMuddyId(int muddyId) {
        this.muddyId = muddyId;
    }

    public String getMuddyName() {
        return muddyName;
    }

    public void setMuddyName(String muddyName) {
        this.muddyName = muddyName;
    }

    public int getMuddyAddress() {
        return muddyAddress;
    }

    public void setMuddyAddress(int muddyAddress) {
        this.muddyAddress = muddyAddress;
    }

    public double getMuddyData() {
        return muddyData;
    }

    public void setMuddyData(double muddyData) {
        this.muddyData = muddyData;
    }

    public double getMuddyMaxData() {
        return muddyMaxData;
    }

    public void setMuddyMaxData(double muddyMaxData) {
        this.muddyMaxData = muddyMaxData;
    }

    public double getMuddyMinData() {
        return muddyMinData;
    }

    public void setMuddyMinData(double muddyMinData) {
        this.muddyMinData = muddyMinData;
    }

    public int getIntervalTime() {
        return intervalTime;
    }

    public void setIntervalTime(int intervalTime) {
        this.intervalTime = intervalTime;
    }

    public int getMuddyStatus() {
        return muddyStatus;
    }

    public void setMuddyStatus(int muddyStatus) {
        this.muddyStatus = muddyStatus;
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
