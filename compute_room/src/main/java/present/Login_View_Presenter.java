package present;

import android.util.Log;

import com.google.gson.JsonSyntaxException;

import base.BasePresenter;
import bean.LoginJSONBean;
import http.MyGson;
import http.OkHttpCallBack;
import http.OkHttpUtil;
import m.m_interface.I_Login_M;
import m.m_interface.Login_Mode;
import util.I_ToastManage;
import v_interface.I_Login_View;
import view.Lai_Login_View;

/**
 * 登陆视图Presenter
 *
 * @author Create by Yin Luo Fei
 * @version 0.1
 * @Time 2019/7/30 14 : 42
 */
public class Login_View_Presenter extends BasePresenter implements OkHttpCallBack {

    //View引用
    private I_Login_View login_view;
    //Mode引用
    private I_Login_M i_login_m;
    //Toast 的异常使用
    private I_ToastManage i_toastManage;

    /**
     * 初始化 Lain_View_Presenter
     *
     * @param login_view 主视图实体类的引用，用于和主视图进行通信
     */
    public Login_View_Presenter(Lai_Login_View login_view) {
        //初始化主视图MODE
        this.login_view = login_view;
        //实例化保存的账号信息的 MODE
        i_login_m = new Login_Mode();
    }

    /**
     * 执行登陆
     */
    public void loginApp() {

        //登陆成功后，等待2秒停止动画，2秒后再跳转并推送消息
        //POST 账号 密码
        OkHttpUtil.getInstance().loginRequest(login_view.getUserName(), login_view.getUserPassword(), this);

//            Map<String, String> loginMap = new LinkedHashMap<>();
//            loginMap.put("username", login_view.getUserName());
//            loginMap.put("password", login_view.getUserPassword());
//
//            //进行登陆
//
////            String url = LainNewApi.getInstance().getRootUrl() + LainNewApi.login;
//            String url = LainNewApi.getInstance().getRootPath() + LainNewApi.login;
//
//            //发送登陆请求
//            OkHttpUtil.getInstance().sendPostOkHttp("login", url, loginMap, this);

    }

    @Override
    public void httpRequestFailure(String requestTag, String requestUrl, String errorMsg) {

        // 由于搜索服务器的是并发请求，在APP中未对并发进行处理，所以在获取服务器的同时进行登陆的话，
        // Okhttp 的 CallBack 因为地址改变了，所以服务器获取地址是否成功的回调方法就到登陆失败的这里了

        //请求登陆接口失败，判断如果是登陆接口才会回调
        if (requestTag.equals("login"))
            login_view.loginFailure(errorMsg);

    }

    //登陆请求回调
    @Override
    public void httpRequestSuccess(String requestTag, String requestUrl, String responseStr) {


        //解析登陆的数据
        LoginJSONBean loginJSONBean = null;
        try {
            loginJSONBean = MyGson.getInstance().fromJson(responseStr, LoginJSONBean.class);
        } catch (JsonSyntaxException e) {
            Log.d("Login_View_Presenter", "httpRequestSuccess: 解析登陆数据失败");
        }

        if (loginJSONBean.getZt()) {
            //登陆成功
            login_view.loginResult(loginJSONBean.getZt());
            //登陆成功后，将账号信息保存到数据库中，供下次免登陆功能使用
            i_login_m.saveLoginInfo(login_view.getUserName(), login_view.getUserPassword());
        } else {
            //登陆失败，密码有误
            login_view.loginResult(loginJSONBean.getZt());
        }

    }

}
