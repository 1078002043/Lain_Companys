package computer_room.bean;

/**
 * 二氧化碳 报警数据
 */
public class CarbonAlertBean {


    /**
     * ecaId : 1
     * ecmId : 1
     * ecmName : 二氧化碳1
     * ecaInfo : ffffff
     * ecaTime : 2019-12-24 14:15:46
     */

    private int ecaId;
    private String ecmId;
    private String ecmName;
    private String ecaInfo;
    private String ecaTime;

    public int getEcaId() {
        return ecaId;
    }

    public void setEcaId(int ecaId) {
        this.ecaId = ecaId;
    }

    public String getEcmId() {
        return ecmId;
    }

    public void setEcmId(String ecmId) {
        this.ecmId = ecmId;
    }

    public String getEcmName() {
        return ecmName;
    }

    public void setEcmName(String ecmName) {
        this.ecmName = ecmName;
    }

    public String getEcaInfo() {
        return ecaInfo;
    }

    public void setEcaInfo(String ecaInfo) {
        this.ecaInfo = ecaInfo;
    }

    public String getEcaTime() {
        return ecaTime;
    }

    public void setEcaTime(String ecaTime) {
        this.ecaTime = ecaTime;
    }
}
