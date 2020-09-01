package environment.bean;

/**
 * @ClassName: SingleBatteryHistoryBean
 * @Description: 单体蓄电池-历史数据
 * @Author: YIN LUO FEI
 * @Date: 2020/3/28 14:59
 */
public class SingleBatteryHistoryBean {


    /**
     * batteryHistoryId : 1
     * batteryId : 1
     * batteryName : 单体蓄电池1
     * batteryData : 25.0
     * batteryHistoryTime : 2020-02-24 09:14:59
     */

    private int batteryHistoryId;
    private int batteryId;
    private String batteryName;
    private double batteryData;
    private String batteryHistoryTime;

    public int getBatteryHistoryId() {
        return batteryHistoryId;
    }

    public void setBatteryHistoryId(int batteryHistoryId) {
        this.batteryHistoryId = batteryHistoryId;
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

    public String getBatteryHistoryTime() {
        return batteryHistoryTime;
    }

    public void setBatteryHistoryTime(String batteryHistoryTime) {
        this.batteryHistoryTime = batteryHistoryTime;
    }
}
