package bean;

public class NitriteAlertBean {

    /**
     * nitriteAlarmId : 3
     * nitriteId : 3
     * nitriteName : 亚硝酸盐3号
     * nitriteData : 20.0
     * info : 报警
     * nitriteAlarmTime : 2019-07-27 16:50:53
     */

    private int nitriteAlarmId;
    private int nitriteId;
    private String nitriteName;
    private double nitriteData;
    private String info;
    private String nitriteAlarmTime;

    public int getNitriteAlarmId() {
        return nitriteAlarmId;
    }

    public void setNitriteAlarmId(int nitriteAlarmId) {
        this.nitriteAlarmId = nitriteAlarmId;
    }

    public int getNitriteId() {
        return nitriteId;
    }

    public void setNitriteId(int nitriteId) {
        this.nitriteId = nitriteId;
    }

    public String getNitriteName() {
        return nitriteName;
    }

    public void setNitriteName(String nitriteName) {
        this.nitriteName = nitriteName;
    }

    public double getNitriteData() {
        return nitriteData;
    }

    public void setNitriteData(double nitriteData) {
        this.nitriteData = nitriteData;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getNitriteAlarmTime() {
        return nitriteAlarmTime;
    }

    public void setNitriteAlarmTime(String nitriteAlarmTime) {
        this.nitriteAlarmTime = nitriteAlarmTime;
    }
}
