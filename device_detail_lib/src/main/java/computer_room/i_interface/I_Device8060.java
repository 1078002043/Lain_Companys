package computer_room.i_interface;

import java.util.List;

import base.BaseBean;

/**
 * 8060设备接口
 */
public interface I_Device8060<E>  {
    //获取实时链接
    public String getRealLink();
    //获取报警数据
    public String getAlertLink();
    //获取历史数据
    public String getHistoryLink();
    //获取设备管理
    public String getManageLink();

    <E> void jsonParseResponse(List<E> listClass);

    <E> void jsonParseAlert(List<E> alertList);

    <E> void jsonParseHistory(List<E> historyList);

    <E> void jsonParseManage(List<E> manageList);

    public <E> Class<E> getParseClass();


}
