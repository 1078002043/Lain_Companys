package bean;

import base.BaseBean;

/**
 * @ClassName: UreaRealBean
 * @Description:
 * @Author: YIN LUO FEI
 * @Date: 2020/3/28 1:37
 */
public class UreaRealBean extends BaseBean {


    /**
     * ureaId : 1
     * ureaName : 尿素3
     * ureaAddress : 0
     * ureaData : 0.0
     * ureaMaxData : 0.0
     * ureaMinData : 0.0
     * intervalTime : 0
     * ureaStatus : 0
     * diId : 0
     * gId : 0
     */

    private int ureaId;
    private String ureaName;
    private int ureaAddress;
    private double ureaData;
    private double ureaMaxData;
    private double ureaMinData;
    private int intervalTime;
    private int ureaStatus;
    private int diId;
    private int gId;

    public int getUreaId() {
        return ureaId;
    }

    public void setUreaId(int ureaId) {
        this.ureaId = ureaId;
    }

    public String getUreaName() {
        return ureaName;
    }

    public void setUreaName(String ureaName) {
        this.ureaName = ureaName;
    }

    public int getUreaAddress() {
        return ureaAddress;
    }

    public void setUreaAddress(int ureaAddress) {
        this.ureaAddress = ureaAddress;
    }

    public double getUreaData() {
        return ureaData;
    }

    public void setUreaData(double ureaData) {
        this.ureaData = ureaData;
    }

    public double getUreaMaxData() {
        return ureaMaxData;
    }

    public void setUreaMaxData(double ureaMaxData) {
        this.ureaMaxData = ureaMaxData;
    }

    public double getUreaMinData() {
        return ureaMinData;
    }

    public void setUreaMinData(double ureaMinData) {
        this.ureaMinData = ureaMinData;
    }

    public int getIntervalTime() {
        return intervalTime;
    }

    public void setIntervalTime(int intervalTime) {
        this.intervalTime = intervalTime;
    }

    public int getUreaStatus() {
        return ureaStatus;
    }

    public void setUreaStatus(int ureaStatus) {
        this.ureaStatus = ureaStatus;
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
