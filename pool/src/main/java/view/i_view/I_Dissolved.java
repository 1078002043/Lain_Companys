package view.i_view;

import java.util.List;

import bean.DissolvedRealBean;

/**
 * Author：YIN_LUO_FEI
 * Date：2019/12/15 11:13
 * Description：溶氧仪View接口
 **/
public interface I_Dissolved {
    /**
     * 实时数据请求成功回调
     * @param dissolvedRealBeans
     */
    void dissolvedRealComplete(List<DissolvedRealBean> dissolvedRealBeans);

}
