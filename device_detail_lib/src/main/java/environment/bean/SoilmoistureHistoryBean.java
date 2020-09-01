package environment.bean;

/**
 * @ClassName: SoilmoistureHistoryBean
 * @Description:
 * @Author: YIN LUO FEI
 * @Date: 2020/3/28 14:34
 */
public class SoilmoistureHistoryBean {


    /**
     * ehhId : 1
     * ehhTem : 26.5
     * ehhHum : 62.2
     * ehhTime : 2019-08-21 14:15:55
     * ehmDeviceAddress : 1
     * diId : 1
     */

    private int ehhId;
    private double ehhTem;
    private double ehhHum;
    private String ehhTime;
    private int ehmDeviceAddress;
    private int diId;

    public int getEhhId() {
        return ehhId;
    }

    public void setEhhId(int ehhId) {
        this.ehhId = ehhId;
    }

    public double getEhhTem() {
        return ehhTem;
    }

    public void setEhhTem(double ehhTem) {
        this.ehhTem = ehhTem;
    }

    public double getEhhHum() {
        return ehhHum;
    }

    public void setEhhHum(double ehhHum) {
        this.ehhHum = ehhHum;
    }

    public String getEhhTime() {
        return ehhTime;
    }

    public void setEhhTime(String ehhTime) {
        this.ehhTime = ehhTime;
    }

    public int getEhmDeviceAddress() {
        return ehmDeviceAddress;
    }

    public void setEhmDeviceAddress(int ehmDeviceAddress) {
        this.ehmDeviceAddress = ehmDeviceAddress;
    }

    public int getDiId() {
        return diId;
    }

    public void setDiId(int diId) {
        this.diId = diId;
    }
}
