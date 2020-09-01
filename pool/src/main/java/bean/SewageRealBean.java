package bean;

import base.BaseBean;

/**
 * @ClassName: SewageRealBean
 * @Description: 污水流量-实时
 * @Author: YIN LUO FEI
 * @Date: 2020/3/27 22:26
 */
public class SewageRealBean extends BaseBean {


    /**
     * sewageDischargeId : 1
     * sewageDischargeName : 污水流量
     * sewageDischargeAddress : 0
     * sewageDischargeData : 0.0
     * sewageDischargeMaxData : 0.0
     * sewageDischargeMinData : 0.0
     * intervalTime : 0
     * sewageDischargeStatus : 2
     * diId : 0
     * gId : 0
     */

    private int sewageDischargeId;
    private String sewageDischargeName;
    private int sewageDischargeAddress;
    private double sewageDischargeData;
    private double sewageDischargeMaxData;
    private double sewageDischargeMinData;
    private int intervalTime;
    private int sewageDischargeStatus;
    private int diId;
    private int gId;

    public int getSewageDischargeId() {
        return sewageDischargeId;
    }

    public void setSewageDischargeId(int sewageDischargeId) {
        this.sewageDischargeId = sewageDischargeId;
    }

    public String getSewageDischargeName() {
        return sewageDischargeName;
    }

    public void setSewageDischargeName(String sewageDischargeName) {
        this.sewageDischargeName = sewageDischargeName;
    }

    public int getSewageDischargeAddress() {
        return sewageDischargeAddress;
    }

    public void setSewageDischargeAddress(int sewageDischargeAddress) {
        this.sewageDischargeAddress = sewageDischargeAddress;
    }

    public double getSewageDischargeData() {
        return sewageDischargeData;
    }

    public void setSewageDischargeData(double sewageDischargeData) {
        this.sewageDischargeData = sewageDischargeData;
    }

    public double getSewageDischargeMaxData() {
        return sewageDischargeMaxData;
    }

    public void setSewageDischargeMaxData(double sewageDischargeMaxData) {
        this.sewageDischargeMaxData = sewageDischargeMaxData;
    }

    public double getSewageDischargeMinData() {
        return sewageDischargeMinData;
    }

    public void setSewageDischargeMinData(double sewageDischargeMinData) {
        this.sewageDischargeMinData = sewageDischargeMinData;
    }

    public int getIntervalTime() {
        return intervalTime;
    }

    public void setIntervalTime(int intervalTime) {
        this.intervalTime = intervalTime;
    }

    public int getSewageDischargeStatus() {
        return sewageDischargeStatus;
    }

    public void setSewageDischargeStatus(int sewageDischargeStatus) {
        this.sewageDischargeStatus = sewageDischargeStatus;
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
