package bean;

/**
 * @ClassName: ChlorophyllAlertBean
 * @Description: 叶绿素-报警
 * @Author: YIN LUO FEI
 * @Date: 2020/3/27 22:43
 */
public class ChlorophyllAlertBean {


    /**
     * chlorophyllAlarmId : 1
     * chlorophyllId : 1
     * chlorophyllName : 叶绿素2
     * chlorophyllData : 30.0
     * temp : 1.0
     * info : 1
     * chlorophyllAlarmTime : 2020-02-24 11:19:00
     */

    private int chlorophyllAlarmId;
    private int chlorophyllId;
    private String chlorophyllName;
    private double chlorophyllData;
    private double temp;
    private String info;
    private String chlorophyllAlarmTime;

    public int getChlorophyllAlarmId() {
        return chlorophyllAlarmId;
    }

    public void setChlorophyllAlarmId(int chlorophyllAlarmId) {
        this.chlorophyllAlarmId = chlorophyllAlarmId;
    }

    public int getChlorophyllId() {
        return chlorophyllId;
    }

    public void setChlorophyllId(int chlorophyllId) {
        this.chlorophyllId = chlorophyllId;
    }

    public String getChlorophyllName() {
        return chlorophyllName;
    }

    public void setChlorophyllName(String chlorophyllName) {
        this.chlorophyllName = chlorophyllName;
    }

    public double getChlorophyllData() {
        return chlorophyllData;
    }

    public void setChlorophyllData(double chlorophyllData) {
        this.chlorophyllData = chlorophyllData;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getChlorophyllAlarmTime() {
        return chlorophyllAlarmTime;
    }

    public void setChlorophyllAlarmTime(String chlorophyllAlarmTime) {
        this.chlorophyllAlarmTime = chlorophyllAlarmTime;
    }
}
