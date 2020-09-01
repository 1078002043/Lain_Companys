package computer_room.i_interface;

import java.util.List;

import computer_room.bean.SplitAirBean;

/**
 * 分体空调
 *
 * @author Create by Yin Luo Fei
 * @version 0.1
 * @Time 2019/8/17 16 : 09
 */
public interface I_SplitAir {
    /**
     * 分体空调 设备列表
     *
     * @param splitAirList 设备列表数据
     */

    /**
     * 设备管理
     *
     * @param dataList 设备管理请求的列表数据
     */
    void devicesManage(List<SplitAirBean> dataList);

//    void setSplitAirList(List<SplitAirBean> splitAirList);
//
//    void setSplitAirManage(List<SplitAirManageBean> airManageBeans);
//
//    void devicesAlert(List<SplitAirAlertBean> dataList);




}
