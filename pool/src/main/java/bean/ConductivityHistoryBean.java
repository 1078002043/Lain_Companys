package bean;

public class ConductivityHistoryBean {

    /**
     * conHistoryId : 1
     * conId : 1
     * conName : null
     * conData : 12.0
     * conHistoryTime : 2020-01-15 10:55:59
     */

    private int conHistoryId;
    private int conId;
    private Object conName;
    private double conData;
    private String conHistoryTime;

    public int getConHistoryId() {
        return conHistoryId;
    }

    public void setConHistoryId(int conHistoryId) {
        this.conHistoryId = conHistoryId;
    }

    public int getConId() {
        return conId;
    }

    public void setConId(int conId) {
        this.conId = conId;
    }

    public Object getConName() {
        return conName;
    }

    public void setConName(Object conName) {
        this.conName = conName;
    }

    public double getConData() {
        return conData;
    }

    public void setConData(double conData) {
        this.conData = conData;
    }

    public String getConHistoryTime() {
        return conHistoryTime;
    }

    public void setConHistoryTime(String conHistoryTime) {
        this.conHistoryTime = conHistoryTime;
    }
}
