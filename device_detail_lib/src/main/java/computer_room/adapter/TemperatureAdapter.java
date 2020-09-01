package computer_room.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;


import androidx.annotation.NonNull;

import com.example.device_detail_lib.R;

import java.util.List;

import base.BaseRecyclerViewAdapter;
import base.BaseViewHolder;
import bean.TemperatureBean;

/**
 * 温湿度设备详情中的 实时数据 曲线图 列表
 * 一定不要动里面的改变颜色背景的代码位置，一调整可能就调不回来了
 * @author Create by Yin Luo Fei
 * @version 0.1
 * @Time 2019/8/8 14 : 30
 */
public class TemperatureAdapter extends BaseRecyclerViewAdapter<TemperatureBean> {
    public static float value = 0;
    private LinearLayout temperature_real = null;
    private Context context;
    private TextView temp_name;
    private String clickName = "";
    private int btnPost = 0;

    //保存当前的点击位置
    private int pos = -1;

    List<TemperatureBean> dataList;

    /**
     * 初始化添加设备列表
     *
     * @param context  Context
     * @param dataList 数据列表
     * @param layoutId 列表 ID
     */
    public TemperatureAdapter(Context context, List<TemperatureBean> dataList, int layoutId) {
        super(context, dataList, layoutId);
        this.context = context;
        this.dataList = dataList;
    }

    @Override
    protected void bindData(BaseViewHolder holder, TemperatureBean data, int position) {

//        //设备名称
//        TextView temperatureName = holder.getView(R.id.temperature_name);
//        //设置设备名称
//        temperatureName.setText(data.getEhmDeviceName());
//
//        //默认选择 第一个，如果已经选中其它的按钮，则不进行设置
//        selectedItem(position, data, temperatureName);
//
//
////        if (position == 0) {
////            //保存选择按钮实例
////            this.temperature_real = temperature_real;
////            temp_name = temperatureName;
////            clickName = temperatureName.getText().toString();
////            //设置背景
////            setBtnBackground(temperature_real);
////            temperatureName.setTextColor(Color.WHITE);
////        }
//
//        //点击切换设备数据
//        temperatureName.setOnClickListener((v)->{
//
////            //将当前点击的按钮的文字与位置，与上次的按钮文件与位置比较，如果点击的是同一个，则不进行任何处理
////            if (clickName.equals(temperatureName.getText().toString()) && btnPost == position)
////                return;
////
////            //设置当前选中背景
////            setBtnBackground(temperature_real);
////            temperatureName.setTextColor(Color.parseColor("#ffffff"));
////
////            //恢复上一次的按钮背景
////            if (this.temperature_real != null)
////                revertBtnBackground(this.temperature_real);
////
////            //保存按钮的文字
////            clickName = temperatureName.getText().toString();
////            //保存文字控件
////            temp_name = temperatureName;
////            //保存按钮的位置
////            btnPost = position;
////
////            //将本次点击的按钮保存下来，用户下次恢复颜色
////            this.temperature_real = temperature_real;
//
//            //如果 != -1 代表点击过，通过记录到的位置，将数据源中的元素设为未选中
//            if (pos != -1)
//                dataList.get(pos).setCheck(false);
//            //设置当前的点击位置
//            pos = position;
//            //更新当前选中元素的选中状态
//            data.setCheck(true);
//
//            //更新列表，解决视图不更新的问题
//            notifyDataSetChanged();
//
//            if (itemClickListener != null)
//                itemClickListener.onItemClickListener(holder.getRootView(), position);
//
//
//
//        });

    }

    private void selectedItem(int position, TemperatureBean data, TextView temperatureName) {
        //从数据源中获取该元素是否选中
        if (data.isCheck()) {
            temperatureName.setBackground(context.getDrawable(R.drawable.real_sel_item));
            temperatureName.setTextColor(Color.parseColor("#FFFFFF"));
        } else {
            //不选中时，设置相关属性
            temperatureName.setBackground(context.getDrawable(R.drawable.real_no_sel_item));
            temperatureName.setTextColor(Color.parseColor("#1EABDD"));
        }
    }

    /**
     * 设置按钮背景
     *
     * @param btn 按钮实例
     */
    private void setBtnBackground(LinearLayout btn) {
        //设置字体颜色与背景
        btn.setBackground(context.getDrawable(R.drawable.temp_sel));

    }

    /**
     * 恢复上次点击的按钮背景色与文字颜色
     *
     * @param btn 需要恢复颜色的按钮
     */
    private void revertBtnBackground(LinearLayout btn) {
        //设置字体颜色与背景
        btn.setBackground(context.getDrawable(R.drawable.temp_no_sel));
        temp_name.setTextColor(Color.parseColor("#75AFFF"));
    }

}
