package computer_room.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.device_detail_lib.R;
import com.nex3z.flowlayout.FlowLayout;

import org.litepal.util.cipher.CipherUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import base.BaseRecyclerViewAdapter;
import base.BaseViewHolder;
import base.GlideApp;
import computer_room.bean.NewUserManageBean;
import computer_room.bean.NewUserManageBean;
import i_v.UserUpdateListener;
import kotlin.jvm.internal.PropertyReference0Impl;
import util.ChipsUtil;
import util.I_AdapterCall;
import util.LainNewApi;

/**
 * Author：YIN_LUO_FEI
 * Date：2019/11/20 16:46
 * Description：管理员-适配器
 **/
public class NewDeviceManageAdapter extends BaseRecyclerViewAdapter<NewUserManageBean> implements I_AdapterCall {

    //编辑用户成功后，调用接口来刷新数据
    private UserUpdateListener userUpdateListener;
    //用户管理，角色管理对权限操作的URL不同，因此需要在不同的适配器中，保存不同的URL
    private String url = LainNewApi.updateCharacter;

    /**
     * 初始化添加设备列表
     *
     * @param context  Context
     * @param dataList 数据列表
     * @param layoutId 列表 ID
     */
    public NewDeviceManageAdapter(Context context, List<NewUserManageBean> dataList, int layoutId) {
        super(context, dataList, layoutId);
    }

    public NewDeviceManageAdapter(Context context, List<NewUserManageBean> dataList, int layoutId, UserUpdateListener userUpdateListener) {
        super(context, dataList, layoutId);
        this.userUpdateListener = userUpdateListener;
    }

    @Override
    protected void bindData(BaseViewHolder holder, NewUserManageBean data, int position) {

        Button editBtn = holder.getView(R.id.user_change);
        //用户头像
        ImageView userIcon = holder.getView(R.id.user_manage_icon);

        Button user_del = holder.getView(R.id.user_del);
        TextView superUsername = holder.getView(R.id.super_username);
        TextView phoneNumber = holder.getView(R.id.phone_number);
        TextView emailNumber = holder.getView(R.id.email_number);
        //用户拥有权限
        FlowLayout userTagView = holder.getView(R.id.user_tag_view);

        //每次刷新都需要将FlowLayout下的子控件清空，不然会叠加
        userTagView.removeAllViews();

        //获取用户权限
        ChipsUtil.getInstance().addChipViewObj(recyclerContext, data.getRoleList(), userTagView, position, data.getId(), userUpdateListener, null);

        //电话
        LinearLayout telphone = holder.getView(R.id.phone_layout);
        //邮箱
        LinearLayout emailLayout = holder.getView(R.id.email_layout);

        superUsername.setText(data.getUsername());
        phoneNumber.setText(data.getTelephone());
        emailNumber.setText(data.getEmail());
        //邮箱
        emailLayout.setOnClickListener((v) -> {
            if (itemClickListener != null)
                itemClickListener.onItemClickListener(v, position);
        });
        //电话
        telphone.setOnClickListener((v) -> {
            if (itemClickListener != null)
                itemClickListener.onItemClickListener(v, position);
        });
        //编辑按钮，必须使用内部类，不然无法取到实时位置
        editBtn.setOnClickListener((v) -> {
            if (itemClickListener != null)
                itemClickListener.onItemClickListener(v, position);
        });
        //删除用户
        user_del.setOnClickListener((v) -> {
            if (itemClickListener != null)
                itemClickListener.onItemClickListener(v, position);
        });

        //修改头像
            Log.d("jhjhkdf", "bindData: " + data.getIcon() + "----" + data.getUsername());
        if (data.getIcon() != null) {
            Log.d("derwerw", "bindData: " + data.getIcon() + "----" + data.getUsername());


            GlideApp.with(recyclerContext).load(LainNewApi.getInstance().getRootPath() + data.getIcon())
                    .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                    //设置缓存策略才可以正常加载图片
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .into(new SimpleTarget<Drawable>() {
                        @Override
                        public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                            Log.d("kljkldfdf", "onResourceReady: " + data.getUsername());
                            userIcon.setTag(data.getUsername());
                            userIcon.setImageDrawable(resource);
                        }
                    });

        } else {

            userIcon.setImageDrawable(recyclerContext.getDrawable(R.drawable.supermanage_icon));

        }

    }

    private List<String> getRoleList(List<NewUserManageBean.RoleListBean> roleListBeanList) {

        List<String> roleStrList = new ArrayList<>();

        for (NewUserManageBean.RoleListBean roleBean :
                roleListBeanList) {
            roleStrList.add(roleBean.getDesc_());
        }


        return roleStrList;
    }

    @Override
    public void actionCall() {

//        if (userUpdateListener != null)
//            userUpdateListener.refreshUserInfo();

        notifyDataSetChanged();

    }
}
