package base;

import android.util.Log;
import android.util.SparseArray;
import android.view.View;

import com.marshalchen.ultimaterecyclerview.UltimateRecyclerviewViewHolder;

/**
 * 基类 Ultimate ViewHolder
 *
 * @author Create by Yin Luo Fei
 * @version 0.1
 * @Time 2019/8/5 09 : 26
 */
public class BaseViewHolder extends UltimateRecyclerviewViewHolder {

    private SparseArray<View> viewSparseArray;

    public BaseViewHolder(View view) {
        super(view);
        viewSparseArray = new SparseArray<>();

    }

    public <T extends View> T getView(int viewId) {
        View view = viewSparseArray.get(viewId);
        //不存在该 View 时，就要添加才能设置控件的属性
        if (view == null) {
            view = itemView.findViewById(viewId);
            viewSparseArray.put(viewId, view);
        }

        return (T) view;
    }

    public View getRootView() {
        return itemView;
    }

}
