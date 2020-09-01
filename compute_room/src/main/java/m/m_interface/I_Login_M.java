package m.m_interface;

/**
 * 登陆 Model，保存登陆信息
 */
public interface I_Login_M {
    /**
     * 登陆账号，密码的保存
     * @param username 用户名
     * @param password 密码
     */
    void saveLoginInfo(String username, String password);

    /**
     * 获取账号的信息
     */
    String getLoginInfo();
}
