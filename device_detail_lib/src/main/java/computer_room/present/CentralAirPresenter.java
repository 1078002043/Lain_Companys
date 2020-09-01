package computer_room.present;

import android.content.Context;

import java.util.List;

import base.BasePresenter;
import base.Lain_Application;
import computer_room.bean.CentralAirBean;
import computer_room.i_interface.I_CentralAir;
import http.OkHttpCallBack;
import http.OkHttpUtil;

/**
 * 中央空调 Presenter
 *
 * @author Create by Yin Luo Fei
 * @version 0.1
 * @Time 2019/8/17 13 : 53
 */
public class CentralAirPresenter extends BasePresenter implements OkHttpCallBack {
    private Context context = Lain_Application.getContext();
    private I_CentralAir i_centralAir;

    public CentralAirPresenter(I_CentralAir i_centralAir) {
        this.i_centralAir = i_centralAir;
    }

    /**
     * 初始化 实时数据列表
     */
    public void initCentralAirList() {

    }

    @Override
    public void httpRequestSuccess(String requestTag, String requestUrl, String response) {
        //将结果改成 数组JSON类型，这样解析容易些
        String responseStr = "[" + response + "]";
        //解析JSON
        List<CentralAirBean> centralAirList = OkHttpUtil.getInstance().formatResponse(responseStr, CentralAirBean.class);

    }
}
