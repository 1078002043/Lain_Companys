package view.i_view;

import java.util.List;

import bean.DischargeRealBean;
import bean.DissolvedRealBean;

public interface I_Discharge {

    /**
     * 实时数据请求成功回调
     * @param dischargeRealBeans
     */
    void dischargeRealComplete(List<DischargeRealBean> dischargeRealBeans);


}
