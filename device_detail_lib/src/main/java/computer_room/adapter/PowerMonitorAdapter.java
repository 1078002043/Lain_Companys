package computer_room.adapter;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.device_detail_lib.R;

import java.util.List;

import base.BaseRecyclerViewAdapter;
import base.BaseViewHolder;
import bean.ConfigBean;

/**
 * 动力监控
 *
 * @author Create by Yin Luo Fei
 * @version 0.1
 * @Time 2019/8/7 01 : 55
 */
public class PowerMonitorAdapter extends BaseRecyclerViewAdapter<ConfigBean> {

    private Context context;

    /**
     * 初始化添加设备列表
     *
     * @param context  Context
     * @param dataList 数据列表
     * @param layoutId 列表 ID
     */
    public PowerMonitorAdapter(Context context, List<ConfigBean> dataList, int layoutId) {
        super(context, dataList, layoutId);
        this.context = context;
    }

    @Override
    protected void bindData(BaseViewHolder holder, ConfigBean data, int position) {

//        List<DeviceListMap> deviceListMaps = SaveDeviceList.getInstance().findFromTitle(data);
//
//        if (!deviceListMaps.isEmpty() && !deviceListMaps.get(0).isShow())
//            return;
        Uri uri = null;
        //卡片
        LinearLayout cardView = holder.getView(R.id.environmental_card2);
        //设备图标
        ImageView miniIcon = holder.getView(R.id.monitor_img);
        Glide.with(context).load(data.getImage()).into(miniIcon);
//        switch (data) {
//            case "电量仪":
//                uri = Uri.parse("android.resource://" + context.getPackageName() + "/" + R.mipmap.powermonitor_icon1);
//                Log.d("klsdkljfl33-----"+data, "onCreate: "+uri.toString());
////                Glide.with(context).load("android.resource://cn.com.lain/2131689514").into(miniIcon);
//                miniIcon.setImageResource(R.mipmap.powermonitor_icon1);
//                break;
//            case "配电监控":
//                uri = Uri.parse("android.resource://" + context.getPackageName() + "/" + R.mipmap.powermonitor_icon2);
//                Log.d("klsdkljfl33-----"+data, "onCreate: "+uri.toString());
////                Glide.with(context).load("android.resource://cn.com.lain/2131689514").into(miniIcon);
//                miniIcon.setImageResource(R.mipmap.powermonitor_icon2);
//                break;
//            case "市电监控":
//                uri = Uri.parse("android.resource://" + context.getPackageName() + "/" + R.mipmap.powermonitor_icon3);
//                Log.d("klsdkljfl33-----"+data, "onCreate: "+uri.toString());
////                Glide.with(context).load("android.resource://cn.com.lain/2131689514").into(miniIcon);
//                miniIcon.setImageResource(R.mipmap.powermonitor_icon3);
//                break;
//            case "电池监控":
//                uri = Uri.parse("android.resource://" + context.getPackageName() + "/" + R.mipmap.powermonitor_icon4);
//                Log.d("klsdkljfl33-----"+data, "onCreate: "+uri.toString());
////                Glide.with(context).load("android.resource://cn.com.lain/").into(miniIcon);
//                miniIcon.setImageResource(R.mipmap.powermonitor_icon4);
//                break;
//            case "UPS":
//                uri = Uri.parse("android.resource://" + context.getPackageName() + "/" + R.mipmap.powermonitor_icon5);
//                Log.d("klsdkljfl33-----"+data, "onCreate: "+uri.toString());
////                Glide.with(context).load("android.resource://cn.com.lain/").into(miniIcon);
//                miniIcon.setImageResource(R.mipmap.powermonitor_icon5);
//                break;
//        }
        //设备名称
        TextView monitorName = holder.getView(R.id.monitor_name);
        //设置设备名称
        monitorName.setText(data.getTitle());

        //设置Item点击事件
        cardView.setOnClickListener((v) -> {
            //设置监听
            if (itemClickListener != null)
                itemClickListener.onItemClickListener(holder.getRootView(), position);
        });
    }
}
