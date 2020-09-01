package bean;

public class SalinityAlertBean {


    /**
     * salinityAlarmId : 1
     * salinityId : 1
     * salinityName : 盐度1号
     * salinityData : 30.0
     * info : 超过设定范围
     * salinityAlarmTime : 2019-11-30 08:55:27
     */

    private int salinityAlarmId;
    private int salinityId;
    private String salinityName;
    private double salinityData;
    private String info;
    private String salinityAlarmTime;

    public int getSalinityAlarmId() {
        return salinityAlarmId;
    }

    public void setSalinityAlarmId(int salinityAlarmId) {
        this.salinityAlarmId = salinityAlarmId;
    }

    public int getSalinityId() {
        return salinityId;
    }

    public void setSalinityId(int salinityId) {
        this.salinityId = salinityId;
    }

    public String getSalinityName() {
        return salinityName;
    }

    public void setSalinityName(String salinityName) {
        this.salinityName = salinityName;
    }

    public double getSalinityData() {
        return salinityData;
    }

    public void setSalinityData(double salinityData) {
        this.salinityData = salinityData;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getSalinityAlarmTime() {
        return salinityAlarmTime;
    }

    public void setSalinityAlarmTime(String salinityAlarmTime) {
        this.salinityAlarmTime = salinityAlarmTime;
    }
}
