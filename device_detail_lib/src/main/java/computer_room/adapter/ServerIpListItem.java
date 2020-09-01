package computer_room.adapter;

import android.content.Context;
import android.util.Log;
import android.widget.TextView;

import com.example.device_detail_lib.R;

import java.util.List;

import base.BaseRecyclerViewAdapter;
import base.BaseViewHolder;
import computer_room.bean.ServerIpItemBean;

/**
 * @ClassName: ServerIpListItem
 * @Description: 所有服务器的 IP 列表
 * @Author: YIN LUO FEI
 * @Date: 2020/7/8 20:56
 */
public class ServerIpListItem extends BaseRecyclerViewAdapter<ServerIpItemBean> {


    public ServerIpListItem(Context context, List<ServerIpItemBean> dataList, int layoutId) {
        super(context, dataList, layoutId);

    }

    @Override
    protected void bindData(BaseViewHolder holder, ServerIpItemBean data, int position) {

        TextView serverItem = holder.getView(R.id.server_ip_text);
        serverItem.setText(data.getIp() + " : " + data.getPort());

        serverItem.setOnClickListener((v)->{
            if (itemClickListener != null)
                itemClickListener.onItemClickListener(holder.getRootView(), position);
        });


    }

}
