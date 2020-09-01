package present;

import base.BasePresenter;
import bean.MainMonthAlert;
import http.OkHttpCallBack;
import http.OkHttpUtil;
import util.LainNewApi;
import v_interface.I_MainAlert;

/**
 * Author：YIN_LUO_FEI
 * Date：2019/11/22 14:00
 * Description：主页-报警消息 Presenter
 **/
public class MainAlertPresenter extends BasePresenter implements OkHttpCallBack {

    private I_MainAlert i_mainAlert;

    public MainAlertPresenter(I_MainAlert i_mainAlert) {
        this.i_mainAlert = i_mainAlert;
    }

    /**
     * 报警数据请求
     * @param status 0 查询全部，1 查询已读，2 查询未读
     * @param startTime
     * @param endTime
     */
    public void getMonthAlert(int status, String startTime, String endTime) {

        String url = null;
        switch (status) {

            case 0:
                url = LainNewApi.getInstance().getRootPath() + LainNewApi.findAllAlertMsg + startTime + "/" + endTime;
                break;
            case 1:
                url = LainNewApi.getInstance().getRootPath() + LainNewApi.isReadAlert + "1";
                break;
            case 2:
                url = LainNewApi.getInstance().getRootPath() + LainNewApi.isReadAlert + "0";
                break;

        }

        OkHttpUtil.getInstance().sendGetOkHttp("month_alert", url, this);
    }

    //报警数据回调
    @Override
    public void httpRequestSuccess(String requestTag, String requestUrl, String responseStr) {

        i_mainAlert.monthAlert(OkHttpUtil.getInstance().formatResponse(responseStr, MainMonthAlert.class));

    }
}
