package environment.bean;

import base.BaseBean;

/**
 * @ClassName: PatchTempRealBean
 * @Description:
 * @Author: YIN LUO FEI
 * @Date: 2020/3/28 11:09
 */
public class PatchTempRealBean extends BaseBean {


    /**
     * patchId : 1
     * patchName : 贴片式温度1
     * patchAddress : 0
     * patchData : 11.0
     * patchMaxData : 0.0
     * patchMinData : 0.0
     * intervalTime : 0
     * patchStatus : 2
     * diId : 0
     * gId : 0
     */

    private int patchId;
    private String patchName;
    private int patchAddress;
    private double patchData;
    private double patchMaxData;
    private double patchMinData;
    private int intervalTime;
    private int patchStatus;
    private int diId;
    private int gId;

    public int getPatchId() {
        return patchId;
    }

    public void setPatchId(int patchId) {
        this.patchId = patchId;
    }

    public String getPatchName() {
        return patchName;
    }

    public void setPatchName(String patchName) {
        this.patchName = patchName;
    }

    public int getPatchAddress() {
        return patchAddress;
    }

    public void setPatchAddress(int patchAddress) {
        this.patchAddress = patchAddress;
    }

    public double getPatchData() {
        return patchData;
    }

    public void setPatchData(double patchData) {
        this.patchData = patchData;
    }

    public double getPatchMaxData() {
        return patchMaxData;
    }

    public void setPatchMaxData(double patchMaxData) {
        this.patchMaxData = patchMaxData;
    }

    public double getPatchMinData() {
        return patchMinData;
    }

    public void setPatchMinData(double patchMinData) {
        this.patchMinData = patchMinData;
    }

    public int getIntervalTime() {
        return intervalTime;
    }

    public void setIntervalTime(int intervalTime) {
        this.intervalTime = intervalTime;
    }

    public int getPatchStatus() {
        return patchStatus;
    }

    public void setPatchStatus(int patchStatus) {
        this.patchStatus = patchStatus;
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
