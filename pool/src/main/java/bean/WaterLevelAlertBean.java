package bean;

public class WaterLevelAlertBean {


    /**
     * waterLevelAlarmId : 1
     * waterLevelId : 1
     * waterLevelName : con
     * waterLevelData : 21.0
     * info : con12
     * waterLevelAlarmTime : 2020-01-03 13:42:10
     */

    private int waterLevelAlarmId;
    private int waterLevelId;
    private String waterLevelName;
    private double waterLevelData;
    private String info;
    private String waterLevelAlarmTime;

    public int getWaterLevelAlarmId() {
        return waterLevelAlarmId;
    }

    public void setWaterLevelAlarmId(int waterLevelAlarmId) {
        this.waterLevelAlarmId = waterLevelAlarmId;
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

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getWaterLevelAlarmTime() {
        return waterLevelAlarmTime;
    }

    public void setWaterLevelAlarmTime(String waterLevelAlarmTime) {
        this.waterLevelAlarmTime = waterLevelAlarmTime;
    }
}
