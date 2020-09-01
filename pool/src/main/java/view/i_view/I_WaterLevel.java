package view.i_view;

import java.util.List;

import bean.SamplingRealBean;
import bean.WaterLevelRealBean;

/**
 * 水位检测
 */
public interface I_WaterLevel {
    /**
     * 实时数据请求成功回调
     * @param waterLevelRealBeans
     */
    void waterLevelRealComplete(List<WaterLevelRealBean> waterLevelRealBeans);
}
