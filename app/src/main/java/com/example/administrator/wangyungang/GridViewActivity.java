package com.example.administrator.wangyungang;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class GridViewActivity extends AppCompatActivity {

    @InjectView(R.id.myGridView)
    GridView myGridView;

    private List<Map<String, Integer>> list = new ArrayList<Map<String, Integer>>();
    private SimpleAdapter simpleAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view);
        ButterKnife.inject(this);
        this.initAdapter();
        myGridView.setAdapter(this.simpleAdapter);
        myGridView.setOnItemClickListener(new OnItemClickListenerImpl());

    }

    private class OnItemClickListenerImpl implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            ImageView showImg = new ImageView(GridViewActivity.this);//定义图片组件
            showImg.setScaleType(ImageView.ScaleType.CENTER);//居中显示
            showImg.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            Map<String, Integer> map = (Map<String, Integer>) GridViewActivity.this.simpleAdapter.getItem(i);
            showImg.setImageResource(map.get("img"));

            Dialog dialog = new AlertDialog.Builder(GridViewActivity.this)
                    .setIcon(R.drawable.people1)
                    .setTitle("查看图片")
                    .setView(showImg)//使用自定义布局
                    .setNegativeButton("关闭", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    })
                    .create();
            dialog.show();
        }
    }

    public void initAdapter() {
        Field[] fields = R.drawable.class.getDeclaredFields();
        for (int x = 0; x < fields.length; x++) {
            if (fields[x].getName().startsWith("people")) {
                Map<String, Integer> map = new HashMap<String, Integer>();
                try {
                    map.put("img", fields[x].getInt(R.drawable.class));
                } catch (Exception e) {
                }
                this.list.add(map);
            }
        }

        this.simpleAdapter = new SimpleAdapter(this, this.list, R.layout.gallery_item, new String[]{"img"}, new int[]{R.id.img});
    }
}

