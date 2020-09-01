package bean;

public class WaterLevelHistoryBean {


    /**
     * waterLevelHistoryId : 1
     * waterLevelId : 1
     * waterLevelName : 水位计
     * waterLevelData : 23.0
     * waterLevelHistoryTime : 2020-01-16 15:14:43
     */

    private int waterLevelHistoryId;
    private int waterLevelId;
    private String waterLevelName;
    private double waterLevelData;
    private String waterLevelHistoryTime;

    public int getWaterLevelHistoryId() {
        return waterLevelHistoryId;
    }

    public void setWaterLevelHistoryId(int waterLevelHistoryId) {
        this.waterLevelHistoryId = waterLevelHistoryId;
    }

    public int getWaterLevelId() {
        return waterLevelId;
    }

    public void setWaterLevelId(int waterLevelId) {
        this.waterLevelId = waterLevelId;
    }

    public String getWaterLevelName() {
        return waterLevelName;
    }

    public void setWaterLevelName(String waterLevelName) {
        this.waterLevelName = waterLevelName;
    }

    public double getWaterLevelData() {
        return waterLevelData;
    }

    public void setWaterLevelData(double waterLevelData) {
        this.waterLevelData = waterLevelData;
    }

    public String getWaterLevelHistoryTime() {
        return waterLevelHistoryTime;
    }

    public void setWaterLevelHistoryTime(String waterLevelHistoryTime) {
        this.waterLevelHistoryTime = waterLevelHistoryTime;
    }
}
