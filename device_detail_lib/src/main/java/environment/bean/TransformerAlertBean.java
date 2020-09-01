package environment.bean;

/**
 * @ClassName: TransformerAlertBean
 * @Description: 变压器温控器-报警
 * @Author: YIN LUO FEI
 * @Date: 2020/3/28 13:43
 */
public class TransformerAlertBean {


    /**
     * transformerAlarmId : 1
     * transformerId : 1
     * transformerName : iiiii
     * transformerDeviceAddress : 1
     * diId : 1
     * transformerAlarmInfo : 22
     * transformerAlarmTime : 2020-02-24 14:15:18
     */

    private int transformerAlarmId;
    private int transformerId;
    private String transformerName;
    private int transformerDeviceAddress;
    private int diId;
    private String transformerAlarmInfo;
    private String transformerAlarmTime;

    public int getTransformerAlarmId() {
        return transformerAlarmId;
    }

    public void setTransformerAlarmId(int transformerAlarmId) {
        this.transformerAlarmId = transformerAlarmId;
    }

    public int getTransformerId() {
        return transformerId;
    }

    public void setTransformerId(int transformerId) {
        this.transformerId = transformerId;
    }

    public String getTransformerName() {
        return transformerName;
    }

    public void setTransformerName(String transformerName) {
        this.transformerName = transformerName;
    }

    public int getTransformerDeviceAddress() {
        return transformerDeviceAddress;
    }

    public void setTransformerDeviceAddress(int transformerDeviceAddress) {
        this.transformerDeviceAddress = transformerDeviceAddress;
    }

    public int getDiId() {
        return diId;
    }

    public void setDiId(int diId) {
        this.diId = diId;
    }

    public String getTransformerAlarmInfo() {
        return transformerAlarmInfo;
    }

    public void setTransformerAlarmInfo(String transformerAlarmInfo) {
        this.transformerAlarmInfo = transformerAlarmInfo;
    }

    public String getTransformerAlarmTime() {
        return transformerAlarmTime;
    }

    public void setTransformerAlarmTime(String transformerAlarmTime) {
        this.transformerAlarmTime = transformerAlarmTime;
    }
}
