package bean;

public class ConductivityAlertBean {


     /**
     * conAlarmId : 1
     * conId : 1
     * conName : con
     * conData : 21.0
     * info : con12
     * conAlarmTime : 2020-01-15 11:54:33
     */

    private int conAlarmId;
    private int conId;
    private String conName;
    private double conData;
    private String info;
    private String conAlarmTime;

    public int getConAlarmId() {
        return conAlarmId;
    }

    public void setConAlarmId(int conAlarmId) {
        this.conAlarmId = conAlarmId;
    }

    public int getConId() {
        return conId;
    }

    public void setConId(int conId) {
        this.conId = conId;
    }

    public String getConName() {
        return conName;
    }

    public void setConName(String conName) {
        this.conName = conName;
    }

    public double getConData() {
        return conData;
    }

    public void setConData(double conData) {
        this.conData = conData;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getConAlarmTime() {
        return conAlarmTime;
    }

    public void setConAlarmTime(String conAlarmTime) {
        this.conAlarmTime = conAlarmTime;
    }
}
