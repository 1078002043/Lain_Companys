package computer_room.adapter;

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

import com.example.device_detail_lib.R;
import com.marshalchen.ultimaterecyclerview.SwipeableUltimateViewAdapter;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerviewViewHolder;
import com.marshalchen.ultimaterecyclerview.swipe.SwipeLayout;

import java.util.ArrayList;
import java.util.List;

import base.Lain_Application;
import http.OkHttpCallBack;
import http.OkHttpUtil;
import i_v.I_DeviceDetailManageLink;
import util.LainNewApi;
import util.ToastManage;

/**
 * 设备详情中设备管理的 基适配器
 * T 设备列表的 BEAN E 在修改删除时，都需要设备的一些信息，为了达到封装，所以需要传入
 * @param <T>
 */
public class BaseDeviceDetailManageAdapter<T, E> extends SwipeableUltimateViewAdapter<T> {

    private PopupWindow popupWindow;
    private LinearLayout changeDevice;
    private LinearLayout deleteDevice;

    private int normalLayoutResId = 0;

    private Context context;

    private String linkDeleted = "";
    private String linkChange = "";

    private String deviceName = "";

    private List<E> deviceManageBean = new ArrayList<>();

//    private BaseDeviceDetailManageAdapter.SVHolder svHolder;

    //交接接口
    private I_DeviceDetailManageLink i_ManageLink;

    public BaseDeviceDetailManageAdapter(List<T> list) {
        super(list);
    }

    /**
     * 普通的适配器
     * @param context
     * @param dataList
     * @param layoutId
     */
    public BaseDeviceDetailManageAdapter(Context context, List<T> dataList, int layoutId) {
        super(dataList);
        this.context = context;
    }

    /**
     * 封装好的设备管理中的删除，修改设备信息
     * @param list
     * @param linkDeleted
     * @param linkChange
     */
    public BaseDeviceDetailManageAdapter(List<T> list,Context context,int layoutId, String linkDeleted, String linkChange) {
        super(list);
        this.linkDeleted = linkDeleted;
        this.linkChange = linkChange;
        this.context = context;
        this.normalLayoutResId = layoutId;

    }

    /**
     * 封装好的设备管理中的删除，修改设备信息
     * @param list 设备的BEAN
     * @param deviceInfoList 删除，修改，都需要该设备的信息
     * @param context 上下文
     * @param layoutId 加载的ID
     * @param linkDeleted 删除链接
     * @param linkChange 修改链接
     */
    public BaseDeviceDetailManageAdapter(List<T> list, List<E> deviceInfoList, Context context,int layoutId, String linkDeleted, String linkChange, I_DeviceDetailManageLink link, String deviceName) {
        super(list);
        this.linkDeleted = LainNewApi.getInstance().getRootPath() + linkDeleted;
        this.linkChange = LainNewApi.getInstance().getRootPath() + linkChange;

        this.context = context;
        this.normalLayoutResId = layoutId;
        deviceManageBean = deviceInfoList;
        //交接接口
        this.i_ManageLink = link;
        //保存设备名称
        this.deviceName = deviceName;
    }

    /**
     * 获取 ITEM ID
     * @param position
     * @return
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * 获取头部ID
     * @param i
     * @return
     */
    @Override
    public long generateHeaderId(int i) {
        return i;
    }

    /**
     * 获取整个layout
     * @return
     */
    @Override
    protected int getNormalLayoutResId() {
        return normalLayoutResId;
    }

    /**
     * 绑定设置数据
     * @param holder
     * @param data
     * @param position
     */
    @Override
    protected void withBindHolder(UltimateRecyclerviewViewHolder holder, T data, int position) {
        super.withBindHolder(holder, data, position);

        //这里必须是强转，不然设置的数据是错误的
        i_ManageLink.withBindDeviceManage((SVHolder)holder,data,position);


    }

