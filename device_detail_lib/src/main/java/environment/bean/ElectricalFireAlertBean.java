package environment.bean;

/**
 * @ClassName: ElectricalFireAlertBean
 * @Description:
 * @Author: YIN LUO FEI
 * @Date: 2020/3/28 13:12
 */
public class ElectricalFireAlertBean {

    /**
     * electricalFireAlarmId : 1
     * electricalFireId : 2
     * electricalFireName : 报警****
     * temp : 1.0
     * current : 3.0
     * info : ********
     * electricalFireAlarmTime : 2020-02-26T01:50:28.000+0000
     */

    private int electricalFireAlarmId;
    private int electricalFireId;
    private String electricalFireName;
    private double temp;
    private double current;
    private String info;
    private String electricalFireAlarmTime;

    public int getElectricalFireAlarmId() {
        return electricalFireAlarmId;
    }

    public void setElectricalFireAlarmId(int electricalFireAlarmId) {
        this.electricalFireAlarmId = electricalFireAlarmId;
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

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getElectricalFireAlarmTime() {
        return electricalFireAlarmTime;
    }

    public void setElectricalFireAlarmTime(String electricalFireAlarmTime) {
        this.electricalFireAlarmTime = electricalFireAlarmTime;
    }
}
