package computer_room.present;

import base.BasePresenter;
import bean.AlertBeans;
import bean.TemperatureBean;
import computer_room.i_interface.I_Temperature;
import http.OkHttpUtil;
import util.LainNewApi;

/**
 * 温湿度 Presenter
 *
 * @author Create by Yin Luo Fei
 * @version 0.1
 * @Time 2019/8/28 15 : 00
 */
public class TemperaturePresenter extends BasePresenter{

    private I_Temperature i_temperature;

    public TemperaturePresenter(I_Temperature i_temperature) {
        this.i_temperature = i_temperature;
    }

    @Override
    public void queryRealData() {
        //请求实时数据列表
        String url = LainNewApi.getInstance().getRootPath() + LainNewApi.temperatureAndHumidity;
        OkHttpUtil.getInstance().sendGetOkHttp(REAL_DATA, url, this);
    }

    @Override
    public void queryHistoryData(int ehmId, String statTime, String endTime) {
        String url = LainNewApi.getInstance().getRootPath() + LainNewApi.tempHistory + ehmId + "/" + statTime + "/" + endTime;
        OkHttpUtil.getInstance().sendGetOkHttp(HISTORY_DATA, url, this);
    }

    @Override
    public void queryAlertData(int ehmId, String statTime, String endTime){
        String url = LainNewApi.getInstance().getRootPath() + LainNewApi.tempAlert + ehmId + "/" + statTime + "/" + endTime;
        OkHttpUtil.getInstance().sendGetOkHttp(ALERT_DATA, url, this);
    }


    @Override
    public void queryDeviceManage() {
        String url = LainNewApi.getInstance().getRootPath() + LainNewApi.tempManage;
        OkHttpUtil.getInstance().sendGetOkHttp(MANAGE_DATA, url, this);
    }
    /**
     * 实时数据
     * @param responseStr
     */
    @Override
    public void realResponse(String responseStr) {

        super.realResponse(responseStr);
        i_temperature.initTemperatureRecycler(jsonBaseParse(responseStr, TemperatureBean.class));
    }

    /**
     * 报警数据
     * @param responseStr
     */
    @Override
    public void alertResponse(String responseStr) {
        super.alertResponse(responseStr);
        i_temperature.devicesAlert(jsonBaseParse(responseStr, AlertBeans.class));
    }

    /**
     * 历史数据
     * @param responseStr
     */
    @Override
    public void historyResponse(String responseStr) {
        super.historyResponse(responseStr);
//        i_temperature.initChartTemper(jsonBaseParse(responseStr, TemperatureLineBean.class));
    }

    /**
     * 设备数据
     * @param responseStr
     */
    @Override
    public void manageResponse(String responseStr) {
        super.manageResponse(responseStr);
//        i_temperature.devicesManage(jsonBaseParse(responseStr, TemperatureBean.class));
    }
}
