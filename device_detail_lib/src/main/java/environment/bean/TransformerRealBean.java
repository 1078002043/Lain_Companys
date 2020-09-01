package environment.bean;

import base.BaseBean;

/**
 * @ClassName: TransformerRealBean
 * @Description: 变压器温控器- 实时
 * @Author: YIN LUO FEI
 * @Date: 2020/3/28 13:43
 */
public class TransformerRealBean extends BaseBean {


    /**
     * dId : 0
     * name : null
     * isShow : 0
     * diId : 0
     * diAddress : null
     * diPort : 0
     * diIsConnect : 0
     * diOperate : 0
     * gId : 0
     * transformerId : 1
     * transformerDeviceAddress : 0
     * transformerDeviceFunction : 0
     * transformerDeviceName : 温控器2
     * transformerTem : 22.0
     * transformerHum : 11.0
     * transformerMaxTem : 0.0
     * transformerMinTem : 0.0
     * transformerMaxHum : 0.0
     * transformerMinHum : 0.0
     * transformerStatus : 2
     * intervalTime : 0
     */

    private int dId;
    private Object name;
    private int isShow;
    private int diId;
    private Object diAddress;
    private int diPort;
    private int diIsConnect;
    private int diOperate;
    private int gId;
    private int transformerId;
    private int transformerDeviceAddress;
    private int transformerDeviceFunction;
    private String transformerDeviceName;
    private double transformerTem;
    private double transformerHum;
    private double transformerMaxTem;
    private double transformerMinTem;
    private double transformerMaxHum;
    private double transformerMinHum;
    private int transformerStatus;
    private int intervalTime;

    public int getDId() {
        return dId;
    }

    public void setDId(int dId) {
        this.dId = dId;
    }

    public Object getName() {
        return name;
    }

    public void setName(Object name) {
        this.name = name;
    }

    public int getIsShow() {
        return isShow;
    }

    public void setIsShow(int isShow) {
        this.isShow = isShow;
    }

    public int getDiId() {
        return diId;
    }

    public void setDiId(int diId) {
        this.diId = diId;
    }

    public Object getDiAddress() {
        return diAddress;
    }

    public void setDiAddress(Object diAddress) {
        this.diAddress = diAddress;
    }

    public int getDiPort() {
        return diPort;
    }

    public void setDiPort(int diPort) {
        this.diPort = diPort;
    }

    public int getDiIsConnect() {
        return diIsConnect;
    }

    public void setDiIsConnect(int diIsConnect) {
        this.diIsConnect = diIsConnect;
    }

    public int getDiOperate() {
        return diOperate;
    }

    public void setDiOperate(int diOperate) {
        this.diOperate = diOperate;
    }

    public int getGId() {
        return gId;
    }

    public void setGId(int gId) {
        this.gId = gId;
    }

    public int getTransformerId() {
        return transformerId;
    }

    public void setTransformerId(int transformerId) {
        this.transformerId = transformerId;
    }

    public int getTransformerDeviceAddress() {
        return transformerDeviceAddress;
    }

    public void setTransformerDeviceAddress(int transformerDeviceAddress) {
        this.transformerDeviceAddress = transformerDeviceAddress;
    }

    public int getTransformerDeviceFunction() {
        return transformerDeviceFunction;
    }

    public void setTransformerDeviceFunction(int transformerDeviceFunction) {
        this.transformerDeviceFunction = transformerDeviceFunction;
    }

    public String getTransformerDeviceName() {
        return transformerDeviceName;
    }

    public void setTransformerDeviceName(String transformerDeviceName) {
        this.transformerDeviceName = transformerDeviceName;
    }

    public double getTransformerTem() {
        return transformerTem;
    }

    public void setTransformerTem(double transformerTem) {
        this.transformerTem = transformerTem;
    }

    public double getTransformerHum() {
        return transformerHum;
    }

    public void setTransformerHum(double transformerHum) {
        this.transformerHum = transformerHum;
    }

    public double getTransformerMaxTem() {
        return transformerMaxTem;
    }

    public void setTransformerMaxTem(double transformerMaxTem) {
        this.transformerMaxTem = transformerMaxTem;
    }

    public double getTransformerMinTem() {
        return transformerMinTem;
    }

    public void setTransformerMinTem(double transformerMinTem) {
        this.transformerMinTem = transformerMinTem;
    }

    public double getTransformerMaxHum() {
        return transformerMaxHum;
    }

    public void setTransformerMaxHum(double transformerMaxHum) {
        this.transformerMaxHum = transformerMaxHum;
    }

    public double getTransformerMinHum() {
        return transformerMinHum;
    }

    public void setTransformerMinHum(double transformerMinHum) {
        this.transformerMinHum = transformerMinHum;
    }

    public int getTransformerStatus() {
        return transformerStatus;
    }

    public void setTransformerStatus(int transformerStatus) {
        this.transformerStatus = transformerStatus;
    }

    public int getIntervalTime() {
        return intervalTime;
    }

    public void setIntervalTime(int intervalTime) {
        this.intervalTime = intervalTime;
    }
}
