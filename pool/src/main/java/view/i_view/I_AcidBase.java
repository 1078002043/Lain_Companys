package view.i_view;

import java.util.List;

import bean.AcidBaseRealBean;
import bean.DissolvedRealBean;

/**
 * 酸碱仪  PH View接口
 */
public interface I_AcidBase {
    /**
     * 实时数据请求成功回调
     * @param acidBaseRealBeans
     */
    void acidBaseRealComplete(List<AcidBaseRealBean> acidBaseRealBeans);

}
