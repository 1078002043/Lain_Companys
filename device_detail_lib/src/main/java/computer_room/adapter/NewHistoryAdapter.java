package computer_room.adapter;

import android.content.Context;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;


import com.example.device_detail_lib.R;

import java.util.List;

import base.BaseRecyclerViewAdapter;
import base.BaseViewHolder;
import computer_room.bean.TemperatureLineBean;
import computer_room.i_interface.I_HistoryAdapter;

/**
 * 历史数据 报警
 */
public class NewHistoryAdapter<T> extends BaseRecyclerViewAdapter<T> {

    //数据的回调接口
    private I_HistoryAdapter<T> i_historyAdapter;

    public NewHistoryAdapter(Context context, List<T> dataList, int layoutId) {
        super(context, dataList, layoutId);
    }

    /**
     *
     * @param context
     * @param dataList
     * @param layoutId
     * @param i_historyAdapter 接收调用者的回调
     */
    public NewHistoryAdapter(Context context, List<T> dataList, int layoutId, I_HistoryAdapter<T> i_historyAdapter) {
        super(context, dataList, layoutId);
        this.i_historyAdapter = i_historyAdapter;

    }

    @Override
    protected void bindData(BaseViewHolder holder, T data, int position) {
        Log.d("oijower", "bindData: t run");
        i_historyAdapter.bindHistoryData(holder, data, position);

//        TextView historyName = holder.getView(R.id.new_history_name);
//        TextView historyTemp = holder.getView(R.id.new_history_temp);
//        TextView historyHum = holder.getView(R.id.new_history_hum);
//        TextView historyTime = holder.getView(R.id.new_history_time);
//        Button loogQx = holder.getView(R.id.look_qx);
//
//        historyTemp.setText("温度" + data.getEhhTem());
//        historyTemp.setText("温度" + data.getEhhHum());
//        historyTime.setText("时间" + data.getEhhTime());
//
//        loogQx.setOnClickListener((v)->{
//            if (itemClickListener != null)
//                itemClickListener.onItemClickListener(holder.getRootView(), position);
//        });

    }

}
