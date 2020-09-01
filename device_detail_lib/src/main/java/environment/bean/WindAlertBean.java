package environment.bean;

/**
 * @ClassName: WindAlertBean
 * @Description:
 * @Author: YIN LUO FEI
 * @Date: 2020/3/28 13:32
 */
public class WindAlertBean {


    /**
     * windSpeedAlarmId : 1
     * windSpeedId : 1
     * windSpeedName : *******
     * windSpeedData : 20.0
     * info : 1
     * windSpeedAlarmTime : 2020-02-24 15:26:43
     */

    private int windSpeedAlarmId;
    private int windSpeedId;
    private String windSpeedName;
    private double windSpeedData;
    private String info;
    private String windSpeedAlarmTime;

    public int getWindSpeedAlarmId() {
        return windSpeedAlarmId;
    }

    public void setWindSpeedAlarmId(int windSpeedAlarmId) {
        this.windSpeedAlarmId = windSpeedAlarmId;
    }

    public int getWindSpeedId() {
        return windSpeedId;
    }

    public void setWindSpeedId(int windSpeedId) {
        this.windSpeedId = windSpeedId;
    }

    public String getWindSpeedName() {
        return windSpeedName;
    }

    public void setWindSpeedName(String windSpeedName) {
        this.windSpeedName = windSpeedName;
    }

    public double getWindSpeedData() {
        return windSpeedData;
    }

    public void setWindSpeedData(double windSpeedData) {
        this.windSpeedData = windSpeedData;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getWindSpeedAlarmTime() {
        return windSpeedAlarmTime;
    }

    public void setWindSpeedAlarmTime(String windSpeedAlarmTime) {
        this.windSpeedAlarmTime = windSpeedAlarmTime;
    }
}
