package i_v;

import computer_room.adapter.BaseDeviceDetailManageAdapter;

/**
 * 设备详情中的设备管理接口
 * @param <T> 解析类
 */
public interface I_DeviceDetailManageLink<T> {
    /**
     * 设备管理-删除设备
     * @param position 需要删除设备所有的的位置
     * @param svHolder 设备的VIEW
     */
    void deleteDevice(int position, BaseDeviceDetailManageAdapter.SVHolder svHolder);

    /**
     * 设备管理-修改设备信息
     * @param flag 需要提供设备的名称
     * @param svHolder 修改设备的 View
     */
    void changeDevice(String flag, BaseDeviceDetailManageAdapter.SVHolder svHolder);

    /**
     * 删除，修改Popup
     */
//    void editPopup();

    void withBindDeviceManage( BaseDeviceDetailManageAdapter.SVHolder svHolder, T data, int position);

}
