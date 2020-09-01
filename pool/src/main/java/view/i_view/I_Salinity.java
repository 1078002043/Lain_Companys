package view.i_view;

import java.util.List;

import bean.OxygenIncreasingRealBean;
import bean.SalinityRealBean;

/**
 * 盐度 View 接口
 */
public interface I_Salinity {
    /**
     * 实时数据请求成功回调
     * @param salinityRealBeans
     */
    void salinityRealComplete(List<SalinityRealBean> salinityRealBeans);
}
