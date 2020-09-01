package computer_room.fragment;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.device_detail_lib.R;
import com.example.mympchart.MarkerViewListener;
import com.example.mympchart.MyLineChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.venmo.view.TooltipView;
import com.yw.game.floatmenu.FloatMenuView;

import java.util.ArrayList;
import java.util.List;

import base.Lain_Base_Activity;
import util.FloatButtonUtil;

/**
 * 电量仪的历史曲线，会有很多条
 */
public class ElectricityHistory extends Lain_Base_Activity implements MarkerViewListener{

    private MyLineChart myLineChart;
    private MyLineChart myLineChart2;

    //记录查看的数据类型
    private String tableType = "电压电流";
    private LineChart lineChart;
    private LineChart lineChart2;

    @Override
    protected String getToolbarTitle() {
        return "";
    }

    @Override
    public int setLayoutView() {
        return R.layout.device_history_activity;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        lineChart2 = findViewById(R.id.chart1_444);
        lineChart = findViewById(R.id.chart1_333);

        //获取电流
        List<String> aCur = getIntent().getStringArrayListExtra("aCur");
        List<String> bCur = getIntent().getStringArrayListExtra("bCur");
        List<String> cCur = getIntent().getStringArrayListExtra("cCur");

        //获取电压
        List<String> aVol = getIntent().getStringArrayListExtra("aVol");
        List<String> bVol = getIntent().getStringArrayListExtra("bVol");
        List<String> cVol = getIntent().getStringArrayListExtra("cVol");

        //功率电量数据
        List<String> totalPower = getIntent().getStringArrayListExtra("totalPower");
        List<String> electricityTime = getIntent().getStringArrayListExtra("electricityTime");
        List<String> electricity = getIntent().getStringArrayListExtra("electricity");

        //获取时间
        List<String> times = getIntent().getStringArrayListExtra("times");

        //调用状态栏透明
        makeStatusBarTransparent(this);

        //电流电压数据集合
        List<ArrayList<Float>> arrayLists = new ArrayList<>();

        //功率电量数据集合
        List<ArrayList<Float>> arrayLists2 = new ArrayList<>();


        //电流
        ArrayList<Float> aCurFloat = new ArrayList<>();
        ArrayList<Float> bCurFloat = new ArrayList<>();
        ArrayList<Float> cCurFloat = new ArrayList<>();

        //电压
        ArrayList<Float> aVolFloat = new ArrayList<>();
        ArrayList<Float> bVolFloat = new ArrayList<>();
        ArrayList<Float> cVolFloat = new ArrayList<>();

        //功率电量数据
        ArrayList<Float> totalPowerData = new ArrayList<>();    //总功率
        ArrayList<Float> electricityTimeData = new ArrayList<>();   //用电时间
        ArrayList<Float> electricityData = new ArrayList<>();       //电量

        //遍历获取所有数据，转成Float
        assert aCur != null;
        for (int i = 0; i < aCur.size(); i++) {

            //电流
            float aCurData = Float.parseFloat(aCur.get(i));
            assert bCur != null;
            float bCurData = Float.parseFloat(bCur.get(i));
            assert cCur != null;
            float cCurData = Float.parseFloat(cCur.get(i));

            //电压
            assert aVol != null;
            float aVolData = Float.parseFloat(aVol.get(i));

            assert bVol != null;
            float bVolData = Float.parseFloat(bVol.get(i));

            assert cVol != null;
            float cVolData = Float.parseFloat(cVol.get(i));

            //功率电量数据
            assert totalPower != null;
            float totalData = Float.parseFloat(totalPower.get(i));
            assert electricityTime != null;
            float eleTimeData = Float.parseFloat(electricityTime.get(i));
            assert electricity != null;
            float eleData = Float.parseFloat(electricity.get(i));

            //保存电流
            aCurFloat.add(aCurData);
            bCurFloat.add(bCurData);
            cCurFloat.add(cCurData);

            //保存电压
            aVolFloat.add(aVolData);
            bVolFloat.add(bVolData);
            cVolFloat.add(cVolData);

            //保存功率电量数据
            totalPowerData.add(totalData);
            electricityTimeData.add(eleTimeData);
            electricityData.add(eleData);

        }

        //保存电流数据
        arrayLists.add(aCurFloat);
        arrayLists.add(bCurFloat);
        arrayLists.add(cCurFloat);

        //保存电压数据
        arrayLists.add(aVolFloat);
        arrayLists.add(bVolFloat);
        arrayLists.add(cVolFloat);

        //保存功率电量数据
        arrayLists2.add(electricityData);
        arrayLists2.add(totalPowerData);
        arrayLists2.add(electricityTimeData);

        //更新电压电流曲线图
        updateCurChart(times, arrayLists);

        //初始化电量仪历史曲线页面的浮动按钮
        FloatButtonUtil.getInstance().ElectricityHistoryFloat(this, this, new FloatMenuView.OnMenuClickListener() {
            @Override
            public void onItemClick(int position, String title) {

                //保存点击的标识
                tableType = title;

                switch (title) {
                    case "电压电流":

                        if (myLineChart != null) {
                            lineChart2.setVisibility(View.VISIBLE);
                            lineChart.setVisibility(View.GONE);
                            return;
                        }

                        //更新电压电流曲线图
                        updateCurChart(times, arrayLists);

                        break;
                    case "功率电量":

                        if (myLineChart2 != null) {
                            lineChart.setVisibility(View.VISIBLE);
                            lineChart2.setVisibility(View.GONE);
                            return;
                        }

                        //更新功率电量曲线图
                        updateVolChart(times, arrayLists2);
                        break;
                }
            }

            @Override
            public void dismiss() {

            }
        });

    }

