package environment.bean;

/**
 * @ClassName: DS18B20HistoryBean
 * @Description:
 * @Author: YIN LUO FEI
 * @Date: 2020/3/28 10:05
 */
public class DS18B20HistoryBean {


    /**
     * ehbhId : 1
     * ehbhTemp : 7.0
     * ehbhHum : 0.0
     * ehbhTime : 2019-12-27 16:40:37
     * thId : 28
     * diId : 0
     */

    private int ehbhId;
    private double ehbhTemp;
    private double ehbhHum;
    private String ehbhTime;
    private int thId;
    private int diId;

    public int getEhbhId() {
        return ehbhId;
    }

    public void setEhbhId(int ehbhId) {
        this.ehbhId = ehbhId;
    }

    public double getEhbhTemp() {
        return ehbhTemp;
    }

    public void setEhbhTemp(double ehbhTemp) {
        this.ehbhTemp = ehbhTemp;
    }

    public double getEhbhHum() {
        return ehbhHum;
    }

    public void setEhbhHum(double ehbhHum) {
        this.ehbhHum = ehbhHum;
    }

    public String getEhbhTime() {
        return ehbhTime;
    }

    public void setEhbhTime(String ehbhTime) {
        this.ehbhTime = ehbhTime;
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
