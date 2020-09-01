package computer_room.present;

import android.util.Log;

import java.util.List;

import base.BasePresenter;
import computer_room.i_interface.I_Device8060;
import http.OkHttpUtil;
import util.LainNewApi;

public class Device8060Present <T,A,H,M> extends BasePresenter {
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
        String url = rootURL + i_device8060.getHistoryLink() + ehmId + "/" + statTime + "/" + endTime + "/1/20";

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
        Log.d("kljdf", "queryDeviceManage: run");
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
        if (responseStr.equals("[]"))
            return;
        List<A> list = OkHttpUtil.getInstance().formatResponse(responseStr,altClass);
        i_device8060.jsonParseAlert(list);
    }
    /**
     * 获取历史备数据
     */
    @Override
    public void historyResponse(String responseStr) {

        //防止获取到的是空数据
        if (responseStr.equals("[]"))
            return;

        //必须定义成数组不解析，因为懒得再改了 对象解析 {}，数组解析[]
        responseStr =  "[" + responseStr + "]";

        List<H> list = OkHttpUtil.getInstance().formatResponse(responseStr,histClass);

        i_device8060.jsonParseHistory(list);
    }
    /**
     * 获取设备数据
     */
    @Override
    public void manageResponse(String responseStr) {
        Log.d("sdfsd", "manageResponse: "+responseStr);
        //防止获取到的是空数据
        if (responseStr.equals("[]"))
            return;
        List<M> list = OkHttpUtil.getInstance().formatResponse(responseStr,mClass);
        i_device8060.jsonParseManage(list);
    }
}
