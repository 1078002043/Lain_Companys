package computer_room.present;

import android.util.Log;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import base.BasePresenter;
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
import computer_room.i_interface.I_NewDeviceManage;
import http.OkHttpUtil;
import util.LainNewApi;

/**
 * Author：YIN_LUO_FEI
 * Date：2019/11/19 21:59
 * Description：设备管理 Presenter
 **/
public class NewDevicePresenter extends BasePresenter {

    private I_NewDeviceManage i_newDeviceManage;

    public NewDevicePresenter(I_NewDeviceManage i_newDeviceManage) {
        this.i_newDeviceManage = i_newDeviceManage;
    }

    /**
     * 更新报警设置报警方式
     *
     * @param alertSettingBean
     */
    public void updateAlertAway(AlertSettingBean alertSettingBean) {

        Map<String, String> map = new HashMap<>();

        //报警方式
        map.put("emailStatus", alertSettingBean.getEmailStatus() + "");
        map.put("smsStatus", alertSettingBean.getSmsStatus() + "");
        map.put("soundLightStatus", alertSettingBean.getSoundLightStatus() + "");
        map.put("phoneStatus", alertSettingBean.getPhoneStatus() + "");
        map.put("daId", alertSettingBean.getDaId() + "");

        String url = LainNewApi.getInstance().getRootPath() + LainNewApi.alertUpdateAway;

        OkHttpUtil.getInstance().sendPutOkHttp("updateAlertAway", url, OkHttpUtil.getInstance().mapToRow(map), this);

    }


    /**
     * 更新报警设置信息
     */
    public void updateAlertInfo(Map<String, String> map) {

        String url = LainNewApi.getInstance().getRootPath() + LainNewApi.alertInfoUpdate;

        OkHttpUtil.getInstance().sendPutOkHttp("updateAlertInfo", url, OkHttpUtil.getInstance().mapToRow(map), this);

    }

    /**
     * 请求IP设置列表
     */
    public void getDeviceIPList() {

//        String url = LainNewApi.getInstance().getRootUrl() + LainNewApi.findDeviceIPList;
        String url = LainNewApi.getInstance().getRootPath() + LainNewApi.findDeviceIPList;
        OkHttpUtil.getInstance().sendGetOkHttp("findDeviceIPList", url, this);

    }

    /**
     * 请求资产管理
     */
    public void getAssetsList() {

        String url = LainNewApi.getInstance().getRootPath() + LainNewApi.deviceAssetsList;
        OkHttpUtil.getInstance().sendGetOkHttp("deviceAssetsSetting", url, this);

    }

    /**
     * 请求报警设置
     */
    public void getAlertSetting() {

        String url = LainNewApi.getInstance().getRootPath() + LainNewApi.deviceAlertList;
        OkHttpUtil.getInstance().sendGetOkHttp("deviceAlertSetting", url, this);

    }

    /**
     * 请求 8060 列表
     */
    public void getDevice8060List() {

//        String url = LainNewApi.getInstance().getRootUrl() + LainNewApi.device8060List;
        String url = LainNewApi.getInstance().getRootPath() + LainNewApi.device8060List;
        OkHttpUtil.getInstance().sendGetOkHttp("findDevice8060List", url, this);

    }

    /**
     * 请求 8052 列表
     */
    public void getDevice8052List() {

//        String url = LainNewApi.getInstance().getRootUrl() + LainNewApi.device8052List;
        String url = LainNewApi.getInstance().getRootPath() + LainNewApi.device8052List;
        OkHttpUtil.getInstance().sendGetOkHttp("findDevice8052List", url, this);

    }

    /**
     * 请求设备IP中的列表
     */
    public void getDeviceIPAll() {
        String url = LainNewApi.getInstance().getRootPath() + LainNewApi.findDeviceIP;
        OkHttpUtil.getInstance().sendGetOkHttp("findDeviceIP", url, this);
    }

