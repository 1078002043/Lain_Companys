package computer_room.fragment;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.device_detail_lib.R;
import com.example.device_detail_lib.R2;

import java.util.List;

import base.Lain_Base_Activity;
import bean.EelectricityBean;
import butterknife.BindView;
import computer_room.bean.ElectricityDataBean;
import http.OkHttpCallBack;
import http.OkHttpUtil;
import util.LainNewApi;

/**
 * 电量仪监控的详请页
 */
public class ElectricityDetail extends Lain_Base_Activity implements OkHttpCallBack {

    //A电流
    @BindView(R2.id.a_electricity)
    TextView aElectricity;
    //A电压
    @BindView(R2.id.a_voltage)
    TextView aVoltage;
    //A有功功率
    @BindView(R2.id.a_active_power)
    TextView aActivePower;
    //A无功功率
    @BindView(R2.id.a_reactive_power)
    TextView aReactivePower;
    //A功素因素
    @BindView(R2.id.a_power_factor)
    TextView aPowerFactor;
    //B电流
    @BindView(R2.id.b_electricity)
    TextView bElectricity;
    //B电压
    @BindView(R2.id.b_voltage)
    TextView bVoltage;
    //B有功功率
    @BindView(R2.id.b_active_power)
    TextView bActivePower;
    //B无功功率
    @BindView(R2.id.b_reactive_power)
    TextView bReactivePower;
    //B功素因素
    @BindView(R2.id.b_power_factor)
    TextView bPowerFactor;
    //C电流
    @BindView(R2.id.c_electricity)
    TextView cElectricity;
    //C电压
    @BindView(R2.id.c_voltage)
    TextView cVoltage;
    //C有功功率
    @BindView(R2.id.c_active_power)
    TextView cActivePower;
    //C无功功率
    @BindView(R2.id.c_reactive_power)
    TextView cReactivePower;
    //C功率因素
    @BindView(R2.id.c_power_factor)
    TextView cPowerFactor;
    //AB相电压
    @BindView(R2.id.ab_line_voltage)
    TextView abLineVoltage;
    //BC相电压
    @BindView(R2.id.bc_line_voltage)
    TextView bcLineVoltage;
    //CA相电压
    @BindView(R2.id.ca_line_voltage)
    TextView caLineVoltage;
    //总功率因素
    @BindView(R2.id.total_power_factor)
    TextView totalPowerFactor;
    //总无功功率
    @BindView(R2.id.total_reactive_power)
    TextView totalReactivePower;
    //总有功功率
    @BindView(R2.id.total_active_power)
    TextView totalActivePower;
    //设备的详情数据
    private int electricityPemId;
    private volatile List<ElectricityDataBean> electricityDataBeans;

    //获取Toolbar
    @Override
    protected String getToolbarTitle() {
        return getIntent().getStringExtra("tool_title");
    }

    //设置 View
    @Override
    public int setLayoutView() {
        return R.layout.electricity_detail;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //获取设备的详情数据
        electricityPemId = getIntent().getIntExtra("ele_id", 0);

        //获取电量仪的详细数据
        getElectricityData();


    }

    /**
     * 获取电量仪设备数据
     */
    private void getElectricityData() {

        String url = LainNewApi.getInstance().getRootPath() + LainNewApi.elecDataAll;
        OkHttpUtil.getInstance().sendGetOkHttp("eleData", url, this);

    }

    @Override
    public void httpRequestSuccess(String requestTag, String requestUrl, String responseStr) {

        //解析电量仪的数据
        electricityDataBeans = OkHttpUtil.getInstance().formatResponse(responseStr, ElectricityDataBean.class);
        //请求成功后，更新数据
        runOnUiThread(this::updateData);

    }

    /**
     * 更新UI数据
     */
    private void updateData() {

        //根据 PemId 来获取对应设备的数据
        for (ElectricityDataBean dataBean :
                electricityDataBeans) {

            if (dataBean.getPemId() == electricityPemId) {
                //获取设备相应数据开，开始更新UI
                setElecData(dataBean);
            }

        }


    }

    /**
     * 修改UI数据
     *
     * @param dataBean 电量仪设备的数据
     */
    private void setElecData(ElectricityDataBean dataBean) {



        //A电流
        aElectricity.setText(dataBean.getPedAcur() + " A");
        //A电压
        aVoltage.setText(dataBean.getPedAvol() + " V");
        //A有功功率
        aActivePower.setText(dataBean.getPedApap() + " W");
        //A无功功率
        aReactivePower.setText(dataBean.getPedAprp() + " W");
        //A功素因素
        aPowerFactor.setText(dataBean.getPedAppf() + "");
        //B电流
        bElectricity.setText(dataBean.getPedBcur() + " A");
        //B电压
        bVoltage.setText(dataBean.getPedBvol() + " V");
        //B有功功率
        bActivePower.setText(dataBean.getPedBpap() + " W");
        //B无功功率
        bReactivePower.setText(dataBean.getPedBprp() + " W");
        //B功素因素
        bPowerFactor.setText(dataBean.getPedBppf() + "");
        //C电流
        cElectricity.setText(dataBean.getPedCcur() + " A");
        //C电压
        cVoltage.setText(dataBean.getPedCvol() + " V");
        //C有功功率
        cActivePower.setText(dataBean.getPedCpap() + " W");
        //C无功功率
        cReactivePower.setText(dataBean.getPedCprp() + " W");
        //C功率因素
        cPowerFactor.setText(dataBean.getPedCppf() + "");
        //AB相电压
        abLineVoltage.setText(dataBean.getPedABvol() + " V");
        //BC相电压
        bcLineVoltage.setText(dataBean.getPedBCvol() + " V");
        //CA相电压
        caLineVoltage.setText(dataBean.getPedCAvol() + " V");
        //总功率因素
        totalPowerFactor.setText(dataBean.getPedTppf() + "");
        //总无功功率
        totalReactivePower.setText(dataBean.getPedTprp() + " W");
        //总有功功率
        totalActivePower.setText(dataBean.getPedTpap() + " W");

    }

    @Override
    public void httpRequestFailure(String requestTag, String requestUrl, String errorMsg) {

    }

}
