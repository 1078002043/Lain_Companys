package computer_room.bean;

/**
 * 噪音历史BEAN
 */
public class NoiseHistoryBean {


    /**
     * enhId : 1
     * enhNoise : 34
     * enmAddress : 2
     * diId : 38
     * enhTime : 2020-01-02 14:44:41
     */

    private int enhId;
    private int enhNoise;
    private int enmAddress;
    private int diId;
    private String enhTime;

    public int getEnhId() {
        return enhId;
    }

    public void setEnhId(int enhId) {
        this.enhId = enhId;
    }

    public int getEnhNoise() {
        return enhNoise;
    }

    public void setEnhNoise(int enhNoise) {
        this.enhNoise = enhNoise;
    }

    public int getEnmAddress() {
        return enmAddress;
    }

    public void setEnmAddress(int enmAddress) {
        this.enmAddress = enmAddress;
    }

    public int getDiId() {
        return diId;
    }

    public void setDiId(int diId) {
        this.diId = diId;
    }

    public String getEnhTime() {
        return enhTime;
    }

    public void setEnhTime(String enhTime) {
        this.enhTime = enhTime;
    }
}
