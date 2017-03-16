package com.example.administrator.wangyungang;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.List;

import Adapter.Spinner4Adapter;
import Adapter.SpinnerAdapter3;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class SpinnerActivity extends AppCompatActivity {

    @InjectView(R.id.spinner1)
    Spinner spinner1;
    @InjectView(R.id.spinner2)
    Spinner spinner2;
    @InjectView(R.id.spinner3)
    Spinner spinner3;
    @InjectView(R.id.spinner4)
    Spinner spinner4;
    @InjectView(R.id.tp1)
    TimePicker tp1;
    @InjectView(R.id.tp2)
    TimePicker tp2;

    private ArrayAdapter<CharSequence> spinner1Adapter;
    private ArrayAdapter<String> spinner2Adapter;
    SpinnerAdapter3 spinner3Adapter;//spinner的Adapter
    Spinner4Adapter spinner4Adapter;//spinner的Adapter
    private List<String> list;
    private List<String> list2;

    private List<String> areaList;//分型线
    private List<String> doornameList;//门体型号
    private List<String> cntList;//库存
    private List<String> lackList;//报警
    private List<String> maxList;//最大

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner);
        ButterKnife.inject(this);
        list = new ArrayList<String>();
        list.add("博士");
        list.add("硕士");
        list.add("学士");
        list.add("高中");


        list2 = new ArrayList<String>();
        list2.add("吃饭");
        list2.add("睡觉");
        list2.add("学习");
        list2.add("打游戏");

        // 使用Adapter 和  string.xml中 color_spinner数组
        spinner1.setPrompt("请选择你喜欢的颜色");
        spinner1Adapter = ArrayAdapter.createFromResource(this, R.array.color_spinner, android.R.layout.simple_spinner_item);
        spinner1Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(spinner1Adapter);
       // spinner1.setPrompt(""); //设置spinner的弹出框模式 要在xml中加android:spinnerMode="dialog"
        spinner1.setSelection(0);//自定义默认项
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        // 自定义  布局文件spinner2_item 和 list
        spinner2Adapter = new ArrayAdapter<String>(SpinnerActivity.this, R.layout.spinner2_item, R.id.spinner_Text, list);
        spinner2.setAdapter(spinner2Adapter);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                TextView text = (TextView) view.findViewById(R.id.spinner_Text);
                spinner2Adapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        // 自定义Adapter 布局文件spinner2_item 和 list2
        spinner3Adapter = new SpinnerAdapter3(list2, SpinnerActivity.this);
        spinner3.setAdapter(spinner3Adapter);

        areaList = new ArrayList<String>();
        doornameList = new ArrayList<String>();
        cntList = new ArrayList<String>();
        lackList = new ArrayList<String>();
        maxList = new ArrayList<String>();

        for (int j = 0; j < 20; j++) {
            areaList.add("1");
            doornameList.add("2");
            cntList.add("8");//库存
            lackList.add("10");//报警
            maxList.add("20");//最大
        }
        // 自定义Adapter 布局文件spinner2_item 和 list2
        spinner4Adapter = new Spinner4Adapter(areaList, doornameList, cntList, lackList, maxList,SpinnerActivity.this);
        spinner4.setAdapter(spinner4Adapter);

        tp2.setIs24HourView(true);


    }
}
