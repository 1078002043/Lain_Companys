package com.example.mympchart;

import com.github.mikephil.charting.data.Entry;
import com.venmo.view.TooltipView;

import java.util.List;

public interface MarkerViewListener {
    /**
     * 回调 markview 的值
     * @param xValue X 轴的数据
     * @param entryList Y 轴的数据（左右两边），根据数量来决定的
     * @param tvContent
     */
    void markerValue(String xValue, List<Entry> entryList, TooltipView tvContent);

    /**
     * 获取曲线图的颜色
     * @return
     */
    List<Integer> getLineChartColor();

    /**
     * 获取数据的虚线图，图例的颜色
     * @return
     */
    List<Integer> getLineCircleColor();

    /**
     * 获取数据的图例文字
     * @return
     */
    List<String> getLineLegendText();

}
