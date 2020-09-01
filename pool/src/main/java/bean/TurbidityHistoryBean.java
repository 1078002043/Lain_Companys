package bean;

/**
 * @ClassName: TurbidityHistoryBean
 * @Description:
 * @Author: YIN LUO FEI
 * @Date: 2020/3/27 22:08
 */
public class TurbidityHistoryBean {

    /**
     * muddyHistoryId : 1
     * muddyId : 1
     * muddyName : 浑浊度1好
     * muddyData : 40.0
     * muddyHistoryTime : 2019-03-27 22:10:03
     */

    private int muddyHistoryId;
    private int muddyId;
    private String muddyName;
    private double muddyData;
    private String muddyHistoryTime;

    public int getMuddyHistoryId() {
        return muddyHistoryId;
    }

    public void setMuddyHistoryId(int muddyHistoryId) {
        this.muddyHistoryId = muddyHistoryId;
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

    public String getMuddyHistoryTime() {
        return muddyHistoryTime;
    }

    public void setMuddyHistoryTime(String muddyHistoryTime) {
        this.muddyHistoryTime = muddyHistoryTime;
    }
}
