package present;

import base.BasePresenter;
import bean.WaterLevelRealBean;
import http.OkHttpUtil;
import view.i_view.I_WaterLevel;
/**
 * 水位检测 Present
 */
public class WaterLevelPresent extends BasePresenter {
    private I_WaterLevel i_waterLevel;

    public WaterLevelPresent(I_WaterLevel i_waterLevel) {
        this.i_waterLevel = i_waterLevel;
    }

    @Override
    public void httpRequestSuccess(String requestTag, String requestUrl, String responseStr) {
        super.httpRequestSuccess(requestTag, requestUrl, responseStr);
        switch (requestTag) {
            case "water_level_real":
                i_waterLevel.waterLevelRealComplete(OkHttpUtil.getInstance().formatResponse(responseStr, WaterLevelRealBean.class));
                break;
        }
    }
}
