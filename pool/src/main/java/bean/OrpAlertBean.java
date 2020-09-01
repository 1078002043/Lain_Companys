package bean;

/**
 * @ClassName: OrpAlertBean
 * @Description: ORP变送器-报警
 * @Author: YIN LUO FEI
 * @Date: 2020/3/28 0:40
 */
public class OrpAlertBean {


    /**
     * orpMeterAlarmId : 1
     * orpMeterId : 1
     * orpMeterName : 5
     * orpMeterData : 22.0
     * info : 43
     * orpMeterAlarmTime : 2020-02-26 11:23:37
     */

    private int orpMeterAlarmId;
    private int orpMeterId;
    private String orpMeterName;
    private double orpMeterData;
    private String info;
    private String orpMeterAlarmTime;

    public int getOrpMeterAlarmId() {
        return orpMeterAlarmId;
    }

    public void setOrpMeterAlarmId(int orpMeterAlarmId) {
        this.orpMeterAlarmId = orpMeterAlarmId;
    }

    public int getOrpMeterId() {
        return orpMeterId;
    }

    public void setOrpMeterId(int orpMeterId) {
        this.orpMeterId = orpMeterId;
    }

    public String getOrpMeterName() {
        return orpMeterName;
    }

    public void setOrpMeterName(String orpMeterName) {
        this.orpMeterName = orpMeterName;
    }

    public double getOrpMeterData() {
        return orpMeterData;
    }

    public void setOrpMeterData(double orpMeterData) {
        this.orpMeterData = orpMeterData;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getOrpMeterAlarmTime() {
        return orpMeterAlarmTime;
    }

    public void setOrpMeterAlarmTime(String orpMeterAlarmTime) {
        this.orpMeterAlarmTime = orpMeterAlarmTime;
    }
}
