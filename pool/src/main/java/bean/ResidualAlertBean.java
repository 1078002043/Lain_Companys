package bean;

/**
 * @ClassName: ResidualAlertBean
 * @Description: 余氯-报警
 * @Author: YIN LUO FEI
 * @Date: 2020/3/27 20:14
 */
public class ResidualAlertBean {

    /**
     * residualChlorineAlarmId : 1
     * residualChlorineId : 1
     * residualChlorineName : 余氯
     * residualChlorineData : 66.0
     * info : 超出阈值45
     * residualChlorineAlarmTime : 2020-01-15 10:18:48
     */

    private int residualChlorineAlarmId;
    private int residualChlorineId;
    private String residualChlorineName;
    private double residualChlorineData;
    private String info;
    private String residualChlorineAlarmTime;

    public int getResidualChlorineAlarmId() {
        return residualChlorineAlarmId;
    }

    public void setResidualChlorineAlarmId(int residualChlorineAlarmId) {
        this.residualChlorineAlarmId = residualChlorineAlarmId;
    }

    public int getResidualChlorineId() {
        return residualChlorineId;
    }

    public void setResidualChlorineId(int residualChlorineId) {
        this.residualChlorineId = residualChlorineId;
    }

    public String getResidualChlorineName() {
        return residualChlorineName;
    }

    public void setResidualChlorineName(String residualChlorineName) {
        this.residualChlorineName = residualChlorineName;
    }

    public double getResidualChlorineData() {
        return residualChlorineData;
    }

    public void setResidualChlorineData(double residualChlorineData) {
        this.residualChlorineData = residualChlorineData;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getResidualChlorineAlarmTime() {
        return residualChlorineAlarmTime;
    }

    public void setResidualChlorineAlarmTime(String residualChlorineAlarmTime) {
        this.residualChlorineAlarmTime = residualChlorineAlarmTime;
    }
}
