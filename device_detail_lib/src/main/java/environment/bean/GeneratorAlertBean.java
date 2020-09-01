package environment.bean;

/**
 * @ClassName: GeneratorAlertBean
 * @Description: 发电机-报警
 * @Author: YIN LUO FEI
 * @Date: 2020/3/28 15:14
 */
public class GeneratorAlertBean {

    /**
     * alternatorAlarmId : 1
     * alternatorId : 1
     * alternatorName : 报警
     * alternatorAvol : 0.0
     * alternatorBvol : 4.0
     * alternatorCvol : 0.0
     * alternatorAcur : 5.0
     * alternatorBcur : 55.0
     * alternatorCcur : 22.0
     * info : 456
     * alternatorAlarmTime : 2020-02-27 14:06:15
     */

    private int alternatorAlarmId;
    private int alternatorId;
    private String alternatorName;
    private double alternatorAvol;
    private double alternatorBvol;
    private double alternatorCvol;
    private double alternatorAcur;
    private double alternatorBcur;
    private double alternatorCcur;
    private String info;
    private String alternatorAlarmTime;

    public int getAlternatorAlarmId() {
        return alternatorAlarmId;
    }

    public void setAlternatorAlarmId(int alternatorAlarmId) {
        this.alternatorAlarmId = alternatorAlarmId;
    }

    public int getAlternatorId() {
        return alternatorId;
    }

    public void setAlternatorId(int alternatorId) {
        this.alternatorId = alternatorId;
    }

    public String getAlternatorName() {
        return alternatorName;
    }

    public void setAlternatorName(String alternatorName) {
        this.alternatorName = alternatorName;
    }

    public double getAlternatorAvol() {
        return alternatorAvol;
    }

    public void setAlternatorAvol(double alternatorAvol) {
        this.alternatorAvol = alternatorAvol;
    }

    public double getAlternatorBvol() {
        return alternatorBvol;
    }

    public void setAlternatorBvol(double alternatorBvol) {
        this.alternatorBvol = alternatorBvol;
    }

    public double getAlternatorCvol() {
        return alternatorCvol;
    }

    public void setAlternatorCvol(double alternatorCvol) {
        this.alternatorCvol = alternatorCvol;
    }

    public double getAlternatorAcur() {
        return alternatorAcur;
    }

    public void setAlternatorAcur(double alternatorAcur) {
        this.alternatorAcur = alternatorAcur;
    }

    public double getAlternatorBcur() {
        return alternatorBcur;
    }

    public void setAlternatorBcur(double alternatorBcur) {
        this.alternatorBcur = alternatorBcur;
    }

    public double getAlternatorCcur() {
        return alternatorCcur;
    }

    public void setAlternatorCcur(double alternatorCcur) {
        this.alternatorCcur = alternatorCcur;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getAlternatorAlarmTime() {
        return alternatorAlarmTime;
    }

    public void setAlternatorAlarmTime(String alternatorAlarmTime) {
        this.alternatorAlarmTime = alternatorAlarmTime;
    }
}
