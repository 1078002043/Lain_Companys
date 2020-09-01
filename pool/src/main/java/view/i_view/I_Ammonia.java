package view.i_view;

import java.util.List;

import bean.AcidBaseRealBean;
import bean.AmmoniaRealBean;

public interface I_Ammonia {
    /**
     * 实时数据请求成功回调
     * @param ammoniaRealBeans
     */
    void ammoniaRealComplete(List<AmmoniaRealBean> ammoniaRealBeans);
}
