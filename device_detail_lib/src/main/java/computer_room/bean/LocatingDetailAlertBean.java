package computer_room.bean;

/**
 * 定位漏水 报警信息
 */
public class LocatingDetailAlertBean {


    /**
     * elaId : 1
     * elmId : 16
     * elmName : 定位漏水1
     * elaInfo : null
     * elmLength : 3.0
     * time : 2019-09-25 10:22:44
     */

    private int elaId;
    private int elmId;
    private String elmName;
    private Object elaInfo;
    private double elmLength;
    private String time;

    public int getElaId() {
        return elaId;
    }

    public void setElaId(int elaId) {
        this.elaId = elaId;
    }

    public int getElmId() {
        return elmId;
    }

    public void setElmId(int elmId) {
        this.elmId = elmId;
    }

    public String getElmName() {
        return elmName;
    }

    public void setElmName(String elmName) {
        this.elmName = elmName;
    }

    public Object getElaInfo() {
        return elaInfo;
    }

    public void setElaInfo(Object elaInfo) {
        this.elaInfo = elaInfo;
    }

    public double getElmLength() {
        return elmLength;
    }

    public void setElmLength(double elmLength) {
        this.elmLength = elmLength;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
