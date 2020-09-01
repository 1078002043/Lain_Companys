package computer_room.adapter;

import android.content.Context;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.example.device_detail_lib.R;

import java.util.List;

import base.BaseRecyclerViewAdapter;
import base.BaseViewHolder;
import computer_room.bean.RoleManageBean;

/**
 * @ClassName: RoleManageAdapter
 * @Description:
 * @Author: YIN LUO FEI
 * @Date: 2020/7/15 16:08
 */
public class RoleManageAdapter extends BaseRecyclerViewAdapter<RoleManageBean> {

    private TextView allRoleName;
    private TextView allRoleUrl;
    private TextView allRoleDesc;
    private Button allRoleDelete;
    private Button allRoleChange;

    public RoleManageAdapter(Context context, List<RoleManageBean> dataList, int layoutId) {
        super(context, dataList, layoutId);
    }

    @Override
    protected void bindData(BaseViewHolder holder, RoleManageBean data, int position) {
        
        allRoleName = holder.getView(R.id.all_role_name);
        allRoleUrl = holder.getView(R.id.all_role_url);
        allRoleDesc = holder.getView(R.id.all_role_desc);
        allRoleDelete = holder.getView(R.id.all_role_delete);
        allRoleChange = holder.getView(R.id.all_role_change);

        allRoleName.setText(data.getName());
        allRoleUrl.setText(data.getUrl());
        allRoleDesc.setText(data.getDesc_());


        //删除权限
        allRoleDelete.setOnClickListener((v) -> {
            if (itemClickListener != null)
                itemClickListener.onItemClickListener(v, position);
        });

        //编辑权限
        allRoleChange.setOnClickListener((v -> {

            if (itemClickListener != null)
                itemClickListener.onItemClickListener(v, position);

        }));

    }
}
