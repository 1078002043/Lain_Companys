package environment.bean;

/**
 * @ClassName: WindHistoryBean
 * @Description:
 * @Author: YIN LUO FEI
 * @Date: 2020/3/28 13:32
 */
public class WindHistoryBean {


    /**
     * windSpeedHistoryId : 1
     * windSpeedId : 1
     * windSpeedName : 风速
     * windSpeedData : 10.0
     * windSpeedHistoryTime : 2020-02-24 15:25:53
     */

    private int windSpeedHistoryId;
    private int windSpeedId;
    private String windSpeedName;
    private double windSpeedData;
    private String windSpeedHistoryTime;

    public int getWindSpeedHistoryId() {
        return windSpeedHistoryId;
    }

    public void setWindSpeedHistoryId(int windSpeedHistoryId) {
        this.windSpeedHistoryId = windSpeedHistoryId;
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

    public String getWindSpeedHistoryTime() {
        return windSpeedHistoryTime;
    }

    public void setWindSpeedHistoryTime(String windSpeedHistoryTime) {
        this.windSpeedHistoryTime = windSpeedHistoryTime;
    }
}
