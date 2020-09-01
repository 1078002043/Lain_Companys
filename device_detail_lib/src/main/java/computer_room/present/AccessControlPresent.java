package computer_room.present;

import base.BasePresenter;
import bean.Alert8052Bean;
import computer_room.bean.Device8052Bean;
import computer_room.i_interface.I_Device8052;
import http.OkHttpUtil;
import util.LainNewApi;

/**
 * 门禁 Presenter
 */
public class AccessControlPresent extends BasePresenter {
    private I_Device8052 i_device8052;

    public AccessControlPresent(I_Device8052 i_device8052) {
        this.i_device8052 = i_device8052;
    }

    @Override
    public void queryRealData() {
        super.queryRealData();
        String url = LainNewApi.getInstance().getRootPath() + LainNewApi.doorNo;
        OkHttpUtil.getInstance().sendGetOkHttp(REAL_DATA, url, this);
    }

    @Override
    public void queryAlertData(int ehmId, String statTime, String endTime) {
        super.queryAlertData(ehmId, statTime, endTime);
//        String url = LainNewApi.getInstance().getRootPath() + LainNewApi.doorNoAlarm + ehmId + "/" + statTime + "/" + endTime;
//        OkHttpUtil.getInstance().sendGetOkHttp(ALERT_DATA, url, this);
    }

    @Override
    public void realResponse(String responseStr) {
        i_device8052.dealRealData8052(jsonBaseParse(responseStr, Device8052Bean.class));
    }

    @Override
    public void alertResponse(String responseStr) {
        i_device8052.dealAlertData8052(jsonBaseParse(responseStr, Alert8052Bean.class));
    }
}
