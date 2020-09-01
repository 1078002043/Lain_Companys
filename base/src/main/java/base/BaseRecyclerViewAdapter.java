package base;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.marshalchen.ultimaterecyclerview.UltimateRecyclerviewViewHolder;
import com.marshalchen.ultimaterecyclerview.quickAdapter.easyRegularAdapter;

import java.util.List;

import adapter.I_RealData;


/**
 * 基类 UltimateViewAdapter
 *
 * @author Create by Yin Luo Fei
 * @version 0.1
 * @Time 2019/8/5 09 : 35
 */
public abstract class BaseRecyclerViewAdapter<T> extends easyRegularAdapter<T,BaseViewHolder> {

    //保存实时数据的回调接口，调用者需要自己去处理视频绑定操作
    public I_RealData i_realData;
    //保存动画的名称
    public String lottieName = "";
    //Context
    public Context recyclerContext;
    //列表数据
    public List<T> dataList;
    //列表 ID
    private int layoutId;
    //加载Layout
    private LayoutInflater inflater;
    //单击监听
    protected OnItemClickListener itemClickListener;
    //长按点击
    protected OnItemLongClickListener longClickListener;

    public int[] statusImg;

    public BaseRecyclerViewAdapter(List<T> dataList) {
        super(dataList);
        this.dataList = dataList;
    }

    /**
     * 初始化添加设备列表
     *
     * @param context  Context
     * @param dataList 数据列表
     * @param layoutId 列表 ID
     */
    public BaseRecyclerViewAdapter(Context context, List<T> dataList, int layoutId) {

        super(dataList);

        this.recyclerContext = context;
        this.dataList = dataList;
        this.layoutId = layoutId;
        this.inflater = LayoutInflater.from(context);


    }

    /**
     * 初始化添加设备列表
     *
     * @param context    Context
     * @param dataList   数据列表
     * @param layoutId   列表 ID
     * @param i_realData 视图不在适配器中设置，由调用者进行操作
     */
    public BaseRecyclerViewAdapter(Context context, List<T> dataList, int layoutId, I_RealData i_realData) {

        super(dataList);

        this.recyclerContext = context;
        this.dataList = dataList;
        this.layoutId = layoutId;
        this.inflater = LayoutInflater.from(context);
        this.i_realData = i_realData;
    }

    /**
     * 初始化添加设备列表
     *
     * @param context  Context
     * @param dataList 数据列表
     * @param layoutId 列表 ID
     */
    public BaseRecyclerViewAdapter(Context context, List<T> dataList, int layoutId, int[] statusImg) {

        super(dataList);

        this.recyclerContext = context;
        this.dataList = dataList;
        this.layoutId = layoutId;
        this.inflater = LayoutInflater.from(context);
        //设备的状态图标
        this.statusImg = statusImg;
    }

    /**
     * 底部Holder
     *
     * @param view View
     * @return BaseViewHolder
     */
    @Override
    public BaseViewHolder newFooterHolder(View view) {
        return new BaseViewHolder(view);
    }

    /**
     * 头部Holder
     *
     * @param view View
     * @return BaseViewHolder
     */
    @Override
    public BaseViewHolder newHeaderHolder(View view) {
        return new BaseViewHolder(view);
    }

    /**
     * 创建列表
     *
     * @param parent 视图组
     * @return BaseViewHolder
     */
    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent) {

        Log.d("kljldf", "onCreateViewHolder: BaseViewHolder");

        return new BaseViewHolder(inflater.inflate(layoutId, parent, false));

    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        swapPositions(fromPosition, toPosition);
        super.onItemMove(fromPosition, toPosition);
    }

    @Override
    public void onItemDismiss(int position) {
        if (position > 0)
            removeAt(position);
        super.onItemDismiss(position);
    }

    /**
     * 获取列表长度
     *
     * @return 列表长度
     */
    @Override
    public int getAdapterItemCount() {
        return dataList == null ? 0 : dataList.size();
    }

    /**
     * 头部ID
     *
     * @param position 位置
     * @return 位置
     */
    @Override
    public long generateHeaderId(int position) {
        return 0;
    }

    /**
     * 绑定视图
     *
     * @param holder   视图引用
     * @param position 视图当前的位置
     */
