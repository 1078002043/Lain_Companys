package view;

import base.BasePresenter;
import http.OkHttpUtil;
import util.LainNewApi;
import view_interface.I_Base8052Fragment;

/**
 * 8050 设备的Present
 *
 * @param <R> 实时数据
 * @param <A> 报警数据
 */
public class Base8052Present<R, A> extends BasePresenter {

    private I_Base8052Fragment i_base8052Fragment;
    //解析泛型的类
    private Class<R> realClass;
    private Class<A> alertClass;

    public Base8052Present(I_Base8052Fragment i_base8052Fragment) {
        this.i_base8052Fragment = i_base8052Fragment;
    }

    @Override
    public void queryRealData() {
        super.queryRealData();
    }

    /**
     * 查询8052实时数据
     *
     * @param tClass 查询完成后，解析JSON时需要用到的具体类型
     */
    public void queryRealData(Class<R> tClass) {
        realClass = tClass;
        String url = LainNewApi.getInstance().getRootPath() + i_base8052Fragment.getLinkReal();
        OkHttpUtil.getInstance().sendGetOkHttp(REAL_DATA, url, this);
    }

    @Override
    public void queryAlertData(int ehmId, String statTime, String endTime) {
        super.queryAlertData(ehmId, statTime, endTime);

    }

    /**
     * 查询8052报警数据
     *
     * @param alertClass 解析报警数据时的泛型类
     * @param ehmId      查询设备对应的ID
     * @param statTime   开始时间
     * @param endTime    结果时间
     */
    public void queryAlertData(Class<A> alertClass, int ehmId, String statTime, String endTime) {
        this.alertClass = alertClass;
        String url = LainNewApi.getInstance().getRootPath() + i_base8052Fragment.getLinkAlert() + ehmId + "/" + statTime + "/" + endTime;
        OkHttpUtil.getInstance().sendGetOkHttp(ALERT_DATA, url, this);
    }

    /**
     * 处理查询到的8052实时数据，由调用者处理
     *
     * @param responseStr 查询的结果
     */
    @Override
    public void realResponse(String responseStr) {
        i_base8052Fragment.deal8052Real(jsonBaseParse(responseStr, realClass));
    }

    /**
     * 处理查询到的8052报警数据，由调用者处理
     *
     * @param responseStr 查询的结果
     */
    @Override
    public void alertResponse(String responseStr) {
        i_base8052Fragment.deal8052Alert(jsonBaseParse(responseStr, alertClass));
    }

}
