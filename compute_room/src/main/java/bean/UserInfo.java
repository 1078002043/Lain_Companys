package bean;

import org.litepal.crud.LitePalSupport;

/**
 * 保存用户的信息
 *
 * @author Create by Yin Luo Fei
 * @version 0.1
 * @Time 2019/7/29 10 : 01
 */
public class UserInfo extends LitePalSupport {
    //用户名
    private String username;
    //密码
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
