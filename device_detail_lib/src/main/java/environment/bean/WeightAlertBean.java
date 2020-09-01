package environment.bean;

/**
 * @ClassName: WeightAlertBean
 * @Description:
 * @Author: YIN LUO FEI
 * @Date: 2020/3/28 11:46
 */
public class WeightAlertBean {


    /**
     * weighAlarmId : 1
     * weighId : 1
     * weighName : 54
     * weighData : 40.0
     * info : 54
     * weighAlarmTime : 2019-04-01 11:47:49
     */

    private int weighAlarmId;
    private int weighId;
    private String weighName;
    private double weighData;
    private String info;
    private String weighAlarmTime;

    public int getWeighAlarmId() {
        return weighAlarmId;
    }

    public void setWeighAlarmId(int weighAlarmId) {
        this.weighAlarmId = weighAlarmId;
    }

    public int getWeighId() {
        return weighId;
    }

    public void setWeighId(int weighId) {
        this.weighId = weighId;
    }

    public String getWeighName() {
        return weighName;
    }

    public void setWeighName(String weighName) {
        this.weighName = weighName;
    }

    public double getWeighData() {
        return weighData;
    }

    public void setWeighData(double weighData) {
        this.weighData = weighData;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getWeighAlarmTime() {
        return weighAlarmTime;
    }

    public void setWeighAlarmTime(String weighAlarmTime) {
        this.weighAlarmTime = weighAlarmTime;
    }
}
