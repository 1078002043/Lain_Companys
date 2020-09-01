package view.i_view;

import java.util.List;

import bean.SamplingRealBean;
import bean.WaterTempRealBean;

/**
 * 水温检测
 */
public interface I_WaterTemp {

    /**
     * 实时数据请求成功回调
     * @param waterTempRealBeans
     */
    void waterTempRealComplete(List<WaterTempRealBean> waterTempRealBeans);

}
