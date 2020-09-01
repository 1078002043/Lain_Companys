package present;

import base.BasePresenter;
import bean.SalinityRealBean;
import http.OkHttpUtil;
import view.i_view.I_Salinity;
/**
 * 盐度 Present
 */
public class SalinityPresent extends BasePresenter {
    private I_Salinity i_salinity;

    public SalinityPresent(I_Salinity i_salinity) {
        this.i_salinity = i_salinity;
    }

    @Override
    public void httpRequestSuccess(String requestTag, String requestUrl, String responseStr) {
        super.httpRequestSuccess(requestTag, requestUrl, responseStr);
        switch (requestTag) {
            case "salinity_real":
                i_salinity.salinityRealComplete(OkHttpUtil.getInstance().formatResponse(responseStr, SalinityRealBean.class));

                break;
        }
    }
}
