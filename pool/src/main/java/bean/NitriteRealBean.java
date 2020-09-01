package bean;

import base.BaseBean;

/**
 * 亚硝酸盐
 */
public class NitriteRealBean extends BaseBean {


    /**
     * nitriteId : 3
     * nitriteName : 亚硝酸盐3号
     * nitriteAddress : 4
     * nitriteData : 10.0
     * nitriteMaxData : 30.0
     * nitriteMinData : 1.0
     * intervalTime : 30
     * nitriteStatus : 2
     * diId : 1
     * gId : 1
     */

    private int nitriteId;
    private String nitriteName;
    private int nitriteAddress;
    private double nitriteData;
    private double nitriteMaxData;
    private double nitriteMinData;
    private int intervalTime;
    private int nitriteStatus;
    private int diId;
    private int gId;

    public int getNitriteId() {
        return nitriteId;
    }

    public void setNitriteId(int nitriteId) {
        this.nitriteId = nitriteId;
    }

    public String getNitriteName() {
        return nitriteName;
    }

    public void setNitriteName(String nitriteName) {
        this.nitriteName = nitriteName;
    }

    public int getNitriteAddress() {
        return nitriteAddress;
    }

    public void setNitriteAddress(int nitriteAddress) {
        this.nitriteAddress = nitriteAddress;
    }

    public double getNitriteData() {
        return nitriteData;
    }

    public void setNitriteData(double nitriteData) {
        this.nitriteData = nitriteData;
    }

    public double getNitriteMaxData() {
        return nitriteMaxData;
    }

    public void setNitriteMaxData(double nitriteMaxData) {
        this.nitriteMaxData = nitriteMaxData;
    }

    public double getNitriteMinData() {
        return nitriteMinData;
    }

    public void setNitriteMinData(double nitriteMinData) {
        this.nitriteMinData = nitriteMinData;
    }

    public int getIntervalTime() {
        return intervalTime;
    }

    public void setIntervalTime(int intervalTime) {
        this.intervalTime = intervalTime;
    }

    public int getNitriteStatus() {
        return nitriteStatus;
    }

    public void setNitriteStatus(int nitriteStatus) {
        this.nitriteStatus = nitriteStatus;
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
