package computer_room.present;

import base.BasePresenter;
import bean.Alert8052Bean;
import computer_room.bean.Device8052Bean;
import computer_room.i_interface.I_Base8052Fragment;
import http.OkHttpUtil;
import util.LainNewApi;

public class Base8052Present<R,A> extends BasePresenter {
    
    private I_Base8052Fragment i_base8052Fragment;
    private Class<R> realClass;
    private Class<A> alertClass;

    public Base8052Present(I_Base8052Fragment i_base8052Fragment) {
        this.i_base8052Fragment = i_base8052Fragment;
    }

    @Override
    public void queryRealData() {
        super.queryRealData();
    }

    public void queryRealData(Class<R> tClass) {
        realClass = tClass;
        String url = LainNewApi.getInstance().getRootPath() + i_base8052Fragment.getLinkReal();
        OkHttpUtil.getInstance().sendGetOkHttp(REAL_DATA, url, this);
    }

    @Override
    public void queryAlertData(int ehmId, String statTime, String endTime) {
        super.queryAlertData(ehmId, statTime, endTime);

    }

    public void queryAlertData(Class<A> alertClass, int ehmId, String statTime, String endTime) {
        this.alertClass = alertClass;
        String url = LainNewApi.getInstance().getRootPath() + i_base8052Fragment.getLinkAlert() + ehmId + "/" + statTime + "/" + endTime;
        OkHttpUtil.getInstance().sendGetOkHttp(ALERT_DATA, url, this);
    }

    @Override
    public void realResponse(String responseStr) {
        i_base8052Fragment.deal8052Real(jsonBaseParse(responseStr, realClass));
    }

    @Override
    public void alertResponse(String responseStr) {
        i_base8052Fragment.deal8052Alert(jsonBaseParse(responseStr, alertClass));
    }
    
}
