package environment.bean;

/**
 * @ClassName: SingleBatteryRealBean
 * @Description: 单体蓄电池-实时数据
 * @Author: YIN LUO FEI
 * @Date: 2020/3/28 14:59
 */
public class SingleBatteryRealBean {


    /**
     * batteryId : 1
     * batteryName : 蓄电池1
     * batteryAddress : 0
     * batteryData : 20.0
     * batteryMaxData : 0.0
     * batteryMinData : 0.0
     * intervalTime : 0
     * batteryStatus : 2
     * diId : 0
     * gId : 0
     */

    private int batteryId;
    private String batteryName;
    private int batteryAddress;
    private double batteryData;
    private double batteryMaxData;
    private double batteryMinData;
    private int intervalTime;
    private int batteryStatus;
    private int diId;
    private int gId;

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

    public int getBatteryAddress() {
        return batteryAddress;
    }

    public void setBatteryAddress(int batteryAddress) {
        this.batteryAddress = batteryAddress;
    }

    public double getBatteryData() {
        return batteryData;
    }

    public void setBatteryData(double batteryData) {
        this.batteryData = batteryData;
    }

    public double getBatteryMaxData() {
        return batteryMaxData;
    }

    public void setBatteryMaxData(double batteryMaxData) {
        this.batteryMaxData = batteryMaxData;
    }

    public double getBatteryMinData() {
        return batteryMinData;
    }

    public void setBatteryMinData(double batteryMinData) {
        this.batteryMinData = batteryMinData;
    }

    public int getIntervalTime() {
        return intervalTime;
    }

    public void setIntervalTime(int intervalTime) {
        this.intervalTime = intervalTime;
    }

    public int getBatteryStatus() {
        return batteryStatus;
    }

    public void setBatteryStatus(int batteryStatus) {
        this.batteryStatus = batteryStatus;
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
