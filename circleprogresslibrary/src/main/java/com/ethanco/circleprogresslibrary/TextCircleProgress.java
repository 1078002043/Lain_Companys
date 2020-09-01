package com.ethanco.circleprogresslibrary;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ethanco.circleprogresslibrary.utils.DisplayUtil;
import com.robinhood.ticker.TickerUtils;
import com.robinhood.ticker.TickerView;

import java.net.PortUnreachableException;

/**
 * @Description 具有文本显示的CircleProgress
 * Created by YOLANDA on 2015-12-11.
 */
public class TextCircleProgress extends RelativeLayout {
    private final int headColor;
    private final int subHeadColor;
    private final float headSize;
    private final float subHeadSize;
    private final TextView tvBottomHead;
    private final int bottomHeadColor;
    private final float bottomHeadSize;
    private CircleProgress circleProgress;
    private TickCircleProgress tickCircleProgress;
    private final TickerView tvHead;
    private final TickerView tvSub;
    private final TextView tvSubHead;

    //内圆最大值
    public float innerMaxValue;
    //外圆最大值
    public float outerMaxValue;

    public TextCircleProgress(Context context) {
        this(context, null);
    }

    public TextCircleProgress(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TextCircleProgress(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.TextCircleProgress);
        headColor = ta.getColor(R.styleable.TextCircleProgress_headColor, Color.BLACK);
        subHeadColor = ta.getColor(R.styleable.TextCircleProgress_subHeadColor, Color.GRAY);
        headSize = DisplayUtil.px2sp(context, ta.getDimension(R.styleable.TextCircleProgress_headSize, 30));
        subHeadSize = DisplayUtil.px2sp(context, ta.getDimension(R.styleable.TextCircleProgress_subHeadSize, 18));
        bottomHeadColor = ta.getColor(R.styleable.TextCircleProgress_bottomHeadColor, Color.BLACK);
        bottomHeadSize = DisplayUtil.px2sp(context, ta.getDimension(R.styleable.TextCircleProgress_bottomHeadSize, 22));
        ta.recycle();

        View contentView = View.inflate(context, R.layout.layout_circleprogress_text, this);
        circleProgress = (CircleProgress) contentView.findViewById(R.id.circleProgress);
        tickCircleProgress = (TickCircleProgress) contentView.findViewById(R.id.tickCircleProgress);

        //内部数值
        tvHead = (TickerView) contentView.findViewById(R.id.tvHead);
        tvHead.setCharacterLists(TickerUtils.provideNumberList());
//        tvHead.setTextSize(40);
        //外部数值
        tvSub = (TickerView) contentView.findViewById(R.id.tvBottomSub);
        tvSub.setCharacterLists(TickerUtils.provideNumberList());

        tvSubHead = (TextView) contentView.findViewById(R.id.tvSubhead);
        tvBottomHead = (TextView) contentView.findViewById(R.id.tvBottomHead);

        tvHead.setTextColor(headColor);
//        tvHead.setTextSize(headSize);
        tvSubHead.setTextColor(subHeadColor);
//        tvSubHead.setTextSize(18);
        tvBottomHead.setTextColor(bottomHeadColor);
        tvSub.setTextColor(bottomHeadColor);
//        tvBottomHead.setTextSize(18);
    }

    // ============================================================
    // = Z- 对外开放的方法
    // ============================================================

    /**
     * 设置主进度条进度
     *
     * @param progress
     */
    public void setOuterProgress(float progress) {

        //计算百分比
        float percent = progress / outerMaxValue * 100;

        //如果当前值大于最大值，进度条进度填满
        if (progress >= outerMaxValue) {
            percent = 100;
            //改变文案颜色
            tvSub.setTextColor(Color.RED);
            tvBottomHead.setTextColor(Color.RED);

        } else {
            tvSub.setTextColor(Color.parseColor("#0b71d6"));
            tvBottomHead.setTextColor(Color.parseColor("#0b71d6"));
        }

        setOuter(progress + "");
        circleProgress.setProgress((int) percent);
    }

    /**
     * 设置子进度条进度
     *
     * @param progress
     */
    public void setInnerProgress(float progress) {

        //计算百分比
        float percent = progress / innerMaxValue * 100;

        //如果当前值大于最大值，进度条进度填满
        if (progress >= innerMaxValue) {
            percent = 100;
            //改变文案颜色
            tvHead.setTextColor(Color.RED);
            tvSubHead.setTextColor(Color.RED);

        } else {
            tvHead.setTextColor(Color.parseColor("#0b71d6"));
            tvSubHead.setTextColor(Color.parseColor("#0b71d6"));
        }
        setInner(progress + "");
        tickCircleProgress.setProgress((int) percent);
    }

    /**
     * 设置主标题
     *
     * @param text
     */
    public void setInner(String text) {
        tvHead.setText(text);
    }

    public void setOuter(String text) {
        tvSub.setText(text);
    }

    /**
     * 设置副标题
     *
     * @param text
     */
    public void setInnerHead(String text) {

        tvSubHead.setText(text);
    }

    /**
     * 设置底部标题
     *
     * @param text
     */
    public void setOuterHead(String text) {
        tvBottomHead.setText(text);
    }

    /**
     * 是否显示外层圆
     *
     * @param isShow 是否显示
     */
    public void isShowInnerCircle(boolean isShow) {

        int result = isShow ? VISIBLE : GONE;

        //如果不显示该圆，则将相关控件全部隐藏
        tickCircleProgress.setVisibility(result);
        tvSubHead.setVisibility(result);
        tvHead.setVisibility(result);

    }

    /**
     * 是否显示外层圆
     *
     * @param isShow 是否显示
     */
    public void isShowOuterCircle(boolean isShow) {

        int result = isShow ? VISIBLE : GONE;
        //如果不显示该圆，则将相关控件全部隐藏
        circleProgress.setVisibility(result);
        tvSub.setVisibility(result);
        tvBottomHead.setVisibility(result);
    }

    /**
     * 设置内圆最大值
     *
     * @param innerMaxValue 数值
     */
    public void setInnerMaxValue(float innerMaxValue) {
        this.innerMaxValue = innerMaxValue;
    }

    /**
     * 设置外圆最大值
     *
     * @param outerMaxValue 数值
     */
    public void setOuterMaxValue(float outerMaxValue) {
        this.outerMaxValue = outerMaxValue;
    }
}
