package computer_room.bean;

import java.io.Serializable;

/**
 * 超级管理员Bean
 *
 * @version 0.1
 * @Time 2019/8/11 21 : 47
 */
public class SuperManageBean implements Serializable {


    /**
     * username : wwww
     * dutyplace : 一楼大厅
     * data : 星期三，星期二
     * phonenumber : 115552565
     * emailnumber : 54545474
     */

    private String username;
    private String dutyplace;
    private String data;
    private String phonenumber;
    private String emailnumber;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDutyplace() {
        return dutyplace;
    }

    public void setDutyplace(String dutyplace) {
        this.dutyplace = dutyplace;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getEmailnumber() {
        return emailnumber;
    }

    public void setEmailnumber(String emailnumber) {
        this.emailnumber = emailnumber;
    }


}
