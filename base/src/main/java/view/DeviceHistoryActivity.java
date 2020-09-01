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
 * 历史数据中查看历史曲线的Activity
 */
public class DeviceHistoryActivity extends Lain_Base_Activity implements MarkerViewListener {

    @Override
    protected String getToolbarTitle() {
        return getIntent().getStringExtra("title");
    }

    @Override
    public int setLayoutView() {
        return R.layout.device_history_activity;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);

        //获取温度
        List<String> temps = getIntent().getStringArrayListExtra("temps");
        //获取时间
        List<String> times = getIntent().getStringArrayListExtra("times");
        //获取湿度
        List<String> hums = getIntent().getStringArrayListExtra("hums");

        if (temps == null || times == null || hums == null)
            return;

        //取出数据并添加到容器中
        List<ArrayList<Float>> arrayLists = new ArrayList<>();
        ArrayList<Float> a1 = new ArrayList<>();
        ArrayList<Float> a2 = new ArrayList<>();
        for (int i = 0; i < temps.size(); i++) {
            float val = Float.parseFloat(temps.get(i));
            float val2 = Float.parseFloat(hums.get(i));
            a1.add(val);
            a2.add(val2);
        }
        arrayLists.add(a1);
        arrayLists.add(a2);
        //绑定曲线的控件
        final LineChart lineChart = findViewById(R.id.chart1_333);
        //获取单位
        List<String> suffix = getIntent().getStringArrayListExtra("suffixList");
        //执行初始化 曲线控件
        MyLineChart myLineChart = new MyLineChart(times, lineChart, suffix, 100, 0, this, arrayLists, this);

    }

    /**
     * 取出所点击的节点上相应的数值
     * @param xValue X 轴的数据
     * @param entryList Y 轴的数据（左右两边），根据数量来决定的
     * @param tvContent
     */
    @Override
    public void markerValue(String xValue, List<Entry> entryList, TooltipView tvContent) {

        String tempData = entryList.get(0).getY() + "";
        String humData = entryList.get(1).getY() + "";

        tvContent.setText("时间：" + xValue + "" +
                "\n温度：" + tempData + "\n湿度：" + humData);

    }

    /**
     * 曲线图背景色
     * @return
     */
    @Override
    public List<Integer> getLineChartColor() {

        List<Integer> colors = new ArrayList<>();
        colors.add(R.drawable.fade_blue);
        colors.add(R.drawable.fade_red);

        return colors;
    }

    /**
     * 曲线上圆点的颜色
     * @return
     */
    @Override
    public List<Integer> getLineCircleColor() {
        List<Integer> colors = new ArrayList<>();

        colors.add(Color.parseColor("#FF7256"));
        colors.add(Color.BLUE);

        return colors;
    }

    /**
     * 自定义图例文字
     * @return
     */
    @Override
    public List<String> getLineLegendText() {
        List<String> strList = new ArrayList<>();
        strList.add("温度");
        strList.add("湿度");

        return strList;
    }

}
