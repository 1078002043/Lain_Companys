package present;

import android.app.Activity;
import android.util.Log;

import com.example.mymodule.UpdateAppUtil;
import com.google.gson.JsonSyntaxException;

import org.json.JSONException;
import org.json.JSONObject;
import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.LoginUserInfoBean;
import http.MyGson;
import http.OkHttpCallBack;
import http.OkHttpUtil;
import p_interface.I_Lain_View_Presenter;
import util.APPConfig;
import util.AppVersionUtil;
import util.DeviceListMap;
import util.LainNewApi;
import v_interface.I_Lain_View;
import view.Lain_View;

import static http.OkHttpUtil.*;

/**
 * 主视图的 Presenter
 *
 * @author Create by Yin Luo Fei
 * @version 0.1
 * @Time 2019/7/30 10 : 17
 */
public class Lain_View_Presenter implements I_Lain_View_Presenter, OkHttpCallBack {

    //主视图 VIEW
    private I_Lain_View lain_view;

    /**
     * 初始化 Lain_View_Presenter
     *
     * @param lain_view 主视图实体类的引用，用于和主视图进行通信
     */
    public Lain_View_Presenter(Lain_View lain_view) {
        this.lain_view = lain_view;
    }

    //是否进行APP的更新
    @Override
    public void httpRequestSuccess(String requestTag, String requestUrl, String responseStr) {

        Log.d("mnmcv", "httpRequestSuccess: " + responseStr);

        switch (requestTag) {
            case "getUserInfo":
                try {
                    LoginUserInfoBean infoBean = MyGson.getInstance().fromJson(responseStr, LoginUserInfoBean.class);
                    lain_view.getUserLoginInfo(infoBean);
                } catch (JsonSyntaxException e) {
                    Log.d(getClass().getName(), "httpRequestSuccess: 获取用户信息失败 " + e.getMessage());
                }
                break;

        }

    }

    @Override
    public void httpRequestFailure(String requestTag, String requestUrl, String errorMsg) {

    }

    /**
     * 获取用户的相关信息
     */
    public void getUserMessage() {

        String url = LainNewApi.getInstance().getRootPath() + LainNewApi.userLoginInfo;

        OkHttpUtil.getInstance().sendGetOkHttp("getUserInfo", url, this);

    }

}
