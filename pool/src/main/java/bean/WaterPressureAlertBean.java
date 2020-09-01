package bean;

public class WaterPressureAlertBean {


    /**
     * waterPressureAlarmId : 1
     * waterPressureId : 1
     * waterPressureName : 水压力
     * waterPressureData : 50.0
     * info : 555
     * waterPressureAlarmTime : 2019-03-27 17:34:28
     */

    private int waterPressureAlarmId;
    private int waterPressureId;
    private String waterPressureName;
    private double waterPressureData;
    private String info;
    private String waterPressureAlarmTime;

    public int getWaterPressureAlarmId() {
        return waterPressureAlarmId;
    }

    public void setWaterPressureAlarmId(int waterPressureAlarmId) {
        this.waterPressureAlarmId = waterPressureAlarmId;
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

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getWaterPressureAlarmTime() {
        return waterPressureAlarmTime;
    }

    public void setWaterPressureAlarmTime(String waterPressureAlarmTime) {
        this.waterPressureAlarmTime = waterPressureAlarmTime;
    }
}
