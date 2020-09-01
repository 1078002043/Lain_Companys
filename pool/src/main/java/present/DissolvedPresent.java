package present;

import base.BasePresenter;
import bean.DissolvedRealBean;
import http.OkHttpUtil;
import view.i_view.I_Dissolved;

/**
 * Author：YIN_LUO_FEI
 * Date：2019/12/15 10:54
 * Description：溶氧仪 Present
 **/

public class DissolvedPresent extends BasePresenter {

    private I_Dissolved i_dissolved;

    public DissolvedPresent(I_Dissolved i_dissolved) {
        this.i_dissolved = i_dissolved;
    }

    @Override
    public void httpRequestSuccess(String requestTag, String requestUrl, String responseStr) {
        super.httpRequestSuccess(requestTag, requestUrl, responseStr);

        switch (requestTag) {
            case "dissolved_real":
                i_dissolved.dissolvedRealComplete(OkHttpUtil.getInstance().formatResponse(responseStr, DissolvedRealBean.class));
                break;
        }
    }
}
