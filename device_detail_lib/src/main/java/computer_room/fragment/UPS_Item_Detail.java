package computer_room.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.device_detail_lib.R;
import com.example.device_detail_lib.R2;

import base.Lain_Base_Activity;
import butterknife.BindView;
import computer_room.bean.UpsBean;

/**
 * UPS 详情页
 */
public class UPS_Item_Detail extends Lain_Base_Activity {

    //电池电流
    @BindView(R2.id.battery_current)
    TextView batteryCurrent;
    //电池温度
    @BindView(R2.id.battery_temperature)
    TextView batteryTemperature;
    //输入频率
    @BindView(R2.id.input_frequency)
    TextView inputFrequency;
    //旁路频率
    @BindView(R2.id.bypass_frequency)
    TextView bypassFrequency;
    //UPS温度
    @BindView(R2.id.ups_temperature)
    TextView upsTemperature;
    //UPS负载
    @BindView(R2.id.ups_load)
    TextView upsLoad;
    //UPS工作状态
//    @BindView(R2.id.ups_working_state)
    TextView upsWorkingState;
    //电池状态
    @BindView(R2.id.battery_status)
    TextView batteryStatus;
    //逆变器状态
    @BindView(R2.id.inverter_status)
    TextView inverterStatus;
    //整流器状态
    @BindView(R2.id.rectifier_state)
    TextView rectifierState;
    //A相输入电压
    @BindView(R2.id.input_voltage_a)
    TextView inputVoltageA;
    //B相输入电压
    @BindView(R2.id.input_voltage_b)
    TextView inputVoltageB;
    //C相输入电压
    @BindView(R2.id.input_voltage_c)
    TextView inputVoltageC;
    //A相旁路电压
    @BindView(R2.id.bypass_voltage_a)
    TextView bypassVoltageA;
    //B相旁路电压
    @BindView(R2.id.bypass_voltage_b)
    TextView bypassVoltageB;
    //C相旁路电压
    @BindView(R2.id.bypass_voltage_c)
    TextView bypassVoltageC;
    //A相逆变器电压
    @BindView(R2.id.inverter_voltage_a)
    TextView inverterVoltageA;
    //B相逆变器电压
    @BindView(R2.id.inverter_voltage_b)
    TextView inverterVoltageB;
    //C相逆变器电压
    @BindView(R2.id.inverter_voltage_c)
    TextView inverterVoltageC;
    //A相负载百分比
    @BindView(R2.id.load_percentage_a)
    TextView loadPercentageA;
    //B相负载百分比
    @BindView(R2.id.load_percentage_b)
    TextView loadPercentageB;
    //C相负载百分比
    @BindView(R2.id.load_percentage_c)
    TextView loadPercentageC;
    //电池类型（在线式/后备式）
    @BindView(R2.id.battery_type)
    TextView batteryType;
    //剩余容量
    @BindView(R2.id.reactive_power_b)
    TextView reactivePower;
    //剩余时间
    @BindView(R2.id.power_factor_b)
    TextView powerFactor;
    @BindView(R2.id.ups_load_text)
    TextView upsLoadText;
    @BindView(R2.id.ups_temperature_text)
    TextView upsTemperatureText;
    @BindView(R2.id.battery_type_text)
    TextView batteryTypeText;
    @BindView(R2.id.inverter_status_text)
    TextView inverterStatusText;
    @BindView(R2.id.rectifier_state_text)
    TextView rectifierStateText;
    //单个的UPS 设备数据
    private UpsBean upsBean;

    @Override
    protected String getToolbarTitle() {
        //获取传递过来的 UPS 设备数据
        upsBean = (UpsBean) getIntent().getSerializableExtra("ups");
        return upsBean.getName();
    }

