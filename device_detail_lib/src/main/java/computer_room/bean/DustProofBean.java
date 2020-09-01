package computer_room.bean;

import java.io.Serializable;

import base.BaseBean;

/**
 * 粉尘 Bean
 *
 * @author Create by Yin Luo Fei
 * @version 0.1
 * @Time 2019/8/11 21 : 47
 */
public class DustProofBean extends BaseBean implements Serializable {


    /**
     * edmId : 1
     * edmName : 粉尘111
     * edmAddress : 3
     * edmAlarmData : 45
     * edmCurrentData : 12
     * diId : 39
     * gId : 3
     * edmStatus : 2
     * intervalTime : 0
     */

    private int edmId;
    private String edmName;
    private int edmAddress;
    private int edmAlarmData;
    private int edmCurrentData;
    private int diId;
    private int gId;
    private int edmStatus;
    private int intervalTime;

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

    public int getEdmAddress() {
        return edmAddress;
    }

    public void setEdmAddress(int edmAddress) {
        this.edmAddress = edmAddress;
    }

    public int getEdmAlarmData() {
        return edmAlarmData;
    }

    public void setEdmAlarmData(int edmAlarmData) {
        this.edmAlarmData = edmAlarmData;
    }

    public int getEdmCurrentData() {
        return edmCurrentData;
    }

    public void setEdmCurrentData(int edmCurrentData) {
        this.edmCurrentData = edmCurrentData;
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

    public int getEdmStatus() {
        return edmStatus;
    }

    public void setEdmStatus(int edmStatus) {
        this.edmStatus = edmStatus;
    }

    public int getIntervalTime() {
        return intervalTime;
    }

    public void setIntervalTime(int intervalTime) {
        this.intervalTime = intervalTime;
    }
}
