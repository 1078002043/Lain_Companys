package action;

import android.print.PrinterId;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: SaveInterface
 * @Description: 保存接口的实例
 * @Author: YIN LUO FEI
 * @Date: 2020/4/20 17:41
 */
public class SaveInterface {
    //设备添加，修改接口回调
    private DeviceAction deviceAction;
    //保存获取到的设备所有组
    private List<DeviceGroupBean> groupBeanList = new ArrayList<>();
    //保存获取到的设备IP
    private List<DeviceIPBean> deviceIPBeanList;

    private static SaveInterface saveInterface;

    public static SaveInterface getInstance() {

        if (saveInterface == null)
            synchronized (DeviceAction.class) {
                if (saveInterface == null) {

                    saveInterface = new SaveInterface();

                }
            }

        return saveInterface;
    }

    /**
     * 获取接口保存的实例
     *
     * @return
     */
    public DeviceAction getDeviceAction() {
        return deviceAction;
    }

    /**
     * 设置接口的实例
     *
     * @param deviceAction
     */
    public void setDeviceAction(DeviceAction deviceAction) {
        this.deviceAction = deviceAction;
    }

    /**
     * 获取Spinner的值
     *
     * @param spinner
     * @return
     */
    public String getSpinnerString(Spinner spinner) {
        return "";
    }

    /**
     * 获取所有的组
     *
     * @return 所有的组
     */
    public List<DeviceGroupBean> getGroupBeanList() {

        if (groupBeanList.isEmpty()) {
            DeviceGroupBean b = new DeviceGroupBean();
            b.setGName("莱安");
            groupBeanList.add(b);
        }

        return groupBeanList;
    }

    /**
     * 保存所有的组
     *
     * @return 所有的组
     */
    public void setGroupBeanList(List<DeviceGroupBean> groupBeanList) {
        this.groupBeanList = groupBeanList;
    }

    /**
     * 获取设备的所有IP
     *
     * @return IP列表
     */
    public List<DeviceIPBean> getDeviceIPBeanList() {
        return deviceIPBeanList;
    }

    /**
     * 保存设备的所有IP
     *
     * @param deviceIPBeanList IP列表
     */
    public void setDeviceIPBeanList(List<DeviceIPBean> deviceIPBeanList) {
        this.deviceIPBeanList = deviceIPBeanList;
    }
}
