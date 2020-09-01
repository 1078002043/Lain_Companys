package environment.bean;

/**
 * @ClassName: FiberHistoryBean
 * @Description:
 * @Author: YIN LUO FEI
 * @Date: 2020/3/28 11:28
 */
public class FiberHistoryBean {


    /**
     * opticalHistoryId : 1
     * opticalId : 1
     * opticalName : 5245
     * opticalData : 50.0
     * opticalHistoryTime : 2019-07-28 11:33:52
     */

    private int opticalHistoryId;
    private int opticalId;
    private String opticalName;
    private double opticalData;
    private String opticalHistoryTime;

    public int getOpticalHistoryId() {
        return opticalHistoryId;
    }

    public void setOpticalHistoryId(int opticalHistoryId) {
        this.opticalHistoryId = opticalHistoryId;
    }

    public int getOpticalId() {
        return opticalId;
    }

    public void setOpticalId(int opticalId) {
        this.opticalId = opticalId;
    }

    public String getOpticalName() {
        return opticalName;
    }

    public void setOpticalName(String opticalName) {
        this.opticalName = opticalName;
    }

    public double getOpticalData() {
        return opticalData;
    }

    public void setOpticalData(double opticalData) {
        this.opticalData = opticalData;
    }

    public String getOpticalHistoryTime() {
        return opticalHistoryTime;
    }

    public void setOpticalHistoryTime(String opticalHistoryTime) {
        this.opticalHistoryTime = opticalHistoryTime;
    }
}
