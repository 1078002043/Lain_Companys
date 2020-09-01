package bean;

/**
 * @ClassName: TotalphosphorusHistoryBean
 * @Description:
 * @Author: YIN LUO FEI
 * @Date: 2020/3/27 23:36
 */
public class TotalphosphorusHistoryBean {


    /**
     * phosphorusHistoryId : 1
     * phosphorusId : 1
     * phosphorusName : 总磷水质
     * phosphorusData : 40.0
     * phosphorusHistoryTime : 2019-07-27 23:34:31
     */

    private int phosphorusHistoryId;
    private int phosphorusId;
    private String phosphorusName;
    private double phosphorusData;
    private String phosphorusHistoryTime;

    public int getPhosphorusHistoryId() {
        return phosphorusHistoryId;
    }

    public void setPhosphorusHistoryId(int phosphorusHistoryId) {
        this.phosphorusHistoryId = phosphorusHistoryId;
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

    public String getPhosphorusHistoryTime() {
        return phosphorusHistoryTime;
    }

    public void setPhosphorusHistoryTime(String phosphorusHistoryTime) {
        this.phosphorusHistoryTime = phosphorusHistoryTime;
    }
}
