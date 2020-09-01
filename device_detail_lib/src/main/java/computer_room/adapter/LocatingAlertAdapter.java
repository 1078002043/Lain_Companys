package computer_room.adapter;

import android.content.Context;
import android.widget.TextView;

import java.util.List;

import base.BaseRecyclerViewAdapter;
import base.BaseViewHolder;
import computer_room.bean.LocatingDetailAlertBean;

public class LocatingAlertAdapter extends BaseRecyclerViewAdapter<LocatingDetailAlertBean> {
    public LocatingAlertAdapter(Context context, List<LocatingDetailAlertBean> dataList, int layoutId) {
        super(context, dataList, layoutId);
    }

    @Override
    protected void bindData(BaseViewHolder holder, LocatingDetailAlertBean data, int position) {
        /**
         * 定位漏水
         */
        TextView mAlertName = holder.getView(com.example.base.R.id.alert_8052_name);
        /**
         * 2019-10-15  14:24:00
         */
        TextView mAlertTime = holder.getView(com.example.base.R.id.alert_8052_time);

        TextView mAlertGallery = holder.getView(com.example.base.R.id.alert_8052_gallery);

        mAlertGallery.setText("漏水："+data.getElmLength());
        mAlertName.setText(data.getElmName());
        mAlertTime.setText(data.getTime());

    }
}
