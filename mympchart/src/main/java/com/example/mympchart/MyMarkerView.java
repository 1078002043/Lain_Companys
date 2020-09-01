
package com.example.mympchart;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.widget.TextView;

import com.example.mympchart.R;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.CandleEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Utils;
import com.venmo.view.TooltipView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Custom implementation of the MarkerView.
 *
 * @author Philipp Jahoda
 */
@SuppressLint("ViewConstructor")
public class MyMarkerView extends MarkerView {
    private final TooltipView tvContent;
    private final ValueFormatter xAxisValueFormatter;
    private LineChart chart;
    private final DecimalFormat format;

    private MarkerViewListener markerViewListener;

    public MyMarkerView(Context context, ValueFormatter xAxisValueFormatter, LineChart chart, MarkerViewListener markerViewListener) {
        super(context, R.layout.custom_marker_view);

        this.xAxisValueFormatter = xAxisValueFormatter;
        this.chart = chart;
        tvContent = findViewById(R.id.tooltip_1);
        this.markerViewListener = markerViewListener;
        format = new DecimalFormat("###.0");
    }

    // runs every time the MarkerView is redrawn, can be used to update the
    // content (user-interface)
    @Override
    public void refreshContent(Entry e, Highlight highlight) {
        //保存获取到的数据
        List<Entry> entryList = new ArrayList<>();
        //下面这里是关键的
        LineData lineData=chart.getLineData();//得到已经绘制成型的折线图的数据
        //获取所有的折线图Y轴数据
        List<LineDataSet> lineDataSetList = new ArrayList<>();

        for (int i = 0; i < lineData.getDataSetCount(); i++){
            lineDataSetList.add((LineDataSet)lineData.getDataSetByIndex(i));
        }

//        LineDataSet set=(LineDataSet)lineData.getDataSetByIndex(0);//获取第一条折线图Y轴数据
//        LineDataSet set1=(LineDataSet)lineData.getDataSetByIndex(1);//获取第二条折线图Y轴数据

        int DataSetIndex=highlight.getDataSetIndex();//获取点击的是哪条折线上的交叉点，0就是第一条，以此类推
        int index = lineDataSetList.get(DataSetIndex).getEntryIndex(e);
//        if (DataSetIndex==0){
//            index= set.getEntryIndex(e);//根据点击的该条折线的点，获取当前Y轴数据对应的index值，
//        }else {
//            index= set1.getEntryIndex(e);//根据点击的该条折线的点，获取当前Y轴数据对应的index值，
//        }
        //根据index值，分别获取所有当前X轴上对应的两条折线的Y轴的值
        for (int i = 0; i < lineDataSetList.size(); i++){
            entryList.add(lineDataSetList.get(i).getEntryForIndex(index));
        }

        //回调数据
        markerViewListener.markerValue(xAxisValueFormatter.getFormattedValue(e.getX()), entryList, tvContent);

        super.refreshContent(e, highlight);
    }

    @Override
    public MPPointF getOffset() {
        return new MPPointF(-(getWidth() / 2), -getHeight());
    }
}
