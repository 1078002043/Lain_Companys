package computer_room.bean;

/**
 * 电量仪报警数据
 */
public class ElectricityAlertBean {

    /**
     * peaId : 1
     * pemId : 1
     * pemName : 电量仪
     * peaTime : 2019-12-28 15:52:21
     * peaInfo : A相电压超出报警值
     */

    private int peaId;
    private int pemId;
    private String pemName;
    private String peaTime;
    private String peaInfo;

    public int getPeaId() {
        return peaId;
    }

    public void setPeaId(int peaId) {
        this.peaId = peaId;
    }

    public int getPemId() {
        return pemId;
    }

    public void setPemId(int pemId) {
        this.pemId = pemId;
    }

    public String getPemName() {
        return pemName;
    }

    public void setPemName(String pemName) {
        this.pemName = pemName;
    }

    public String getPeaTime() {
        return peaTime;
    }

    public void setPeaTime(String peaTime) {
        this.peaTime = peaTime;
    }

    public String getPeaInfo() {
        return peaInfo;
    }

    public void setPeaInfo(String peaInfo) {
        this.peaInfo = peaInfo;
    }
}
