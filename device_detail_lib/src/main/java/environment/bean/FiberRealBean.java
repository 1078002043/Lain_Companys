package environment.bean;

import base.BaseBean;

/**
 * @ClassName: FiberRealBean
 * @Description:
 * @Author: YIN LUO FEI
 * @Date: 2020/3/28 11:27
 */
public class FiberRealBean extends BaseBean {


    /**
     * opticalId : 1
     * opticalName : 电缆测温1
     * opticalAddress : 0
     * opticalData : 0.0
     * opticalMaxData : 0.0
     * opticalMinData : 0.0
     * intervalTime : 0
     * opticalStatus : 2
     * diId : 0
     * gId : 0
     */

    private int opticalId;
    private String opticalName;
    private int opticalAddress;
    private double opticalData;
    private double opticalMaxData;
    private double opticalMinData;
    private int intervalTime;
    private int opticalStatus;
    private int diId;
    private int gId;

    public int getOpticalId() {
        return opticalId;
    }

    public void setOpticalId(int opticalId) {
        this.opticalId = opticalId;
    }

    public String getOpticalName() {
        return opticalName;
    }

    public void setOpticalName(String opticalName) {
        this.opticalName = opticalName;
    }

    public int getOpticalAddress() {
        return opticalAddress;
    }

    public void setOpticalAddress(int opticalAddress) {
        this.opticalAddress = opticalAddress;
    }

    public double getOpticalData() {
        return opticalData;
    }

    public void setOpticalData(double opticalData) {
        this.opticalData = opticalData;
    }

    public double getOpticalMaxData() {
        return opticalMaxData;
    }

    public void setOpticalMaxData(double opticalMaxData) {
        this.opticalMaxData = opticalMaxData;
    }

    public double getOpticalMinData() {
        return opticalMinData;
    }

    public void setOpticalMinData(double opticalMinData) {
        this.opticalMinData = opticalMinData;
    }

    public int getIntervalTime() {
        return intervalTime;
    }

    public void setIntervalTime(int intervalTime) {
        this.intervalTime = intervalTime;
    }

    public int getOpticalStatus() {
        return opticalStatus;
    }

    public void setOpticalStatus(int opticalStatus) {
        this.opticalStatus = opticalStatus;
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
