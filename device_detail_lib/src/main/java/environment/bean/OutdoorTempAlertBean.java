package environment.bean;

/**
 * @ClassName: OutdoorTempAlertBean
 * @Description: 室外温度变送器-报警
 * @Author: YIN LUO FEI
 * @Date: 2020/3/28 14:00
 */
public class OutdoorTempAlertBean {


    /**
     * outdoorHumitureAlarmId : 1
     * outdoorHumitureId : 1
     * outdoorHumitureName : 温湿度
     * outdoorHumitureDeviceAddress : 1
     * diId : 1
     * outdoorHumitureAlarmInfo : 1
     * outdoorHumitureAlarmTime : 2020-02-24 13:54:49
     */

    private int outdoorHumitureAlarmId;
    private int outdoorHumitureId;
    private String outdoorHumitureName;
    private int outdoorHumitureDeviceAddress;
    private int diId;
    private String outdoorHumitureAlarmInfo;
    private String outdoorHumitureAlarmTime;

    public int getOutdoorHumitureAlarmId() {
        return outdoorHumitureAlarmId;
    }

    public void setOutdoorHumitureAlarmId(int outdoorHumitureAlarmId) {
        this.outdoorHumitureAlarmId = outdoorHumitureAlarmId;
    }

    public int getOutdoorHumitureId() {
        return outdoorHumitureId;
    }

    public void setOutdoorHumitureId(int outdoorHumitureId) {
        this.outdoorHumitureId = outdoorHumitureId;
    }

    public String getOutdoorHumitureName() {
        return outdoorHumitureName;
    }

    public void setOutdoorHumitureName(String outdoorHumitureName) {
        this.outdoorHumitureName = outdoorHumitureName;
    }

    public int getOutdoorHumitureDeviceAddress() {
        return outdoorHumitureDeviceAddress;
    }

    public void setOutdoorHumitureDeviceAddress(int outdoorHumitureDeviceAddress) {
        this.outdoorHumitureDeviceAddress = outdoorHumitureDeviceAddress;
    }

    public int getDiId() {
        return diId;
    }

    public void setDiId(int diId) {
        this.diId = diId;
    }

    public String getOutdoorHumitureAlarmInfo() {
        return outdoorHumitureAlarmInfo;
    }

    public void setOutdoorHumitureAlarmInfo(String outdoorHumitureAlarmInfo) {
        this.outdoorHumitureAlarmInfo = outdoorHumitureAlarmInfo;
    }

    public String getOutdoorHumitureAlarmTime() {
        return outdoorHumitureAlarmTime;
    }

    public void setOutdoorHumitureAlarmTime(String outdoorHumitureAlarmTime) {
        this.outdoorHumitureAlarmTime = outdoorHumitureAlarmTime;
    }
}
