package com.example.administrator.wangyungang;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SlidingDrawer;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class SlidingDrawerActivity extends Activity {

    @InjectView(R.id.handle)
    ImageView handle;
    @InjectView(R.id.content)
    LinearLayout content;
    @InjectView(R.id.slidingdrawer)
    SlidingDrawer slidingdrawer;
    private String data[] = {"1111111", "2222222", "3333333", "4444444", "5555555"};
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sliding_drawer);
        ButterKnife.inject(this);

        this.listView = new ListView(this);
        listView.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,this.data));
        content.addView(listView);

        this.slidingdrawer.setOnDrawerOpenListener(new OnDrawerOpenListenerImpl());
        this.slidingdrawer.setOnDrawerCloseListener(new OnDrawerCloseListenerImpl());
        this.slidingdrawer.setOnDrawerScrollListener(new OnDrawerScrollListenerImpl());
    }

    private class OnDrawerOpenListenerImpl implements SlidingDrawer.OnDrawerOpenListener{

        @Override
        public void onDrawerOpened() {
            handle.setImageResource(R.drawable.a1);
        }
    }

    private class OnDrawerCloseListenerImpl implements SlidingDrawer.OnDrawerCloseListener{

        @Override
        public void onDrawerClosed() {
            handle.setImageResource(R.drawable.a2);
        }
    }

    private class OnDrawerScrollListenerImpl implements SlidingDrawer.OnDrawerScrollListener{

        @Override
        public void onScrollStarted() {
            Toast.makeText(SlidingDrawerActivity.this,"正在拖动窗口",Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onScrollEnded() {
            Toast.makeText(SlidingDrawerActivity.this,"窗口拖动结束",Toast.LENGTH_SHORT).show();
        }
    }
}
