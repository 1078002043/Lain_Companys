package present;

import android.content.Context;
import android.util.Log;

import com.google.gson.JsonSyntaxException;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import bean.SmartMainBean;
import http.OkHttpCallBack;
import http.OkHttpUtil;
import p_interface.I_SmartDevice_P;
import util.LainNewApi;
import v_interface.I_SmartDevice;

/**
 * 报警数据 Presenter，请求报警数据时，出现错误，会进不去
 *
 * @author Create by Yin Luo Fei
 * @version 0.1
 * @Time 2019/8/4 23 : 53
 */
public class SmartDevice_Presenter implements OkHttpCallBack {

    //View 引用
    private I_SmartDevice i_smartDevice;

    public SmartDevice_Presenter(I_SmartDevice i_smartDevice) {
        this.i_smartDevice = i_smartDevice;
    }

    //获取主页所有的数据
    public void getMainAllData() {
        String url = LainNewApi.getInstance().getRootPath() + LainNewApi.mainMessage;
        OkHttpUtil.getInstance().sendGetOkHttp("main_data", url, this);
    }

    @Override
    public void httpRequestSuccess(String requestTag, String requestUrl, String responseStr) {

        if (responseStr.isEmpty())
            return;

        try {

            if (requestTag.equals("main_data")) {

                //转成数组格式

                    String parseJson = "[" + responseStr + "]";
                    List<SmartMainBean> smartMainBeans = OkHttpUtil.getInstance().formatResponse(parseJson, SmartMainBean.class);
                    i_smartDevice.getSmartAllData(smartMainBeans.get(0));

            }

        } catch (Exception e) {
            Log.d(getClass().getName(), "httpRequestSuccess: 获取主页数据失败 " + e.getMessage());
        }

    }

    @Override
    public void httpRequestFailure(String requestTag, String requestUrl, String errorMsg) {

    }
}






