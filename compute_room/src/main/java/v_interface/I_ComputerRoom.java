package v_interface;

import java.util.List;

import bean.ConfigBean;

/**
 * 主页中 机房监控内的设备 View 接口
 *
 * @author Create by Yin Luo Fei
 * @version 0.1
 * @Time 2019/8/4 22 : 17
 */
public interface I_ComputerRoom {
    /**
     * 设置机房监控头部分类
     *
     * @param topNavs 顶部导航栏数据
     */
    void setTopNavigation(List<ConfigBean> topNavs);

}
