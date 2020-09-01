package environment.bean;

/**
 * @ClassName: PT100AlertBean
 * @Description:
 * @Author: YIN LUO FEI
 * @Date: 2020/3/28 9:40
 */
public class PT100AlertBean {


    /**
     * ehpa_id : 1
     * th_id : 1
     * di_id : 4
     * ehpa_info : 1号PT100设备温度报警
     * ehpa_time : 26/12/2019 14:07:35
     * isChecked : 1
     */

    private String ehpaId;
    private String thId;
    private String diId;
    private String ehpaInfo;
    private String ehpaTime;
    private String isChecked;

    public String getEhpaId() {
        return ehpaId;
    }

    public void setEhpaId(String ehpaId) {
        this.ehpaId = ehpaId;
    }

    public String getThId() {
        return thId;
    }

    public void setThId(String thId) {
        this.thId = thId;
    }

    public String getDiId() {
        return diId;
    }

    public void setDiId(String diId) {
        this.diId = diId;
    }

    public String getEhpaInfo() {
        return ehpaInfo;
    }

    public void setEhpaInfo(String ehpaInfo) {
        this.ehpaInfo = ehpaInfo;
    }

    public String getEhpaTime() {
        return ehpaTime;
    }

    public void setEhpaTime(String ehpaTime) {
        this.ehpaTime = ehpaTime;
    }

    public String getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(String isChecked) {
        this.isChecked = isChecked;
    }
}
