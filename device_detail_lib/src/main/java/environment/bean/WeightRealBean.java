package environment.bean;

import base.BaseBean;

/**
 * @ClassName: WeightRealBean
 * @Description:
 * @Author: YIN LUO FEI
 * @Date: 2020/3/28 11:45
 */
public class WeightRealBean extends BaseBean {


    /**
     * weighId : 1
     * weighName : 压力传感器
     * weighAddress : 0
     * weighData : 0.0
     * weighMaxData : 0.0
     * weighMinData : 0.0
     * intervalTime : 0
     * weighStatus : 2
     * diId : 0
     * gId : 0
     */

    private int weighId;
    private String weighName;
    private int weighAddress;
    private double weighData;
    private double weighMaxData;
    private double weighMinData;
    private int intervalTime;
    private int weighStatus;
    private int diId;
    private int gId;

    public int getWeighId() {
        return weighId;
    }

    public void setWeighId(int weighId) {
        this.weighId = weighId;
    }

    public String getWeighName() {
        return weighName;
    }

    public void setWeighName(String weighName) {
        this.weighName = weighName;
    }

    public int getWeighAddress() {
        return weighAddress;
    }

    public void setWeighAddress(int weighAddress) {
        this.weighAddress = weighAddress;
    }

    public double getWeighData() {
        return weighData;
    }

    public void setWeighData(double weighData) {
        this.weighData = weighData;
    }

    public double getWeighMaxData() {
        return weighMaxData;
    }

    public void setWeighMaxData(double weighMaxData) {
        this.weighMaxData = weighMaxData;
    }

    public double getWeighMinData() {
        return weighMinData;
    }

    public void setWeighMinData(double weighMinData) {
        this.weighMinData = weighMinData;
    }

    public int getIntervalTime() {
        return intervalTime;
    }

    public void setIntervalTime(int intervalTime) {
        this.intervalTime = intervalTime;
    }

    public int getWeighStatus() {
        return weighStatus;
    }

    public void setWeighStatus(int weighStatus) {
        this.weighStatus = weighStatus;
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
