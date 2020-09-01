package bean;

public class WaterTempAlertBean {


    /**
     * waterTempAlarmId : 1
     * waterTempId : 3
     * waterTempName : 水温1
     * waterTempData : 2.0
     * info : 检测报警
     * waterTempAlarmTime : 2020-01-08 13:43:21
     */

    private int waterTempAlarmId;
    private int waterTempId;
    private String waterTempName;
    private double waterTempData;
    private String info;
    private String waterTempAlarmTime;

    public int getWaterTempAlarmId() {
        return waterTempAlarmId;
    }

    public void setWaterTempAlarmId(int waterTempAlarmId) {
        this.waterTempAlarmId = waterTempAlarmId;
    }

    public int getWaterTempId() {
        return waterTempId;
    }

    public void setWaterTempId(int waterTempId) {
        this.waterTempId = waterTempId;
    }

    public String getWaterTempName() {
        return waterTempName;
    }

    public void setWaterTempName(String waterTempName) {
        this.waterTempName = waterTempName;
    }

    public double getWaterTempData() {
        return waterTempData;
    }

    public void setWaterTempData(double waterTempData) {
        this.waterTempData = waterTempData;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getWaterTempAlarmTime() {
        return waterTempAlarmTime;
    }

    public void setWaterTempAlarmTime(String waterTempAlarmTime) {
        this.waterTempAlarmTime = waterTempAlarmTime;
    }
}
