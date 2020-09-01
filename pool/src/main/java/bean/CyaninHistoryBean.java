package bean;

/**
 * @ClassName: CyaninHistoryBean
 * @Description: 蓝藻素-历史
 * @Author: YIN LUO FEI
 * @Date: 2020/3/27 23:20
 */
public class CyaninHistoryBean {


    /**
     * cyaninHistoryId : 1
     * cyaninId : 1
     * cyaninName : 蓝藻素设备
     * cyaninData : 2.0
     * temp : 3.0
     * cyaninHistoryTime : 2020-02-24 11:46:13
     */

    private int cyaninHistoryId;
    private int cyaninId;
    private String cyaninName;
    private double cyaninData;
    private double temp;
    private String cyaninHistoryTime;

    public int getCyaninHistoryId() {
        return cyaninHistoryId;
    }

    public void setCyaninHistoryId(int cyaninHistoryId) {
        this.cyaninHistoryId = cyaninHistoryId;
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

    public String getCyaninHistoryTime() {
        return cyaninHistoryTime;
    }

    public void setCyaninHistoryTime(String cyaninHistoryTime) {
        this.cyaninHistoryTime = cyaninHistoryTime;
    }
}
