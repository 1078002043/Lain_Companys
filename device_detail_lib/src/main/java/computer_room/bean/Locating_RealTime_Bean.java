package computer_room.bean;

import java.io.Serializable;

/**
 * 定位漏水 实时数据 BEAN
 *
 * @author Create by Yin Luo Fei
 * @version 0.1
 * @Time 2019/8/8 22 : 52
 */
public class Locating_RealTime_Bean implements Serializable {


    /**
     * elmId : 1
     * elmName : 定位漏水1
     * elmAddress : 1
     * elmLength : 0.0
     * elmStatus : 0
     * diId : 4
     * gId : 2
     */

    private int elmId;
    private String elmName;
    private int elmAddress;
    private double elmLength;
    private int elmStatus;
    private int diId;
    private int gId;

    public int getElmId() {
        return elmId;
    }

    public void setElmId(int elmId) {
        this.elmId = elmId;
    }

    public String getElmName() {
        return elmName;
    }

    public void setElmName(String elmName) {
        this.elmName = elmName;
    }

    public int getElmAddress() {
        return elmAddress;
    }

    public void setElmAddress(int elmAddress) {
        this.elmAddress = elmAddress;
    }

    public double getElmLength() {
        return elmLength;
    }

    public void setElmLength(double elmLength) {
        this.elmLength = elmLength;
    }

    public int getElmStatus() {
        return elmStatus;
    }

    public void setElmStatus(int elmStatus) {
        this.elmStatus = elmStatus;
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
}
