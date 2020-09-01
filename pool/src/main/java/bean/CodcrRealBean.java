package bean;

import base.BaseBean;

/**
 * @ClassName: CodcrRealBean
 * @Description: Codcr分析-实时
 * @Author: YIN LUO FEI
 * @Date: 2020/3/28 0:20
 */
public class CodcrRealBean extends BaseBean {


    /**
     * codId : 1
     * codName : Codcr分析
     * codAddress : 2
     * codData : 40.0
     * codMaxData : 50.0
     * codMinData : 20.0
     * intervalTime : 30
     * codStatus : 2
     * diId : 1
     * gId : 1
     */

    private int codId;
    private String codName;
    private int codAddress;
    private double codData;
    private double codMaxData;
    private double codMinData;
    private int intervalTime;
    private int codStatus;
    private int diId;
    private int gId;

    public int getCodId() {
        return codId;
    }

    public void setCodId(int codId) {
        this.codId = codId;
    }

    public String getCodName() {
        return codName;
    }

    public void setCodName(String codName) {
        this.codName = codName;
    }

    public int getCodAddress() {
        return codAddress;
    }

    public void setCodAddress(int codAddress) {
        this.codAddress = codAddress;
    }

    public double getCodData() {
        return codData;
    }

    public void setCodData(double codData) {
        this.codData = codData;
    }

    public double getCodMaxData() {
        return codMaxData;
    }

    public void setCodMaxData(double codMaxData) {
        this.codMaxData = codMaxData;
    }

    public double getCodMinData() {
        return codMinData;
    }

    public void setCodMinData(double codMinData) {
        this.codMinData = codMinData;
    }

    public int getIntervalTime() {
        return intervalTime;
    }

    public void setIntervalTime(int intervalTime) {
        this.intervalTime = intervalTime;
    }

    public int getCodStatus() {
        return codStatus;
    }

    public void setCodStatus(int codStatus) {
        this.codStatus = codStatus;
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
