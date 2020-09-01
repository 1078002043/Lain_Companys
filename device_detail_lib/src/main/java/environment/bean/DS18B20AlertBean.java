package environment.bean;

/**
 * @ClassName: DS18B20AlertBean
 * @Description:
 * @Author: YIN LUO FEI
 * @Date: 2020/3/28 10:05
 */
public class DS18B20AlertBean {


    /**
     * ehbaId : 1
     * thId : 28
     * diId : 28
     * ehbaInfo : 报警
     * ehbaTime : 27/12/2019 16:40:34
     * isChecked : 1
     */

    private String ehba_id;
    private String th_id;
    private String di_id;
    private String ehba_info;
    private String ehba_time;
    private String isChecked;

    public String getEhba_id() {
        return ehba_id;
    }

    public void setEhba_id(String ehba_id) {
        this.ehba_id = ehba_id;
    }

    public String getTh_id() {
        return th_id;
    }

    public void setTh_id(String th_id) {
        this.th_id = th_id;
    }

    public String getDi_id() {
        return di_id;
    }

    public void setDi_id(String di_id) {
        this.di_id = di_id;
    }

    public String getEhba_info() {
        return ehba_info;
    }

    public void setEhba_info(String ehba_info) {
        this.ehba_info = ehba_info;
    }

    public String getEhba_time() {
        return ehba_time;
    }

    public void setEhba_time(String ehba_time) {
        this.ehba_time = ehba_time;
    }

    public String getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(String isChecked) {
        this.isChecked = isChecked;
    }
}
