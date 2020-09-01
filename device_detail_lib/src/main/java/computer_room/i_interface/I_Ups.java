package computer_room.i_interface;

import java.util.List;

import computer_room.bean.UpsBean;


/**
 * UPS View 接口
 *
 * @author Create by Yin Luo Fei
 * @version 0.1
 * @Time 2019/8/17 16 : 38
 */
public interface I_Ups {
    /**
     * UPS 数据列表
     *
     * @param beans 设备数据列表
     */
    void setUPSData(List<UpsBean> beans);

    /**
     * 设备管理
     * @param dataList 设备管理请求的列表数据
     */
    void devicesManage(List<UpsBean> dataList);

//    void devicesAlert(List<UPSAlertBean> dataList);


}
