package bean;

/**
 * @ClassName: CodcrHistoryBean
 * @Description: Codcr分析-历史
 * @Author: YIN LUO FEI
 * @Date: 2020/3/28 0:21
 */
public class CodcrHistoryBean {


    /**
     * codHistoryId : 1
     * codId : 1
     * codName : Codcr分析
     * codData : 50.0
     * codHistoryTime : 2019-07-27 23:54:01
     */

    private int codHistoryId;
    private int codId;
    private String codName;
    private double codData;
    private String codHistoryTime;

    public int getCodHistoryId() {
        return codHistoryId;
    }

    public void setCodHistoryId(int codHistoryId) {
        this.codHistoryId = codHistoryId;
    }

    public int getCodId() {
        return codId;
    }

    public void setCodId(int codId) {
        this.codId = codId;
    }

    public String getCodName() {
        return codName;
    }

    public void setCodName(String codName) {
        this.codName = codName;
    }

    public double getCodData() {
        return codData;
    }

    public void setCodData(double codData) {
        this.codData = codData;
    }

    public String getCodHistoryTime() {
        return codHistoryTime;
    }

    public void setCodHistoryTime(String codHistoryTime) {
        this.codHistoryTime = codHistoryTime;
    }
}
