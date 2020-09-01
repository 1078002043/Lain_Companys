package bean;

/**
 * @ClassName: SewageHistoryBean
 * @Description: 污水流量-历史
 * @Author: YIN LUO FEI
 * @Date: 2020/3/27 22:26
 */
public class SewageHistoryBean {

    /**
     * sewageDischargeHistoryId : 1
     * sewageDischargeId : 1
     * sewageDischargeName : 污水流量
     * sewageDischargeData : 40.0
     * sewageDischargeHistoryTime : 2019-06-27 22:29:36
     */

    private int sewageDischargeHistoryId;
    private int sewageDischargeId;
    private String sewageDischargeName;
    private double sewageDischargeData;
    private String sewageDischargeHistoryTime;

    public int getSewageDischargeHistoryId() {
        return sewageDischargeHistoryId;
    }

    public void setSewageDischargeHistoryId(int sewageDischargeHistoryId) {
        this.sewageDischargeHistoryId = sewageDischargeHistoryId;
    }

    public int getSewageDischargeId() {
        return sewageDischargeId;
    }

    public void setSewageDischargeId(int sewageDischargeId) {
        this.sewageDischargeId = sewageDischargeId;
    }

    public String getSewageDischargeName() {
        return sewageDischargeName;
    }

    public void setSewageDischargeName(String sewageDischargeName) {
        this.sewageDischargeName = sewageDischargeName;
    }

    public double getSewageDischargeData() {
        return sewageDischargeData;
    }

    public void setSewageDischargeData(double sewageDischargeData) {
        this.sewageDischargeData = sewageDischargeData;
    }

    public String getSewageDischargeHistoryTime() {
        return sewageDischargeHistoryTime;
    }

    public void setSewageDischargeHistoryTime(String sewageDischargeHistoryTime) {
        this.sewageDischargeHistoryTime = sewageDischargeHistoryTime;
    }
}
