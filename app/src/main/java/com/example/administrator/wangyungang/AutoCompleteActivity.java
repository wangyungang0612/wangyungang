package com.example.administrator.wangyungang;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.TextView;

import Adapter.GalleryAdapter;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class AutoCompleteActivity extends Activity {

    @InjectView(R.id.Auto)
    AutoCompleteTextView Auto;

    private static final String name[] = new String[]{"王运刚", "王运刚好帅", "王运刚好酷", "王运刚很勤奋", "王运刚有个漂亮的女朋友"};
    @InjectView(R.id.seekBar)
    SeekBar seekBar;
    @InjectView(R.id.seekBarTextView)
    TextView seekBarTextView;
    @InjectView(R.id.image)
    ImageView image;
    @InjectView(R.id.seekBar2)
    SeekBar seekBar2;
    @InjectView(R.id.seekBar3)
    SeekBar seekBar3;
    @InjectView(R.id.RatingBar1)
    RatingBar RatingBar1;
    @InjectView(R.id.RatingBar2)
    RatingBar RatingBar2;
    @InjectView(R.id.ratingBarTextView)
    TextView ratingBarTextView;
    @InjectView(R.id.change)
    Button change;
    @InjectView(R.id.Textchange)
    Button Textchange;
    @InjectView(R.id.Tuola)
    Button Tuola;
    @InjectView(R.id.wangge)
    Button wangge;
    @InjectView(R.id.biaoqian)
    Button biaoqian;
    @InjectView(R.id.caidan)
    Button caidan;
    @InjectView(R.id.scaidan)
    Button scaidan;


    private int[] pic = new int[]{R.drawable.people1, R.drawable.people2, R.drawable.people3,
            R.drawable.people4, R.drawable.people5, R.drawable.people6,};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_complete);
        ButterKnife.inject(this);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, name);
        Auto.setAdapter(adapter);
        seekBarTextView.setMovementMethod(ScrollingMovementMethod.getInstance());//滚动文本
        seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListenerImpl());

        seekBar2.setMax(6);
        seekBar2.setOnSeekBarChangeListener(new OnSeekBarChangeListenerImpl2());

        seekBar3.setMax(100);//以后计算的时候除以100   seekBar3是调节屏幕亮度
        seekBar3.setOnSeekBarChangeListener(new OnSeekBarChangeListenerImpl3());

        RatingBar1.setOnRatingBarChangeListener(new OnRatingBarChangeListenerImpl());


    }

    @OnClick({R.id.change, R.id.Textchange, R.id.Tuola, R.id.wangge, R.id.biaoqian,R.id.caidan,R.id.scaidan})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.change:
                Intent intent = new Intent(this, ImageSwitcherActivity.class);
                startActivity(intent);
                break;
            case R.id.Textchange:
                Intent intent2 = new Intent(this, TextSwitcherActivity.class);
                startActivity(intent2);
                break;
            case R.id.Tuola:
                Intent intent3 = new Intent(this, GalleryActivity.class);
                startActivity(intent3);
                break;
            case R.id.wangge:
                Intent intent4 = new Intent(this, GridViewActivity.class);
                startActivity(intent4);
                break;
            case R.id.biaoqian:
                Intent intent5 = new Intent(this, TabHostActivity.class);
                startActivity(intent5);
                break;
            case R.id.caidan:
                Intent intent6 = new Intent(this, OptionsMenuActivity.class);
                startActivity(intent6);
                break;
            case R.id.scaidan:
                Intent intent7 = new Intent(this, ContextMenuActivity.class);
                startActivity(intent7);
                break;
        }
    }


    private class OnSeekBarChangeListenerImpl implements SeekBar.OnSeekBarChangeListener {

        @Override
        public void onProgressChanged(SeekBar seekBar1, int progress, boolean fromUser) {
            AutoCompleteActivity.this.seekBarTextView.append("*** 开始拖动，当前值:" + seekBar1.getProgress() + "\n");

        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar1) {
            seekBarTextView.append("*** 正在拖动，当前值:" + seekBar1.getProgress() + "\n");
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar1) {
            seekBarTextView.append("*** 停止拖动，当前值:" + seekBar1.getProgress() + "\n");
        }
    }

    private class OnSeekBarChangeListenerImpl2 implements SeekBar.OnSeekBarChangeListener {

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            AutoCompleteActivity.this.image.setImageResource(AutoCompleteActivity.this.pic[seekBar2.getProgress()]);
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    }

    private class OnSeekBarChangeListenerImpl3 implements SeekBar.OnSeekBarChangeListener {

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            AutoCompleteActivity.this.setScreenBrightness((float) seekBar.getProgress() / 100);//计算出当前值
        }
    }

    private void setScreenBrightness(float num) {
        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();//取得Window属性
        layoutParams.screenBrightness = num;//num已经除以100
        super.getWindow().setAttributes(layoutParams);// 0到1之间
    }


    private class OnRatingBarChangeListenerImpl implements RatingBar.OnRatingBarChangeListener {

        @Override
        public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
            AutoCompleteActivity.this.ratingBarTextView.append("*** 当前值(Rating):" + ratingBar.getRating() + ",增长步长:" + ratingBar.getStepSize() + "\n");

        }
    }




}