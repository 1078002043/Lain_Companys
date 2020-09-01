package present;

import android.util.Log;

import base.BasePresenter;
import bean.SamplingRealBean;
import http.OkHttpUtil;
import view.i_view.I_Sampling;
/**
 * 取样泵 Present
 */
public class SamplingPresent extends BasePresenter {
    private I_Sampling i_sampling;

    public SamplingPresent(I_Sampling i_sampling) {
        this.i_sampling = i_sampling;
    }

    @Override
    public void httpRequestSuccess(String requestTag, String requestUrl, String responseStr) {

        super.httpRequestSuccess(requestTag, requestUrl, responseStr);
        switch (requestTag) {
            case "sampling_real":
                Log.d("jkhlet", "httpRequestSuccess: "+responseStr);
                i_sampling.samplingRealComplete(OkHttpUtil.getInstance().formatResponse(responseStr, SamplingRealBean.class));
                break;
        }
    }
}
