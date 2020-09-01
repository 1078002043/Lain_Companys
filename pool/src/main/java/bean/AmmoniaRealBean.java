package bean;

import base.BaseBean;

/**
 * 氨氮监测
 */
public class AmmoniaRealBean extends BaseBean {


    /**
     * ammoniaNitrogenId : 3
     * ammoniaNitrogenName : 氨氮13
     * ammoniaNitrogenAddress : 0
     * ammoniaData : 22.0
     * ammoniaMaxData : 0.0
     * ammoniaMinData : 0.0
     * nitrogenData : 11.0
     * nitrogenMaxData : 0.0
     * nitrogenMinData : 0.0
     * ammoniaNitrogenStatus : 2
     * intervalTime : 0
     * diId : 0
     * gId : 0
     */

    private int ammoniaNitrogenId;
    private String ammoniaNitrogenName;
    private int ammoniaNitrogenAddress;
    private double ammoniaData;
    private double ammoniaMaxData;
    private double ammoniaMinData;
    private double nitrogenData;
    private double nitrogenMaxData;
    private double nitrogenMinData;
    private int ammoniaNitrogenStatus;
    private int intervalTime;
    private int diId;
    private int gId;

    public int getAmmoniaNitrogenId() {
        return ammoniaNitrogenId;
    }

    public void setAmmoniaNitrogenId(int ammoniaNitrogenId) {
        this.ammoniaNitrogenId = ammoniaNitrogenId;
    }

    public String getAmmoniaNitrogenName() {
        return ammoniaNitrogenName;
    }

    public void setAmmoniaNitrogenName(String ammoniaNitrogenName) {
        this.ammoniaNitrogenName = ammoniaNitrogenName;
    }

    public int getAmmoniaNitrogenAddress() {
        return ammoniaNitrogenAddress;
    }

    public void setAmmoniaNitrogenAddress(int ammoniaNitrogenAddress) {
        this.ammoniaNitrogenAddress = ammoniaNitrogenAddress;
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

    public double getNitrogenData() {
        return nitrogenData;
    }

    public void setNitrogenData(double nitrogenData) {
        this.nitrogenData = nitrogenData;
    }

    public double getNitrogenMaxData() {
        return nitrogenMaxData;
    }

    public void setNitrogenMaxData(double nitrogenMaxData) {
        this.nitrogenMaxData = nitrogenMaxData;
    }

    public double getNitrogenMinData() {
        return nitrogenMinData;
    }

    public void setNitrogenMinData(double nitrogenMinData) {
        this.nitrogenMinData = nitrogenMinData;
    }

    public int getAmmoniaNitrogenStatus() {
        return ammoniaNitrogenStatus;
    }

    public void setAmmoniaNitrogenStatus(int ammoniaNitrogenStatus) {
        this.ammoniaNitrogenStatus = ammoniaNitrogenStatus;
    }

    public int getIntervalTime() {
        return intervalTime;
    }

    public void setIntervalTime(int intervalTime) {
        this.intervalTime = intervalTime;
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
