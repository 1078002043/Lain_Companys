package view.i_view;

import java.util.List;

import bean.AcidBaseRealBean;
import bean.HydrogenRealBean;

/**
 * 硫化氢 View 接口
 */
public interface I_Hydrogen {
    /**
     * 实时数据请求成功回调
     * @param hyDrogenRealBeans
     */
    void hyDrogenRealComplete(List<HydrogenRealBean> hyDrogenRealBeans);
}
