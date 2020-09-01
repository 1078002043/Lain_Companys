package computer_room.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.SwitchCompat;

import com.example.device_detail_lib.R;
import com.example.device_detail_lib.R2;
import com.king.view.circleprogressview.CircleProgressView;

import base.Lain_Base_Activity;
import butterknife.BindView;
import computer_room.bean.Precision_Air_ConditionerBean;

/**
 * 精密空调 详情
 */
public class PrecisionDetail extends Lain_Base_Activity {
    //温度进度条
    @BindView(R2.id.cpv)
    CircleProgressView mCpv;
    //湿度进度条
    @BindView(R2.id.cpv2)
    CircleProgressView mCpv2;
    //温度适宜 文本
    @BindView(R2.id.temper_text)
    TextView mTemperText;

    //制冷状态
    @BindView(R2.id.Refrigeration_state)
    SwitchCompat refrigerationState;
    //除湿状态
    @BindView(R2.id.dehumidification_state)
    SwitchCompat dehumidificationState;
    //风机状态
    @BindView(R2.id.fan_status)
    SwitchCompat fanStatus;
    //加湿状态
    @BindView(R2.id.humidification_state)
    SwitchCompat humidifiState;
    //加热状态
    @BindView(R2.id.heating_state)
    SwitchCompat headtingState;
    //开关机状态
    @BindView(R2.id.on_off_state)
    SwitchCompat onOffState;
    //高压报警
    @BindView(R2.id.high_voltage_alarm)
    TextView highVoltageAlarm;
    //低压报警
    @BindView(R2.id.low_voltage_alarm)
    TextView lowVoltageAlarm;
    //低温报警
    @BindView(R2.id.low_temperature_alarm)
    TextView lowTemperatureAlarm;
    //高湿报警
    @BindView(R2.id.high_hum_alarm)
    TextView highHumAlarm;
    //低湿报警
    @BindView(R2.id.low_humidity_alarm)
    TextView lowHumidityAlarm;
    //电源故障报警
    @BindView(R2.id.power_failure_alarm)
    TextView powerFailureAlarm;
    @BindView(R2.id.high_voltage_text)
    TextView highVoltageText;
    @BindView(R2.id.low_voltage_text)
    TextView lowVoltageText;
    @BindView(R2.id.low_temperature_text)
    TextView lowTemperatureText;
    @BindView(R2.id.high_hum_text)
    TextView highHumText;
    @BindView(R2.id.low_humidity_text)
    TextView lowHumidityText;
    @BindView(R2.id.power_failure_text)
    TextView powerFailureText;
    @BindView(R2.id.refrigeration_text)
    TextView refrigerationText;
    @BindView(R2.id.dehumidification_text)
    TextView dehumidificationText;
    @BindView(R2.id.fan_text)
    TextView fanText;
    @BindView(R2.id.humidification_text)
    TextView humidificationText;
    @BindView(R2.id.heating_text)
    TextView heatingText;
    @BindView(R2.id.compressor_text)
    TextView compressorText;
    @BindView(R2.id.on_off_text)
    TextView onOffText;

    @BindView(R2.id.running_text)
    TextView runningText;
    @BindView(R2.id.running_status)
    TextView runningStatus;
    @BindView(R2.id.hot_gas_text)
    TextView hotGasText;
    @BindView(R2.id.hot_gas_alarm)
    TextView hotGasAlarm;
    @BindView(R2.id.high_temperature_text)
    TextView highTemperatureText;
    @BindView(R2.id.high_temperature_alarm)
    TextView highTemperatureAlarm;
    //精密空调的单个设备数据
    private Precision_Air_ConditionerBean precisionBeans;

    @Override
    protected String getToolbarTitle() {
        precisionBeans = (Precision_Air_ConditionerBean) getIntent().getSerializableExtra("precision");
        return precisionBeans.getEcmDeviceName();
    }

    @Override
    public int setLayoutView() {
        return R.layout.precision_detail;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mCpv.setLabelText(precisionBeans.getEcmTemp() + " ℃");
        mCpv.setProgress(precisionBeans.getEcmTemp());

        mCpv2.setLabelText(precisionBeans.getEcmHum() + " %RH");
        mCpv2.setProgress((int) precisionBeans.getEcmHum());

        initView();

    }

