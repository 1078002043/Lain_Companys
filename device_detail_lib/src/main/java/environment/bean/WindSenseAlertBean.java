package environment.bean;

/**
 * @ClassName: WindSenseAlertBean
 * @Description:
 * @Author: YIN LUO FEI
 * @Date: 2020/3/28 14:20
 */
public class WindSenseAlertBean {


    /**
     * lightAlarmId : 1
     * lightId : 1
     * lightName : 525
     * lightData : 15.0
     * info : 54
     * lightAlarmTime : 2019-07-28 10:48:54
     */

    private int lightAlarmId;
    private int lightId;
    private String lightName;
    private double lightData;
    private String info;
    private String lightAlarmTime;

    public int getLightAlarmId() {
        return lightAlarmId;
    }

    public void setLightAlarmId(int lightAlarmId) {
        this.lightAlarmId = lightAlarmId;
    }

    public int getLightId() {
        return lightId;
    }

    public void setLightId(int lightId) {
        this.lightId = lightId;
    }

    public String getLightName() {
        return lightName;
    }

    public void setLightName(String lightName) {
        this.lightName = lightName;
    }

    public double getLightData() {
        return lightData;
    }

    public void setLightData(double lightData) {
        this.lightData = lightData;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getLightAlarmTime() {
        return lightAlarmTime;
    }

    public void setLightAlarmTime(String lightAlarmTime) {
        this.lightAlarmTime = lightAlarmTime;
    }
}
