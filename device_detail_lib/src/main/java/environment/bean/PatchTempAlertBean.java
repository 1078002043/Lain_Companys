package environment.bean;

/**
 * @ClassName: PatchTempAlertBean
 * @Description:
 * @Author: YIN LUO FEI
 * @Date: 2020/3/28 11:09
 */
public class PatchTempAlertBean {


    /**
     * patchAlarmId : 1
     * patchId : 1
     * patchName : 贴片式温度
     * patchData : 1.0
     * info : 1
     * patchAlarmTime : 2020-02-24 10:18:32
     */

    private int patchAlarmId;
    private int patchId;
    private String patchName;
    private double patchData;
    private String info;
    private String patchAlarmTime;

    public int getPatchAlarmId() {
        return patchAlarmId;
    }

    public void setPatchAlarmId(int patchAlarmId) {
        this.patchAlarmId = patchAlarmId;
    }

    public int getPatchId() {
        return patchId;
    }

    public void setPatchId(int patchId) {
        this.patchId = patchId;
    }

    public String getPatchName() {
        return patchName;
    }

    public void setPatchName(String patchName) {
        this.patchName = patchName;
    }

    public double getPatchData() {
        return patchData;
    }

    public void setPatchData(double patchData) {
        this.patchData = patchData;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getPatchAlarmTime() {
        return patchAlarmTime;
    }

    public void setPatchAlarmTime(String patchAlarmTime) {
        this.patchAlarmTime = patchAlarmTime;
    }
}
