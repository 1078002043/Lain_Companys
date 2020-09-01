package bean;

/**
 * @ClassName: ChlorophyllHistoryBean
 * @Description: 叶绿素-历史
 * @Author: YIN LUO FEI
 * @Date: 2020/3/27 22:43
 */
public class ChlorophyllHistoryBean {


    /**
     * chlorophyllHistoryId : 1
     * chlorophyllId : 1
     * chlorophyllName : 叶绿素1报警
     * chlorophyllData : 20.0
     * temp : 10.0
     * chlorophyllHistoryTime : 2020-02-24 11:18:42
     */

    private int chlorophyllHistoryId;
    private int chlorophyllId;
    private String chlorophyllName;
    private double chlorophyllData;
    private double temp;
    private String chlorophyllHistoryTime;

    public int getChlorophyllHistoryId() {
        return chlorophyllHistoryId;
    }

    public void setChlorophyllHistoryId(int chlorophyllHistoryId) {
        this.chlorophyllHistoryId = chlorophyllHistoryId;
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

    public String getChlorophyllHistoryTime() {
        return chlorophyllHistoryTime;
    }

    public void setChlorophyllHistoryTime(String chlorophyllHistoryTime) {
        this.chlorophyllHistoryTime = chlorophyllHistoryTime;
    }
}
