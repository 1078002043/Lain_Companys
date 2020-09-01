package view_interface;

import adapter.BaseDeviceDetailManageAdapter;

public interface I_DeviceDetailManageLink<T> {
    /**
     * 设备管理-删除设备
     * @param position 需要删除设备所有的的位置
     * @param svHolder 设备的VIEW
     */
    void deleteDevice(int position, BaseDeviceDetailManageAdapter.SVHolder svHolder);

    /**
     * 设备管理-修改设备信息
     * @param deviceName 需要提供设备的名称
     * @param svHolder 修改设备的 View
     */
    void changeDevice(String deviceName, BaseDeviceDetailManageAdapter.SVHolder svHolder);

    void withBindDeviceManage(BaseDeviceDetailManageAdapter.SVHolder svHolder, T data, int position);

}
