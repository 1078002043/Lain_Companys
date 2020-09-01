package bean;

/**
 * @ClassName: CirculationAlertBean
 * @Description:
 * @Author: YIN LUO FEI
 * @Date: 2020/3/28 1:17
 */
public class CirculationAlertBean {


    /**
     * flowCellAlarmId : 1
     * flowCellId : 1
     * flowCellName : *****
     * temp : 0.0
     * phValue : 33.0
     * info : 报警
     * flowCellAlarmTime : 2020-02-25 16:02:31
     */

    private int flowCellAlarmId;
    private int flowCellId;
    private String flowCellName;
    private double temp;
    private double phValue;
    private String info;
    private String flowCellAlarmTime;

    public int getFlowCellAlarmId() {
        return flowCellAlarmId;
    }

    public void setFlowCellAlarmId(int flowCellAlarmId) {
        this.flowCellAlarmId = flowCellAlarmId;
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

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getFlowCellAlarmTime() {
        return flowCellAlarmTime;
    }

    public void setFlowCellAlarmTime(String flowCellAlarmTime) {
        this.flowCellAlarmTime = flowCellAlarmTime;
    }
}
