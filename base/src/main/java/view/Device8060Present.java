package view;

import android.util.Log;

import java.util.List;

import base.BasePresenter;
import http.OkHttpUtil;
import util.LainNewApi;
import view_interface.I_Device8060;

public class Device8060Present<T,A,H,M> extends BasePresenter {
    //网络的根路径
    private final String rootURL = LainNewApi.getInstance().getRootPath();
    //接口引用
    private I_Device8060 i_device8060;
    private Class<T> realClass;
    private Class<A> altClass;
    private Class<H> histClass;
    private Class<M> mClass;

    public Device8060Present(I_Device8060 i_device8060) {
        this.i_device8060 = i_device8060;
    }

    /**
     * 请求实时数据
     */
    @Override
    public void queryRealData() {

    }

    public void queryRealData(Class<T> tClass) {
        realClass = tClass;
        String url = rootURL + i_device8060.getRealLink();
        OkHttpUtil.getInstance().sendGetOkHttp(REAL_DATA, url, this);
    }

    /**
     * 请求报警数据
     */
    @Override
    public void queryAlertData(int ehmId, String statTime, String endTime) {

    }

    public void queryAlertData(Class<A> alertClass, int ehmId, String statTime, String endTime) {
        altClass = alertClass;
        String url = rootURL + i_device8060.getAlertLink() + ehmId + "/" + statTime + "/" + endTime;
        Log.d("dfsdf", "queryAlertData: "+url);
        OkHttpUtil.getInstance().sendGetOkHttp(ALERT_DATA, url, this);
    }

    /**
     * 请求历史数据
     */
    @Override
    public void queryHistoryData(int ehmId, String statTime, String endTime) {

    }

    public void queryHistoryData(Class<H> historyClass, int ehmId, String statTime, String endTime) {

        histClass = historyClass;
        String url = rootURL + i_device8060.getHistoryLink() + ehmId + "/" + statTime + "/" + endTime;
        OkHttpUtil.getInstance().sendGetOkHttp(HISTORY_DATA, url, this);
    }

    /**
     * 请求设备数据
     */
    @Override
    public void queryDeviceManage() {
        String url = rootURL + i_device8060.getManageLink();
        OkHttpUtil.getInstance().sendGetOkHttp(MANAGE_DATA, url, this);
    }

    public void queryDeviceManage(Class<M> manageClass) {
        mClass = manageClass;
        String url = rootURL + i_device8060.getManageLink();
        OkHttpUtil.getInstance().sendGetOkHttp(MANAGE_DATA, url, this);
    }

    /**
     * 获取实时备数据
     */
    @Override
    public void realResponse(String responseStr) {
        //防止获取到的是空数据
        if (responseStr.equals("[]"))
            return;
        List<T> list = OkHttpUtil.getInstance().formatResponse(responseStr,realClass);
        i_device8060.jsonParseResponse(list);

    }
    /**
     * 获取报警备数据
     */
    @Override
    public void alertResponse(String responseStr) {
        //防止获取到的是空数据
        try {
            if (responseStr.equals("[]"))
                return;
            List<A> list = OkHttpUtil.getInstance().formatResponse(responseStr,altClass);
            i_device8060.jsonParseAlert(list);
        } catch (IllegalStateException e) {
            Log.d("alertResponse", "alertResponse: 获取数据失败"+e.getMessage());
        }
    }
    /**
     * 获取历史备数据
     */
    @Override
    public void historyResponse(String responseStr) {
        //防止获取到的是空数据
        if (responseStr.equals("[]"))
            return;
        List<H> list = OkHttpUtil.getInstance().formatResponse(responseStr,histClass);
        i_device8060.jsonParseHistory(list);
    }
    /**
     * 获取设备数据
     */
    @Override
    public void manageResponse(String responseStr) {
        //防止获取到的是空数据
        if (responseStr.equals("[]"))
            return;
        List<M> list = OkHttpUtil.getInstance().formatResponse(responseStr,mClass);
        i_device8060.jsonParseManage(list);
    }
}
