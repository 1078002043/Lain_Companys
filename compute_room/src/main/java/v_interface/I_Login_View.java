package v_interface;


import com.dd.CircularProgressButton;

/**
 * 登陆接口
 *
 * @author Create by Yin Luo Fei
 * @version 0.1
 * @Time 2019/7/30 14 : 29
 */
public interface I_Login_View {

    //获取用户输入的用户名
    String getUserName();
    //获取用户输入的密码
    String getUserPassword();
    //登陆结果
    void loginResult(boolean loginResult);
    //获取登陆按钮
    CircularProgressButton getLoginButton();
    //登陆失败时的回调
    void loginFailure(String errorMsg);
}
