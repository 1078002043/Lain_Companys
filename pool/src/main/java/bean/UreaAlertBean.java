package bean;

/**
 * @ClassName: UreaAlertBean
 * @Description:
 * @Author: YIN LUO FEI
 * @Date: 2020/3/28 1:38
 */
public class UreaAlertBean {


    /**
     * ureaAlarmId : 1
     * ureaId : 1
     * ureaName : 588
     * ureaData : 50.0
     * info : 4
     * ureaAlarmTime : 2019-07-28 01:46:04
     */

    private int ureaAlarmId;
    private int ureaId;
    private String ureaName;
    private double ureaData;
    private String info;
    private String ureaAlarmTime;

    public int getUreaAlarmId() {
        return ureaAlarmId;
    }

    public void setUreaAlarmId(int ureaAlarmId) {
        this.ureaAlarmId = ureaAlarmId;
    }

    public int getUreaId() {
        return ureaId;
    }

    public void setUreaId(int ureaId) {
        this.ureaId = ureaId;
    }

    public String getUreaName() {
        return ureaName;
    }

    public void setUreaName(String ureaName) {
        this.ureaName = ureaName;
    }

    public double getUreaData() {
        return ureaData;
    }

    public void setUreaData(double ureaData) {
        this.ureaData = ureaData;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getUreaAlarmTime() {
        return ureaAlarmTime;
    }

    public void setUreaAlarmTime(String ureaAlarmTime) {
        this.ureaAlarmTime = ureaAlarmTime;
    }
}
