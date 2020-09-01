package bean;

public class DischargeHistoryBean {


    /**
     * waterFlowHistoryId : 1
     * waterFlowId : 2
     * waterFlowName : 水流量
     * waterFlowData : 20.0
     * waterFlowHistoryTime : 2020-01-16 15:09:22
     */

    private int waterFlowHistoryId;
    private int waterFlowId;
    private String waterFlowName;
    private double waterFlowData;
    private String waterFlowHistoryTime;

    public int getWaterFlowHistoryId() {
        return waterFlowHistoryId;
    }

    public void setWaterFlowHistoryId(int waterFlowHistoryId) {
        this.waterFlowHistoryId = waterFlowHistoryId;
    }

    public int getWaterFlowId() {
        return waterFlowId;
    }

    public void setWaterFlowId(int waterFlowId) {
        this.waterFlowId = waterFlowId;
    }

    public String getWaterFlowName() {
        return waterFlowName;
    }

    public void setWaterFlowName(String waterFlowName) {
        this.waterFlowName = waterFlowName;
    }

    public double getWaterFlowData() {
        return waterFlowData;
    }

    public void setWaterFlowData(double waterFlowData) {
        this.waterFlowData = waterFlowData;
    }

    public String getWaterFlowHistoryTime() {
        return waterFlowHistoryTime;
    }

    public void setWaterFlowHistoryTime(String waterFlowHistoryTime) {
        this.waterFlowHistoryTime = waterFlowHistoryTime;
    }
}
