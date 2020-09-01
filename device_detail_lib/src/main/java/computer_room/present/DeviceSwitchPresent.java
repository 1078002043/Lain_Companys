package computer_room.present;

import base.BasePresenter;
import computer_room.bean.DeviceSwitchBean;
import computer_room.i_interface.I_DeviceSwitch;
import http.OkHttpUtil;
import util.LainNewApi;

public class DeviceSwitchPresent extends BasePresenter {

    private I_DeviceSwitch i_deviceSwitch;

    public DeviceSwitchPresent(I_DeviceSwitch i_deviceSwitch) {
        this.i_deviceSwitch = i_deviceSwitch;
    }

    @Override
    public void queryRealData() {
        String url = LainNewApi.getInstance().getRootPath() + i_deviceSwitch.getRealLink();
        OkHttpUtil.getInstance().sendGetOkHttp(REAL_DATA, url, this);
    }

    @Override
    public void realResponse(String responseStr) {
        i_deviceSwitch.realDatResponse(jsonBaseParse(responseStr, DeviceSwitchBean.class));
    }
}
