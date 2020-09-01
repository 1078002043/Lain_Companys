package bean;

/**
 * 溶氧仪 报警数据
 */
public class DissolvedAlertBean {


    /**
     * edoaId : 1
     * edoaInfo : 检测温度27.5°C超过报警阈值20.0°C。
     * edoaTime : 2019-08-21 15:42:32
     * thId : 1
     * diId : 1
     * deviceName : 1号溶氧仪设备
     * deviceTempName : 1溶氧仪设备温度
     */

    private int edoaId;
    private String edoaInfo;
    private String edoaTime;
    private int thId;
    private int diId;
    private String deviceName;
    private String deviceTempName;

    public int getEdoaId() {
        return edoaId;
    }

    public void setEdoaId(int edoaId) {
        this.edoaId = edoaId;
    }

    public String getEdoaInfo() {
        return edoaInfo;
    }

    public void setEdoaInfo(String edoaInfo) {
        this.edoaInfo = edoaInfo;
    }

    public String getEdoaTime() {
        return edoaTime;
    }

    public void setEdoaTime(String edoaTime) {
        this.edoaTime = edoaTime;
    }

    public int getThId() {
        return thId;
    }

    public void setThId(int thId) {
        this.thId = thId;
    }

    public int getDiId() {
        return diId;
    }

    public void setDiId(int diId) {
        this.diId = diId;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDeviceTempName() {
        return deviceTempName;
    }

    public void setDeviceTempName(String deviceTempName) {
        this.deviceTempName = deviceTempName;
    }
}
