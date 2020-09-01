package computer_room.i_interface;

import java.util.List;

import bean.AlertBeans;
import bean.TemperatureBean;
import computer_room.bean.TemperatureLineBean;

/**
 * 温湿度 View 接口
 *
 * @author Create by Yin Luo Fei
 * @version 0.1
 * @Time 2019/8/28 15 : 01
 */
public interface I_Temperature {
    /**
     * 更新历史曲线列表
     *
     * @param dataList 列表数据
     */
    void initTemperatureRecycler(List<TemperatureBean> dataList);


    /**
     * 设备管理
     *
     * @param dataList 设备管理请求的列表数据
     */
//    void devicesManage(List<TemperatureBean> dataList);

    /**
     * 设备管理
     *
     * @param dataList 设备管理请求的列表数据
     */
    void devicesAlert(List<AlertBeans> dataList);

    /*
     * 先使用测试接口
     * */
//    void initChartTemper(List<TemperatureLineBean> historyBeans);//温度曲线

}
