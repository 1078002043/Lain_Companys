package bean;

public class SalinityHistoryBean {


    /**
     * salinityHistoryId : 1
     * salinityId : 1
     * salinityName : 盐度1号
     * salinityData : 30.0
     * salinityHistoryTime : 2019-12-18 15:28:49
     */

    private int salinityHistoryId;
    private int salinityId;
    private String salinityName;
    private double salinityData;
    private String salinityHistoryTime;

    public int getSalinityHistoryId() {
        return salinityHistoryId;
    }

    public void setSalinityHistoryId(int salinityHistoryId) {
        this.salinityHistoryId = salinityHistoryId;
    }

    public int getSalinityId() {
        return salinityId;
    }

    public void setSalinityId(int salinityId) {
        this.salinityId = salinityId;
    }

    public String getSalinityName() {
        return salinityName;
    }

    public void setSalinityName(String salinityName) {
        this.salinityName = salinityName;
    }

    public double getSalinityData() {
        return salinityData;
    }

    public void setSalinityData(double salinityData) {
        this.salinityData = salinityData;
    }

    public String getSalinityHistoryTime() {
        return salinityHistoryTime;
    }

    public void setSalinityHistoryTime(String salinityHistoryTime) {
        this.salinityHistoryTime = salinityHistoryTime;
    }
}
