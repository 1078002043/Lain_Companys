package computer_room.i_interface;

import java.util.List;

import computer_room.bean.AlertSettingBean;
import computer_room.bean.AssetDeviceAllBean;
import computer_room.bean.Device8052List;
import computer_room.bean.Device8060AllBean;
import computer_room.bean.Device8060List;
import computer_room.bean.DeviceAll8052Bean;
import computer_room.bean.DeviceAllBean;
import computer_room.bean.DeviceAssetsList;
import computer_room.bean.DeviceIPAllBean;
import computer_room.bean.DeviceIpList;
import computer_room.bean.DeviceManage8052Bean;
import computer_room.bean.DeviceManage8060Bean;
import computer_room.bean.DeviceManageDid;
import computer_room.bean.DeviceRunningBean;

/**
 * Author：YIN_LUO_FEI
 * Date：2019/12/3 8:03
 * Description：设备管理的 V 接口
 **/
public interface I_NewDeviceManage {

    /**
     * 更新报警设置信息
     * @param responseStr 更新结果
     */
    public void updateAlertSetting(String responseStr);

    /**
     * 设备管理中的设备
     * @param runningBeans 正在运行的设备
     * @param alertDeviceBeanList 报警中的设备
     * @param microstartList 未启动的设备
     */
//    public void getAllIPDevice(List<DeviceIPAllBean> runningBeans);
    public void getAllDevice(List<DeviceAllBean> deviceAllBeans);

    /**
     * 插入设备
     * @param insertResult 插入设备的结果
     */
    public void insertDevice(String insertResult);

    /**
     * 获取设备的 Did，用于插入添加
     * @param deviceManageDids 设备的Did
     */
    public void getDeviceDid(List<DeviceManageDid> deviceManageDids);

    /**
     * 设备的通信
     */
    public void deviceCommunication(String communicationResult);

    /**
     * 删除设备
     * @param deleteResult 删除设备的结果
     */
    public void deleteDevice(String deleteResult);

    /**
     * 更新设备
     * @param updateResult 更新设备的结果
     */
    public void updateDevice(String updateResult);

    public void findDevice8060(List<DeviceManage8060Bean> manage8060Beans);
    public void findDevice8052(List<DeviceManage8052Bean> manage8060Beans);
    public void findDeviceAll8052(List<DeviceAll8052Bean> manage8060Beans);

    public void findAllDevice8060(List<Device8060AllBean> device8060AllBeans);
    //资产管理
    public void findAllDeviceAsset(List<AssetDeviceAllBean> assetDeviceAllBeans);

    //查询所有设备的 IP 和 设备名称
    public void findDeviceIPList(List<DeviceIpList> assetDeviceAllBeans);
    //查询8060所有设备的 IP 和 设备名称
    public void findDevice8060List(List<Device8060List> assetDeviceAllBeans);
    //查询8060所有设备的 IP 和 设备名称
    void findDevice8052List(List<Device8052List> device8052Lists);
    //查询所有报警设置
    void findAlertSettingList(List<AlertSettingBean> device8052Lists);
    //查询所有资产管理
    void findAssetsList(List<DeviceAssetsList> deviceAssetsLists);

    //查询8060的IP
    void find8060Ip(List<DeviceIPAllBean> ip8060);
    //查询8052的IP
    void find8052Ip(List<DeviceIPAllBean> ip8052);
    //查询资产管理的IP
    void findAssetsIp(List<DeviceIPAllBean> ipAssets);
}
