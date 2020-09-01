package bean;

public class DischargeAlertBean {


    /**
     * waterFlowAlarmId : 2
     * waterFlowId : 2
     * waterFlowName : con
     * waterFlowData : 21.0
     * info : con1
     * waterFlowAlarmTime : 2020-01-09 13:42:49
     */

    private int waterFlowAlarmId;
    private int waterFlowId;
    private String waterFlowName;
    private double waterFlowData;
    private String info;
    private String waterFlowAlarmTime;

    public int getWaterFlowAlarmId() {
        return waterFlowAlarmId;
    }

    public void setWaterFlowAlarmId(int waterFlowAlarmId) {
        this.waterFlowAlarmId = waterFlowAlarmId;
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

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getWaterFlowAlarmTime() {
        return waterFlowAlarmTime;
    }

    public void setWaterFlowAlarmTime(String waterFlowAlarmTime) {
        this.waterFlowAlarmTime = waterFlowAlarmTime;
    }
}
