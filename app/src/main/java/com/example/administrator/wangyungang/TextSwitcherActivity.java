package com.example.administrator.wangyungang;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class TextSwitcherActivity extends Activity {

    @InjectView(R.id.textSwitcher)
    TextSwitcher textSwitcher;
    @InjectView(R.id.time0)
    Button time0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_switcher);
        ButterKnife.inject(this);
        textSwitcher.setFactory(new ViewFactoryImpl());//设置转换工厂
        textSwitcher.setInAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_in));//设置动画
        textSwitcher.setOutAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_out));//设置动画
    }

    @OnClick({R.id.textSwitcher, R.id.time0})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.textSwitcher:
                break;
            case R.id.time0:
                TextSwitcherActivity.this.textSwitcher.setText("当前时间为："+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date()));
                break;
        }
    }

    private class ViewFactoryImpl implements ViewSwitcher.ViewFactory {

        @Override
        public View makeView() {
            TextView textView = new TextView(TextSwitcherActivity.this);
            textView.setBackgroundColor(0xFFFFFFFF);
            textView.setTextColor(0xFFFFFFFF);
            textView.setLayoutParams(new TextSwitcher.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT));
          //  textView.setLayoutParams(new TextSwitcher.LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.FILL_PARENT));
            textView.setTextSize(30);
            return textView;
        }
    }
}
