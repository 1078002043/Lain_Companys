package computer_room.adapter;

import android.content.Context;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.example.device_detail_lib.R;
import com.nex3z.flowlayout.FlowLayout;

import java.util.List;

import base.BaseRecyclerViewAdapter;
import base.BaseViewHolder;
import computer_room.bean.CharacterManageBean;
import util.ChipsUtil;
import util.I_AdapterCall;
import util.LainNewApi;

/**
 * @ClassName: RolePermissonAdapter
 * @Description:
 * @Author: YIN LUO FEI
 * @Date: 2020/7/15 14:25
 */
public class RolePermissionAdapter extends BaseRecyclerViewAdapter<CharacterManageBean> implements I_AdapterCall {

    //用户管理，角色管理对权限操作的URL不同，因此需要在不同的适配器中，保存不同的URL
    private String url = LainNewApi.updateCharacter;

    private TextView roleIcon;
    private TextView roleDesc;
    private TextView roleName;
    private FlowLayout roleTagView;
    private Button roleDel;
    private Button roleChange;

    public RolePermissionAdapter(Context context, List<CharacterManageBean> dataList, int layoutId) {
        super(context, dataList, layoutId);
    }

    @Override
    protected void bindData(BaseViewHolder holder, CharacterManageBean data, int position) {

        try {
            roleIcon = holder.getView(R.id.role_icon);
            roleDesc = holder.getView(R.id.role_desc);
            roleName = holder.getView(R.id.role_name);
            roleTagView = holder.getView(R.id.role_tag_view);
            roleDel = holder.getView(R.id.role_del);
            roleChange = holder.getView(R.id.role_change);

            roleIcon.setText(String.valueOf(data.getName().charAt(0)).toUpperCase());
            roleDesc.setText(data.getDesc_());
            roleName.setText(data.getName());
        } catch (Exception e) {
            Log.d(getClass().getName(), "bindData: 设置角色列表异常");
        }


        //每次刷新都需要将FlowLayout下的子控件清空，不然会叠加
        roleTagView.removeAllViews();
        //显示权限所拥有的操作
        ChipsUtil.getInstance().addChipViewRole(recyclerContext, data.getPermissions(),roleTagView, data.getId(), url, data, this);

        //删除权限
        roleDel.setOnClickListener((v) -> {

            if (itemClickListener != null)
                itemClickListener.onItemClickListener(v, position);

        });
        //修改权限
        roleChange.setOnClickListener((v) -> {

            if (itemClickListener != null)
                itemClickListener.onItemClickListener(v, position);

        });

    }

    @Override
    public void actionCall() {
        //操作成功后，更新列表
        notifyDataSetChanged();
    }
}