    /**
     * 请求所有设备中的列表
     */
    public void getDeviceAll() {
        String url = LainNewApi.getInstance().getRootPath() + LainNewApi.findDevice;
        OkHttpUtil.getInstance().sendGetOkHttp("findDevice", url, this);
    }

    /**
     * 请求所有设备中的列表
     */
    public void getDeviceAll8060() {
        String url = LainNewApi.getInstance().getRootPath() + LainNewApi.deviceManage8060Device;
        OkHttpUtil.getInstance().sendGetOkHttp("findDeviceAll8060", url, this);
    }

    /**
     * 请求所有设备中的列表
     */
    public void getDeviceAll8052() {
        String url = LainNewApi.getInstance().getRootPath() + LainNewApi.device8052AllDevice;
        OkHttpUtil.getInstance().sendGetOkHttp("findDeviceAll8052", url, this);
    }

    /**
     * 请求所有设备中的列表
     */
    public void get8060DeviceAll() {
        String url = LainNewApi.getInstance().getRootPath() + LainNewApi.deviceManage8060;
        OkHttpUtil.getInstance().sendGetOkHttp("find8060Device", url, this);
    }

    /**
     * 请求8052设备中的列表
     */
    public void get8052DeviceAll() {
        String url = LainNewApi.getInstance().getRootPath() + LainNewApi.find8052;
        OkHttpUtil.getInstance().sendGetOkHttp("find8052Device", url, this);
    }

    /**
     * 请求8052设备中的列表
     */
    public void getAssetDeviceAll() {
        String url = LainNewApi.getInstance().getRootPath() + LainNewApi.assetManageDevice;
        OkHttpUtil.getInstance().sendGetOkHttp("findAssetDevice", url, this);
    }

    //请求8060模块数据
    public void get8060DeviceIP() {
        String url = LainNewApi.getInstance().getRootPath() + LainNewApi.deviceManage8060IP;
        OkHttpUtil.getInstance().sendGetOkHttp("findDevice8060IP", url, this);
    }

    //请求8060模块数据
    public void getAssetDeviceIP() {
        String url = LainNewApi.getInstance().getRootPath() + LainNewApi.assetIP;
        OkHttpUtil.getInstance().sendGetOkHttp("findDeviceAssetIP", url, this);
    }

    //请求8060模块数据
    public void get8052DeviceIP() {
        String url = LainNewApi.getInstance().getRootPath() + LainNewApi.device8052IP;
        OkHttpUtil.getInstance().sendGetOkHttp("findDevice8052IP", url, this);
    }

