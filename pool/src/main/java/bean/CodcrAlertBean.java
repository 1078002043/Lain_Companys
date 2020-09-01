package bean;

/**
 * @ClassName: CodcrAlertBean
 * @Description: Codcr分析-报警
 * @Author: YIN LUO FEI
 * @Date: 2020/3/28 0:21
 */
public class CodcrAlertBean {


    /**
     * codAlarmId : 1
     * codId : 1
     * codName : Codcr分析
     * codData : 60.0
     * info : 999
     * codAlarmTime : 2019-07-27 23:54:48
     */

    private int codAlarmId;
    private int codId;
    private String codName;
    private double codData;
    private String info;
    private String codAlarmTime;

    public int getCodAlarmId() {
        return codAlarmId;
    }

    public void setCodAlarmId(int codAlarmId) {
        this.codAlarmId = codAlarmId;
    }

    public int getCodId() {
        return codId;
    }

    public void setCodId(int codId) {
        this.codId = codId;
    }

    public String getCodName() {
        return codName;
    }

    public void setCodName(String codName) {
        this.codName = codName;
    }

    public double getCodData() {
        return codData;
    }

    public void setCodData(double codData) {
        this.codData = codData;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getCodAlarmTime() {
        return codAlarmTime;
    }

    public void setCodAlarmTime(String codAlarmTime) {
        this.codAlarmTime = codAlarmTime;
    }
}
