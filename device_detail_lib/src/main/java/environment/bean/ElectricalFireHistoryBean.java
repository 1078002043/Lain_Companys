package environment.bean;

/**
 * @ClassName: ElectricalFireHistory
 * @Description:
 * @Author: YIN LUO FEI
 * @Date: 2020/3/28 13:12
 */
public class ElectricalFireHistoryBean {

    /**
     * electricalFireHistoryId : 1
     * electricalFireId : 2
     * electricalFireName : 1号设备
     * temp : 12.0
     * current : 33.0
     * electricalFireHistoryTime : 2020-02-26T01:49:06.000+0000
     */

    private int electricalFireHistoryId;
    private int electricalFireId;
    private String electricalFireName;
    private double temp;
    private double current;
    private String electricalFireHistoryTime;

    public int getElectricalFireHistoryId() {
        return electricalFireHistoryId;
    }

    public void setElectricalFireHistoryId(int electricalFireHistoryId) {
        this.electricalFireHistoryId = electricalFireHistoryId;
    }

    public int getElectricalFireId() {
        return electricalFireId;
    }

    public void setElectricalFireId(int electricalFireId) {
        this.electricalFireId = electricalFireId;
    }

    public String getElectricalFireName() {
        return electricalFireName;
    }

    public void setElectricalFireName(String electricalFireName) {
        this.electricalFireName = electricalFireName;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public double getCurrent() {
        return current;
    }

    public void setCurrent(double current) {
        this.current = current;
    }

    public String getElectricalFireHistoryTime() {
        return electricalFireHistoryTime;
    }

    public void setElectricalFireHistoryTime(String electricalFireHistoryTime) {
        this.electricalFireHistoryTime = electricalFireHistoryTime;
    }
}
