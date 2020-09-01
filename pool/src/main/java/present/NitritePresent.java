package present;

import base.BasePresenter;
import bean.NitriteRealBean;
import http.OkHttpUtil;
import view.i_view.I_Nitrite;
/**
 * 亚硝酸盐 Present
 */
public class NitritePresent extends BasePresenter {
    private I_Nitrite i_nitrite;

    public NitritePresent(I_Nitrite i_nitrite) {
        this.i_nitrite = i_nitrite;
    }

    @Override
    public void httpRequestSuccess(String requestTag, String requestUrl, String responseStr) {
        super.httpRequestSuccess(requestTag, requestUrl, responseStr);
        switch (requestTag) {
            case "nitrite_real":
                i_nitrite.nitriteRealComplete(OkHttpUtil.getInstance().formatResponse(responseStr, NitriteRealBean.class));

                break;
        }
    }
}