    /**
     * 绑定设置视图
     * @param view
     * @return
     */
    @Override
    protected UltimateRecyclerviewViewHolder newViewHolder(View view) {
        //这个 SVHolder 绝对不能改成 成员变量，不然获取不到的位置报错
        BaseDeviceDetailManageAdapter.SVHolder svHolder = new BaseDeviceDetailManageAdapter.SVHolder(view, true);
        //解决需要强转才能获取到View的问题，但这个 SVHolder 绝对不能用来获取 ITEM 位置，会报错，获取位置只能能上面的
//        this.svHolder = svHolder;


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
            } else if(popupWindow.isShowing()){
                popupWindow.dismiss();
            }
            else{
                showPopupWindow(svHolder);
                popupWindow.showAsDropDown(svHolder.editPopup,-180,-60);
            }

        });
        
        return svHolder;

    }

    public List<E> getDeviceManageBean() {
        return deviceManageBean;
    }

    //抽屉内部类
    public class SVHolder extends UltimateRecyclerviewViewHolder implements OkHttpCallBack{
        //记录删除的是哪个ITEM
        int delItem = 0;
        //初始化控件
        public TextView textView;
        public TextView address;
        public TextView manageDeviceName;
        public Button deleteButton;
        public Button changeButton;
        public SwipeLayout swipeLayout;

        public LinearLayout linearLayout;
        public ImageView imageView;

        //删除，修改popup
        private ImageView editPopup;
        
        public SVHolder(View itemView, boolean bind) {
            super(itemView);
            if (bind) {

                //内容
//                textView = (TextView) itemView.findViewById(R.id.add_device_detail_name);
//                address = (TextView) itemView.findViewById(R.id.temp_address);

                linearLayout = itemView.findViewById(R.id.manage_info);
                editPopup = itemView.findViewById(R.id.edit_popup);

                imageView = itemView.findViewById(R.id.dynamic_manage_icon);
                manageDeviceName = itemView.findViewById(R.id.manage_device_name);


                //抽柜-删除
                deleteButton = itemView.findViewById(R.id.delete);
                //抽柜-修改
                changeButton = itemView.findViewById(R.id.change);
                swipeLayout = itemView.findViewById(R.id.recyclerview_swipe);
                swipeLayout.setDragEdge(SwipeLayout.DragEdge.Right);
                swipeLayout.setShowMode(SwipeLayout.ShowMode.PullOut);
            }
        }

        public void deleteDevice(String ehmID, int position) {
            delItem = position;
            //发送删除请求
            String url = linkDeleted + ehmID;
            Log.d("deleteURL", "deleteDevice: " + url);
            OkHttpUtil.getInstance().sendDeletedOkHttp("delete", url, this);
        }
        /**
         * 删除，修改，添加设备成功回调
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
         * @param errorMsg
         */
        @Override
        public void httpRequestFailure(String requestTag, String requestUrl, String errorMsg) {
            Log.d("httpRequestFailure", "删除设备失败: "+errorMsg);
        }

        /**
         * 修改设备信息
         */
        public void changeDevice(String name, int position) {

//            Intent changeIntent = new Intent(context, DeviceDetailManageAdd.class);
//            changeIntent.putExtra("change_device", (Serializable) deviceManageBean.get(position));
//            changeIntent.putExtra("change_device_name", name);
//            context.startActivity(changeIntent);
        }
    }

    private void addAnimation(BaseDeviceDetailManageAdapter.SVHolder svHolder) {//加入了旋转动画
        RotateAnimation rotateAnimation=new RotateAnimation(0,360, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        rotateAnimation.setDuration(500);//设置动画时间
        svHolder.editPopup.setAnimation(rotateAnimation);//设置动画
        svHolder.editPopup.startAnimation(rotateAnimation);//开始动画
    }
    private void showPopupWindow(BaseDeviceDetailManageAdapter.SVHolder svHolder) {

        int position = svHolder.getAdapterPosition();
        Log.d("dsfsdd", "changeDevice: "+position);

        View view= LayoutInflater.from(Lain_Application.getContext()).inflate(R.layout.pop_layout,null);//获取popupWindow子布局对象
        popupWindow =new PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT,false);//初始化
        popupWindow.showAsDropDown(svHolder.editPopup,-180,-60);//在ImageView控件下方弹出
        popupWindow.setAnimationStyle(R.style.popupAnim);//设置动画

        changeDevice = view.findViewById(R.id.popup_change);
        deleteDevice = view.findViewById(R.id.popup_delete);

        changeDevice.setOnClickListener((v)->{
            i_ManageLink.changeDevice("修改", svHolder);
            popupWindow.dismiss();
        });

        deleteDevice.setOnClickListener((v)->{

            int pos = svHolder.getAdapterPosition();
            i_ManageLink.deleteDevice(pos, svHolder);

            popupWindow.dismiss();
        });

    }

}
