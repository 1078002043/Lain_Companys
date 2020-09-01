package base;

import android.content.Context;

import view_interface.I_Base_DevicesDetail_M;
import view_interface.I_Base_Devices_Detail;
import view_interface.I_Base_Devices_Detail_P;

/**
 * 设备点击进去的基类详情 Presenter
 *
 * @author Create by Yin Luo Fei
 * @version 0.1
 * @Time 2019/8/7 01 : 16
 */
public class Base_Devices_Detail_Presenter implements I_Base_Devices_Detail_P {
    //设备详情的 V Base_Devices_Detail
    private I_Base_Devices_Detail i_base_devices_detail;
    //设备详情的 M Base_DevicesDetail_Model
    private I_Base_DevicesDetail_M i_base_devicesDetail_m;
    private Context context;

    /**
     * 初始化 M 和 V
     *
     * @param i_base_devices_detail Base_Devices_Detail
     */
    public Base_Devices_Detail_Presenter(I_Base_Devices_Detail i_base_devices_detail) {
        this.i_base_devices_detail = i_base_devices_detail;
        this.i_base_devicesDetail_m = new Base_DevicesDetail_Model();
        context = Lain_Application.getContext();
    }


}
