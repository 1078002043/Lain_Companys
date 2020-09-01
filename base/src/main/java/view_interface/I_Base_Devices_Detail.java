package view_interface;

import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.cardview.widget.CardView;

import java.util.List;

import bean.DeviceDetailTopBean;

/**
 * ${function}
 *
 * @author Create by Yin Luo Fei
 * @version 0.1
 * @Time 2019/8/7 01 : 16
 */
public interface I_Base_Devices_Detail {
    /**
     * 设置顶部导航栏
     * @param beans 顶部导航栏的数据
     */
    void setTopNavigation(List<DeviceDetailTopBean> beans);

    /**
     * 获取 ViewPager 的 Layout
     * @return ViewPager中所有的 View
     */
    List<Integer> getViewPagerView();

    /**
     * 设置显示的ITEM
     * @param head_panel
     * @param real
     * @param alert
     * @param history
     * @param manage
     */
    void setTopNav(RelativeLayout head_panel, LinearLayout real, LinearLayout alert, LinearLayout history, LinearLayout manage);

}
