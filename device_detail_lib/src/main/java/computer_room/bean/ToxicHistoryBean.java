package computer_room.bean;

/**
 * 有毒气体历史数据
 */
public class ToxicHistoryBean {


    /**
     * ephId : 1
     * ephData : 12.0
     * epmAddress : 1
     * diId : 24
     * ephTime : 2020-01-02 14:56:09
     */

    private int ephId;
    private double ephData;
    private int epmAddress;
    private int diId;
    private String ephTime;

    public int getEphId() {
        return ephId;
    }

    public void setEphId(int ephId) {
        this.ephId = ephId;
    }

    public double getEphData() {
        return ephData;
    }

    public void setEphData(double ephData) {
        this.ephData = ephData;
    }

    public int getEpmAddress() {
        return epmAddress;
    }

    public void setEpmAddress(int epmAddress) {
        this.epmAddress = epmAddress;
    }

    public int getDiId() {
        return diId;
    }

    public void setDiId(int diId) {
        this.diId = diId;
    }

    public String getEphTime() {
        return ephTime;
    }

    public void setEphTime(String ephTime) {
        this.ephTime = ephTime;
    }
}
