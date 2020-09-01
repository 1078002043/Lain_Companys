package view.i_view;

import java.util.List;

import bean.HydrogenRealBean;
import bean.NitriteRealBean;

/**
 * 亚硝酸盐
 */
public interface I_Nitrite {
    /**
     * 实时数据请求成功回调
     * @param nitriteRealBeans
     */
    void nitriteRealComplete(List<NitriteRealBean> nitriteRealBeans);
}
