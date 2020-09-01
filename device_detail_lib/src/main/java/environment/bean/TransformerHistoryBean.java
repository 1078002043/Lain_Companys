package environment.bean;

/**
 * @ClassName: TransformerHistoryBean
 * @Description: 变压器温控器-历史
 * @Author: YIN LUO FEI
 * @Date: 2020/3/28 13:44
 */
public class TransformerHistoryBean {


    /**
     * transformerHistoryId : 1
     * transformerId : 1
     * transformerName : 温控1
     * transformerTem : 22.0
     * transformerHum : 30.0
     * transformerHistoryTime : 2020-02-24 14:14:50
     * transformerDeviceAddress : 1
     * diId : 1
     */

    private int transformerHistoryId;
    private int transformerId;
    private String transformerName;
    private double transformerTem;
    private double transformerHum;
    private String transformerHistoryTime;
    private int transformerDeviceAddress;
    private int diId;

    public int getTransformerHistoryId() {
        return transformerHistoryId;
    }

    public void setTransformerHistoryId(int transformerHistoryId) {
        this.transformerHistoryId = transformerHistoryId;
    }

    public int getTransformerId() {
        return transformerId;
    }

    public void setTransformerId(int transformerId) {
        this.transformerId = transformerId;
    }

    public String getTransformerName() {
        return transformerName;
    }

    public void setTransformerName(String transformerName) {
        this.transformerName = transformerName;
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

    public String getTransformerHistoryTime() {
        return transformerHistoryTime;
    }

    public void setTransformerHistoryTime(String transformerHistoryTime) {
        this.transformerHistoryTime = transformerHistoryTime;
    }

    public int getTransformerDeviceAddress() {
        return transformerDeviceAddress;
    }

    public void setTransformerDeviceAddress(int transformerDeviceAddress) {
        this.transformerDeviceAddress = transformerDeviceAddress;
    }

    public int getDiId() {
        return diId;
    }

    public void setDiId(int diId) {
        this.diId = diId;
    }
}
