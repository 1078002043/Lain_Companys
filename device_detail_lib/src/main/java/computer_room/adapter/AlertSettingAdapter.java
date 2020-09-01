package computer_room.adapter;

import android.content.Context;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.device_detail_lib.R;

import java.util.List;

import base.BaseRecyclerViewAdapter;
import base.BaseViewHolder;
import computer_room.bean.AlertSettingBean;
import computer_room.i_interface.I_AlertMethod;
import util.ToastManage;

/**
 * @ClassName: AlertSettingAdapter
 * @Description:
 * @Author: YIN LUO FEI
 * @Date: 2020/7/14 14:42
 */
public class AlertSettingAdapter extends BaseRecyclerViewAdapter<AlertSettingBean> {

    //报警周期的图标
    ImageView alertSettingIcon;
    //报警周期
    TextView alertSettingWeek;
    //报警间隔
    TextView alertSettingInterval;
    //报警编辑按钮
    Button alertSettingEdit;
    //开始时间一
    TextView alertSettingStart1;
    //结束时间一
    TextView alertSettingEnd1;
    //开始时间二
    TextView alertSettingStart2;
    //结束时间二
    TextView alertSettingEnd2;
    //开始时间三
    TextView alertSettingStart3;
    //结束时间三
    TextView alertSettingEnd3;
    //语音报警
    CheckBox alertSettingMode1;
    //短信报警
    CheckBox alertSettingMode2;
    //声光报警
    CheckBox alertSettingMode3;
    //邮箱报警
    CheckBox alertSettingMode4;

    private I_AlertMethod i_alertMethod;

    public AlertSettingAdapter(Context context, List<AlertSettingBean> dataList, int layoutId) {
        super(context, dataList, layoutId);
    }

    public AlertSettingAdapter(Context context, List<AlertSettingBean> dataList, int layoutId, I_AlertMethod i_alertMethod) {
        super(context, dataList, layoutId);
        this.i_alertMethod = i_alertMethod;
    }

    @Override
    protected void bindData(BaseViewHolder holder, AlertSettingBean data, int position) {


        //报警周期的图标
        TextView alertSettingIcon = holder.getView(R.id.alert_setting_icon);
        //报警周期
        TextView alertSettingWeek = holder.getView(R.id.alert_setting_week);
        //报警间隔
        TextView alertSettingInterval = holder.getView(R.id.alert_setting_interval);
        //报警编辑按钮
        Button alertSettingEdit = holder.getView(R.id.alert_setting_edit);
        //开始时间一
        TextView alertSettingStart1 = holder.getView(R.id.alert_setting_start_1);
        //结束时间一
        TextView alertSettingEnd1 = holder.getView(R.id.alert_setting_end_1);
        //开始时间二
        TextView alertSettingStart2 = holder.getView(R.id.alert_setting_start_2);
        //结束时间二
        TextView alertSettingEnd2 = holder.getView(R.id.alert_setting_end_2);
        //开始时间三
        TextView alertSettingStart3 = holder.getView(R.id.alert_setting_start_3);
        //结束时间三
        TextView alertSettingEnd3 = holder.getView(R.id.alert_setting_end_3);

        //语音报警
        CheckBox alertSettingMode1 = holder.getView(R.id.alert_setting_mode1);
        //短信报警
        CheckBox alertSettingMode2 = holder.getView(R.id.alert_setting_mode2);
        //声光报警
        CheckBox alertSettingMode3 = holder.getView(R.id.alert_setting_mode3);
        //邮箱报警
        CheckBox alertSettingMode4 = holder.getView(R.id.alert_setting_mode4);

        //设置周期信息
        alertSettingWeek.setText(data.getWeek());
        //设置报警间隔
        alertSettingInterval.setText("间隔 " + data.getIntervalTime());

        //分隔字符时间段
        String[] time1 = data.getTimeQuantumOne().split("-");
        String[] time2 = data.getTimeQuantumTwo().split("-");
        String[] time3 = data.getTimeQuantumThree().split("-");

        //设置报警ITEM的图标
        setItemIcon(alertSettingIcon, data.getWeek());

        if (time1.length > 1) {
            //设置时间段一
            alertSettingStart1.setText(time1[0]);
            alertSettingEnd1.setText(time1[1]);
            //设置时间段二
            alertSettingStart2.setText(time2[0]);
            alertSettingEnd2.setText(time2[1]);
            //设置时间段三
            alertSettingStart3.setText(time3[0]);
            alertSettingEnd3.setText(time3[1]);
        }

        //报警方式
        alertSettingMode1.setChecked(getAlertStatus(data.getPhoneStatus()));    //语音报警
        alertSettingMode2.setChecked(getAlertStatus(data.getSmsStatus()));  //短信报警
        alertSettingMode3.setChecked(getAlertStatus(data.getSoundLightStatus()));   //声光报警
        alertSettingMode4.setChecked(getAlertStatus(data.getEmailStatus()));     //邮箱报警

        alertSettingEdit.setOnClickListener((v) -> {
            if (itemClickListener != null)
                itemClickListener.onItemClickListener(v, position);
        });


        //设置报警方式的点击监听
        radioChangeListener(alertSettingMode1, position);
        radioChangeListener(alertSettingMode2, position);
        radioChangeListener(alertSettingMode3, position);
        radioChangeListener(alertSettingMode4, position);

    }

    /**
     * 设置 ITEM 的图标
     * @param alertSettingIcon item 图标控件
     * @param week 标识符
     */
    private void setItemIcon(TextView alertSettingIcon, String week) {

        switch (week) {
            case "星期一":
                alertSettingIcon.setText("M");
                break;
            case "星期三":
                alertSettingIcon.setText("W");
                break;
            case "星期二":
            case "星期四":
                alertSettingIcon.setText("T");
                break;
            case "星期五":
                alertSettingIcon.setText("F");
                break;
            case "星期六":
            case "星期日":
                alertSettingIcon.setText("S");
                break;
        }

    }

    //设置报警方式选择按钮的监听
    void radioChangeListener(CheckBox radioButton, int position) {

        radioButton.setOnClickListener(v -> {

            i_alertMethod.updateAlertMethod(radioButton.getText().toString(), radioButton.isChecked(), position);

        });

    }

    boolean getAlertStatus(int num) {

        return num == 1;

    }

}
