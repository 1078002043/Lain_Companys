package bean;

public class AcidBaseAlertBean {


    /**
     * ephaId : 1
     * ephaInfo : 1
     * ephaTime : 2020-01-10 13:43:49
     * thId : 1
     * diId : 1
     * deviceName : 酸碱仪
     */

    private int ephaId;
    private String ephaInfo;
    private String ephaTime;
    private int thId;
    private int diId;
    private String deviceName;

    public int getEphaId() {
        return ephaId;
    }

    public void setEphaId(int ephaId) {
        this.ephaId = ephaId;
    }

    public String getEphaInfo() {
        return ephaInfo;
    }

    public void setEphaInfo(String ephaInfo) {
        this.ephaInfo = ephaInfo;
    }

    public String getEphaTime() {
        return ephaTime;
    }

    public void setEphaTime(String ephaTime) {
        this.ephaTime = ephaTime;
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
}
