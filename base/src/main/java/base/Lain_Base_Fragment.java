package base;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import butterknife.ButterKnife;

/**
 * 基类 Fragment，抽取共有动作
 *
 * @author Create by Yin Luo Fei
 * @version 0.1
 * @Time 2019/7/31 14 : 44
 */
public abstract class Lain_Base_Fragment extends Fragment {
    //保存 Fragment 所加载的 Layout 视图
    private View subView;

    /**
     * Fragment 创建完毕时执行
     *
     * @param savedInstanceState 临时保存数据
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    /**
     * 加载Fragment
     *
     * @param inflater           加载View
     * @param container          ViewGroup
     * @param savedInstanceState 保存Activity临时数据
     * @return Fragment所加载的View
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //所有的子 Fragment 都由这句来生成 View
        View view = getFragmentLayout(inflater, container, savedInstanceState);
        //子Fragment支持 ButterKnife
        ButterKnife.bind(this, view);
        //子Fragment 的 View
        subView = view;
        return view;
    }

    /**
     * 做了Fragment的封装，获取Fragment的View
     *
     * @param inflater           加载视图
     * @param container          视图容器
     * @param savedInstanceState 临时保存数据
     * @return Fragment View
     */
    protected abstract View getFragmentLayout(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    /**
     * 生成随机数
     *
     * @param range 随机数范围
     * @param start 随机数开始值
     * @return 生成的随机数
     */
    protected float getRandom(float range, float start) {
        return (float) (Math.random() * range) + start;
    }

    /**
     * 获取 子视图 的 View
     *
     * @return 子视频 View
     */
    public View getSubView() {
        return subView;
    }

}
