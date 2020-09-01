
package com.example.mympchart;

import android.Manifest;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;

import androidx.core.content.ContextCompat;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.Legend.LegendForm;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.LimitLine.LimitLabelPosition;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IFillFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.dataprovider.LineDataProvider;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Example of a heavily customized {@link LineChart} with limit lines, custom line shapes, etc.
 *
 * @version 3.1.0
 * @since 1.7.4
 */
public class MyLineChart extends DemoBase implements
        OnChartValueSelectedListener{

    //曲线实例
    private LineChart chart;
    // X 轴的数据
    private List<String> xLabels;
    // X Y ( 左右边的单位 )
    private List<String> suffixList;
    //最大 Y 值
    private float maximum;
    //最小 Y 值
    private float minimum;

    Context context;

    //count 显示多少个数据，在 X 轴上也会是对应的
    List<ArrayList<Float>> allLineData = new ArrayList<>();

    private MarkerViewListener markerViewListener;

    /**
     *
     *
     *
     */
    /**
     * 曲线图
     * @param xLabels @param xLabels X 轴数据
     * @param lineChart 曲线图实例
     * @param suffixList 后缀，只能有两个，左边，右边
     * @param maximum 最大值
     * @param minimum 最小值
     * @param context 上下文
     * @param allLineData 每条线的数据
     * @param markerViewListener 曲线图上点击数据回调接口
     */
    public MyLineChart(List<String> xLabels, LineChart lineChart, List<String> suffixList, float maximum, float minimum, Context context, List<ArrayList<Float>> allLineData, MarkerViewListener markerViewListener) {
        //更新数据，X 轴由两行来显示
//        for (int index = 0; index < xLabels.size(); index++) {
//
//            String tempLabels = xLabels.get(index).replace(" ", "\n");
//            xLabels.set(index, tempLabels);
//            Log.d("kljd", "MyLineChart: "+xLabels.get(index));
//
//        }

        if (suffixList.size() > 2)
            throw new ArrayIndexOutOfBoundsException("曲线图单位只能有 左(0)，右(1) 两个 Y 轴的单击");

        this.xLabels = xLabels;
        this.suffixList = suffixList;
        this.maximum = maximum;
        this.minimum = minimum;
        this.chart = lineChart;
        this.context = context;
        this.allLineData = allLineData;

        this.markerViewListener = markerViewListener;

        initLineChart();
    }

    /**
     * 初始化 单条 LineChart
     */
    private void initLineChart() {
        {   // // Chart Style // //

            // background color
            chart.setBackgroundColor(Color.WHITE);

            // disable description text
            chart.getDescription().setEnabled(false);

            // enable touch gestures
            chart.setTouchEnabled(true);

            // set listeners
            chart.setOnChartValueSelectedListener(this);
            chart.setDrawGridBackground(false);

            // enable scaling and dragging
            chart.setDragEnabled(true);
            chart.setScaleEnabled(true);
            // chart.setScaleXEnabled(true);
            // chart.setScaleYEnabled(true);

            // force pinch zoom along both axis
            chart.setPinchZoom(false);
        }
//        List<String> labels = new ArrayList<>();

//        for (int i = 0; i < 900; i++){
//            labels.add("2019-08-21 14:15:55");
//        }
        chart.setScaleXEnabled(true);
        //10f 放大是比较合适，每一格 X 轴数据才能显示
        chart.zoom(10f, 0, 0, 0);

        Transformer trans = chart.getTransformer(YAxis.AxisDependency.LEFT);
        //自定义X轴标签位置
        chart.setXAxisRenderer(new CustomXAxisRenderer(chart.getViewPortHandler(),
                chart.getXAxis(), trans));

        XAxis xAxis;
        {   // // X-Axis Style // //
            xAxis = chart.getXAxis();

            //X 轴显示的位置
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

            //设置 X轴 为字符串显示
            xAxis.setValueFormatter(new IndexAxisValueFormatter(xLabels));

            // vertical grid lines
            xAxis.enableGridDashedLine(10f, 10f, 0f);
            xAxis.setLabelRotationAngle(3f);
//            xAxis.setLabelCount(100);
//            xAxis.setAxisMaxLabels(200);
        }
        //左边的 单位
        ValueFormatter leftVFormatter = new LeftLadleFormatter(suffixList.get(0));
        // create marker to display box when values are selected

        //左边 Y轴标题
        YAxis yLAxis;
        {   // // Y-Axis Style // //
            yLAxis = chart.getAxisLeft();
            // horizontal grid lines
            yLAxis.enableGridDashedLine(10f, 10f, 0f);
            yLAxis.setValueFormatter(leftVFormatter);
            // axis range
            yLAxis.setAxisMaximum(maximum);
            yLAxis.setAxisMinimum(minimum);
        }

        if (suffixList.size() > 1){

            //右边单位
            ValueFormatter rightVFormatter = new LeftLadleFormatter(suffixList.get(1));
            //右边 Y轴标题
            YAxis yRAxis;
            {
                // disable dual axis (only use LEFT axis)
                chart.getAxisRight().setEnabled(true);
                yRAxis = chart.getAxisRight();
                yRAxis.setValueFormatter(rightVFormatter);
                // axis range
                yRAxis.setAxisMaximum(maximum);
                yRAxis.setAxisMinimum(minimum);
            }
        } else {
            chart.getAxisRight().setEnabled(false);
        }

        {   // // Create Limit Lines // //
            LimitLine llXAxis = new LimitLine(9f, "Index 10");
            llXAxis.setLineWidth(4f);
            llXAxis.enableDashedLine(10f, 10f, 0f);
            llXAxis.setLabelPosition(LimitLabelPosition.RIGHT_BOTTOM);
            llXAxis.setTextSize(10f);
            llXAxis.setTypeface(tfRegular);
            // limit 设置最大值
            LimitLine ll1 = new LimitLine(50f, "最大值");
            ll1.setLineWidth(4f);
            ll1.enableDashedLine(10f, 10f, 0f);
            ll1.setLabelPosition(LimitLabelPosition.RIGHT_TOP);
            ll1.setTextSize(10f);
            ll1.setTypeface(tfRegular);
            // limit 设置最小值
            LimitLine ll2 = new LimitLine(-30f, "最小值");
            ll2.setLineWidth(4f);
            ll2.enableDashedLine(10f, 10f, 0f);
            ll2.setLabelPosition(LimitLabelPosition.RIGHT_BOTTOM);
            ll2.setTextSize(10f);
            ll2.setTypeface(tfRegular);

            // draw limit lines behind data instead of on top
            yLAxis.setDrawLimitLinesBehindData(true);
            xAxis.setDrawLimitLinesBehindData(true);

            // add limit lines
            yLAxis.addLimitLine(ll1);
            yLAxis.addLimitLine(ll2);
        }


//        ArrayList<Float> floats = new ArrayList<>();
//        ArrayList<Float> floats2 = new ArrayList<>();
//
//        for (int i = 0; i < 100; i++) {
//            float val = (float) (Math.random() * 50) - 30;
//            float val2 = (float) (Math.random() * 80) - 50;
//            floats.add(val);
//            floats2.add(val2);
//        }
//
//        allLineData.add(floats);
//        allLineData.add(floats2);

        //选中值后弹出信息框
        MyMarkerView mv = new MyMarkerView(context, xAxis.getValueFormatter(), chart, markerViewListener);

        // Set the marker to the chart
        mv.setChartView(chart);
        chart.setMarker(mv);
        //设置列表数据
        setData(allLineData);

        // draw points over time
        chart.animateY(2000);

        //图例，表示每个图代表什么
        Legend l = chart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        l.setForm(LegendForm.SQUARE);
        l.setFormSize(9f);
        l.setTextSize(11f);
        l.setXEntrySpace(4f);
        //对图表做 平滑 处理
        List<ILineDataSet> sets = chart.getData()
                .getDataSets();

        for (ILineDataSet iSet : sets) {

            LineDataSet set = (LineDataSet) iSet;
            set.setMode(set.getMode() == LineDataSet.Mode.HORIZONTAL_BEZIER
                    ? LineDataSet.Mode.LINEAR
                    : LineDataSet.Mode.HORIZONTAL_BEZIER);
        }
        //初始化 chart
        chart.invalidate();
    }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        setContentView(R.layout.activity_linechart);
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1);
//        }
//
//        {   // // Chart Style // //
//            chart = findViewById(R.id.chart1);
//
//            // background color
//            chart.setBackgroundColor(Color.WHITE);
//
//            // disable description text
//            chart.getDescription().setEnabled(false);
//
//            // enable touch gestures
//            chart.setTouchEnabled(true);
//
//            // set listeners
//            chart.setOnChartValueSelectedListener(this);
//            chart.setDrawGridBackground(false);
//
//            // enable scaling and dragging
//            chart.setDragEnabled(true);
//            chart.setScaleEnabled(true);
//            // chart.setScaleXEnabled(true);
//            // chart.setScaleYEnabled(true);
//
//            // force pinch zoom along both axis
//            chart.setPinchZoom(false);
//        }
//        List<String> labels = new ArrayList<>();
//
//        for (int i = 0; i < 900; i++){
//            labels.add("2019-08-21 14:15:55");
//        }
//        chart.setScaleXEnabled(true);
//
//        chart.zoom(14f,0,0,0);
//
//        //移动到指定的 X 轴位置，RIGHT OR LEFT
//        chart.moveViewToAnimated(200,0, YAxis.AxisDependency.RIGHT, 50000);
//        XAxis xAxis;
//        {   // // X-Axis Style // //
//            xAxis = chart.getXAxis();
//
//            //X 轴显示的位置
//            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
//            //设置 X轴 为字符串显示
//            xAxis.setValueFormatter(new IndexAxisValueFormatter(labels));
//            // vertical grid lines
//            xAxis.enableGridDashedLine(10f, 10f, 0f);
//            xAxis.setLabelRotationAngle(3f);
////            xAxis.setLabelCount(100);
////            xAxis.setAxisMaxLabels(200);
//
//        }
//
//
//        ValueFormatter leftVFormatter = new LeftLadleFormatter(" ℃ ");
////        LineChartMarkView lineChartMarkView = new LineChartMarkView(this, leftVFormatter);
//        // create marker to display box when values are selected
//
//        //左边 Y轴标题
//        YAxis yLAxis;
//        {   // // Y-Axis Style // //
//            yLAxis = chart.getAxisLeft();
//            // horizontal grid lines
//            yLAxis.enableGridDashedLine(10f, 10f, 0f);
//            yLAxis.setValueFormatter(leftVFormatter);
//            // axis range
//            yLAxis.setAxisMaximum(200f);
//            yLAxis.setAxisMinimum(-50f);
//        }
//
//        ValueFormatter rightVFormatter = new LeftLadleFormatter(" RH ");
//        //右边 Y轴标题
//        YAxis yRAxis;
//        {
//            // disable dual axis (only use LEFT axis)
//            chart.getAxisRight().setEnabled(true);
//            yRAxis = chart.getAxisRight();
//            yRAxis.setValueFormatter(rightVFormatter);
//            // axis range
//            yRAxis.setAxisMaximum(200f);
//            yRAxis.setAxisMinimum(-50f);
//        }
//
//
//        {   // // Create Limit Lines // //
//            LimitLine llXAxis = new LimitLine(9f, "Index 10");
//            llXAxis.setLineWidth(4f);
//            llXAxis.enableDashedLine(10f, 10f, 0f);
//            llXAxis.setLabelPosition(LimitLabelPosition.RIGHT_BOTTOM);
//            llXAxis.setTextSize(10f);
//            llXAxis.setTypeface(tfRegular);
//
//            LimitLine ll1 = new LimitLine(150f, "最大值");
//            ll1.setLineWidth(4f);
//            ll1.enableDashedLine(10f, 10f, 0f);
//            ll1.setLabelPosition(LimitLabelPosition.RIGHT_TOP);
//            ll1.setTextSize(10f);
//            ll1.setTypeface(tfRegular);
//
//            LimitLine ll2 = new LimitLine(-30f, "最小值");
//            ll2.setLineWidth(4f);
//            ll2.enableDashedLine(10f, 10f, 0f);
//            ll2.setLabelPosition(LimitLabelPosition.RIGHT_BOTTOM);
//            ll2.setTextSize(10f);
//            ll2.setTypeface(tfRegular);
//
//            // draw limit lines behind data instead of on top
//            yLAxis.setDrawLimitLinesBehindData(true);
//            xAxis.setDrawLimitLinesBehindData(true);
//
//            // add limit lines
//            yLAxis.addLimitLine(ll1);
//            yLAxis.addLimitLine(ll2);
//        }
//
//        MyMarkerView mv = new MyMarkerView(this, xAxis.getValueFormatter(), chart);
//
//        // Set the marker to the chart
//        mv.setChartView(chart);
//        chart.setMarker(mv);
//
//        //count 显示多少个数据，在 X 轴上也会是对应的
//        setData(100, 180);
//
//        // draw points over time
//        chart.animateY(2000);
//
//
//        //图例，表示每个图代表什么
//        Legend l = chart.getLegend();
//        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
//        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
//        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
//        l.setDrawInside(false);
//        l.setForm(LegendForm.SQUARE);
//        l.setFormSize(9f);
//        l.setTextSize(11f);
//        l.setXEntrySpace(4f);
//
//        List<ILineDataSet> sets = chart.getData()
//                .getDataSets();
//
//        for (ILineDataSet iSet : sets) {
//
//            LineDataSet set = (LineDataSet) iSet;
//            set.setMode(set.getMode() == LineDataSet.Mode.HORIZONTAL_BEZIER
//                    ? LineDataSet.Mode.LINEAR
//                    :  LineDataSet.Mode.HORIZONTAL_BEZIER);
//        }
//        chart.invalidate();
//    }

    private void setData(List<ArrayList<Float>> floatList) {

        ArrayList<Entry> values = new ArrayList<>();
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();;

        LineDataSet set1 = null;

        if (chart.getData() != null &&
                chart.getData().getDataSetCount() > 0) {
            //如果 > 0 则取出所有的 LineDataSet 来更新数据
            for (int i = 0; i < chart.getData().getDataSetCount(); i++) {

                set1 = (LineDataSet) chart.getData().getDataSetByIndex(i);
                set1.setValues(values);
                set1.notifyDataSetChanged();

                chart.getData().notifyDataChanged();
                chart.notifyDataSetChanged();

            }

        } else {

            for (int i = 0; i < floatList.size(); i++) {
                //这里必须是使用 new ArrayList<>()，和 RecyclerView 使用类似，是根据 地址 来保存实例的，如果是 clear()， 就会清除
                //上一条数据
                values = new ArrayList<>();
                ArrayList<Float> entryData = floatList.get(i);

                for (int j = 0; j < entryData.size(); j++) {
                    values.add(new Entry(j, entryData.get(j), context.getResources().getDrawable(R.drawable.star)));
                }


                // create a dataset and give it a type
                set1 = new LineDataSet(values, markerViewListener.getLineLegendText().get(i));

                set1.setDrawIcons(false);

                // draw dashed line
                set1.enableDashedLine(10f, 5f, 0f);

                // black lines and points 线，圆点颜色
                set1.setColor(markerViewListener.getLineCircleColor().get(i)); //图例的颜色也是由它来决定
                set1.setCircleColor(Color.BLACK);

                // line thickness and point size
                set1.setLineWidth(1f);
                set1.setCircleRadius(3f);

                // draw points as solid circles
                set1.setDrawCircleHole(false);

                // customize legend entry
                set1.setFormLineWidth(1f);
                set1.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));
                set1.setFormSize(15.f);

                // text size of values
                set1.setValueTextSize(9f);

                // draw selection line as dashed
                set1.enableDashedHighlightLine(10f, 5f, 0f);

                // set the filled area
                set1.setDrawFilled(true);
                set1.setFillFormatter(new IFillFormatter() {
                    @Override
                    public float getFillLinePosition(ILineDataSet dataSet, LineDataProvider dataProvider) {
                        return chart.getAxisLeft().getAxisMinimum();
                    }
                });

                // set color of filled area
                if (Utils.getSDKInt() >= 18) {
                    // drawables only supported on api level 18 and above
                    Drawable drawable = ContextCompat.getDrawable(context, markerViewListener.getLineChartColor().get(i));
                    set1.setFillDrawable(drawable);
                } else {
                    set1.setFillColor(Color.BLACK);
                }

                //在这里添加多条报表
                dataSets.add(set1); // add the data sets

            }

            // create a data object with the data sets
            LineData data = new LineData(dataSets);

            // set data
            chart.setData(data);

        }
    }

    /**
     * 保存图片
     */
    @Override
    public void saveToGallery() {
        saveToGallery(chart, "LineChartActivity2", context);
    }

    /**
     * 点击值时的监听
     *
     * @param e The selected Entry
     * @param h The corresponding highlight object that contains information
     */
    @Override
    public void onValueSelected(Entry e, Highlight h) {

        Log.i("Entry selected", e.toString());
        Log.i("LOW HIGH", "low: " + chart.getLowestVisibleX() + ", high: " + chart.getHighestVisibleX());
        Log.i("MIN MAX", "xMin: " + chart.getXChartMin() + ", xMax: " + chart.getXChartMax() + ", yMin: " + chart.getYChartMin() + ", yMax: " + chart.getYChartMax());
    }

    /**
     * 没有选中时的监听
     */
    @Override
    public void onNothingSelected() {
        Log.i("Nothing selected", "Nothing selected.");
    }

    /**
     * 移动 曲线图 到指定的位置，这个方法主要是配合滚动截图，目前只能是 向右滑动
     *
     * @param xValue   移动到 X 轴的位置
     * @param yValue   移动到 Y 轴的位置
     * @param duration 移动的时间，速度也是由时间来控制
     */
    public void moveToView(float xValue, float yValue, long duration) {
        //移动到指定的 X 轴位置，RIGHT OR LEFT
        chart.moveViewToAnimated(xValue, yValue, YAxis.AxisDependency.RIGHT, duration);
    }



}
