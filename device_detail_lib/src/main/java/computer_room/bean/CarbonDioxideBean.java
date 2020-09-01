package computer_room.bean;

import base.BaseBean;

/**
 * 二氧化碳 实时数据
 *
 * @author Create by Yin Luo Fei
 * @version 0.1
 * @Time 2019/8/11 21 : 33
 */
public class CarbonDioxideBean extends BaseBean {

    /**
     * ecmId : 1
     * ecmAddress : 1
     * ecmName : 二氧化碳1
     * ecmAlarmData : 60.0
     * ecmCurrentData : 11.0
     * diId : 20
     * gId : 2
     * ecmStatus : 2
     */

    private int ecmId;
    private int ecmAddress;
    private String ecmName;
    private double ecmAlarmData;
    private double ecmCurrentData;
    private int diId;
    private int gId;
    private int ecmStatus;

    public int getEcmId() {
        return ecmId;
    }

    public void setEcmId(int ecmId) {
        this.ecmId = ecmId;
    }

    public int getEcmAddress() {
        return ecmAddress;
    }

    public void setEcmAddress(int ecmAddress) {
        this.ecmAddress = ecmAddress;
    }

    public String getEcmName() {
        return ecmName;
    }

    public void setEcmName(String ecmName) {
        this.ecmName = ecmName;
    }

    public double getEcmAlarmData() {
        return ecmAlarmData;
    }

    public void setEcmAlarmData(double ecmAlarmData) {
        this.ecmAlarmData = ecmAlarmData;
    }

    public double getEcmCurrentData() {
        return ecmCurrentData;
    }

    public void setEcmCurrentData(double ecmCurrentData) {
        this.ecmCurrentData = ecmCurrentData;
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

    public int getEcmStatus() {
        return ecmStatus;
    }

    public void setEcmStatus(int ecmStatus) {
        this.ecmStatus = ecmStatus;
    }
}
