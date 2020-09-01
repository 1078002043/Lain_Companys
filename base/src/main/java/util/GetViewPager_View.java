package util;

import android.view.LayoutInflater;
import android.view.View;

import java.util.ArrayList;
import java.util.List;


/**
 * 获取 ViewPager 的 View 工具类，加载 ViewPager 中的 Layout 也在
 * 本类中获取
 *
 * @author Create by Yin Luo Fei
 * @version 0.1
 * @Time 2019/8/9 16 : 05
 */
public class GetViewPager_View {
    //单例实例
    private static GetViewPager_View getViewPagerView;
    //单例 View Layout ID
    private static ArrayList<Integer> viewList;

    /**
     * 使用双重检查单例模式获取 GetViewPager_View 的实例
     *
     * @return GetViewPager_View 实例
     */
    public static GetViewPager_View getInstance() {
        //只要有一个是 NULL 实例，就进行重新创建实例
        if (getViewPagerView == null)
            synchronized (GetViewPager_View.class) {
                if (getViewPagerView == null) {
                    //创建本类实例
                    getViewPagerView = new GetViewPager_View();
                    return getViewPagerView;
                }

            }
        //如果存在该实例，直接返回
        return getViewPagerView;
    }

    /**
     * 获取 ViewPager 中的 View 工具类
     *
     * @param layouts  保存需要加载的 Layout ID
     * @param inflater 加载视图需要使用到的 引用
     * @return 加载之后的 View 集合
     */
    public List<View> getViewViewPager(List<Integer> layouts, LayoutInflater inflater) {

        //如果传入的是一个空的集合，直接结束调用
        if (layouts.isEmpty())
            throw new NullPointerException();
        //单例 View List
        ArrayList<View> viewArrayList = new ArrayList<>();

        //遍历 需要加载的 View ID，添加在集合中
        for (int layoutID :
                layouts) {
            View view = inflater.inflate(layoutID, null);
            viewArrayList.add(view);
        }
        //将保存的集合返回
        return viewArrayList;

    }

    /**
     * 所有的子ViewPager都会传入ViewPager的Layout
     *
     * @return 温湿度设备详细信息 Layout View
     */
    public ArrayList<Integer> getViewByInteger(ArrayList<Integer> arrayView) {
        viewList = new ArrayList<>();
        //将所有的View遍历添加到 viewList中
        viewList.addAll(arrayView);
        return viewList;

    }

}