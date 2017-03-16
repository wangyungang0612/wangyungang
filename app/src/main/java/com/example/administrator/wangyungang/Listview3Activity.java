package com.example.administrator.wangyungang;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import Adapter.MyAdapter;
import butterknife.ButterKnife;
import butterknife.InjectView;

public class Listview3Activity extends Activity {

    @InjectView(R.id.tv_show2)
    TextView tvShow;
    @InjectView(R.id.ListView11)
    ListView ListView11;
    @InjectView(R.id.bt_selectall2)
    Button btSelectall;
    @InjectView(R.id.bt_cancel2)
    Button btCancel;
    @InjectView(R.id.bt_deselectall2)
    Button btDeselectall;

    private ArrayList<String> list;
    private MyAdapter mAdapter;
    private int checkNum; // 记录选中的条目数量

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview3);
        ButterKnife.inject(this);
        list = new ArrayList<String>();
        list.add("博士");
        list.add("硕士");
        list.add("学士");
        list.add("高中");
        list.add("初中");
        list.add("小学");
        mAdapter = new MyAdapter(list, this);
        ListView11.setAdapter(mAdapter);

        // 全选按钮的回调接口
        btSelectall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 遍历list的长度，将MyAdapter中的map值全部设为true
                for (int i = 0; i < list.size(); i++) {
                    MyAdapter.getIsSelected().put(i, true);
                }
                // 数量设为list的长度
                checkNum = list.size();
                // 刷新listview和TextView的显示
                dataChanged();
            }
        });

        // 反选按钮的回调接口
        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 遍历list的长度，将已选的设为未选，未选的设为已选
                for (int i = 0; i < list.size(); i++) {
                    if (MyAdapter.getIsSelected().get(i)) {
                        MyAdapter.getIsSelected().put(i, false);
                        checkNum--;
                    } else {
                        MyAdapter.getIsSelected().put(i, true);
                        checkNum++;
                    }
                }
                // 刷新listview和TextView的显示
                dataChanged();
            }
        });

        // 取消按钮的回调接口
        btDeselectall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 遍历list的长度，将已选的按钮设为未选
                for (int i = 0; i < list.size(); i++) {
                    if (MyAdapter.getIsSelected().get(i)) {
                        MyAdapter.getIsSelected().put(i, false);
                        checkNum--;// 数量减1
                    }
                }
                // 刷新listview和TextView的显示
                dataChanged();
            }
        });

        // 绑定listView的监听器
        ListView11.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                // 取得ViewHolder对象，这样就省去了通过层层的findViewById去实例化我们需要的cb实例的步骤
                //ViewHolder holder = (ViewHolder) arg1.getTag();
                TextView   tv = (TextView) arg1.findViewById(R.id.item_tv);
                CheckBox cb = (CheckBox) arg1.findViewById(R.id.item_cb);
                // 改变CheckBox的状态
                cb.toggle();
                // 将CheckBox的选中状况记录下来
                MyAdapter.getIsSelected().put(arg2, cb.isChecked());
                // 调整选定条目
                if (cb.isChecked() == true) {
                    checkNum++;
                } else {
                    checkNum--;
                }
                // 用TextView显示
                tvShow.setText("已选中" + checkNum + "项");
            }
        });
    }

    // 初始化数据
    private void initDate() {
        for (int i = 0; i < 15; i++) {
            list.add("data" + " " + i);
        }
        ;
    }

    // 刷新listview和TextView的显示
    private void dataChanged() {
        // 通知listView刷新
        mAdapter.notifyDataSetChanged();
        // TextView显示最新的选中数目
        tvShow.setText("已选中" + checkNum + "项");
    }
}