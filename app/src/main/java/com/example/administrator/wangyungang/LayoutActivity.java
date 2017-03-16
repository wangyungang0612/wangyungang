package com.example.administrator.wangyungang;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class LayoutActivity extends AppCompatActivity {

    @InjectView(R.id.textView01)
    TextView textView01;
    @InjectView(R.id.button01)
    Button button01;
    @InjectView(R.id.textView)
    TextView textView;
    @InjectView(R.id.edit_text)
    EditText editText;
    @InjectView(R.id.checkbox)
    CheckBox checkbox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout);
        ButterKnife.inject(this);

    }

    @OnClick({R.id.textView01, R.id.button01,R.id.checkbox})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.textView01:
                break;
            case R.id.button01:
                //如果是横排,则改为竖排
                if (getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)//横屏 数值0
                {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//竖屏  数值1
                }
                //如果是竖排,则改为横排
                else if (getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                }
                break;
            case R.id.checkbox:
                if(checkbox.isChecked()){
                    editText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else {
                    editText.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
        }
    }
}