    //初始化View，根据数据来决定开还是关
    private void initView() {

        //制冷状态
        setAirStatus(precisionBeans.getEcmRefrigerationStatus(), refrigerationText, refrigerationState);
//        if (precisionBeans.getEcmRefrigerationStatus() == 0)
//            refrigerationState.setChecked(true);
//        else
//            refrigerationState.setChecked(false);
        //除湿状态
        setAirStatus(precisionBeans.getEcmDehumidifyStatus(), dehumidificationText, dehumidificationState);
//        if (precisionBeans.getEcmDehumidifyStatus() == 0)
//            dehumidificationState.setChecked(true);
//        else
//            dehumidificationState.setChecked(false);
        //风机状态
        setAirStatus(precisionBeans.getEcmFanStatus(), fanText, fanStatus);
//        if (precisionBeans.getEcmFanStatus() == 0)
//            fanStatus.setChecked(true);
//        else
//            fanStatus.setChecked(false);
        //加湿状态
        setAirStatus(precisionBeans.getEcmHumidificationStatus(), humidificationText, humidifiState);
//        if (precisionBeans.getEcmHumidificationStatus() == 0)
//            humidifiState.setChecked(true);
//        else
//            humidifiState.setChecked(false);
        //加热状态
        setAirStatus(precisionBeans.getEcmHeatingStatus(), heatingText, headtingState);
//        if (precisionBeans.getEcmHeatingStatus() == 0)
//            headtingState.setChecked(true);
//        else
//            headtingState.setChecked(false);
        //开关机状态
        setAirStatus(precisionBeans.getEcmOnoroffStatus(), onOffText, onOffState);
//        if (precisionBeans.getEcmOnoroffStatus() == 0)
//            onOffState.setChecked(true);
//        else
//            onOffState.setChecked(false);


        //高压报警
        setAirAlarm(precisionBeans.getEcmHighPressureAlarm(), highVoltageAlarm, highVoltageText);
//        if (precisionBeans.getEcmHighPressureAlarm() == 0)
//            highVoltageAlarm.setText("正常");
//        else if (precisionBeans.getEcmHighPressureAlarm() == 1) {
//            highVoltageAlarm.setText("异常");
//            highVoltageAlarm.setTextColor(Color.RED);
//        }
//
//        else{}
//            highVoltageAlarm.setText("未连接");


        //低压报警
        setAirAlarm(precisionBeans.getEcmLowPressureAlarm(), lowVoltageAlarm, lowVoltageText);
//        if (precisionBeans.getEcmLowPressureAlarm() == 0)
//            lowVoltageAlarm.setText("正常");
//        else if (precisionBeans.getEcmLowPressureAlarm() == 1) {
//            lowVoltageAlarm.setText("异常");
//            lowVoltageAlarm.setTextColor(Color.RED);
//
//        }
//        else{}
//            lowVoltageAlarm.setText("未连接");

        //运行状态
        setAirAlarm(precisionBeans.getEcmRunningStatus(), runningStatus, runningText);

        //低温报警
        setAirAlarm(precisionBeans.getEcmLowTempAlarm(), lowTemperatureAlarm, lowTemperatureText);
        //高温报警
        setAirAlarm(precisionBeans.getEcmHighTempAlarm(), highTemperatureAlarm, highTemperatureText);

        //热气通阀
        setAirAlarm(precisionBeans.getEcmHotGasByPassValveAlarm(), hotGasAlarm, hotGasText);

//        if (precisionBeans.getEcmLowTempAlarm() == 0)
//            lowTemperatureAlarm.setText("正常");
//        else if (precisionBeans.getEcmLowTempAlarm() == 1)
//            lowTemperatureAlarm.setText("异常");
//        else
//            lowTemperatureAlarm.setText("未连接");


        //高湿报警
        setAirAlarm(precisionBeans.getEcmHighHumAlarm(), highHumAlarm, highHumText);
//        if (precisionBeans.getEcmHighHumAlarm() == 0)
//            highTemperatureAlarm.setText("正常");
//        else if (precisionBeans.getEcmHighHumAlarm() == 1) {
//            highTemperatureAlarm.setText("异常");
//            highTemperatureAlarm.setTextColor(Color.RED);
//
//        }
//        else{}
//            highTemperatureAlarm.setText("未连接");


        //低湿报警
        setAirAlarm(precisionBeans.getEcmLowHumAlarm(), lowHumidityAlarm, lowHumidityText);
//        if (precisionBeans.getEcmLowHumAlarm() == 0)
//            lowHumidityAlarm.setText("正常");
//        else if (precisionBeans.getEcmLowHumAlarm() == 1) {
//            lowHumidityAlarm.setText("异常");
//            lowHumidityAlarm.setTextColor(Color.RED);
//
//        }
//        else{}
//            lowHumidityAlarm.setText("未连接");

        //电源故障报警
        setAirAlarm(precisionBeans.getEcmPowerFailureAlarm(), powerFailureAlarm, powerFailureText);
//        if (precisionBeans.getEcmPowerFailureAlarm() == 0)
//            powerFailureAlarm.setText("正常");
//        else if (precisionBeans.getEcmPowerFailureAlarm() == 1) {
//            powerFailureAlarm.setText("异常");
//            powerFailureAlarm.setTextColor(Color.RED);

//        }
//        else{}
//            powerFailureAlarm.setText("未连接");

    }

    /**
     * 设置精密空调的设备模块状态
     *
     * @param status   设备模块状态
     * @param textView 显示状态的文本
     * @param compat   是否开启的控件
     */
    public void setAirStatus(int status, TextView textView, SwitchCompat compat) {

        switch (status) {

            case 0:
                //开启
                textView.setTextColor(getColor(R.color.colorPrimary));
                compat.setChecked(true);
                break;
            default:
                //关闭
                textView.setTextColor(Color.GRAY);
                compat.setChecked(false);
                break;

        }

    }

    public void setAirAlarm(int status, TextView airText, TextView textView) {

        switch (status) {
            case 0:
                airText.setText("正常");
                airText.setTextColor(getColor(R.color.colorPrimary));
                textView.setTextColor(getColor(R.color.colorPrimary));
                break;
            case 1:
                airText.setText("报警");
                airText.setTextColor(Color.RED);
                textView.setTextColor(Color.RED);
                break;
            default:
                airText.setText("关闭");
                airText.setTextColor(Color.GRAY);
                textView.setTextColor(Color.GRAY);
                break;
        }

    }

}
