package bean;

/**
 * @ClassName: CyaninAlertBean
 * @Description: 蓝藻素-报警
 * @Author: YIN LUO FEI
 * @Date: 2020/3/27 23:20
 */
public class CyaninAlertBean {


    /**
     * cyaninAlarmId : 1
     * cyaninId : 1
     * cyaninName : 报警
     * cyaninData : 23.0
     * temp : 12.0
     * info : 1
     * cyaninAlarmTime : 2020-02-24 11:46:34
     */

    private int cyaninAlarmId;
    private int cyaninId;
    private String cyaninName;
    private double cyaninData;
    private double temp;
    private String info;
    private String cyaninAlarmTime;

    public int getCyaninAlarmId() {
        return cyaninAlarmId;
    }

    public void setCyaninAlarmId(int cyaninAlarmId) {
        this.cyaninAlarmId = cyaninAlarmId;
    }

    public int getCyaninId() {
        return cyaninId;
    }

    public void setCyaninId(int cyaninId) {
        this.cyaninId = cyaninId;
    }

    public String getCyaninName() {
        return cyaninName;
    }

    public void setCyaninName(String cyaninName) {
        this.cyaninName = cyaninName;
    }

    public double getCyaninData() {
        return cyaninData;
    }

    public void setCyaninData(double cyaninData) {
        this.cyaninData = cyaninData;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getCyaninAlarmTime() {
        return cyaninAlarmTime;
    }

    public void setCyaninAlarmTime(String cyaninAlarmTime) {
        this.cyaninAlarmTime = cyaninAlarmTime;
    }
}
