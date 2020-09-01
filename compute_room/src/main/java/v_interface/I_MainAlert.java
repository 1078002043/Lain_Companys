package v_interface;

import java.util.List;

import bean.MainMonthAlert;

/**
 * Author：YIN_LUO_FEI
 * Date：2019/11/22 14:03
 * Description：主页-报警
 **/
public interface I_MainAlert {
    /**
     * 更新报警列表
     * @param alerts 报警数据
     */
    void monthAlert(List<MainMonthAlert> alerts);

}
