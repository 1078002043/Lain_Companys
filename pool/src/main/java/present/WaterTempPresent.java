package present;

import base.BasePresenter;
import bean.WaterTempRealBean;
import http.OkHttpUtil;
import view.i_view.I_WaterTemp;
/**
 * 水温检测 Present
 */
public class WaterTempPresent extends BasePresenter {
    private I_WaterTemp i_waterTemp;

    public WaterTempPresent(I_WaterTemp i_waterTemp) {
        this.i_waterTemp = i_waterTemp;
    }

    @Override
    public void httpRequestSuccess(String requestTag, String requestUrl, String responseStr) {
        super.httpRequestSuccess(requestTag, requestUrl, responseStr);
        switch (requestTag) {
            case "water_temp_real":
                i_waterTemp.waterTempRealComplete(OkHttpUtil.getInstance().formatResponse(responseStr, WaterTempRealBean.class));
                break;
        }
    }
}
