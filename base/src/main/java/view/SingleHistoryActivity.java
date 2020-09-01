package view;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.view.WindowManager;

import androidx.annotation.Nullable;

import com.example.base.R;

import com.example.mympchart.MarkerViewListener;
import com.example.mympchart.MyLineChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.venmo.view.TooltipView;

import java.util.ArrayList;
import java.util.List;

import base.Lain_Base_Activity;

/**
 * 单条曲线
 */
public class SingleHistoryActivity extends Lain_Base_Activity implements MarkerViewListener {

    private String markUnit = "";
    private String linesText = "";

    @Override
    protected String getToolbarTitle() {
        return "";
    }

    @Override
    public int setLayoutView() {
        return R.layout.activity_linechart;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);

        //获取温度
        List<String> values = getIntent().getStringArrayListExtra("temps");
        List<String> times = getIntent().getStringArrayListExtra("times");
        markUnit = getIntent().getStringExtra("markUnit") == null ? "" : getIntent().getStringExtra("markUnit");
        linesText = getIntent().getStringExtra("linesText") == null ? "" : getIntent().getStringExtra("linesText");

        List<ArrayList<Float>> arrayLists = new ArrayList<>();
        ArrayList<Float> a1 = new ArrayList<>();
        for (int i = 0; i < values.size(); i++) {
            float val = Float.parseFloat(values.get(i));
            a1.add(val);
        }

        arrayLists.add(a1);
        final LineChart lineChart = findViewById(R.id.chart_single);
        List<String> suffix = getIntent().getStringArrayListExtra("suffixList");
        MyLineChart myLineChart = new MyLineChart(times, lineChart, suffix, 100, 0, this, arrayLists, this);
    }

    @Override
    public void markerValue(String xValue, List<Entry> entryList, TooltipView tvContent) {
        String tempData = entryList.get(0).getY() + "";

        String tvText = "";
        if (markUnit.equals(""))
            tvText = "时间：" + xValue + "" + "\n浓度：" + tempData;
        else
            tvText = "时间：" + xValue + "" + "\n" + markUnit + "：" + tempData;


        tvContent.setText(tvText);
    }

    @Override
    public List<Integer> getLineChartColor() {

        List<Integer> colors = new ArrayList<>();
        colors.add(R.drawable.fade_blue);

        return colors;
    }

    @Override
    public List<Integer> getLineCircleColor() {
        List<Integer> colors = new ArrayList<>();

        colors.add(Color.parseColor("#FF7256"));

        return colors;
    }

    @Override
    public List<String> getLineLegendText() {
        List<String> strList = new ArrayList<>();
        if (linesText.equals(""))
            strList.add("浓度");
        else
            strList.add(linesText);

        return strList;
    }
}
