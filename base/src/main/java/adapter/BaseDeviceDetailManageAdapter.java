package adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.base.R;
import com.marshalchen.ultimaterecyclerview.SwipeableUltimateViewAdapter;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerviewViewHolder;
import com.marshalchen.ultimaterecyclerview.swipe.SwipeLayout;

import java.util.ArrayList;
import java.util.List;

import base.Lain_Application;
import http.OkHttpCallBack;
import http.OkHttpUtil;
import util.LainNewApi;
import util.ToastManage;
import view_interface.I_DeviceDetailManageLink;

/**
 * 设备详情中设备管理的 基适配器
 * T 设备列表的 BEAN E 在修改删除时，都需要设备的一些信息，为了达到封装，所以需要传入
 *
 * @param <T>
 */
public class BaseDeviceDetailManageAdapter<T, E> extends SwipeableUltimateViewAdapter<T> {

    //显示操作设备的面板，删除，修改设备
    private PopupWindow popupWindow;
    //修改设备
    private LinearLayout changeDevice;
    //删除设备
    private LinearLayout deleteDevice;
    //layout的ID
    private int normalLayoutResId = 0;
    //上下文
    private Context context;
    //删除设备的操作链接
    private String linkDeleted = "";
    //设备管理的Bean
    private List<E> deviceManageBean = new ArrayList<>();

    //交接接口
    private I_DeviceDetailManageLink i_ManageLink;

    /**
     * 适配器的数据
     *
     * @param list 接收设备管理的适配器数据
     */
    public BaseDeviceDetailManageAdapter(List<T> list) {
        super(list);
    }

    /**
     * 普通的适配器
     *
     * @param context  上下文
     * @param dataList 适配器数据
     * @param layoutId 布局ID
     */
    public BaseDeviceDetailManageAdapter(Context context, List<T> dataList, int layoutId) {
        super(dataList);
        this.context = context;
    }

    /**
     * 封装好的设备管理中的删除，修改设备信息
     *
     * @param list        适配器数据
     * @param linkDeleted 删除设备的链接
     * @param linkChange  修改设备的链接
     */
    public BaseDeviceDetailManageAdapter(List<T> list, Context context, int layoutId, String linkDeleted, String linkChange) {
        super(list);
        this.linkDeleted = linkDeleted;
        this.context = context;
        this.normalLayoutResId = layoutId;

    }

    /**
     * 封装好的设备管理中的删除，修改设备信息
     *
     * @param list           设备的BEAN
     * @param deviceInfoList 删除，修改，都需要该设备的信息
     * @param context        上下文
     * @param layoutId       加载的ID
     * @param linkDeleted    删除链接
     * @param linkChange     修改链接
     */
    public BaseDeviceDetailManageAdapter(List<T> list, List<E> deviceInfoList, Context context, int layoutId, String linkDeleted, String linkChange, I_DeviceDetailManageLink link, String deviceName) {
        super(list);
        this.linkDeleted = LainNewApi.getInstance().getRootPath() + linkDeleted;
        //this.linkChange = LainNewApi.getInstance().getRootPath() + linkChange;

        this.context = context;
        this.normalLayoutResId = layoutId;
        deviceManageBean = deviceInfoList;
        //交接接口
        this.i_ManageLink = link;
        //保存设备名称
    }

    /**
     * 获取 ITEM ID
     *
     * @param position 接收Item的位置
     * @return 返回Item的位置
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * 获取头部ID
     *
     * @param i 头部ID
     * @return 头部ID
     */
    @Override
    public long generateHeaderId(int i) {
        return i;
    }

    /**
     * 获取整个layout
     *
     * @return 返回Layout的ID
     */
    @Override
    protected int getNormalLayoutResId() {
        return normalLayoutResId;
    }

    /**
     * 绑定设置数据
     *
     * @param holder   数据的ViewHolder
     * @param data     列表的数据
     * @param position 列表Item的位置
     */
    @Override
    protected void withBindHolder(UltimateRecyclerviewViewHolder holder, T data, int position) {
        super.withBindHolder(holder, data, position);
        //这里必须是强转，不然设置的数据是错误的
        i_ManageLink.withBindDeviceManage((SVHolder) holder, data, position);

    }

    /**
     * 绑定设置视图
     *
     * @param view View
     * @return UltimateRecyclerviewViewHolder
     */
    @Override
    protected UltimateRecyclerviewViewHolder newViewHolder(View view) {

        //这个 SVHolder 绝对不能改成 成员变量，不然获取不到的位置报错
        BaseDeviceDetailManageAdapter.SVHolder svHolder = new BaseDeviceDetailManageAdapter.SVHolder(view, true);
        //解决需要强转才能获取到View的问题，但这个 SVHolder 绝对不能用来获取 ITEM 位置，会报错，获取位置只能能上面的
        //this.svHolder = svHolder;

        //删除设备
        svHolder.deleteButton.setOnClickListener((v) -> {

        });
        //修改设备信息
        svHolder.changeButton.setOnClickListener((v) -> {

        });

        //修改，删除Popup，必须传入 svHolder，不然有最后一个ITEM有效
        svHolder.editPopup.setOnClickListener((v) -> {

            //点击时，开始动画效果
            addAnimation(svHolder);

            if (popupWindow == null) {
                showPopupWindow(svHolder);
            } else if (popupWindow.isShowing()) {
                popupWindow.dismiss();
            } else {
                showPopupWindow(svHolder);
                popupWindow.showAsDropDown(svHolder.editPopup, -180, -60);
            }

        });

        return svHolder;

    }

