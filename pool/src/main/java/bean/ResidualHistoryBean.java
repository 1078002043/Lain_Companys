package bean;

/**
 * @ClassName: ResidualHistoryBean
 * @description: 余氯-历史
 * @Author: YIN LUO FEI
 * @Date: 2020/3/27 20:15
 */
public class ResidualHistoryBean {


    /**
     * residualChlorineHistoryId : 1
     * residualChlorineId : 1
     * residualChlorineName : 1
     * residualChlorineData : 23.0
     * residualChlorineHistoryTime : 2020-01-15 05:27:26
     */

    private int residualChlorineHistoryId;
    private int residualChlorineId;
    private String residualChlorineName;
    private double residualChlorineData;
    private String residualChlorineHistoryTime;

    public int getResidualChlorineHistoryId() {
        return residualChlorineHistoryId;
    }

    public void setResidualChlorineHistoryId(int residualChlorineHistoryId) {
        this.residualChlorineHistoryId = residualChlorineHistoryId;
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

    public String getResidualChlorineHistoryTime() {
        return residualChlorineHistoryTime;
    }

    public void setResidualChlorineHistoryTime(String residualChlorineHistoryTime) {
        this.residualChlorineHistoryTime = residualChlorineHistoryTime;
    }
}
