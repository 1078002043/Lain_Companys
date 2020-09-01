package view_interface;

import java.util.List;

import bean.DeviceDetailTopBean;

/**
 * 设备详细信息中 顶部导航栏
 *
 * @author Create by Yin Luo Fei
 * @version 0.1
 * @Time 2019/8/7 01 : 18
 */
public interface I_Base_DevicesDetail_M {
    /**
     * 保存顶部导航栏的数据
     * @param key 保存的key
     * @param deviceDetailTopBeanList 保存的数据
     */
    void saveDeviceDetailTopNav(String key, List<DeviceDetailTopBean> deviceDetailTopBeanList);

    /**
     * 保存顶部导航栏
     *
     * @param key 保存的 KEY
     * @return 顶部导航栏对象
     */
    List<DeviceDetailTopBean> getDeviceDetailTopNav(String key);
}
