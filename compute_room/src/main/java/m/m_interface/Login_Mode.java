package m.m_interface;

import org.litepal.LitePal;

import bean.UserInfo;


/**
 * 登陆 的 Mode 实现类，实现持久化的登陆
 */
public class Login_Mode implements I_Login_M {

    /**
     * 保存用户信息到数据库中
     * @param username 用户名
     * @param password 密码
     */
    @Override
    public void saveLoginInfo(String username, String password) {
        //确定保存的 账号 / 密码 都不为空
        if (username.isEmpty() || password.isEmpty())
            return;
        //保存用户的信息
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername(username);
        userInfo.setPassword(password);
        //将数据库保存到数据库中
        userInfo.save();
    }

    /**
     * 从数据库中获取用户信息
     * @return 返回用户信息
     */
    @Override
    public String getLoginInfo() {
        //从数据中获取账号信息
        UserInfo userInfo = LitePal.findFirst(UserInfo.class);
        //返回账号密码
        return userInfo.getUsername() + ":" + userInfo.getPassword();
    }
}
