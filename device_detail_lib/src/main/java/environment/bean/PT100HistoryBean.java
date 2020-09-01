package environment.bean;

/**
 * @ClassName: PT100HistoryBean
 * @Description:
 * @Author: YIN LUO FEI
 * @Date: 2020/3/28 9:40
 */
public class PT100HistoryBean {


    /**
     * ehphId : 1
     * ehphTemp : 24.0
     * ehphHum : 0.0
     * ehphTime : 2019-12-26 14:07:35
     * thId : 1
     * diId : 0
     */

    private int ehphId;
    private double ehphTemp;
    private double ehphHum;
    private String ehphTime;
    private int thId;
    private int diId;

    public int getEhphId() {
        return ehphId;
    }

    public void setEhphId(int ehphId) {
        this.ehphId = ehphId;
    }

    public double getEhphTemp() {
        return ehphTemp;
    }

    public void setEhphTemp(double ehphTemp) {
        this.ehphTemp = ehphTemp;
    }

    public double getEhphHum() {
        return ehphHum;
    }

    public void setEhphHum(double ehphHum) {
        this.ehphHum = ehphHum;
    }

    public String getEhphTime() {
        return ehphTime;
    }

    public void setEhphTime(String ehphTime) {
        this.ehphTime = ehphTime;
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
