package present;

import base.BasePresenter;
import bean.HydrogenRealBean;
import http.OkHttpUtil;
import view.i_view.I_Hydrogen;
/**
 * 硫化氢 Present
 */
public class HydrogenPresent extends BasePresenter {
    private I_Hydrogen i_hydrogen;

    public HydrogenPresent(I_Hydrogen i_hydrogen) {
        this.i_hydrogen = i_hydrogen;
    }

    @Override
    public void httpRequestSuccess(String requestTag, String requestUrl, String responseStr) {
        super.httpRequestSuccess(requestTag, requestUrl, responseStr);
        switch (requestTag) {
            case "hyDrogen_real":
                i_hydrogen.hyDrogenRealComplete(OkHttpUtil.getInstance().formatResponse(responseStr, HydrogenRealBean.class));

                break;
        }
    }
}