    /**
     * 请求结果
     *
     * @param requestTag  请求的 标识
     * @param requestUrl  请求的 URL
     * @param responseStr 请求成功返回的 JSON
     */
    @Override
    public void httpRequestSuccess(String requestTag, String requestUrl, String responseStr) {
        Log.d("iopoipiopoip", "httpRequestSuccess: " + requestUrl + "---" + responseStr + "----" + requestTag);
        switch (requestTag) {
            case "findDeviceAssetIP":

                List<DeviceIPAllBean> ipAssets = OkHttpUtil.getInstance().formatResponse(responseStr, DeviceIPAllBean.class);
                i_newDeviceManage.findAssetsIp(ipAssets);

                break;
            case "findDevice8060IP":
                List<DeviceIPAllBean> ip8060 = OkHttpUtil.getInstance().formatResponse(responseStr, DeviceIPAllBean.class);
                i_newDeviceManage.find8060Ip(ip8060);
                break;
            case "findDevice8052IP":
                List<DeviceIPAllBean> ip8052 = OkHttpUtil.getInstance().formatResponse(responseStr, DeviceIPAllBean.class);
                i_newDeviceManage.find8052Ip(ip8052);
                break;
            //查询所有的设备
            case "findDeviceIP":

                //更新列表
//                i_newDeviceManage.getAllIPDevice(runningBeans);
                break;
            case "find8060Device":
                List<DeviceManage8060Bean> device8060Beans = OkHttpUtil.getInstance().formatResponse(responseStr, DeviceManage8060Bean.class);
                //更新列表
                i_newDeviceManage.findDevice8060(device8060Beans);
                break;
            case "find8052Device":

                List<DeviceManage8052Bean> deviceManage8052Beans = OkHttpUtil.getInstance().formatResponse(responseStr, DeviceManage8052Bean.class);
                //更新列表
                i_newDeviceManage.findDevice8052(deviceManage8052Beans);
                break;
            case "findDevice":
                List<DeviceAllBean> deviceAllBeans = OkHttpUtil.getInstance().formatResponse(responseStr, DeviceAllBean.class);
                //更新列表
                i_newDeviceManage.getAllDevice(deviceAllBeans);
                break;
            //插入设备
            case "insert_device":

                i_newDeviceManage.insertDevice(responseStr);
                break;
            //设备的 Did
            case "device_did":

                i_newDeviceManage.getDeviceDid(OkHttpUtil.getInstance().formatResponse(responseStr, DeviceManageDid.class));
                break;
            //通信的结果
            case "communication":
                i_newDeviceManage.deviceCommunication(responseStr);
                break;
            case "delete_device":
                //删除设备
                i_newDeviceManage.deleteDevice(responseStr);
                break;
            case "update_device":
                i_newDeviceManage.updateDevice(responseStr);
                break;
            case "findDeviceAll8060":
                List<Device8060AllBean> device8060AllBeans = OkHttpUtil.getInstance().formatResponse(responseStr, Device8060AllBean.class);
                //更新列表
                i_newDeviceManage.findAllDevice8060(device8060AllBeans);
                break;
            case "findDeviceAll8052":
                List<DeviceAll8052Bean> device8052AllBeans = OkHttpUtil.getInstance().formatResponse(responseStr, DeviceAll8052Bean.class);
                //更新列表
                i_newDeviceManage.findDeviceAll8052(device8052AllBeans);
                break;
            case "findAssetDevice":
                //资产管理
                List<AssetDeviceAllBean> assetDeviceAllBeans = OkHttpUtil.getInstance().formatResponse(responseStr, AssetDeviceAllBean.class);
                //更新列表
                i_newDeviceManage.findAllDeviceAsset(assetDeviceAllBeans);
                break;
            case "findDeviceIPList":
                Log.d("sdfasdf", "httpRequestSuccess: " + responseStr);

                List<DeviceIpList> deviceIpLists = OkHttpUtil.getInstance().formatResponse(responseStr, DeviceIpList.class);
                //更新列表
                i_newDeviceManage.findDeviceIPList(deviceIpLists);
                break;
            case "findDevice8060List":

                List<Device8060List> device8060Lists = OkHttpUtil.getInstance().formatResponse(responseStr, Device8060List.class);
                //更新列表
                i_newDeviceManage.findDevice8060List(device8060Lists);

                break;
            case "findDevice8052List":

                List<Device8052List> device8052Lists = OkHttpUtil.getInstance().formatResponse(responseStr, Device8052List.class);
                //更新列表
                i_newDeviceManage.findDevice8052List(device8052Lists);

                break;
            case "deviceAlertSetting":

                List<AlertSettingBean> alertSettingBeanList = OkHttpUtil.getInstance().formatResponse(responseStr, AlertSettingBean.class);
                //更新列表
                i_newDeviceManage.findAlertSettingList(alertSettingBeanList);

                break;
            case "deviceAssetsSetting":

                List<DeviceAssetsList> deviceAssetsLists = OkHttpUtil.getInstance().formatResponse(responseStr, DeviceAssetsList.class);
                //更新列表
                i_newDeviceManage.findAssetsList(deviceAssetsLists);

                break;
            case "updateAlertAway":
            case "updateAlertInfo":
                //更新报警设置信息
                i_newDeviceManage.updateAlertSetting(responseStr);
                break;
        }
    }

