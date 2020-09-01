package computer_room.adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;

import com.example.device_detail_lib.R;

import org.angmarch.views.NiceSpinner;
import org.angmarch.views.OnSpinnerItemSelectedListener;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import base.BaseRecyclerViewAdapter;
import base.BaseViewHolder;
import computer_room.bean.AssetRealBean;
import http.OkHttpCallBack;
import http.OkHttpUtil;
import util.LainNewApi;
import util.ToastManage;

/**
 * Author：YIN_LUO_FEI
 * Date：2019/11/4 14:57
 * Description：资产管理-实时数据
 **/
public class AssetRealAdapter extends BaseRecyclerViewAdapter<AssetRealBean> implements OnSpinnerItemSelectedListener {
    List<String> spinnerData = new LinkedList<>(Arrays.asList("颜色默认","熄灭", "红", "紫", "黄",
            "绿", "青", "蓝", "白", "红闪烁", "紫闪烁", "黄闪烁", "绿闪烁", "青闪烁", "蓝闪烁","白闪烁"));

    List<AssetRealBean> assetRealBeans;

    static Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {

            ToastManage.getInstance().toastLongShow(msg.obj+"");

        }
    };
    //记录当前点击的ITEM
    private int clickItem = 0;

    /**
     * 初始化添加设备列表
     *
     * @param context  Context
     * @param dataList 数据列表
     * @param layoutId 列表 ID
     */
    public AssetRealAdapter(Context context, List<AssetRealBean> dataList, int layoutId) {
        super(context, dataList, layoutId);
        this.assetRealBeans = dataList;
    }

    @Override
    protected void bindData(BaseViewHolder holder, AssetRealBean data, int position) {

        //绑定
        //名称
        TextView locatingName = holder.getView(R.id.locating_name);
        //地址
        TextView locatingAddress = holder.getView(R.id.locating_address);
        //状态
        TextView locatingStatus = holder.getView(R.id.locating_status);
        //图标
        ImageView imageView = holder.getView(R.id.locating_img);

        //颜色选择
        NiceSpinner niceSpinner = holder.getView(R.id.asset_spinner);
        niceSpinner.attachDataSource(spinnerData);
        niceSpinner.setTextSize(13);
        niceSpinner.setSelected(true);
        niceSpinner.setSelectedIndex(0);
        niceSpinner.setOnSpinnerItemSelectedListener(this);
        //设置点击监控是为了取到当前的点击位置
        niceSpinner.setOnClickListener((v)->{
            this.clickItem = position;
        });

        //设置相关参数
        locatingName.setText(data.getName());
        String address = "通道：" + data.getGallery();
        locatingAddress.setText(address);
        //图标状态
        int[] imageID = new int[]{R.drawable.icon_asset, R.drawable.icon_asset, R.drawable.icon_asset};
        deviceStatusImg(data.getStatus(), imageView, imageID);

    }

    @Override
    public void onItemSelected(NiceSpinner parent, View view, int position, long id) {

        if (position == 0)
            return;

        String url = LainNewApi.getInstance().getRootPath() + LainNewApi.assetChangeColor + assetRealBeans.get(clickItem).getMId() + "/" + (position - 1);
        OkHttpUtil.getInstance().sendGetOkHttp("asset_change_color", url, new OkHttpCallBack() {
            @Override
            public void httpRequestSuccess(String requestTag, String requestUrl, String responseStr) {
                Message message = new Message();

                if (responseStr.equals("false"))
                    message.obj = "操作失败，请检测设备是否连接成功";
                else
                    message.obj = "更新成功，颜色：" + spinnerData.get(position);

                handler.sendMessage(message);
            }

            @Override
            public void httpRequestFailure(String requestTag, String requestUrl, String errorMsg) {

            }

        });

    }
}
