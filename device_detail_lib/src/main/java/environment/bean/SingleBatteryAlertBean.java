package environment.bean;

/**
 * @ClassName: SingleBatteryAlertBent
 * @Description: 单体蓄电池-报警数据
 * @Author: YIN LUO FEI
 * @Date: 2020/3/28 14:59
 */
public class SingleBatteryAlertBean {


    /**
     * batteryAlarmId : 1
     * batteryId : 1
     * batteryName : 单体蓄电池2
     * batteryData : 20.0
     * info : 1
     * batteryAlarmTime : 2020-02-24 09:15:34
     */

    private int batteryAlarmId;
    private int batteryId;
    private String batteryName;
    private double batteryData;
    private String info;
    private String batteryAlarmTime;

    public int getBatteryAlarmId() {
        return batteryAlarmId;
    }

    public void setBatteryAlarmId(int batteryAlarmId) {
        this.batteryAlarmId = batteryAlarmId;
    }

    public int getBatteryId() {
        return batteryId;
    }

    public void setBatteryId(int batteryId) {
        this.batteryId = batteryId;
    }

    public String getBatteryName() {
        return batteryName;
    }

    public void setBatteryName(String batteryName) {
        this.batteryName = batteryName;
    }

    public double getBatteryData() {
        return batteryData;
    }

    public void setBatteryData(double batteryData) {
        this.batteryData = batteryData;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getBatteryAlarmTime() {
        return batteryAlarmTime;
    }

    public void setBatteryAlarmTime(String batteryAlarmTime) {
        this.batteryAlarmTime = batteryAlarmTime;
    }
}
