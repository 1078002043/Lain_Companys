package bean;

import base.BaseBean;

/**
 * @ClassName: CirculationRealBean
 * @Description:
 * @Author: YIN LUO FEI
 * @Date: 2020/3/28 1:17
 */
public class CirculationRealBean extends BaseBean {


    /**
     * flowCellId : 1
     * flowCellAddress : 0
     * flowCellName : 流通池
     * temp : 0.0
     * maxTemp : 0.0
     * minTemp : 0.0
     * phValue : 0.0
     * maxPh : 0.0
     * minPh : 0.0
     * flowCellStatus : 2
     * intervalTime : 21
     * diId : 68
     * gId : 2
     */

    private int flowCellId;
    private int flowCellAddress;
    private String flowCellName;
    private double temp;
    private double maxTemp;
    private double minTemp;
    private double phValue;
    private double maxPh;
    private double minPh;
    private int flowCellStatus;
    private int intervalTime;
    private int diId;
    private int gId;

    public int getFlowCellId() {
        return flowCellId;
    }

    public void setFlowCellId(int flowCellId) {
        this.flowCellId = flowCellId;
    }

    public int getFlowCellAddress() {
        return flowCellAddress;
    }

    public void setFlowCellAddress(int flowCellAddress) {
        this.flowCellAddress = flowCellAddress;
    }

    public String getFlowCellName() {
        return flowCellName;
    }

    public void setFlowCellName(String flowCellName) {
        this.flowCellName = flowCellName;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public double getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(double maxTemp) {
        this.maxTemp = maxTemp;
    }

    public double getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(double minTemp) {
        this.minTemp = minTemp;
    }

    public double getPhValue() {
        return phValue;
    }

    public void setPhValue(double phValue) {
        this.phValue = phValue;
    }

    public double getMaxPh() {
        return maxPh;
    }

    public void setMaxPh(double maxPh) {
        this.maxPh = maxPh;
    }

    public double getMinPh() {
        return minPh;
    }

    public void setMinPh(double minPh) {
        this.minPh = minPh;
    }

    public int getFlowCellStatus() {
        return flowCellStatus;
    }

    public void setFlowCellStatus(int flowCellStatus) {
        this.flowCellStatus = flowCellStatus;
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
