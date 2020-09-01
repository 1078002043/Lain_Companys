package computer_room.i_interface;

import java.util.List;

import bean.AddEventBean;

/**
 * 日志管理
 *
 * @author Create by Yin Luo Fei
 * @version 0.1
 * @Time 2019/8/17 16 : 16
 */
public interface I_LogManagement {

    /**
     * 日志管理 设备查询成功数据列表
     * @param addList 添加事件
     * @param delList 删除事件
     * @param changeList 修改事件
     * @param queryList 查询事件
     * @param allList 所有日志事件
     */
    void setLogManagementList(List<AddEventBean.SysLogBean.ListBean> addList, List<AddEventBean.SysLogBean.ListBean> delList, List<AddEventBean.SysLogBean.ListBean> changeList, List<AddEventBean.SysLogBean.ListBean> queryList, List<AddEventBean.SysLogBean.ListBean> allList);

    /**
     * 查询系统日志
     * @param responseStr 请求的结果
     */
    void logDataParser(String responseStr);

    /**
     * 查询日志失败时回调
     * @param errorMsg
     */
    void httpFailed(String errorMsg);

    /**
     * 日志导出
     * @param responseStr 请求的结果
     */
    void logExport(String responseStr);

}
