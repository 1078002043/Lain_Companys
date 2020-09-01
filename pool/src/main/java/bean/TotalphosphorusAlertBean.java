package bean;

/**
 * @ClassName: TotalphosphorusAlertBean
 * @Description:
 * @Author: YIN LUO FEI
 * @Date: 2020/3/27 23:36
 */
public class TotalphosphorusAlertBean {


    /**
     * phosphorusAlarmId : 1
     * phosphorusId : 1
     * phosphorusName : 总磷水质
     * phosphorusData : 40.0
     * info : 888
     * phosphorusAlarmTime : 2019-07-27 23:34:09
     */

    private int phosphorusAlarmId;
    private int phosphorusId;
    private String phosphorusName;
    private double phosphorusData;
    private String info;
    private String phosphorusAlarmTime;

    public int getPhosphorusAlarmId() {
        return phosphorusAlarmId;
    }

    public void setPhosphorusAlarmId(int phosphorusAlarmId) {
        this.phosphorusAlarmId = phosphorusAlarmId;
    }

    public int getPhosphorusId() {
        return phosphorusId;
    }

    public void setPhosphorusId(int phosphorusId) {
        this.phosphorusId = phosphorusId;
    }

    public String getPhosphorusName() {
        return phosphorusName;
    }

    public void setPhosphorusName(String phosphorusName) {
        this.phosphorusName = phosphorusName;
    }

    public double getPhosphorusData() {
        return phosphorusData;
    }

    public void setPhosphorusData(double phosphorusData) {
        this.phosphorusData = phosphorusData;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getPhosphorusAlarmTime() {
        return phosphorusAlarmTime;
    }

    public void setPhosphorusAlarmTime(String phosphorusAlarmTime) {
        this.phosphorusAlarmTime = phosphorusAlarmTime;
    }
}