    private void updateVolChart(List<String> times, List<ArrayList<Float>> arrayLists) {


        lineChart.setVisibility(View.VISIBLE);
        lineChart2.setVisibility(View.GONE);

        List<String> suffix = new ArrayList<>();
        suffix.add("kW");
        suffix.add("kW-h");

        Log.d("ljlkdfer", "onCreate: " + arrayLists.size());
        myLineChart2 = new MyLineChart(times, lineChart, suffix, 100, 0, this, arrayLists, this);

    }

    private void updateCurChart(List<String> times, List<ArrayList<Float>> arrayLists) {

        lineChart2.setVisibility(View.VISIBLE);
        lineChart.setVisibility(View.GONE);

        List<String> suffix = getIntent().getStringArrayListExtra("suffixList");

        Log.d("ljlkdfer", "onCreate: " + arrayLists.size());
        myLineChart = new MyLineChart(times, lineChart2, suffix, 100, 0, this, arrayLists, this);
    }

    @Override
    public void markerValue(String xValue, List<Entry> entryList, TooltipView tvContent) {

        String markShow;

        if (tableType.equals("电压电流")) {

            String aCurMark = entryList.get(0).getY() + "";
            String bCurMark = entryList.get(1).getY() + "";
            String cCurMark = entryList.get(2).getY() + "";

            String aVolMark = entryList.get(3).getY() + "";
            String bVolMark = entryList.get(4).getY() + "";
            String cVolMark = entryList.get(5).getY() + "";

            markShow = "A电流：" + aCurMark + "\nB电流：" + bCurMark + "\nC电流：" + cCurMark + "\nA电压：" + aVolMark + "\nB电压：" + bVolMark + "\nC电压：" + cVolMark + "\n时间：" + xValue + "";

        } else {

            String mark1 = entryList.get(0).getY() + "";
            String mark2 = entryList.get(1).getY() + "";
            String mark3 = entryList.get(2).getY() + "";

            markShow = "电量：" + mark1 + "\n总功率：" + mark2 + "\n用电时间：" + mark3 + "\n时间：" + xValue + "";

        }



        //显示Mark
        tvContent.setText(markShow);

    }

    @Override
    public List<Integer> getLineChartColor() {

        List<Integer> colors = new ArrayList<>();

        Log.d("kljlkdfer", "getLineChartColor: " + tableType);

        if (tableType.equals("电压电流")) {

            //电流
            colors.add(R.drawable.fade_blue);
            colors.add(R.drawable.fade_red);
            colors.add(R.drawable.ele_color_3);

            //电压
            colors.add(R.drawable.ele_color_4);
            colors.add(R.drawable.ele_color_5);
            colors.add(R.drawable.ele_color_6);
        } else {

            //电流
            colors.add(R.drawable.fade_blue);
            colors.add(R.drawable.fade_red);
            colors.add(R.drawable.ele_color_3);

        }


        return colors;
    }

    @Override
    public List<Integer> getLineCircleColor() {
        List<Integer> colors = new ArrayList<>();

        if (tableType.equals("电压电流")) {

            colors.add(Color.parseColor("#FF6100"));
            colors.add(Color.parseColor("#4169E1"));
            colors.add(Color.parseColor("#802A2A"));
            colors.add(Color.parseColor("#B03060"));
            colors.add(Color.parseColor("#FFFF00"));
            colors.add(Color.parseColor("#00FFFF"));


        } else {

            colors.add(Color.parseColor("#4169E1"));
            colors.add(Color.parseColor("#FF6100"));
            colors.add(Color.parseColor("#802A2A"));

        }



        return colors;
    }

    @Override
    public List<String> getLineLegendText() {
        List<String> strList = new ArrayList<>();

        if (tableType.equals("电压电流")) {
            strList.add("A电流");
            strList.add("B电流");
            strList.add("C电流");

            strList.add("A电压");
            strList.add("B电压");
            strList.add("C电压");
        } else {

            strList.add("电量");
            strList.add("总功率");
            strList.add("用电时间");

        }



        return strList;
    }

    /**
     * 将状态栏设置颜色
     *
     * @param activity 需要设置的Activity
     */
    public void makeStatusBarTransparent(Activity activity) {


        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

    }

    @Override
    protected void onPause() {

        FloatButtonUtil.getInstance().hideFloat();

        super.onPause();
    }

    @Override
    protected void onDestroy() {

        //销毁电量仪历史曲线中的浮动按钮
        FloatButtonUtil.getInstance().destroyElectricityFloat();

        super.onDestroy();
    }
}
