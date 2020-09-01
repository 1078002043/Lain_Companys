package bean;

/**
 * @ClassName: SewageAlertBean
 * @Description: 污水流量-报警
 * @Author: YIN LUO FEI
 * @Date: 2020/3/27 22:26
 */
public class SewageAlertBean {


    /**
     * sewageDischargeAlarmId : 1
     * sewageDischargeId : 1
     * sewageDischargeName : 污水流量
     * sewageDischargeData : 40.0
     * info : dsfsd
     * sewageDischargeAlarmTime : 2019-07-27 22:29:06
     */

    private int sewageDischargeAlarmId;
    private int sewageDischargeId;
    private String sewageDischargeName;
    private double sewageDischargeData;
    private String info;
    private String sewageDischargeAlarmTime;

    public int getSewageDischargeAlarmId() {
        return sewageDischargeAlarmId;
    }

    public void setSewageDischargeAlarmId(int sewageDischargeAlarmId) {
        this.sewageDischargeAlarmId = sewageDischargeAlarmId;
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

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getSewageDischargeAlarmTime() {
        return sewageDischargeAlarmTime;
    }

    public void setSewageDischargeAlarmTime(String sewageDischargeAlarmTime) {
        this.sewageDischargeAlarmTime = sewageDischargeAlarmTime;
    }
}
