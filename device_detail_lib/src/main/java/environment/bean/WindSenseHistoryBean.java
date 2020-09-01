package environment.bean;

/**
 * @ClassName: WindSenseHistoryBean
 * @Description:
 * @Author: YIN LUO FEI
 * @Date: 2020/3/28 14:20
 */
public class WindSenseHistoryBean {


    /**
     * lightHistoryId : 1
     * lightId : 1
     * lightName : 758
     * lightData : 50.0
     * lightHistoryTime : 2019-03-31 10:49:10
     */

    private int lightHistoryId;
    private int lightId;
    private String lightName;
    private double lightData;
    private String lightHistoryTime;

    public int getLightHistoryId() {
        return lightHistoryId;
    }

    public void setLightHistoryId(int lightHistoryId) {
        this.lightHistoryId = lightHistoryId;
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

    public String getLightHistoryTime() {
        return lightHistoryTime;
    }

    public void setLightHistoryTime(String lightHistoryTime) {
        this.lightHistoryTime = lightHistoryTime;
    }
}
