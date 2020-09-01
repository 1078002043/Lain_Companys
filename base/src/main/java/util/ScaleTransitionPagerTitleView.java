package util;

import android.content.Context;

import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;

/**
 * 带颜色渐变和缩放的指示器标题
 * 顶部标题栏的选中未选中动画效果
 */
public class ScaleTransitionPagerTitleView extends ColorTransitionPagerTitleView {
    private float mMinScale = 0.9f;

    public ScaleTransitionPagerTitleView(Context context) {
        super(context);
    }

    /**
     * 选择
     *
     * @param index
     * @param totalCount
     * @param enterPercent
     * @param leftToRight
     */
    @Override
    public void onEnter(int index, int totalCount, float enterPercent, boolean leftToRight) {
        super.onEnter(index, totalCount, enterPercent, leftToRight);    // 实现颜色渐变
        setScaleX(mMinScale + (1f - mMinScale) * enterPercent);
        setScaleY(mMinScale + (1f - mMinScale) * enterPercent);
    }

    /**
     * 未选中
     *
     * @param index
     * @param totalCount
     * @param leavePercent
     * @param leftToRight
     */
    @Override
    public void onLeave(int index, int totalCount, float leavePercent, boolean leftToRight) {
        super.onLeave(index, totalCount, leavePercent, leftToRight);    // 实现颜色渐变
        setScaleX(1f + (mMinScale - 1f) * leavePercent);
        setScaleY(1f + (mMinScale - 1f) * leavePercent);
    }

    public float getMinScale() {
        return mMinScale;
    }

    public void setMinScale(float minScale) {
        mMinScale = minScale;
    }

}
