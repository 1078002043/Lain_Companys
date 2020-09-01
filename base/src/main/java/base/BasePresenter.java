package base;

import android.util.Log;

import java.util.List;

import http.OkHttpCallBack;
import http.OkHttpUtil;

/**
 * Author：YIN_LUO_FEI
 * Date：2019/11/20 17:23
 * Description：根-Presenter，都具备实时数据请求与报警数据的请求
 **/
public abstract class BasePresenter implements OkHttpCallBack{

    public static final String REAL_DATA = "real_data";
    public static final String ALERT_DATA = "alert_data";
    public static final String HISTORY_DATA = "history_data";
    public static final String MANAGE_DATA = "manage_data";

    /**
     * 处理实时数据
     * @param tag 数据的标识
     * @param url 请求的URL
     * @param callBack 请求成功后，数据回调
     */
    public void dealRealData(String url, String tag, OkHttpCallBack callBack){
        OkHttpUtil.getInstance().sendGetOkHttp(tag, url, callBack);
    }

    /**
     * 处理实时数据
     * @param tag 数据的标识
     * @param url 请求的URL
     */
    public void dealRealData(String url, String tag){
        OkHttpUtil.getInstance().sendGetOkHttp(tag, url, this);
    }

    /**
     * 处理报警数据
     * @param tag 数据的标识
     * @param url 请求的URL
     * @param callBack 请求成功后，数据回调
     */
    public void dealAlertData(String url, String tag, OkHttpCallBack callBack){
        OkHttpUtil.getInstance().sendGetOkHttp(tag, url, callBack);
    }

    /**
     * 处理报警数据
     * @param tag 数据的标识
     * @param url 请求的URL
     */
    public void dealAlertData(String url, String tag){
        OkHttpUtil.getInstance().sendGetOkHttp(tag, url, this);
    }

    /**
     * 所有的 Presenter 请求成功后的回调方法
     * @param requestTag 请求的 标识
     * @param requestUrl 请求的 URL
     * @param responseStr 请求成功返回的 JSON
     */
    @Override
    public void httpRequestSuccess(String requestTag, String requestUrl, String responseStr) {

        Log.d("DeviceRequest", "httpRequestSuccess: "+responseStr + "---" + requestTag );

        switch (requestTag) {
            case REAL_DATA:
                realResponse(responseStr);
                break;
            case ALERT_DATA:
                alertResponse(responseStr);
                break;
            case HISTORY_DATA:
                historyResponse(responseStr);
                break;
            case MANAGE_DATA:
                manageResponse(responseStr);
                break;

        }

    }


    /**
     * 实时数据回调
     * @param responseStr
     */
    public void realResponse(String responseStr) {

    }

    /**
     * 报警数据回调
     * @param responseStr
     */
    public void alertResponse(String responseStr) {

    }

    /**
     * 历史数据回调
     * @param responseStr
     */
    public void historyResponse(String responseStr) {

    }

    /**
     * 设备数据回调
     * @param responseStr
     */
    public void manageResponse(String responseStr) {

    }

    /**
     * 所有的 Presenter 请求失败后的回调方法
     * @param errorMsg 失败的错误信息
     */
    @Override
    public void httpRequestFailure(String requestTag, String requestUrl, String errorMsg) {

    }

    /**
     * 查询-实时数据
     */
    public void queryRealData(){

    }

    /**
     * 查询-报警数据
     */
    public void queryAlertData(int ehmId, String statTime, String endTime){

    }

    /**
     * 查询-历史数据
     */
    public void queryHistoryData(int ehmId, String statTime, String endTime){

    }

    /**
     * 查询-设备管理
     */
    public void queryDeviceManage(){

    }

    /**
     * 处理请求到的数据
     * @param responseStr
     * @param t
     * @param <T>
     * @return
     */
    public <T> List<T> jsonBaseParse(String responseStr, Class<T> t){

        return OkHttpUtil.getInstance().formatResponse(responseStr, t);

    }

}
