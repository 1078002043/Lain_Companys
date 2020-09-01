package computer_room.fragment;

import android.os.Bundle;
import android.widget.TextView;

import com.example.device_detail_lib.R;
import com.example.device_detail_lib.R2;

import base.Lain_Base_Activity;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @ClassName: CenterAirDetail
 * @Description: 中央空调设备的详情页面
 * @Author: YIN LUO FEI
 * @Date: 2020/8/26 2:11
 */
public class CenterAirDetail extends Lain_Base_Activity {

    //出水温度文本
    @BindView(R2.id.out_temp_text)
    TextView outTempText;
    //出水温度
    @BindView(R2.id.out_temp)
    TextView outTemp;
    //回水温度文本
    @BindView(R2.id.return_temp_text)
    TextView returnTempText;
    //回水温度
    @BindView(R2.id.return_temp)
    TextView returnTemp;
    //环境温度文本
    @BindView(R2.id.environment_temp_text)
    TextView environmentTempText;
    //环境温度
    @BindView(R2.id.environment_temp)
    TextView environmentTemp;
    //实际温度文本
    @BindView(R2.id.actual_temp_text)
    TextView actualTempText;
    //实际温度
    @BindView(R2.id.actual_temp)
    TextView actualTemp;
    //温度设定文本
    @BindView(R2.id.temp_setting_text)
    TextView tempSettingText;
    //温度设定
    @BindView(R2.id.temp_setting)
    TextView tempSetting;
    //故障数量文本
    @BindView(R2.id.fault_number_text)
    TextView faultNumberText;
    //故障数量
    @BindView(R2.id.fault_number)
    TextView faultNumber;
    //压缩机数量文本
    @BindView(R2.id.compressor_quantity_text)
    TextView compressorQuantityText;
    //压缩机数量
    @BindView(R2.id.compressor_quantity)
    TextView compressorQuantity;
    //负荷输出文本
    @BindView(R2.id.output_load_text)
    TextView outputLoadText;
    //负荷输出
    @BindView(R2.id.output_load)
    TextView outputLoad;


    //--------------系统状态
    //系统状态文本
    @BindView(R2.id.system_state_text)
    TextView systemStateText;
    //系统状态
    @BindView(R2.id.system_state)
    TextView systemState;
    //工作模式文本
    @BindView(R2.id.work_pattern_text)
    TextView workPatternText;
    //工作模式
    @BindView(R2.id.work_pattern)
    TextView workPattern;
    //通讯故障文本
    @BindView(R2.id.communication_failure_text)
    TextView communicationFailureText;
    //通讯故障
    @BindView(R2.id.communication_failure)
    TextView communicationFailure;
    //外部连锁文本
    @BindView(R2.id.external_chain_text)
    TextView externalChainText;
    //外部连锁
    @BindView(R2.id.external_chain)
    TextView externalChain;
    //空调泵过载文本
    @BindView(R2.id.air_conditioning_text)
    TextView airConditioningText;
    //空调泵过载
    @BindView(R2.id.air_conditioning)
    TextView airConditioning;
    //系统出水温度过低文本
    @BindView(R2.id.temperature_low_text)
    TextView temperatureLowText;
    //系统出水温度过低
    @BindView(R2.id.temperature_low)
    TextView temperatureLow;
    //系统出水温度过高文本
    @BindView(R2.id.temperature_high_text)
    TextView temperatureHighText;
    //系统出水温度过高
    @BindView(R2.id.temperature_high)
    TextView temperatureHigh;
    //机组空调总回水探头文本
    @BindView(R2.id.air_backwater_probe_text)
    TextView airBackwaterProbeText;
    //机组空调总回水探头
    @BindView(R2.id.air_backwater_probe)
    TextView airBackwaterProbe;
    //机组空调总出水探头文本
    @BindView(R2.id.air_outlet_probe_text)
    TextView airOutletProbeText;
    //机组空调总出水探头
    @BindView(R2.id.air_outlet_probe)
    TextView airOutletProbe;
    //环境温度探头文本
    @BindView(R2.id.temperature_probe_text)
    TextView temperatureProbeText;
    //环境温度探头
    @BindView(R2.id.temperature_probe)
    TextView temperatureProbe;

    //--------------压缩机输出
    //0# 压缩机文本
    @BindView(R2.id.compressor_0_text)
    TextView compressor0Text;
    //0# 压缩机
    @BindView(R2.id.compressor_0)
    TextView compressor0;
    //1# 压缩机文本
    @BindView(R2.id.compressor_1_text)
    TextView compressor1Text;
    //1# 压缩机
    @BindView(R2.id.compressor_1)
    TextView compressor1;
    //2# 压缩机文本
    @BindView(R2.id.compressor_2_text)
    TextView compressor2Text;
    //2# 压缩机
    @BindView(R2.id.compressor_2)
    TextView compressor2;
    //3# 压缩机文本
    @BindView(R2.id.compressor_3_text)
    TextView compressor3Text;
    //3# 压缩机
    @BindView(R2.id.compressor_3)
    TextView compressor3;

    //--------------翅片温度
    //#0 单元文本
    @BindView(R2.id.radiator_fan_0_text)
    TextView radiatorFan0Text;
    //#0 单元
    @BindView(R2.id.radiator_fan_0)
    TextView radiatorFan0;
    //#1 单元文本
    @BindView(R2.id.radiator_fan_1_text)
    TextView radiatorFan1Text;
    //#1 单元
    @BindView(R2.id.radiator_fan_1)
    TextView radiatorFan1;
    //#2 单元文本
    @BindView(R2.id.radiator_fan_2_text)
    TextView radiatorFan2Text;
    //#2 单元
    @BindView(R2.id.radiator_fan_2)
    TextView radiatorFan2;
    //#3 单元文本
    @BindView(R2.id.radiator_fan_3_text)
    TextView radiatorFan3Text;
    //#3 单元
    @BindView(R2.id.radiator_fan_3)
    TextView radiatorFan3;


