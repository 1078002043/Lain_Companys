package view.i_view;

import java.util.List;

import bean.NitriteRealBean;
import bean.OxygenIncreasingRealBean;

public interface I_Oxygen {
    /**
     * 实时数据请求成功回调
     * @param oxygenRealBeans
     */
    void oxygenRealComplete(List<OxygenIncreasingRealBean> oxygenRealBeans);
}
