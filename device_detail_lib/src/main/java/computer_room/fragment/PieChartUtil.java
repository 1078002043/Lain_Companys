package computer_room.fragment;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.example.device_detail_lib.R;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;

import java.util.ArrayList;

/**
 * @ClassName: PieChartUtil
 * @Description: 饼状图工具类
 * @Author: YIN LUO FEI
 * @Date: 2020/7/10 17:32
 */
public class PieChartUtil implements OnChartValueSelectedListener {

    private PieChart chart;
    protected Typeface tfLight;
    protected Typeface tfRegular;

    public final String[] parties = new String[]{
            "未使用", "已使用"
    };

    public final String[] cpuParties = new String[]{
            "系统使用率", "用户使用率", "总使用率", "当前错误率", "当前等待率", "当前闲置率"
    };

    private final float[] test = new float[]{
            40.6f, 10.8f
    };

    private Context context;

    /**
     * 初始化饼状图
     *
     * @param pieChart    饼状图实例
     * @param context     上下文
     * @param holeEnabled 是否开启饼状图空心
     */
    public PieChartUtil(PieChart pieChart, Context context, boolean holeEnabled, String label, float[] dataSet) {
        initPieChart(pieChart, context, holeEnabled, label, dataSet);
    }

    public void initPieChart(PieChart pieChart, Context context, boolean holeEnabled, String label, float[] dataSet) {
        this.context = context;

        tfLight = Typeface.createFromAsset(context.getAssets(), "OpenSans-Light.ttf");
        tfRegular = Typeface.createFromAsset(context.getAssets(), "OpenSans-Regular.ttf");
        chart = pieChart;
        chart.setUsePercentValues(true);
        chart.getDescription().setEnabled(false);
        chart.setExtraOffsets(5, 10, 5, 5);

        chart.setDragDecelerationFrictionCoef(0.95f);

        chart.setCenterTextTypeface(tfLight);
        //是否开启空心
        chart.setDrawHoleEnabled(holeEnabled);
        chart.setHoleColor(Color.WHITE);

        chart.setTransparentCircleColor(Color.WHITE);
        chart.setTransparentCircleAlpha(110);

        chart.setHoleRadius(58f);
        chart.setTransparentCircleRadius(61f);

        chart.setDrawCenterText(holeEnabled);
        chart.setCenterText(generateCenterSpannableText());

        chart.setRotationAngle(0);
        // enable rotation of the chart by touch
        chart.setRotationEnabled(true);
        chart.setHighlightPerTapEnabled(true);

        // chart.setUnit(" €");
        // chart.setDrawUnitsInChart(true);

        // add a selection listener
        chart.setOnChartValueSelectedListener(this);

        chart.animateY(1400, Easing.EaseInOutQuad);
        // chart.spin(2000, 0, 360);

        Legend l = chart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(0f);
        l.setYOffset(0f);

        // entry label styling
        chart.setEntryLabelColor(Color.WHITE);
        chart.setEntryLabelTypeface(tfRegular);
        chart.setEntryLabelTextSize(12f);

        if (holeEnabled) {
            setData(cpuParties.length, dataSet, label, cpuParties);
        } else {
            setData(parties.length, dataSet, label, parties);
        }

    }

    public void setData(int count, float[] range, String label, String[] parties) {
        ArrayList<PieEntry> entries = new ArrayList<>();

        // NOTE: The order of the entries when being added to the entries array determines their position around the center of
        // the chart.
        for (int i = 0; i < count; i++) {
            //只有有数据的选项才会显示
            if (range[i] == 0.0)
                continue;

            entries.add(new PieEntry(range[i],
                    parties[i % parties.length],
                    context.getResources().getDrawable(R.drawable.star)));
        }

        PieDataSet dataSet = new PieDataSet(entries, label);

        dataSet.setDrawIcons(false);

        dataSet.setSliceSpace(3f);
        dataSet.setIconsOffset(new MPPointF(0, 40));
        dataSet.setSelectionShift(5f);

        // add a lot of colors

        ArrayList<Integer> colors = new ArrayList<>();

//        for (int c : ColorTemplate.VORDIPLOM_COLORS)
//            colors.add(c);
//
//        for (int c : ColorTemplate.JOYFUL_COLORS)
//            colors.add(c);
//
//
//        for (int c : ColorTemplate.LIBERTY_COLORS)
//            colors.add(c);
//
//        for (int c : ColorTemplate.PASTEL_COLORS)
//            colors.add(c);

        colors.add(Color.rgb(80, 151, 255));
        colors.add(Color.rgb(220, 20, 60));
        colors.add(ColorTemplate.getHoloBlue());
        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);

        dataSet.setColors(colors);
        //dataSet.setSelectionShift(0f);

        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter(chart));
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.WHITE);
        data.setValueTypeface(tfLight);
        chart.setData(data);

        // undo all highlights
        chart.highlightValues(null);

        chart.invalidate();
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {

        if (e == null)
            return;
        Log.i("VAL SELECTED",
                "Value: " + e.getY() + ", index: " + h.getX()
                        + ", DataSet index: " + h.getDataSetIndex());
    }

    @Override
    public void onNothingSelected() {
        Log.i("PieChart", "nothing selected");
    }

    private SpannableString generateCenterSpannableText() {

        SpannableString s = new SpannableString("CPU\n使用情况");
        s.setSpan(new RelativeSizeSpan(1.7f), 0, 3, 0);
        return s;
    }

}
