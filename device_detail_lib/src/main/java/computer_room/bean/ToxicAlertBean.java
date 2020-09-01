package computer_room.bean;

/**
 * 有毒气体报警数据
 */
public class ToxicAlertBean {


    /**
     * ecaId : 1
     * ecmId : 1
     * ecmName : 1号有毒气体
     * alarmData : 1.0
     * ecaInfo : 超过设定范围
     * ecaTime : 2019-11-30 08:55:27
     */

    private int ecaId;
    private int ecmId;
    private String ecmName;
    private double alarmData;
    private String ecaInfo;
    private String ecaTime;

    public int getEcaId() {
        return ecaId;
    }

    public void setEcaId(int ecaId) {
        this.ecaId = ecaId;
    }

    public int getEcmId() {
        return ecmId;
    }

    public void setEcmId(int ecmId) {
        this.ecmId = ecmId;
    }

    public String getEcmName() {
        return ecmName;
    }

    public void setEcmName(String ecmName) {
        this.ecmName = ecmName;
    }

    public double getAlarmData() {
        return alarmData;
    }

    public void setAlarmData(double alarmData) {
        this.alarmData = alarmData;
    }

    public String getEcaInfo() {
        return ecaInfo;
    }

    public void setEcaInfo(String ecaInfo) {
        this.ecaInfo = ecaInfo;
    }

    public String getEcaTime() {
        return ecaTime;
    }

    public void setEcaTime(String ecaTime) {
        this.ecaTime = ecaTime;
    }
}
