package v_interface;

import android.content.Context;

import bean.LoginUserInfoBean;

/**
 * 主视图Interface
 *
 * @author Create by Yin Luo Fei
 * @version 0.1
 * @Time 2019/7/30 09 : 53
 */
public interface I_Lain_View {

    //获取 Lain_View Context
    Context getMContext();

    //获取用户的相关信息
    public void getUserLoginInfo(LoginUserInfoBean loginUserInfoBean);

}
