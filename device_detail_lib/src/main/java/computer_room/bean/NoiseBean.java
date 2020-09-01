package computer_room.bean;

import base.BaseBean;

/**
 * 噪声
 *
 * @author Create by Yin Luo Fei
 * @version 0.1
 * @Time 2019/8/12 17 : 28
 */
public class NoiseBean extends BaseBean {

    /**
     * enmId : 1
     * noiseName : 噪音1
     * noiseAddress : 2
     * noiseData : 40.0
     * maxNoise : 66.0
     * minNoise : 23.0
     * diId : 38
     * gId : 2
     * status : 2
     * intervalTime : 21
     */

    private int enmId;
    private String noiseName;
    private int noiseAddress;
    private double noiseData;
    private double maxNoise;
    private double minNoise;
    private int diId;
    private int gId;
    private int status;
    private int intervalTime;

    public int getEnmId() {
        return enmId;
    }

    public void setEnmId(int enmId) {
        this.enmId = enmId;
    }

    public String getNoiseName() {
        return noiseName;
    }

    public void setNoiseName(String noiseName) {
        this.noiseName = noiseName;
    }

    public int getNoiseAddress() {
        return noiseAddress;
    }

    public void setNoiseAddress(int noiseAddress) {
        this.noiseAddress = noiseAddress;
    }

    public double getNoiseData() {
        return noiseData;
    }

    public void setNoiseData(double noiseData) {
        this.noiseData = noiseData;
    }

    public double getMaxNoise() {
        return maxNoise;
    }

    public void setMaxNoise(double maxNoise) {
        this.maxNoise = maxNoise;
    }

    public double getMinNoise() {
        return minNoise;
    }

    public void setMinNoise(double minNoise) {
        this.minNoise = minNoise;
    }

    public int getDiId() {
        return diId;
    }

    public void setDiId(int diId) {
        this.diId = diId;
    }

    public int getGId() {
        return gId;
    }

    public void setGId(int gId) {
        this.gId = gId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getIntervalTime() {
        return intervalTime;
    }

    public void setIntervalTime(int intervalTime) {
        this.intervalTime = intervalTime;
    }
}
