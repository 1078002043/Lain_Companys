package computer_room.i_interface;

import java.util.List;

import bean.Alert8052Bean;
import computer_room.bean.Device8052Bean;

/**
 * 8052设备的接口
 */
public interface I_Device8052 {

    /**
     * 实时数据
     * @param device8052Bean
     */
    void dealRealData8052(List<Device8052Bean> device8052Bean);

    /**
     * 报警数据
     * @param alert8052Beans
     */
    void dealAlertData8052(List<Alert8052Bean> alert8052Beans);

    /**
     * 获取实时数据的请求链接
     * @return 链接
     */
    String getLinkReal();
    /**
     * 获取报警数据的请求链接
     * @return 链接
     */
    String getLinkAlert();

}
