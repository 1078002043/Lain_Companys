package view.i_view;

import java.util.List;

import bean.SalinityRealBean;
import bean.SamplingRealBean;

/**
 * 取样泵
 */
public interface I_Sampling {
    /**
     * 实时数据请求成功回调
     * @param samplingRealBeans
     */
    void samplingRealComplete(List<SamplingRealBean> samplingRealBeans);
}
