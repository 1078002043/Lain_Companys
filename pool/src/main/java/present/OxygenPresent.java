package present;

import base.BasePresenter;
import bean.OxygenIncreasingRealBean;
import http.OkHttpUtil;
import view.i_view.I_Oxygen;
/**
 * 增氧泵 Present
 */
public class OxygenPresent extends BasePresenter {

    private I_Oxygen i_oxygen;

    public OxygenPresent(I_Oxygen i_oxygen) {
        this.i_oxygen = i_oxygen;
    }

    @Override
    public void httpRequestSuccess(String requestTag, String requestUrl, String responseStr) {
        super.httpRequestSuccess(requestTag, requestUrl, responseStr);
        switch (requestTag) {
            case "oxygen_real":
                i_oxygen.oxygenRealComplete(OkHttpUtil.getInstance().formatResponse(responseStr, OxygenIncreasingRealBean.class));

                break;
        }
    }
}
