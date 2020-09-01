package environment.bean;

/**
 * @ClassName: OutdoorTempHistoryBean
 * @Description: 室外温度变送器-历史
 * @Author: YIN LUO FEI
 * @Date: 2020/3/28 14:00
 */
public class OutdoorTempHistoryBean {


    /**
     * outdoorHumitureHistoryId : 1
     * outdoorHumitureTem : 12.0
     * outdoorHumitureHum : 22.0
     * outdoorHumitureHistoryTime : 2020-02-24 13:54:00
     * outdoorHumitureDeviceAddress : 1
     * diId : 1
     */

    private int outdoorHumitureHistoryId;
    private double outdoorHumitureTem;
    private double outdoorHumitureHum;
    private String outdoorHumitureHistoryTime;
    private int outdoorHumitureDeviceAddress;
    private int diId;

    public int getOutdoorHumitureHistoryId() {
        return outdoorHumitureHistoryId;
    }

    public void setOutdoorHumitureHistoryId(int outdoorHumitureHistoryId) {
        this.outdoorHumitureHistoryId = outdoorHumitureHistoryId;
    }

    public double getOutdoorHumitureTem() {
        return outdoorHumitureTem;
    }

    public void setOutdoorHumitureTem(double outdoorHumitureTem) {
        this.outdoorHumitureTem = outdoorHumitureTem;
    }

    public double getOutdoorHumitureHum() {
        return outdoorHumitureHum;
    }

    public void setOutdoorHumitureHum(double outdoorHumitureHum) {
        this.outdoorHumitureHum = outdoorHumitureHum;
    }

    public String getOutdoorHumitureHistoryTime() {
        return outdoorHumitureHistoryTime;
    }

    public void setOutdoorHumitureHistoryTime(String outdoorHumitureHistoryTime) {
        this.outdoorHumitureHistoryTime = outdoorHumitureHistoryTime;
    }

    public int getOutdoorHumitureDeviceAddress() {
        return outdoorHumitureDeviceAddress;
    }

    public void setOutdoorHumitureDeviceAddress(int outdoorHumitureDeviceAddress) {
        this.outdoorHumitureDeviceAddress = outdoorHumitureDeviceAddress;
    }

    public int getDiId() {
        return diId;
    }

    public void setDiId(int diId) {
        this.diId = diId;
    }
}
