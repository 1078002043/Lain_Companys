package bean;

/**
 * @ClassName: DissolvedHistoryBean
 * @Description: 溶氧仪 历史数据
 * @Author: YIN LUO FEI
 * @Date: 2020/3/26 17:14
 * 20
 */

public class DissolvedHistoryBean {


    /**
     * edohId : 1
     * edohO2Value : 32.0
     * edohDoTemp : 12.0
     * edohTime : 2020-01-16 07:46:02
     * thId : 1
     * diId : 0
     */

    private int edohId;
    private double edohO2Value;
    private double edohDoTemp;
    private String edohTime;
    private int thId;
    private int diId;

    public int getEdohId() {
        return edohId;
    }

    public void setEdohId(int edohId) {
        this.edohId = edohId;
    }

    public double getEdohO2Value() {
        return edohO2Value;
    }

    public void setEdohO2Value(double edohO2Value) {
        this.edohO2Value = edohO2Value;
    }

    public double getEdohDoTemp() {
        return edohDoTemp;
    }

    public void setEdohDoTemp(double edohDoTemp) {
        this.edohDoTemp = edohDoTemp;
    }

    public String getEdohTime() {
        return edohTime;
    }

    public void setEdohTime(String edohTime) {
        this.edohTime = edohTime;
    }

    public int getThId() {
        return thId;
    }

    public void setThId(int thId) {
        this.thId = thId;
    }

    public int getDiId() {
        return diId;
    }

    public void setDiId(int diId) {
        this.diId = diId;
    }
}
