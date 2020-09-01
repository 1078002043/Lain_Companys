package bean;

import base.BaseBean;

/**
 * 硫化氢
 */
public class HydrogenRealBean extends BaseBean {


    /**
     * hydrogenSulfideId : 1
     * hydrogenSulfideName : 硫化氢
     * hydrogenSulfideAddress : 8
     * hydrogenSulfideData : 80.0
     * hydrogenSulfideMaxData : 50.0
     * hydrogenSulfideMinData : 20.0
     * intervalTime : 30
     * hydrogenSulfideStatus : 2
     * diId : 1
     * gId : 2
     */

    private int hydrogenSulfideId;
    private String hydrogenSulfideName;
    private int hydrogenSulfideAddress;
    private double hydrogenSulfideData;
    private double hydrogenSulfideMaxData;
    private double hydrogenSulfideMinData;
    private int intervalTime;
    private int hydrogenSulfideStatus;
    private int diId;
    private int gId;

    public int getHydrogenSulfideId() {
        return hydrogenSulfideId;
    }

    public void setHydrogenSulfideId(int hydrogenSulfideId) {
        this.hydrogenSulfideId = hydrogenSulfideId;
    }

    public String getHydrogenSulfideName() {
        return hydrogenSulfideName;
    }

    public void setHydrogenSulfideName(String hydrogenSulfideName) {
        this.hydrogenSulfideName = hydrogenSulfideName;
    }

    public int getHydrogenSulfideAddress() {
        return hydrogenSulfideAddress;
    }

    public void setHydrogenSulfideAddress(int hydrogenSulfideAddress) {
        this.hydrogenSulfideAddress = hydrogenSulfideAddress;
    }

    public double getHydrogenSulfideData() {
        return hydrogenSulfideData;
    }

    public void setHydrogenSulfideData(double hydrogenSulfideData) {
        this.hydrogenSulfideData = hydrogenSulfideData;
    }

    public double getHydrogenSulfideMaxData() {
        return hydrogenSulfideMaxData;
    }

    public void setHydrogenSulfideMaxData(double hydrogenSulfideMaxData) {
        this.hydrogenSulfideMaxData = hydrogenSulfideMaxData;
    }

    public double getHydrogenSulfideMinData() {
        return hydrogenSulfideMinData;
    }

    public void setHydrogenSulfideMinData(double hydrogenSulfideMinData) {
        this.hydrogenSulfideMinData = hydrogenSulfideMinData;
    }

    public int getIntervalTime() {
        return intervalTime;
    }

    public void setIntervalTime(int intervalTime) {
        this.intervalTime = intervalTime;
    }

    public int getHydrogenSulfideStatus() {
        return hydrogenSulfideStatus;
    }

    public void setHydrogenSulfideStatus(int hydrogenSulfideStatus) {
        this.hydrogenSulfideStatus = hydrogenSulfideStatus;
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
