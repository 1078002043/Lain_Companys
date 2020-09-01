package bean;

/**
 * 温湿度 报警信息
 */
public class TemperatureLineBean {


    /**
     * ehhId : 3200
     * ehhTem : 27.5
     * ehhHum : 51.4
     * ehhTime : 2019-10-15 15:18:47
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
