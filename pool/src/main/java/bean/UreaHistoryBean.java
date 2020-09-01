package bean;

/**
 * @ClassName: UreaHistoryBean
 * @Description:
 * @Author: YIN LUO FEI
 * @Date: 2020/3/28 1:38
 */
public class UreaHistoryBean {


    /**
     * ureaHistoryId : 1
     * ureaId : 1
     * ureaName : 858
     * ureaData : 50.0
     * ureaHistoryTime : 2019-03-28 01:47:10
     */

    private int ureaHistoryId;
    private int ureaId;
    private String ureaName;
    private double ureaData;
    private String ureaHistoryTime;

    public int getUreaHistoryId() {
        return ureaHistoryId;
    }

    public void setUreaHistoryId(int ureaHistoryId) {
        this.ureaHistoryId = ureaHistoryId;
    }

    public int getUreaId() {
        return ureaId;
    }

    public void setUreaId(int ureaId) {
        this.ureaId = ureaId;
    }

    public String getUreaName() {
        return ureaName;
    }

    public void setUreaName(String ureaName) {
        this.ureaName = ureaName;
    }

    public double getUreaData() {
        return ureaData;
    }

    public void setUreaData(double ureaData) {
        this.ureaData = ureaData;
    }

    public String getUreaHistoryTime() {
        return ureaHistoryTime;
    }

    public void setUreaHistoryTime(String ureaHistoryTime) {
        this.ureaHistoryTime = ureaHistoryTime;
    }
}
