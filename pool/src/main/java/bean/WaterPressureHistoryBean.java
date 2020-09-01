package bean;

public class WaterPressureHistoryBean {


    /**
     * waterPressureHistoryId : 1
     * waterPressureId : 1
     * waterPressureName : 水压力
     * waterPressureData : 40.0
     * waterPressureHistoryTime : 2019-03-27 17:34:50
     */

    private int waterPressureHistoryId;
    private int waterPressureId;
    private String waterPressureName;
    private double waterPressureData;
    private String waterPressureHistoryTime;

    public int getWaterPressureHistoryId() {
        return waterPressureHistoryId;
    }

    public void setWaterPressureHistoryId(int waterPressureHistoryId) {
        this.waterPressureHistoryId = waterPressureHistoryId;
    }

    public int getWaterPressureId() {
        return waterPressureId;
    }

    public void setWaterPressureId(int waterPressureId) {
        this.waterPressureId = waterPressureId;
    }

    public String getWaterPressureName() {
        return waterPressureName;
    }

    public void setWaterPressureName(String waterPressureName) {
        this.waterPressureName = waterPressureName;
    }

    public double getWaterPressureData() {
        return waterPressureData;
    }

    public void setWaterPressureData(double waterPressureData) {
        this.waterPressureData = waterPressureData;
    }

    public String getWaterPressureHistoryTime() {
        return waterPressureHistoryTime;
    }

    public void setWaterPressureHistoryTime(String waterPressureHistoryTime) {
        this.waterPressureHistoryTime = waterPressureHistoryTime;
    }
}
