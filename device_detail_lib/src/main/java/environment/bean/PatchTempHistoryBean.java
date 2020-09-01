package environment.bean;

/**
 * @ClassName: PatchTempHistoryBean
 * @Description:
 * @Author: YIN LUO FEI
 * @Date: 2020/3/28 11:09
 */
public class PatchTempHistoryBean {


    /**
     * patchHistoryId : 1
     * patchId : 1
     * patchName : 贴片式温度
     * patchData : 1.0
     * patchHistoryTime : 2020-02-24 10:17:37
     */

    private int patchHistoryId;
    private int patchId;
    private String patchName;
    private double patchData;
    private String patchHistoryTime;

    public int getPatchHistoryId() {
        return patchHistoryId;
    }

    public void setPatchHistoryId(int patchHistoryId) {
        this.patchHistoryId = patchHistoryId;
    }

    public int getPatchId() {
        return patchId;
    }

    public void setPatchId(int patchId) {
        this.patchId = patchId;
    }

    public String getPatchName() {
        return patchName;
    }

    public void setPatchName(String patchName) {
        this.patchName = patchName;
    }

    public double getPatchData() {
        return patchData;
    }

    public void setPatchData(double patchData) {
        this.patchData = patchData;
    }

    public String getPatchHistoryTime() {
        return patchHistoryTime;
    }

    public void setPatchHistoryTime(String patchHistoryTime) {
        this.patchHistoryTime = patchHistoryTime;
    }
}
