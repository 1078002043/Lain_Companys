package base;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

/**
 * 设备详情中的 ViewPager
 *
 * @author Create by Yin Luo Fei
 * @version 0.1
 * @Time 2019/8/7 00 : 17
 */
public class Base_DevicesDetail_ViewPager extends PagerAdapter {
    //ViewPager View集合
    private List<View> viewList;

    //初始化ViewPager View集合
    public Base_DevicesDetail_ViewPager(List<View> viewList) {
        this.viewList = viewList;
    }

    /**
     * 获取View的长度
     *
     * @return View的长度
     */
    @Override
    public int getCount() {
        return viewList.size();
    }

    /**
     * 判断是否为同一个View
     */

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    /**
     * 添加View
     *
     * @param container 视图组
     * @param position  当前ViewPager的位置
     * @return View
     */
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        View view = viewList.get(position);
        container.addView(view);
        return view;
    }

    /**
     * 销毁View
     *
     * @param container 视图组
     * @param position  需要销毁的View当前位置
     * @param object    Object View
     */
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView(viewList.get(position));
    }



}
