package environment.bean;

/**
 * @ClassName: GasPressureHistoryBean
 * @Description: 气压强度-历史
 * @Author: YIN LUO FEI
 * @Date: 2020/3/28 10:33
 */
public class GasPressureHistoryBean {


    /**
     * gasPressureHistoryId : 1
     * gasPressureId : 1
     * gasPressureName : null
     * gasPressureData : 12.0
     * gasPressureHistoryTime : 2020-02-25 11:50:58
     */

    private int gasPressureHistoryId;
    private int gasPressureId;
    private Object gasPressureName;
    private double gasPressureData;
    private String gasPressureHistoryTime;

    public int getGasPressureHistoryId() {
        return gasPressureHistoryId;
    }

    public void setGasPressureHistoryId(int gasPressureHistoryId) {
        this.gasPressureHistoryId = gasPressureHistoryId;
    }

    public int getGasPressureId() {
        return gasPressureId;
    }

    public void setGasPressureId(int gasPressureId) {
        this.gasPressureId = gasPressureId;
    }

    public Object getGasPressureName() {
        return gasPressureName;
    }

    public void setGasPressureName(Object gasPressureName) {
        this.gasPressureName = gasPressureName;
    }

    public double getGasPressureData() {
        return gasPressureData;
    }

    public void setGasPressureData(double gasPressureData) {
        this.gasPressureData = gasPressureData;
    }

    public String getGasPressureHistoryTime() {
        return gasPressureHistoryTime;
    }

    public void setGasPressureHistoryTime(String gasPressureHistoryTime) {
        this.gasPressureHistoryTime = gasPressureHistoryTime;
    }
}
