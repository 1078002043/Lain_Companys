package environment.bean;

/**
 * @ClassName: FiberAlertBean
 * @Description:
 * @Author: YIN LUO FEI
 * @Date: 2020/3/28 11:27
 */
public class FiberAlertBean {

    /**
     * opticalAlarmId : 1
     * opticalId : 1
     * opticalName : 45
     * opticalData : 42.0
     * info : 5205
     * opticalAlarmTime : 2019-07-28 11:33:38
     */

    private int opticalAlarmId;
    private int opticalId;
    private String opticalName;
    private double opticalData;
    private String info;
    private String opticalAlarmTime;

    public int getOpticalAlarmId() {
        return opticalAlarmId;
    }

    public void setOpticalAlarmId(int opticalAlarmId) {
        this.opticalAlarmId = opticalAlarmId;
    }

    public int getOpticalId() {
        return opticalId;
    }

    public void setOpticalId(int opticalId) {
        this.opticalId = opticalId;
    }

    public String getOpticalName() {
        return opticalName;
    }

    public void setOpticalName(String opticalName) {
        this.opticalName = opticalName;
    }

    public double getOpticalData() {
        return opticalData;
    }

    public void setOpticalData(double opticalData) {
        this.opticalData = opticalData;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getOpticalAlarmTime() {
        return opticalAlarmTime;
    }

    public void setOpticalAlarmTime(String opticalAlarmTime) {
        this.opticalAlarmTime = opticalAlarmTime;
    }
}