    /**
     * 插入设备
     */
    public void insertDevice(Map<String, String> deviceInfos) {

        String url = LainNewApi.getInstance().getRootPath() + LainNewApi.insertDevice;
        //插入设备
        OkHttpUtil.getInstance().sendRowOkHttp("insert_device", url, OkHttpUtil.getInstance().mapToRow(deviceInfos), this);

    }

    /**
     * 插入设备
     */
    public void insert8060Device(Map<String, String> deviceInfos) {

        String url = LainNewApi.getInstance().getRootPath() + LainNewApi.insert8060;
        //插入设备
        OkHttpUtil.getInstance().sendRowOkHttp("insert_device", url, OkHttpUtil.getInstance().mapToRow(deviceInfos), this);

    }

    /**
     * 插入设备
     */
    public void insert8060Device(Map<String, String> deviceInfos, String url) {

//        String url = LainNewApi.getInstance().getRootPath() + LainNewApi.insert8060;
        //插入设备
        OkHttpUtil.getInstance().sendRowOkHttp("insert_device", url, OkHttpUtil.getInstance().mapToRow(deviceInfos), this);

    }

    /**
     * 开启通信
     */
    public void deviceCommunication(Map<String, String> communicationMap, String diId, String diIsConnect) {
        String url = LainNewApi.getInstance().getRootPath() + LainNewApi.deviceCommunication + diId + "/" + diIsConnect;
        //开始通信
        OkHttpUtil.getInstance().sendPutOkHttp("communication", url, OkHttpUtil.getInstance().mapToRow(communicationMap), this);
    }

    /**
     * 删除设备
     *
     * @param diId 需要删除的设备 DI ID
     */
    public void deleteDevice(String diId) {

        String url = LainNewApi.getInstance().getRootPath() + LainNewApi.deviceDelete + diId;
        OkHttpUtil.getInstance().sendDeletedOkHttp("delete_device", url, this);

    }

    /**
     * 删除8060设备
     *
     * @param diId 需要删除的设备 DI ID
     */
    public void delete8060Device(String diId) {

        String url = LainNewApi.getInstance().getRootPath() + LainNewApi.delete8060 + diId;
        OkHttpUtil.getInstance().sendDeletedOkHttp("delete_device", url, this);

    }

    /**
     * 删除8060设备
     *
     * @param diId 需要删除的设备 DI ID
     */
    public void delete8060Device(String diId, String url) {

//        String url = LainNewApi.getInstance().getRootPath() + LainNewApi.delete8060 + diId;
        OkHttpUtil.getInstance().sendDeletedOkHttp("delete_device", url, this);

    }

    /**
     * 更新设备
     *
     * @param map 更新设备的信息
     */
    public void updateDevice(Map<String, String> map) {
        String url = LainNewApi.getInstance().getRootPath() + LainNewApi.deviceUpdate;
        OkHttpUtil.getInstance().sendPutOkHttp("update_device", url, OkHttpUtil.getInstance().mapToRow(map), this);
    }

    /**
     * 更新8060设备
     *
     * @param map 更新设备的信息
     */
    public void update8060Device(Map<String, String> map) {
        String url = LainNewApi.getInstance().getRootPath() + LainNewApi.update8060;
        OkHttpUtil.getInstance().sendPutOkHttp("update_device", url, OkHttpUtil.getInstance().mapToRow(map), this);
    }

    /**
     * 更新8060设备
     *
     * @param map 更新设备的信息
     */
    public void update8060Device(Map<String, String> map, String url) {
//        String url = LainNewApi.getInstance().getRootPath() + LainNewApi.update8060;
        OkHttpUtil.getInstance().sendPutOkHttp("update_device", url, OkHttpUtil.getInstance().mapToRow(map), this);
    }

}
