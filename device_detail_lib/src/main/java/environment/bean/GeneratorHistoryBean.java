package environment.bean;

/**
 * @ClassName: GeneratorHistoryBean
 * @Description: 发电机-历史
 * @Author: YIN LUO FEI
 * @Date: 2020/3/28 15:14
 */
public class GeneratorHistoryBean {


    /**
     * alternatorHistoryId : 1
     * alternatorId : 1
     * alternatorName : 1号发电
     * alternatorAvol : 2.0
     * alternatorBvol : 3.0
     * alternatorCvol : 13.0
     * alternatorAcur : 44.0
     * alternatorBcur : 33.0
     * alternatorCcur : 2.0
     * alternatorHistoryTime : 2020-02-27 14:05:23
     */

    private int alternatorHistoryId;
    private int alternatorId;
    private String alternatorName;
    private double alternatorAvol;
    private double alternatorBvol;
    private double alternatorCvol;
    private double alternatorAcur;
    private double alternatorBcur;
    private double alternatorCcur;
    private String alternatorHistoryTime;

    public int getAlternatorHistoryId() {
        return alternatorHistoryId;
    }

    public void setAlternatorHistoryId(int alternatorHistoryId) {
        this.alternatorHistoryId = alternatorHistoryId;
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

    public String getAlternatorHistoryTime() {
        return alternatorHistoryTime;
    }

    public void setAlternatorHistoryTime(String alternatorHistoryTime) {
        this.alternatorHistoryTime = alternatorHistoryTime;
    }
}
