package computer_room.bean;

/**
 * 精密空调报警
 */
public class PrecisionAirAlertBean {


    /**
     * ecaId : 1
     * ecmId : 1
     * ecmDeviceName : 1号精密空调
     * diId : 4
     * ecaInfo : 精密空调报警
     * ecaTime : 2019-12-23 11:58:54
     */

    private int ecaId;
    private int ecmId;
    private String ecmDeviceName;
    private int diId;
    private String ecaInfo;
    private String ecaTime;

    public int getEcaId() {
        return ecaId;
    }

    public void setEcaId(int ecaId) {
        this.ecaId = ecaId;
    }

    public int getEcmId() {
        return ecmId;
    }

    public void setEcmId(int ecmId) {
        this.ecmId = ecmId;
    }

    public String getEcmDeviceName() {
        return ecmDeviceName;
    }

    public void setEcmDeviceName(String ecmDeviceName) {
        this.ecmDeviceName = ecmDeviceName;
    }

    public int getDiId() {
        return diId;
    }

    public void setDiId(int diId) {
        this.diId = diId;
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
