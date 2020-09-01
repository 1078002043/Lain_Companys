package computer_room.bean;

import java.io.Serializable;

/**
 * 用户管理 Bean
 *
 * @author Create by Yin Luo Fei
 * @version 0.1
 * @Time 2019/8/29 15 : 25
 */
public class UserManagerBean implements Serializable {

    String managerName;
    String groupName;
    String phone;
    String managerSetting;
    String password;
    String confirmPas;
    String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPas() {
        return confirmPas;
    }

    public void setConfirmPas(String confirmPas) {
        this.confirmPas = confirmPas;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getManagerSetting() {
        return managerSetting;
    }

    public void setManagerSetting(String managerSetting) {
        this.managerSetting = managerSetting;
    }
}