    //--------------单元出水温度
    //#0 出水温度文本
    @BindView(R2.id.uni_out_temp_0_text)
    TextView uniOutTemp0Text;
    //#0 出水温度
    @BindView(R2.id.uni_out_temp_0)
    TextView uniOutTemp0;
    //#1 出水温度文本
    @BindView(R2.id.uni_out_temp_1_text)
    TextView uniOutTemp1Text;
    //#1 出水温度
    @BindView(R2.id.uni_out_temp_1)
    TextView uniOutTemp1;


    //--------------单元板压机状态
    //#0 板压机文本
    @BindView(R2.id.plate_press_0_text)
    TextView platePress0Text;
    //#0 板压机
    @BindView(R2.id.plate_press_0)
    TextView platePress0;
    //#1 板压机文本
    @BindView(R2.id.plate_press_1_text)
    TextView platePress1Text;
    //#1 板压机
    @BindView(R2.id.plate_press_1)
    TextView platePress1;
    //#2 板压机文本
    @BindView(R2.id.plate_press_2_text)
    TextView platePress2Text;
    //#2 板压机
    @BindView(R2.id.plate_press_2)
    TextView platePress2;
    //#3 板压机文本
    @BindView(R2.id.plate_press_3_text)
    TextView platePress3Text;
    //#3 板压机
    @BindView(R2.id.plate_press_3)
    TextView platePress3;


    //--------------压机运行时间
    //#0 单元文本
    @BindView(R2.id.press_0_text)
    TextView press0Text;
    //#0 单元
    @BindView(R2.id.press_0)
    TextView press0;
    //#1 单元文本
    @BindView(R2.id.press_1_text)
    TextView press1Text;
    //#1 单元
    @BindView(R2.id.press_1)
    TextView press1;
    //#2 单元文本
    @BindView(R2.id.press_2_text)
    TextView press2Text;
    //#2 单元
    @BindView(R2.id.press_2)
    TextView press2;
    //#3 单元文本
    @BindView(R2.id.press_3_text)
    TextView press3Text;
    //#3 单元
    @BindView(R2.id.press_3)
    TextView press3;

    //--------------冷凝风机
    //#0 冷凝风机输出文本
    @BindView(R2.id.condensate_fan_out1_text)
    TextView condensateFanOut1Text;
    //#0 冷凝风机输出
    @BindView(R2.id.condensate_fan_out1)
    TextView condensateFanOut1;
    //#1 冷凝风机输出文本
    @BindView(R2.id.condensate_fan_out2_text)
    TextView condensateFanOut2Text;
    //#1 冷凝风机输出
    @BindView(R2.id.condensate_fan_out2)
    TextView condensateFanOut2;

    @Override
    protected String getToolbarTitle() {
        return getIntent().getStringExtra("title");
    }

    @Override
    public int setLayoutView() {
        return R.layout.center_air_detail;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);

        initUiData();

    }

    /**
     * 初始化中央空调的数据
     */
    public void initUiData() {

        //出水温度`
        outTemp.setText("12℃");

        //回水温度
        returnTemp.setText("10℃");

        //环境温度
        environmentTemp.setText("15℃");

        //实际温度
        actualTemp.setText("30℃");

        //温度设定
        tempSetting.setText("40℃");

        //故障数量
        faultNumber.setText("8");

        //压缩机数量
        compressorQuantity.setText("4");

        //负荷输出
        outputLoad.setText("5");


        //--------------系统状态

        //系统状态
        systemState.setText("停机");

        //工作模式
        workPattern.setText("制冷");

        //通讯故障
        communicationFailure.setText("正常");

        //外部连锁
        externalChain.setText("正常");

        //空调泵过载
        airConditioning.setText("正常");

        //系统出水温度过低
        temperatureLow.setText("正常");

        //系统出水温度过高
        temperatureHigh.setText("正常");

        //机组空调总回水探头
        airBackwaterProbe.setText("异常");

        //机组空调总出水探头
        airOutletProbe.setText("异常");

        //环境温度探头
        temperatureProbe.setText("正常");

        //--------------压缩机输出

        //0# 压缩机
        compressor0.setText("无输出");

        //1# 压缩机
        compressor1.setText("输出");

        //2# 压缩机
        compressor2.setText("无输出");

        //3# 压缩机
        compressor3.setText("输出");

        //--------------翅片温度

        //#0 单元
        radiatorFan0.setText("0°");

        //#1 单元
        radiatorFan1.setText("50°");

        //#2 单元
        radiatorFan2.setText("5°");
        //#3 单元
        radiatorFan3.setText("3°");


        //--------------单元出水温度

        //#0 出水温度
        uniOutTemp0.setText("0°");

        //#1 出水温度
        uniOutTemp1.setText("0°");


        //--------------单元板压机状态

        //#0 板压机
        platePress0.setText("停机");

        //#1 板压机
        platePress1.setText("停机");

        //#2 板压机
        platePress2.setText("停机");

        //#3 板压机
        platePress3.setText("开机");


        //--------------压机运行时间

        //#0 单元
        press0.setText("0h");

        //#1 单元
        press1.setText("0h");

        //#2 单元
        press2.setText("0h");

        //#3 单元
        press3.setText("0h");

        //--------------冷凝风机

        //#0 冷凝风机输出
        condensateFanOut1.setText("输出");

        //#1 冷凝风机输出
        condensateFanOut2.setText("无输出");

    }

}
