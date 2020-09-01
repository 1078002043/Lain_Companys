package environment.bean;

/**
 * @ClassName: GasPressureAlertBean
 * @Description: 气压强度-报警
 * @Author: YIN LUO FEI
 * @Date: 2020/3/28 10:33
 */
public class GasPressureAlertBean {


    /**
     * gasPressureAlarmId : 1
     * gasPressureId : 1
     * gasPressureName : frdsfsd
     * gasPressureData : 50.0
     * info : 858
     * gasPressureAlarmTime : 2019-03-28 10:35:35
     */

    private int gasPressureAlarmId;
    private int gasPressureId;
    private String gasPressureName;
    private double gasPressureData;
    private String info;
    private String gasPressureAlarmTime;

    public int getGasPressureAlarmId() {
        return gasPressureAlarmId;
    }

    public void setGasPressureAlarmId(int gasPressureAlarmId) {
        this.gasPressureAlarmId = gasPressureAlarmId;
    }

    public int getGasPressureId() {
        return gasPressureId;
    }

    public void setGasPressureId(int gasPressureId) {
        this.gasPressureId = gasPressureId;
    }

    public String getGasPressureName() {
        return gasPressureName;
    }

    public void setGasPressureName(String gasPressureName) {
        this.gasPressureName = gasPressureName;
    }

    public double getGasPressureData() {
        return gasPressureData;
    }

    public void setGasPressureData(double gasPressureData) {
        this.gasPressureData = gasPressureData;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getGasPressureAlarmTime() {
        return gasPressureAlarmTime;
    }

    public void setGasPressureAlarmTime(String gasPressureAlarmTime) {
        this.gasPressureAlarmTime = gasPressureAlarmTime;
    }
}
