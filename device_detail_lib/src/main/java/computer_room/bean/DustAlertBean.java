package computer_room.bean;

/**
 * 粉尘报警接口
 */
public class DustAlertBean {


    /**
     * edaId : 1
     * edmId : 1
     * edmName : 粉尘1
     * edaData : 0.0
     * edaInfo : 数值超出阈值
     * edaTime : 2020-01-02 09:11:33
     */

    private int edaId;
    private int edmId;
    private String edmName;
    private double edaData;
    private String edaInfo;
    private String edaTime;

    public int getEdaId() {
        return edaId;
    }

    public void setEdaId(int edaId) {
        this.edaId = edaId;
    }

    public int getEdmId() {
        return edmId;
    }

    public void setEdmId(int edmId) {
        this.edmId = edmId;
    }

    public String getEdmName() {
        return edmName;
    }

    public void setEdmName(String edmName) {
        this.edmName = edmName;
    }

    public double getEdaData() {
        return edaData;
    }

    public void setEdaData(double edaData) {
        this.edaData = edaData;
    }

    public String getEdaInfo() {
        return edaInfo;
    }

    public void setEdaInfo(String edaInfo) {
        this.edaInfo = edaInfo;
    }

    public String getEdaTime() {
        return edaTime;
    }

    public void setEdaTime(String edaTime) {
        this.edaTime = edaTime;
    }
}
