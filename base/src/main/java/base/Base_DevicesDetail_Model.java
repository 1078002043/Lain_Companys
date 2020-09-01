package base;

import java.util.List;

import bean.DeviceDetailTopBean;
import bean.EnvironmentBean;
import util.MyPreferencesManager;
import view_interface.I_Base_DevicesDetail_M;

/**
 * 设备点击进去的详情 MODEL
 *
 * @author Create by Yin Luo Fei
 * @version 0.1
 * @Time 2019/8/7 01 : 18
 */
public class Base_DevicesDetail_Model implements I_Base_DevicesDetail_M {
    /**
     * 保存设备详情中的顶部导航栏
     *
     * @param key                 保存的 KEY
     * @param deviceDetailTopBeanList List 数据集
     */
    @Override
    public void saveDeviceDetailTopNav(String key, List<DeviceDetailTopBean> deviceDetailTopBeanList) {

        //获取文件上的设备数据
        List<EnvironmentBean> beforData = MyPreferencesManager.getList(key, EnvironmentBean.class);
        //如果为空或者长度不样，直接进行保存
        if (beforData.isEmpty() || deviceDetailTopBeanList.size() != beforData.size()) {
            MyPreferencesManager.putList(key, deviceDetailTopBeanList);
            return;
        }
        //获取的数据不为空，数据的长度一样时，对数据进行判断
        for (int index = 0; index < deviceDetailTopBeanList.size(); index++) {
            //获取文件上的数据
            String beforDataName = beforData.get(index).getName();
            //两个数据进行比较，如果有一个是不同的，则删除已有的，从新保存一遍
            if (!deviceDetailTopBeanList.get(index).getName().equals(beforDataName)) {
                MyPreferencesManager.remove(key);
                MyPreferencesManager.putList(key, deviceDetailTopBeanList);
                break;
            }
        }

    }

    /**
     * 获取环境监控
     *
     * @param key 通过KEY来获取指定的数据
     * @return List EnvironmentBean 数据
     */
    @Override
    public List<DeviceDetailTopBean> getDeviceDetailTopNav(String key) {
        return MyPreferencesManager.getList(key, DeviceDetailTopBean.class);
    }

}
