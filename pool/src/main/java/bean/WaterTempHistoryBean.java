package bean;

/**
 * @ClassName: WaterTempHistoryBean
 * @Description: 水温的历史数据
 * @Author: YIN LUO FEI
 * @Date: 2020/3/27 11:47
 */

public class WaterTempHistoryBean {


    /**
     * waterTempHistoryId : 1
     * waterTempId : 3
     * waterTempName : 水温1
     * waterTempData : 20.0
     * waterTempHistoryTime : 2020-01-09 13:47:45
     */

    private int waterTempHistoryId;
    private int waterTempId;
    private String waterTempName;
    private double waterTempData;
    private String waterTempHistoryTime;

    public int getWaterTempHistoryId() {
        return waterTempHistoryId;
    }

    public void setWaterTempHistoryId(int waterTempHistoryId) {
        this.waterTempHistoryId = waterTempHistoryId;
    }

    public int getWaterTempId() {
        return waterTempId;
    }

    public void setWaterTempId(int waterTempId) {
        this.waterTempId = waterTempId;
    }

    public String getWaterTempName() {
        return waterTempName;
    }

    public void setWaterTempName(String waterTempName) {
        this.waterTempName = waterTempName;
    }

    public double getWaterTempData() {
        return waterTempData;
    }

    public void setWaterTempData(double waterTempData) {
        this.waterTempData = waterTempData;
    }

    public String getWaterTempHistoryTime() {
        return waterTempHistoryTime;
    }

    public void setWaterTempHistoryTime(String waterTempHistoryTime) {
        this.waterTempHistoryTime = waterTempHistoryTime;
    }
}
