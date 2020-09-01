package computer_room.i_interface;

import java.util.List;

import computer_room.bean.DeviceSwitchBean;

public interface I_DeviceSwitch {

    String getRealLink();
    void realDatResponse(List<DeviceSwitchBean> deviceSwitchBeans);

}
