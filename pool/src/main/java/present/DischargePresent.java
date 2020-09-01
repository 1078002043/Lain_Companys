package present;

import base.BasePresenter;
import bean.DischargeRealBean;
import http.OkHttpUtil;
import view.i_view.I_Discharge;

/**
 * 水流量 Present
 */
public class DischargePresent extends BasePresenter {
    private I_Discharge i_discharge;

    public DischargePresent(I_Discharge i_discharge) {
        this.i_discharge = i_discharge;
    }

    @Override
    public void httpRequestSuccess(String requestTag, String requestUrl, String responseStr) {
        super.httpRequestSuccess(requestTag, requestUrl, responseStr);
        switch (requestTag) {
            case "discharge_real":
                i_discharge.dischargeRealComplete(OkHttpUtil.getInstance().formatResponse(responseStr, DischargeRealBean.class));
                break;
        }
    }
}
