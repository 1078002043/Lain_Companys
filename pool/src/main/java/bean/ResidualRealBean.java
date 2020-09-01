package bean;

import base.BaseBean;

/**
 * @ClassName: ResidualRealBean
 * @description: 余氯-实时
 * @Author: YIN LUO FEI
 * @Date: 2020/3/27 20:15
 */
public class ResidualRealBean extends BaseBean {


    /**
     * residualChlorineId : 1
     * residualChlorineName : 余氯
     * residualChlorineAddress : 0
     * residualChlorineData : 5.6
     * residualChlorineMaxData : 0.0
     * residualChlorineMinData : 0.0
     * intervalTime : 0
     * residualChlorineStatus : 2
     * diId : 0
     * gId : 0
     */

    private int residualChlorineId;
    private String residualChlorineName;
    private int residualChlorineAddress;
    private double residualChlorineData;
    private double residualChlorineMaxData;
    private double residualChlorineMinData;
    private int intervalTime;
    private int residualChlorineStatus;
    private int diId;
    private int gId;

    public int getResidualChlorineId() {
        return residualChlorineId;
    }

    public void setResidualChlorineId(int residualChlorineId) {
        this.residualChlorineId = residualChlorineId;
    }

    public String getResidualChlorineName() {
        return residualChlorineName;
    }

    public void setResidualChlorineName(String residualChlorineName) {
        this.residualChlorineName = residualChlorineName;
    }

    public int getResidualChlorineAddress() {
        return residualChlorineAddress;
    }

    public void setResidualChlorineAddress(int residualChlorineAddress) {
        this.residualChlorineAddress = residualChlorineAddress;
    }

    public double getResidualChlorineData() {
        return residualChlorineData;
    }

    public void setResidualChlorineData(double residualChlorineData) {
        this.residualChlorineData = residualChlorineData;
    }

    public double getResidualChlorineMaxData() {
        return residualChlorineMaxData;
    }

    public void setResidualChlorineMaxData(double residualChlorineMaxData) {
        this.residualChlorineMaxData = residualChlorineMaxData;
    }

    public double getResidualChlorineMinData() {
        return residualChlorineMinData;
    }

    public void setResidualChlorineMinData(double residualChlorineMinData) {
        this.residualChlorineMinData = residualChlorineMinData;
    }

    public int getIntervalTime() {
        return intervalTime;
    }

    public void setIntervalTime(int intervalTime) {
        this.intervalTime = intervalTime;
    }

    public int getResidualChlorineStatus() {
        return residualChlorineStatus;
    }

    public void setResidualChlorineStatus(int residualChlorineStatus) {
        this.residualChlorineStatus = residualChlorineStatus;
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
