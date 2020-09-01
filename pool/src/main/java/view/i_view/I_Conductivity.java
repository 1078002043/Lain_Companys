package view.i_view;

import java.util.List;

import bean.AmmoniaRealBean;
import bean.ConductivityRealBean;

/**
 * 电导率 View 接口
 */
public interface I_Conductivity {
    /**
     * 实时数据请求成功回调
     * @param conductivityRealBeans
     */
    void conductivityRealComplete(List<ConductivityRealBean> conductivityRealBeans);
}
