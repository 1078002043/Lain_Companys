package util;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;

import com.ethanco.circleprogresslibrary.TextCircleProgress;
import com.example.base.R;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;

import java.net.PortUnreachableException;
import java.util.List;

import adapter.I_RealData;
import adapter.RealDataAdapter;
import base.BaseBean;
import base.BaseRecyclerViewAdapter;
import base.BaseViewHolder;
import bean.TemperatureBean;
import view_interface.I_UpdateRealView;

/**
 * @ClassName: RealDataUtil
 * @Description: 实时数据的工具类
 * @Author: YIN LUO FEI
 * @Date: 2020/7/31 8:44
 */
public class RealDataUtil<T extends BaseBean> implements I_RealData<T> {

    //实时数据显示
    private TextCircleProgress deviceRealData;
    //适配器
    RealDataAdapter<T> realDataAdapter;

    private TextCircleProgress baseDeviceRealData;
    private UltimateRecyclerView baseRealList;

    //上下文引用
    private Context context;

    //记录当前的点击位置
    private int itemClickPosition = -1;
    //设置实时数据的回调接口
    private I_UpdateRealView<T> i_updateRealView;

    /**
     * 判定控件
     *
     * @param view 绑定时需要用到的View
     */
    public void initView(View view, Context context, List<T> dataList, BaseRecyclerViewAdapter.OnItemClickListener onItemClickListener, I_UpdateRealView<T> i_updateRealView) {

        if (view == null)
            throw new NullPointerException("绑定实时数据的视图为 NULL");


        //保存上下文
        this.context = view.getContext();
        //初始化实时数据展示控件
        baseDeviceRealData = view.findViewById(R.id.base_device_real_data);
        //初始化实时数据列表控件
        baseRealList = view.findViewById(R.id.base_real_list);
        //初始化适配器
        realDataAdapter = new RealDataAdapter<T>(view.getContext(), dataList, R.layout.base_real_data_temp, this);
        //设置适配器的点击回调，由调用者处理
        realDataAdapter.setItemClickListener(onItemClickListener);
        baseRealList.setLayoutManager(new GridLayoutManager(view.getContext(), 3));
        baseRealList.setAdapter(realDataAdapter);
        //保存视图刷新回调的接口
        this.i_updateRealView = i_updateRealView;

    }

    /**
     * 设置内圆的数据
     *
     * @param innerData 内圆的主标题，显示设备的数据
     */
    public void setInnerData(String innerData) {

        //设置当前的数据
        baseDeviceRealData.setInner(innerData);
        baseDeviceRealData.setInnerProgress(Float.parseFloat(innerData));

    }

    /**
     * 设置内圆的数据，设置数值的符号
     *
     * @param innerData 内圆的主标题，显示设备的数据
     */
    public void setInnerData(String innerData, String tag) {

        //设置当前的数据
        Log.d("jlkjoer", "setInnerData: " + tag + innerData);
        baseDeviceRealData.setInner(tag + innerData);
        baseDeviceRealData.setInnerProgress(Float.parseFloat(innerData));

    }

    /**
     * @param innerTitle 内圆的标题，显示设备的名称
     */
    public void setInnerTitle(String innerTitle) {

        baseDeviceRealData.setInnerHead(innerTitle);

    }

    /**
     * 设置内圆的最大值
     *
     * @param innerMax 内圆最大值
     */
    public void setInnerMax(String innerMax) {
        baseDeviceRealData.setInnerMaxValue(Float.parseFloat(innerMax));
    }

    /**
     * 设置外圆的数据
     *
     * @param outerData 外圆的主标题，显示设备的数据
     */
    public void setOuterData(String outerData) {

        //设置当前的数据
        baseDeviceRealData.setOuter(outerData);
        baseDeviceRealData.setOuterProgress(Float.parseFloat(outerData));

    }

    /**
     * 设置外圆的数据
     *
     * @param outerTitle 外圆的标题，显示设备的名称
     */
    public void setOuterTitle(String outerTitle) {

        baseDeviceRealData.setOuterHead(outerTitle);

    }

    /**
     * 设置外圆的最大值
     *
     * @param outerMax 外圆最大值
     */
    public void setOuterMax(String outerMax) {
        baseDeviceRealData.setOuterMaxValue(Float.parseFloat(outerMax));
    }

    /**
     * 显示圆形数据盘的哪些View
     *
     * @param outerShow 是否显示外圈
     * @param innerShow 是否显示内圈
     */
    public void setCircleShow(boolean outerShow, boolean innerShow) {

        baseDeviceRealData.isShowOuterCircle(outerShow);
        baseDeviceRealData.isShowInnerCircle(innerShow);

    }

    /**
     * 获取适配器
     *
     * @return 实时数据适配器
     */
    public RealDataAdapter<T> getAdapter() {
        return realDataAdapter;
    }

    private void selectedItem(int position, T data, TextView temperatureName) {

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

    @Override
    public void bindData(BaseViewHolder holder, T data, int position) {

        TextView realDeviceItem = holder.getView(R.id.base_real_item_name);
        //默认选择 第一个，如果已经选中其它的按钮，则不进行设置
        selectedItem(position, data, realDeviceItem);

        //按钮的点击事件
        realDeviceItem.setOnClickListener(v -> {

            //如果 != -1 代表点击过，通过记录到的位置，将数据源中的元素设为未选中
            if (itemClickPosition != -1) {
                realDataAdapter.getDataList().get(itemClickPosition).setCheck(false);
            }

            //设置当前的点击位置
            itemClickPosition = position;
            //更新当前选中元素的选中状态
            data.setCheck(true);

            //更新列表，解决视图不更新的问题
            realDataAdapter.notifyDataSetChanged();

            //处理基本操作后，再回调给调用者进行后续操作
            if (realDataAdapter.getItemClickListener() != null)
                realDataAdapter.getItemClickListener().onItemClickListener(v, position);

        });

        i_updateRealView.updateRealView(realDeviceItem, data, position);

    }

    /**
     * 刷新实时数据时，如果未选中Item，则默认选择第一个，否则选择上次选中的Item
     */
    public void notifyRealDataSetChanged() {

        //如果不为空，刷新时，默认选中第一个，再进行刷新列表
        if (itemClickPosition == -1 && !realDataAdapter.dataList.isEmpty()) {

            //记录选中的位置
            itemClickPosition = 0;
            //更新列表
            realDataAdapter.dataList.get(0).setCheck(true);
            realDataAdapter.notifyDataSetChanged();

        } else if (!realDataAdapter.dataList.isEmpty()) {

            //如果刷新时，已经选择过其它的ITEM，则刷新时，也选中所点击的Item
            realDataAdapter.dataList.get(itemClickPosition).setCheck(true);
            realDataAdapter.notifyDataSetChanged();

        }

    }

}
