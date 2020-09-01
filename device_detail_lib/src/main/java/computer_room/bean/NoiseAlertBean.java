package computer_room.bean;

/**
 * 噪声 报警信息
 */
public class NoiseAlertBean {


    /**
     * enaId : 1
     * enmId : 1
     * info : 数值超过报警值
     * noiseName : 噪音1
     * time : 2019-12-28 15:32:34
     */

    private int enaId;
    private int enmId;
    private String info;
    private String noiseName;
    private String time;

    public int getEnaId() {
        return enaId;
    }

    public void setEnaId(int enaId) {
        this.enaId = enaId;
    }

    public int getEnmId() {
        return enmId;
    }

    public void setEnmId(int enmId) {
        this.enmId = enmId;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getNoiseName() {
        return noiseName;
    }

    public void setNoiseName(String noiseName) {
        this.noiseName = noiseName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
