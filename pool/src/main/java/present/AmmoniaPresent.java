package present;

import android.util.Log;

import base.BasePresenter;
import bean.AmmoniaRealBean;
import http.OkHttpUtil;
import view.i_view.I_Ammonia;

/**
 * 氨氮监测 Present
 */
public class AmmoniaPresent extends BasePresenter {
    private I_Ammonia i_ammonia;

    public AmmoniaPresent(I_Ammonia i_ammonia) {
        this.i_ammonia = i_ammonia;
    }

    @Override
    public void httpRequestSuccess(String requestTag, String requestUrl, String responseStr) {
        Log.d("ljler", "httpRequestSuccess: "+responseStr);
        switch (requestTag) {
            case "ammonia_real":
                //初始化实时数据
                i_ammonia.ammoniaRealComplete(OkHttpUtil.getInstance().formatResponse(responseStr, AmmoniaRealBean.class));
                break;
        }
    }
}
