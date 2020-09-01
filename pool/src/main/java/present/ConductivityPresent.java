package present;

import base.BasePresenter;
import bean.ConductivityRealBean;
import http.OkHttpUtil;
import view.i_view.I_Conductivity;

/**
 *  电导率 Present
 */
public class ConductivityPresent extends BasePresenter {
    private I_Conductivity i_conductivity;

    public ConductivityPresent(I_Conductivity i_conductivity) {
        this.i_conductivity = i_conductivity;
    }

    @Override
    public void httpRequestSuccess(String requestTag, String requestUrl, String responseStr) {
        super.httpRequestSuccess(requestTag, requestUrl, responseStr);
        switch (requestTag) {
            case "conductivity_real":
                i_conductivity.conductivityRealComplete(OkHttpUtil.getInstance().formatResponse(responseStr, ConductivityRealBean.class));
                break;
        }
    }
}
