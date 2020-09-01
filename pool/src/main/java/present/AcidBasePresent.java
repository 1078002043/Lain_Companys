package present;

import base.BasePresenter;
import bean.AcidBaseRealBean;
import http.OkHttpUtil;
import view.i_view.I_AcidBase;

/**
 * 酸碱仪  PH Present
 */
public class AcidBasePresent extends BasePresenter {

    private I_AcidBase i_acidBase;

    public AcidBasePresent(I_AcidBase i_acidBase) {
        this.i_acidBase = i_acidBase;
    }

    @Override
    public void httpRequestSuccess(String requestTag, String requestUrl, String responseStr) {
        super.httpRequestSuccess(requestTag, requestUrl, responseStr);
        switch (requestTag) {
            case "acidBase_real":
                i_acidBase.acidBaseRealComplete(OkHttpUtil.getInstance().formatResponse(responseStr, AcidBaseRealBean.class));
                break;
        }
    }
}
