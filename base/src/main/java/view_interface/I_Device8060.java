package view_interface;

import java.util.List;

/**
 * 8060设备接口
 */
public interface I_Device8060<E> {
    //获取实时链接
    public String getRealLink();

    //获取报警数据
    public String getAlertLink();

    //获取历史数据
    public String getHistoryLink();

    //获取设备管理
    public String getManageLink();

    /**
     * 解析实时数据
     *
     * @param listClass 获取到的实时数据
     * @param <E>       解析数据的类
     */
    <E> void jsonParseResponse(List<E> listClass);

    /**
     * 解析报警数据
     *
     * @param alertList 获取到的报警数据
     * @param <E>       解析报警数据的类
     */
    <E> void jsonParseAlert(List<E> alertList);

    /**
     * 解析历史数据
     *
     * @param historyList 获取到历史数据
     * @param <E>         解析历史数据的类
     */
    <E> void jsonParseHistory(List<E> historyList);

    /**
     * 解析设备管理的数据
     *
     * @param manageList 获取到的设备管理数据
     * @param <E>        解析设备管理的数据
     */
    <E> void jsonParseManage(List<E> manageList);

    /**
     * 获取解析的数据的类型
     *
     * @param <E>
     * @return
     */
    public <E> Class<E> getParseClass();


}
