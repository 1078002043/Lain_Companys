package environment.bean;

import base.BaseBean;

/**
 * @ClassName: GasPressureRealBean
 * @Description: 气压强度-实时
 * @Author: YIN LUO FEI
 * @Date: 2020/3/28 10:33
 */
public class GasPressureRealBean extends BaseBean {


    /**
     * gasPressureId : 1
     * gasPressureName : 气压强度1
     * gasPressureAddress : 0
     * gasPressureData : 50.0
     * gasPressureMaxData : 0.0
     * gasPressureMinData : 0.0
     * intervalTime : 0
     * gasPressureStatus : 1
     * diId : 0
     * gId : 0
     */

    private int gasPressureId;
    private String gasPressureName;
    private int gasPressureAddress;
    private double gasPressureData;
    private double gasPressureMaxData;
    private double gasPressureMinData;
    private int intervalTime;
    private int gasPressureStatus;
    private int diId;
    private int gId;

    public int getGasPressureId() {
        return gasPressureId;
    }

    public void setGasPressureId(int gasPressureId) {
        this.gasPressureId = gasPressureId;
    }

    public String getGasPressureName() {
        return gasPressureName;
    }

    public void setGasPressureName(String gasPressureName) {
        this.gasPressureName = gasPressureName;
    }

    public int getGasPressureAddress() {
        return gasPressureAddress;
    }

    public void setGasPressureAddress(int gasPressureAddress) {
        this.gasPressureAddress = gasPressureAddress;
    }

    public double getGasPressureData() {
        return gasPressureData;
    }

    public void setGasPressureData(double gasPressureData) {
        this.gasPressureData = gasPressureData;
    }

    public double getGasPressureMaxData() {
        return gasPressureMaxData;
    }

    public void setGasPressureMaxData(double gasPressureMaxData) {
        this.gasPressureMaxData = gasPressureMaxData;
    }

    public double getGasPressureMinData() {
        return gasPressureMinData;
    }

    public void setGasPressureMinData(double gasPressureMinData) {
        this.gasPressureMinData = gasPressureMinData;
    }

    public int getIntervalTime() {
        return intervalTime;
    }

    public void setIntervalTime(int intervalTime) {
        this.intervalTime = intervalTime;
    }

    public int getGasPressureStatus() {
        return gasPressureStatus;
    }

    public void setGasPressureStatus(int gasPressureStatus) {
        this.gasPressureStatus = gasPressureStatus;
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
