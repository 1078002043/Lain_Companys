package bean;

/**
 * @ClassName: CirculationHistoryBean
 * @Description:
 * @Author: YIN LUO FEI
 * @Date: 2020/3/28 1:17
 */
public class CirculationHistoryBean {


    /**
     * flowCellHistoryId : 1
     * flowCellId : 1
     * flowCellName : 流
     * temp : 0.0
     * phValue : 22.0
     * flowCellHistoryTime : 2020-02-25 16:01:10
     */

    private int flowCellHistoryId;
    private int flowCellId;
    private String flowCellName;
    private double temp;
    private double phValue;
    private String flowCellHistoryTime;

    public int getFlowCellHistoryId() {
        return flowCellHistoryId;
    }

    public void setFlowCellHistoryId(int flowCellHistoryId) {
        this.flowCellHistoryId = flowCellHistoryId;
    }

    public int getFlowCellId() {
        return flowCellId;
    }

    public void setFlowCellId(int flowCellId) {
        this.flowCellId = flowCellId;
    }

    public String getFlowCellName() {
        return flowCellName;
    }

    public void setFlowCellName(String flowCellName) {
        this.flowCellName = flowCellName;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public double getPhValue() {
        return phValue;
    }

    public void setPhValue(double phValue) {
        this.phValue = phValue;
    }

    public String getFlowCellHistoryTime() {
        return flowCellHistoryTime;
    }

    public void setFlowCellHistoryTime(String flowCellHistoryTime) {
        this.flowCellHistoryTime = flowCellHistoryTime;
    }
}