    /**
     * 获取设备管理的列表数据
     *
     * @return 返回设备管理的列表数据
     */
    public List<E> getDeviceManageBean() {
        return deviceManageBean;
    }

    //抽屉内部类
    public class SVHolder extends UltimateRecyclerviewViewHolder implements OkHttpCallBack {
        //记录删除的是哪个ITEM
        int delItem = 0;
        //初始化控件
        public TextView textView;
        public TextView address;
        public TextView manageDeviceName;
        //删除设备按钮
        public Button deleteButton;
        //修改设备按钮
        public Button changeButton;
        //滑动删除，取消使用
        public SwipeLayout swipeLayout;
        //设备信息容器
        public LinearLayout linearLayout;
        //设备图标
        public ImageView imageView;

        //删除，修改popup
        private ImageView editPopup;

        public SVHolder(View itemView, boolean bind) {
            super(itemView);
            if (bind) {

                //设备信息，通过动态添加View的方法在这个容器中添加View
                linearLayout = itemView.findViewById(R.id.manage_info);
                //修改，删除设备时的弹窗
                editPopup = itemView.findViewById(R.id.edit_popup);
                //设备管理的图标
                imageView = itemView.findViewById(R.id.dynamic_manage_icon);
                //设备管理中设备的名称
                manageDeviceName = itemView.findViewById(R.id.manage_device_name);

                //抽柜-删除
                deleteButton = itemView.findViewById(R.id.delete);
                //抽柜-修改
                changeButton = itemView.findViewById(R.id.change);

                //swipe 滑动操作已取消
                swipeLayout = itemView.findViewById(R.id.recyclerview_swipe);
                swipeLayout.setDragEdge(SwipeLayout.DragEdge.Right);
                swipeLayout.setShowMode(SwipeLayout.ShowMode.PullOut);
            }
        }

        /**
         * 删除设备
         *
         * @param ehmID    接收设备的ID
         * @param position 接收设备的位置
         */
        public void deleteDevice(String ehmID, int position) {

            //保存删除设备的位置，后面需要更新列表
            delItem = position;

            //发送删除请求
            String url = linkDeleted + ehmID;
            OkHttpUtil.getInstance().sendDeletedOkHttp("delete", url, this);

        }

        /**
         * 删除，修改，添加设备成功回调
         *
         * @param requestTag  请求的 标识
         * @param requestUrl  请求的 URL
         * @param responseStr 请求成功返回的 JSON
         */
        @Override
        public void httpRequestSuccess(String requestTag, String requestUrl, String responseStr) {
            if (responseStr.equals("true")) {
                //删除 ITEM
                removeAt(delItem);
                //提用户删除成功
                ToastManage.getInstance().toastShortShow("删除设备成功");
            }
        }

        /**
         * 删除，修改，添加设备失败回调
         *
         * @param requestTag 请求的 标识
         * @param requestUrl 请求的 URL
         * @param errorMsg   请求失败返回的信息
         */
        @Override
        public void httpRequestFailure(String requestTag, String requestUrl, String errorMsg) {
            Log.d("httpRequestFailure", "删除设备失败: " + errorMsg);
        }

        /**
         * 修改设备信息
         * 修改设备信息重构成由调用者进行获取数据进行修改
         */
        public void changeDevice(String name, int position) {

//            Intent changeIntent = new Intent(context, DeviceDetailManageAdd.class);
//            changeIntent.putExtra("change_device", (Serializable) deviceManageBean.get(position));
//            changeIntent.putExtra("change_device_name", name);
//            context.startActivity(changeIntent);

        }
    }

    /**
     * 添加图标的旋转动画
     *
     * @param svHolder 通过 SVHolder 获取弹窗的控件实例
     */
    private void addAnimation(BaseDeviceDetailManageAdapter.SVHolder svHolder) {
        //构建动画
        RotateAnimation rotateAnimation = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setDuration(500);//设置动画时间
        svHolder.editPopup.setAnimation(rotateAnimation);//设置动画
        svHolder.editPopup.startAnimation(rotateAnimation);//开始动画
    }

    /**
     * 显示弹窗
     *
     * @param svHolder 通过 SVHolder 获取 Item 的 View
     */
    private void showPopupWindow(BaseDeviceDetailManageAdapter.SVHolder svHolder) {

        //保存点击的控件位置
        int position = svHolder.getAdapterPosition();

        //动态添加View，显示修改，删除弹窗
        View view = LayoutInflater.from(Lain_Application.getContext()).inflate(R.layout.pop_layout, null);//获取popupWindow子布局对象
        popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, false);//初始化
        popupWindow.showAsDropDown(svHolder.editPopup, -180, -60);//在ImageView控件下方弹出
        popupWindow.setAnimationStyle(R.style.popupAnim);//设置动画
        //修改按钮实例
        changeDevice = view.findViewById(R.id.popup_change);
        //删除按钮实例
        deleteDevice = view.findViewById(R.id.popup_delete);

        //点击修改按钮
        changeDevice.setOnClickListener((v) -> {
            //回调给调用者处理
            i_ManageLink.changeDevice("修改", svHolder);
            //关闭弹窗
            popupWindow.dismiss();
        });

        //点击删除设备按钮
        deleteDevice.setOnClickListener((v) -> {
            //获取点击的Item位置
            int pos = svHolder.getAdapterPosition();
            //回调给调用者进行删除操作
            i_ManageLink.deleteDevice(pos, svHolder);
            //关闭弹窗
            popupWindow.dismiss();
        });

    }

}
