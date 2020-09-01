package computer_room.present;

import base.BasePresenter;
import bean.Alert8052Bean;
import computer_room.bean.Device8052Bean;
import computer_room.i_interface.I_Device8052;
import http.OkHttpUtil;
import util.LainNewApi;

public class Device8052Present extends BasePresenter {

    private I_Device8052 i_device8052;

    public Device8052Present(I_Device8052 i_device8052) {
        this.i_device8052 = i_device8052;
    }


}
