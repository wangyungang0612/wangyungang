package com.example.administrator.wangyungang;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.Gallery;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class GalleryActivity extends Activity {

    @InjectView(R.id.myGallery)
    Gallery myGallery;
    @InjectView(R.id.imageSwitcher)
    ImageSwitcher imageSwitcher;

    private List<Map<String, Integer>> list = new ArrayList<Map<String, Integer>>();
    private SimpleAdapter simpleAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        this.initAdapter();
        ButterKnife.inject(this);
        // myGallery.setAdapter(new GalleryAdapter(this));
        imageSwitcher.setFactory(new ViewFactoryImpl());//设置转换工厂
        myGallery.setAdapter(this.simpleAdapter);
        myGallery.setOnItemClickListener(new OnItemClickListenerImpl());
    }

    @OnClick(R.id.imageSwitcher)
    public void onClick() {
    }

    private class OnItemClickListenerImpl implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Toast.makeText(GalleryActivity.this, String.valueOf(i), Toast.LENGTH_SHORT).show();
            Map<String, Integer> map = (Map<String, Integer>)GalleryActivity.this.simpleAdapter.getItem(i);
            GalleryActivity.this.imageSwitcher.setImageResource(map.get("img"));
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


    private class ViewFactoryImpl implements ViewSwitcher.ViewFactory {
        @Override
        public View makeView() {
            ImageView imageView = new ImageView(GalleryActivity.this);
            imageView.setBackgroundColor(0xFFFFFFFF);
            imageView.setScaleType(ImageView.ScaleType.CENTER);//居中显示
            imageView.setLayoutParams(new ImageSwitcher.LayoutParams(FrameLayout.LayoutParams.FILL_PARENT, FrameLayout.LayoutParams.FILL_PARENT));
            return imageView;
        }
    }
}
