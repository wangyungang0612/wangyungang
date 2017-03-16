package com.example.administrator.wangyungang;

import android.app.Activity;
import android.graphics.Color;

import android.os.Bundle;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;



import java.util.ArrayList;
import java.util.List;


public class ChartActivity extends Activity {
    public List<String> allSaoMaTimeList;//所有的扫码时间
    public List<String> allSaoMaTimeList2;//截取整点的扫码时间 //X轴
    public List<String> allSaoMaList;//扫码率
    public List<String> allSaoMaY;//Y轴
    private LineChart mLineChart;
    private List<LineDataSet> lineDataSets;//所有异常原因
//  private Typeface mTf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        mLineChart = (LineChart) findViewById(R.id.chart1);

//      mTf = Typeface.createFromAsset(getAssets(), "OpenSans-Bold.ttf");

        LineData mLineData = getLineData(36, 100);
        showChart(mLineChart, mLineData, Color.rgb(114, 188, 223));

        queryAllSaoMa0();


    }

    //获取扫码成功率的折线图
    public void queryAllSaoMa0() {
        allSaoMaTimeList = new ArrayList<String>();//折线图X轴时间集合
        allSaoMaTimeList2 = new ArrayList<String>();//折线图X轴时间集合筛选整点
        allSaoMaList = new ArrayList<String>();//折线图数据坐标集合
        allSaoMaY = new ArrayList<String>();//折线图Y轴成功率集合
//        for (int i = 0; i < 100; i++) {
//            allSaoMaTimeList.add(i + 1 + "");
//        }
        allSaoMaList.add(100 + "");
        allSaoMaList.add(97 + "");
        allSaoMaList.add(90 + "");
        allSaoMaList.add(96 + "");
        allSaoMaList.add(91 + "");
        allSaoMaList.add(97 + "");
        allSaoMaList.add(90 + "");
        allSaoMaList.add(97 + "");
        allSaoMaList.add(91 + "");
        allSaoMaList.add(98 + "");
        allSaoMaList.add(90 + "");
        allSaoMaList.add(91 + "");
        allSaoMaList.add(97 + "");
        allSaoMaList.add(90 + "");
        allSaoMaList.add(97 + "");
        allSaoMaList.add(91 + "");


        allSaoMaTimeList2.add("7:00");
        allSaoMaTimeList2.add("");
        allSaoMaTimeList2.add("8:00");
        allSaoMaTimeList2.add("");
        allSaoMaTimeList2.add("9:00");
        allSaoMaTimeList2.add("");
        allSaoMaTimeList2.add("10:00");
        allSaoMaTimeList2.add("");
        allSaoMaTimeList2.add("11:00");
        allSaoMaTimeList2.add("");
        allSaoMaTimeList2.add("12:00");
        allSaoMaTimeList2.add("");
        allSaoMaTimeList2.add("13:00");
        allSaoMaTimeList2.add("");
        allSaoMaTimeList2.add("14:00");
        allSaoMaTimeList2.add("");
        allSaoMaTimeList2.add("15:00");
        allSaoMaTimeList2.add("");
        allSaoMaTimeList2.add("16:00");
        allSaoMaTimeList2.add("");
        allSaoMaTimeList2.add("17:00");
        allSaoMaTimeList2.add("");
        allSaoMaTimeList2.add("18:00");
        allSaoMaTimeList2.add("");
        allSaoMaTimeList2.add("19:00");
        allSaoMaTimeList2.add("");
        allSaoMaTimeList2.add("20:00");



//        for (int i = 0; i < allSaoMaTimeList.size(); i = i + 2) {//把时间筛选为整点的集合，不为整点的设为空
//            allSaoMaTimeList2.add(allSaoMaTimeList.get(i));
//            for (int j = 0; j < 1; j++) {
//                allSaoMaTimeList2.add("");
//            }
//        }
        allSaoMaY.add("");
        allSaoMaY.add("");
        allSaoMaY.add("");
        allSaoMaY.add("");
        for (int i = 50; i <= 100; i = i + 10) {//把Y轴设置为整10的集合，其余为空
            allSaoMaY.add(i + "");
            for (int j = 0; j < 4; j++) {
                allSaoMaY.add("");
            }
        }


        ChartView myView1 = (ChartView) this.findViewById(R.id.myView1);//设置折线图
        myView1.SetInfo(allSaoMaTimeList2, // X轴刻度
                allSaoMaY,//Y轴刻度
                allSaoMaList,//数据
                "扫码成功率");//标题
        myView1.invalidate();  //刷新界面

    }


    // 设置显示的样式
    private void showChart(LineChart lineChart, LineData lineData, int color) {
      //  lineChart.setDrawBorders(false);  //是否在折线图上添加边框/************************************/

        // no description text
        lineChart.setDescription("");// 数据描述
        // 如果没有数据的时候，会显示这个，类似listview的emtpyview
        lineChart.setNoDataTextDescription("You need to provide data for the chart.");

        // enable / disable grid background
        lineChart.setDrawGridBackground(false); // 是否显示表格颜色
      //  lineChart.setGridBackgroundColor(Color.WHITE & 0x70FFFFFF); // 表格的的颜色，在这里是是给颜色设置一个透明度/************************/

        // enable touch gestures
        lineChart.setTouchEnabled(true); // 设置是否可以触摸

        // enable scaling and dragging
        lineChart.setDragEnabled(true);// 是否可以拖拽
        lineChart.setScaleEnabled(true);// 是否可以缩放

        // if disabled, scaling can be done on x- and y-axis separately
        lineChart.setPinchZoom(false);//

        lineChart.setBackgroundColor(color);// 设置背景

        // add data
        lineChart.setData(lineData); // 设置数据

        // get the legend (only possible after setting data)
        Legend mLegend = lineChart.getLegend(); // 设置比例图标示，就是那个一组y的value的

        // modify the legend ...
       // mLegend.setPosition(Legend.LegendPosition.LEFT_OF_CHART);
        mLegend.setForm(Legend.LegendForm.CIRCLE);// 样式
        mLegend.setFormSize(6f);// 字体

        mLegend.setTextSize(36f);//标题字体大小
        mLegend.setTextColor(Color.WHITE);// 颜色
      //  mLegend.setTypeface(mTf);// 字体
        lineChart.getAxisRight().setEnabled(false); // 隐藏右边 的坐标轴
        lineChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM); // 让x轴在下面
        lineChart.getXAxis().setGridColor(
                getResources().getColor(R.color.colorAccent));//x轴颜色
        lineChart.getXAxis().setTextSize(15f);//x轴字体大小
        lineChart.getAxisLeft().setTextSize(15f);//Y轴字体大小
        lineChart.animateX(2500); // 立即执行的动画,x轴
    }

    /**
     * 生成一个数据
     * @param count 表示图表中有多少个坐标点
     * @param range 用来生成range以内的随机数
     * @return
     */
    private LineData getLineData(int count, float range) {
        ArrayList<String> xValues = new ArrayList<String>();
        for (int i = 0; i < count; i++) {
            // x轴显示的数据，这里默认使用数字下标显示
            xValues.add("" + i);
        }
        // y轴的数据
        ArrayList<Entry> yValues = new ArrayList<Entry>();
        for (int i = 0; i < count; i++) {
            float value = (float) (Math.random() * range) + 3;
            yValues.add(new Entry(value, i));
        }

        // create a dataset and give it a type
        // y轴的数据集合
        LineDataSet lineDataSet = new LineDataSet(yValues, "测试折线图" /*显示在比例图上*/);
        // mLineDataSet.setFillAlpha(110);
        // mLineDataSet.setFillColor(Color.RED);

        //用y轴的集合来设置参数
        lineDataSet.setLineWidth(1.75f); // 线宽
        lineDataSet.setValueTextSize(13f);
        lineDataSet.setCircleSize(5f);// 显示的圆形大小
        lineDataSet.setColor(Color.WHITE);// 显示颜色
        lineDataSet.setCircleColor(Color.WHITE);// 圆形的颜色
        lineDataSet.setHighLightColor(Color.WHITE); // 高亮的线的颜色

        ArrayList<LineDataSet> lineDataSets = new ArrayList<LineDataSet>();
        lineDataSets.add(lineDataSet); // 添加数据集合

        // create a data object with the datasets
        LineData lineData = new LineData(xValues,lineDataSets);

        return lineData;
    }
}