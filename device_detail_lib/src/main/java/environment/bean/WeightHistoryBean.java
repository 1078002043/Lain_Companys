package environment.bean;

/**
 * @ClassName: WeightHistoryBean
 * @Description:
 * @Author: YIN LUO FEI
 * @Date: 2020/3/28 11:46
 */
public class WeightHistoryBean {


    /**
     * weighHistoryId : 1
     * weighId : 1
     * weighName : 54
     * weighData : 40.0
     * weighHistoryTime : 2019-07-28 11:48:07
     */

    private int weighHistoryId;
    private int weighId;
    private String weighName;
    private double weighData;
    private String weighHistoryTime;

    public int getWeighHistoryId() {
        return weighHistoryId;
    }

    public void setWeighHistoryId(int weighHistoryId) {
        this.weighHistoryId = weighHistoryId;
    }

    public int getWeighId() {
        return weighId;
    }

    public void setWeighId(int weighId) {
        this.weighId = weighId;
    }

    public String getWeighName() {
        return weighName;
    }

    public void setWeighName(String weighName) {
        this.weighName = weighName;
    }

    public double getWeighData() {
        return weighData;
    }

    public void setWeighData(double weighData) {
        this.weighData = weighData;
    }

    public String getWeighHistoryTime() {
        return weighHistoryTime;
    }

    public void setWeighHistoryTime(String weighHistoryTime) {
        this.weighHistoryTime = weighHistoryTime;
    }
}
