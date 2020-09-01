package bean;

/**
 * @ClassName: TurbidityAlertBean
 * @Description:
 * @Author: YIN LUO FEI
 * @Date: 2020/3/27 22:08
 */
public class TurbidityAlertBean {


    /**
     * muddyAlarmId : 1
     * muddyId : 1
     * muddyName : 浑浊度1好
     * muddyData : 50.0
     * info : ffffff
     * muddyAlarmTime : 2019-03-27 22:09:47
     */

    private int muddyAlarmId;
    private int muddyId;
    private String muddyName;
    private double muddyData;
    private String info;
    private String muddyAlarmTime;

    public int getMuddyAlarmId() {
        return muddyAlarmId;
    }

    public void setMuddyAlarmId(int muddyAlarmId) {
        this.muddyAlarmId = muddyAlarmId;
    }

    public int getMuddyId() {
        return muddyId;
    }

    public void setMuddyId(int muddyId) {
        this.muddyId = muddyId;
    }

    public String getMuddyName() {
        return muddyName;
    }

    public void setMuddyName(String muddyName) {
        this.muddyName = muddyName;
    }

    public double getMuddyData() {
        return muddyData;
    }

    public void setMuddyData(double muddyData) {
        this.muddyData = muddyData;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getMuddyAlarmTime() {
        return muddyAlarmTime;
    }

    public void setMuddyAlarmTime(String muddyAlarmTime) {
        this.muddyAlarmTime = muddyAlarmTime;
    }
}