//    @Override
//    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
//        bindData(holder, dataList.get(position), position);
//    }

    @Override
    protected void withBindHolder(BaseViewHolder holder, T data, int position) {
        bindData(holder, dataList.get(position), position);
    }

    /**
     * 绑定视图-解决设备列表更新时，出现闪烁的问题
     * @param holder ViewHolder 视图
     * @param position Item 的位置
     * @param payloads 只要
     */
 /*   @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position, @NonNull List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
        bindData(holder, dataList.get(position), position, payloads);
    }*/

    /**
     * 抽象方法，由子类来实现，列表中的逻辑操作
     *
     * @param holder   视图的引用
     * @param data     列表数据
     * @param position 列表的位置
     */
    protected abstract void bindData(BaseViewHolder holder, T data, int position);

    /**
     * 抽象方法，由子类来实现，列表中的逻辑操作 ---- 解决列表刷新时闪烁的BUG
     *
     * @param holder   视图的引用
     * @param data     列表数据
     * @param position 列表的位置
     */
    protected void bindData(BaseViewHolder holder, T data, int position, List<Object> payloads) {

    }

    /**
     * 创建头部ViewHolder
     *
     * @param parent 视图组
     * @return RecyclerView ViewHolder
     */
    @Override
    public UltimateRecyclerviewViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
        return null;
    }

    @Override
    protected BaseViewHolder newViewHolder(View view) {
        return new BaseViewHolder(view);
    }

    @Override
    protected int getNormalLayoutResId() {
        return layoutId;
    }

    /**
     * 绑定头部ViewHolder
     *
     * @param holder   视图引用
     * @param position 列表位置
     */
    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    public List<T> getDataList() {
        return dataList;
    }

    /**
     * 设置单击监控
     *
     * @param onItemClickListener 点击监听引用
     */
    public void setItemClickListener(OnItemClickListener onItemClickListener) {
        this.itemClickListener = onItemClickListener;
    }

    public OnItemClickListener getItemClickListener() {
        return this.itemClickListener;
    }

    /**
     * 设置长按点击
     *
     * @param longClickListener 长按监听引用
     */
    public void setLongClickListener(OnItemLongClickListener longClickListener) {
        this.longClickListener = longClickListener;
    }

    /**
     * 单击监听 Interface
     */
    public interface OnItemClickListener {
        void onItemClickListener(View v, int position);
    }

    /**
     * 长按监听 Interface
     */
    public interface OnItemLongClickListener {
        void onItemLongClickListener(View v, int position);
    }

    /**
     * 返回设备的状态
     *
     * @param status 设备的当前状态
     */
    public String deviceStatus(int status) {

        switch (status) {
            case 0:
                return "状态：正常";
            case 1:
                return "状态：异常";
            case 2:
                return "状态：未连接";
        }

        return "状态：未连接";
    }

    /**
     * 返回 Boolean 类型的开关状态
     *
     * @param status 状态
     * @return 是否开 或 关
     */
    public boolean deviceSwitch(int status) {

        switch (status) {
            case 0:
            case 1:
                return true;
            case 2:
                return false;
        }

        return false;
    }

    /**
     * 开关亮设备的图标设置
     *
     * @param status
     * @return
     */
    public int deviceStatusImg(int status, int[] statusImg) {

        try {
            switch (status) {
                case 0:
                case 1:
                    return statusImg[0];
                case 2:
                    return statusImg[1];
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            Log.d("status_8060", "deviceStatusImg: 8060设备设置图标失败  " + e.getMessage());
        }

        return 0;
    }

    /**
     * 根据不同的状态，改变图标
     *
     * @param status    状态
     * @param imageView 图标控件
     * @param imageID   连接，报警，未连接，一定要按钮这个顺序传入
     */
    public void deviceStatusImg(int status, ImageView imageView, int[] imageID) {

        try {
            switch (status) {
                case 0:
                    imageView.setImageResource(imageID[0]);
                    break;
                case 1:
                    imageView.setImageResource(imageID[1]);
                    break;
                case 2:
                    imageView.setImageResource(imageID[2]);
                    break;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            Log.d("statusImg", "deviceStatusImg: 需要传入 启动--未启动--报警 状态的图标   " + e.getMessage());
        }

    }

    /**
     * 设置动画的名称
     *
     * @param lottieAnimationName 动画的名称
     */
    public void setLottieAnimationName(String lottieAnimationName) {
        this.lottieName = lottieAnimationName;
    }

}