    @Override
    public int setLayoutView() {
        return R.layout.ups_detail;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //电池电压batCur
        TextView electric_b = findViewById(R.id.electric_b);
        electric_b.setText(upsBean.getBatVol() + " V");

        //电池电流
        TextView battery_current = findViewById(R.id.battery_current);
        battery_current.setText(upsBean.getBatCur() + " A");

        //电池电流
        TextView rectifier_state = findViewById(R.id.rectifier_state);
        //整流器状态
        statusSetText(upsBean.getInTest(), rectifier_state, rectifierStateText);
//        if (upsBean.getInTest() == 0) {
//            rectifier_state.setText("正常");
//        } else if (upsBean.getInTest() == 1)
//            rectifier_state.setText("异常");
//        else
//            rectifier_state.setText("未连接");


        //电池温度
        batteryTemperature.setText(upsBean.getBatTemp() + " °C");
        //输入频率
        inputFrequency.setText(upsBean.getIntFrequency() + " Hz");
        //逆变器状态
        statusSetText(upsBean.getBatVolLow(), inverterStatus, inverterStatusText);
//        if (upsBean.getBatVolLow() == 0) {
//            inverterStatus.setText("正常");
//        } else if (upsBean.getBatVolLow() == 1)
//            inverterStatus.setText("异常");
//        else
//            inverterStatus.setText("未连接");

        //UPS负载状态
        statusSetText(upsBean.getBypActivate(), upsLoad, upsLoadText);
//        if (upsBean.getBypActivate() == 0) {
//            upsLoad.setText("正常");
//        } else if (upsBean.getBypActivate() == 1)
//            upsLoad.setText("异常");
//        else
//            upsLoad.setText("未连接");

        //UPS温度状态
        statusSetText(upsBean.getIntFault(), upsTemperature, upsTemperatureText);
//        if (upsBean.getIntFault() == 0) {
//            upsTemperature.setText("正常");
//        } else if (upsBean.getIntFault() == 1)
//            upsTemperature.setText("异常");
//        else
//            upsTemperature.setText("未连接");

        //UPS工作状态
//        if (upsBean.getIntVolFault() == 0) {
//            batteryStatus.setText("正常");
//        } else if (upsBean.getIntFault() == 1)
//            batteryStatus.setText("异常");
//        else
//            batteryStatus.setText("未连接");

        //电池类型
        setUpsType(upsBean.getUpsType(), batteryType, batteryTypeText);
//        if (upsBean.getUpsType() == 0) {
//            batteryType.setText("在线式");
//        } else {
//            batteryType.setText("后备式");
//        }

        //旁路频率
        bypassFrequency.setText(upsBean.getBypFrequency() + " Hz");
        //电池容量
        reactivePower.setText(upsBean.getBatCapacity() + " %");
        //剩余时间
        powerFactor.setText(upsBean.getResTime() + " 分钟");

       /* //旁路频率
       bypassFrequency.setText();
        //UPS温度
       upsTemperature.setText();
        //UPS负载
       upsLoad.setText();
        //UPS工作状态
        upsWorkingState.setText();
        //电池状态
        batteryStatus.setText();
        //逆变器状态
        inverterStatus.setText();
        //整流器状态
        rectifierState.setText();
        //A相输入电压
        inputVoltageA.setText();
        //B相输入电压
        inputVoltageB.setText();
        //C相输入电压
        inputVoltageC.setText();
        //A相旁路电压
       bypassVoltageA.setText();
        //B相旁路电压
        bypassVoltageB.setText();
        //C相旁路电压
       bypassVoltageC.setText();
        //A相逆变器电压
       inverterVoltageA.setText();
        //B相逆变器电压
       inverterVoltageB.setText();
        //C相逆变器电压
       inverterVoltageC.setText();
        //A相负载百分比
       loadPercentageA.setText();
        //B相负载百分比
        loadPercentageB.setText();
        //C相负载百分比
        loadPercentageC.setText();*/

    }

    public void setUpsType(int status, TextView upsTextView, TextView textView) {

        switch (status) {
            case 0:
                upsTextView.setText("交流输入正常");
                upsTextView.setTextColor(getColor(R.color.colorPrimary));
                textView.setTextColor(getColor(R.color.colorPrimary));
                break;
            case 1:
                upsTextView.setText("后备供电中");
                upsTextView.setTextColor(Color.RED);
                textView.setTextColor(Color.RED);
                break;
            default:
                upsTextView.setText("未连接");
                upsTextView.setTextColor(Color.GRAY);
                textView.setTextColor(Color.GRAY);
                break;
        }

    }

    /**
     * 设置文本的状态
     *
     * @param textView 需要设置的文本控件实例
     */
    public void statusSetText(int status, TextView upsTextView, TextView textView) {

        //UPS温度状态
        switch (status) {
            case 0:
                upsTextView.setText("正常");
                upsTextView.setTextColor(getColor(R.color.colorPrimary));
                textView.setTextColor(getColor(R.color.colorPrimary));
                break;
            case 1:
                upsTextView.setText("异常");
                upsTextView.setTextColor(Color.RED);
                textView.setTextColor(Color.RED);
                break;
            default:
                upsTextView.setText("未连接");
                upsTextView.setTextColor(Color.GRAY);
                textView.setTextColor(Color.GRAY);
                break;
        }

    }

}
