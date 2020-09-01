package bean;

/**
 * @ClassName: OrpHistoryBean
 * @Description: ORP变送器-历史
 * @Author: YIN LUO FEI
 * @Date: 2020/3/28 0:40
 */
public class OrpHistoryBean {


    /**
     * orpMeterHistoryId : 1
     * orpMeterId : 1
     * orpMeterName : 报错
     * orpMeterData : 22.0
     * orpMeterHistoryTime : 2020-02-26 11:23:24
     */

    private int orpMeterHistoryId;
    private int orpMeterId;
    private String orpMeterName;
    private double orpMeterData;
    private String orpMeterHistoryTime;

    public int getOrpMeterHistoryId() {
        return orpMeterHistoryId;
    }

    public void setOrpMeterHistoryId(int orpMeterHistoryId) {
        this.orpMeterHistoryId = orpMeterHistoryId;
    }

    public int getOrpMeterId() {
        return orpMeterId;
    }

    public void setOrpMeterId(int orpMeterId) {
        this.orpMeterId = orpMeterId;
    }

    public String getOrpMeterName() {
        return orpMeterName;
    }

    public void setOrpMeterName(String orpMeterName) {
        this.orpMeterName = orpMeterName;
    }

    public double getOrpMeterData() {
        return orpMeterData;
    }

    public void setOrpMeterData(double orpMeterData) {
        this.orpMeterData = orpMeterData;
    }

    public String getOrpMeterHistoryTime() {
        return orpMeterHistoryTime;
    }

    public void setOrpMeterHistoryTime(String orpMeterHistoryTime) {
        this.orpMeterHistoryTime = orpMeterHistoryTime;
    }
}
