package com.example.administrator.wangyungang;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class ImageSwitcherActivity extends Activity {
    @InjectView(R.id.ImageSwitcher)
    ImageSwitcher imageSwitcher;
    @InjectView(R.id.first)
    Button first;
    @InjectView(R.id.second)
    Button second;

    private int[] imgRes = new int[]{R.drawable.people2,R.drawable.exit,R.drawable.people4,R.drawable.people5,R.drawable.people6};
    private int foot = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_switcher);
        ButterKnife.inject(this);
        imageSwitcher.setFactory(new ViewFactoryImpl());//设置转换工厂
        imageSwitcher.setInAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_in));//设置动画
        imageSwitcher.setOutAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_out));//设置动画
        imageSwitcher.setImageResource(imgRes[foot++]);//设置图片



    }

    @OnClick({R.id.ImageSwitcher, R.id.first, R.id.second})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ImageSwitcher:
                break;
            case R.id.first:
                ImageSwitcherActivity.this.imageSwitcher.setImageResource(imgRes[foot--]);
                ImageSwitcherActivity.this.checkButEnable();
                break;
            case R.id.second:
                ImageSwitcherActivity.this.imageSwitcher.setImageResource(imgRes[foot++]);
                ImageSwitcherActivity.this.checkButEnable();
                break;
        }
    }

    public void checkButEnable(){  //设置按钮状态
        if(this.foot<this.imgRes.length - 1){
            this.first.setEnabled(true);//按钮可用
        }else {
            this.first.setEnabled(false);//按钮不可用
        }
        if(this.foot == 0){
            this.second.setEnabled(false);//按钮不可用
        }else {
            this.second.setEnabled(true);//按钮可用
        }
    }


    private class ViewFactoryImpl implements ViewSwitcher.ViewFactory {

        @Override
        public View makeView() {
            ImageView imageView = new ImageView(ImageSwitcherActivity.this);
            imageView.setBackgroundColor(0xFFFFFFFF);
            imageView.setScaleType(ImageView.ScaleType.CENTER);//居中显示
            imageView.setLayoutParams(new ImageSwitcher.LayoutParams(FrameLayout.LayoutParams.FILL_PARENT, FrameLayout.LayoutParams.FILL_PARENT));
            return imageView;
        }
    }
}
