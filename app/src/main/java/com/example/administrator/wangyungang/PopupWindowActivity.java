package com.example.administrator.wangyungang;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class PopupWindowActivity extends Activity {

    @InjectView(R.id.statusinfo)
    TextView statusinfo;
    @InjectView(R.id.popbut)
    Button popbut;

    private RadioGroup changeStatus = null;
    private Button cancel = null;
    private PopupWindow popupWindow = null;
    private View popView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_window);
        ButterKnife.inject(this);


    }

    @OnClick(R.id.popbut)
    public void onClick() {
        LayoutInflater inflater = LayoutInflater.from(PopupWindowActivity.this);
        PopupWindowActivity.this.popView = inflater.inflate(R.layout.pop_window,null);//读取布局管理器
        PopupWindowActivity.this.popupWindow = new PopupWindow(popView,400,400,true);//实例化PopupWindow
        changeStatus = (RadioGroup) popView.findViewById(R.id.changestatus);
        cancel = (Button) popView.findViewById(R.id.cancel);

        changeStatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton button = (RadioButton) popView.findViewById(radioGroup.getCheckedRadioButtonId());
                statusinfo.setText("当前用户状态:"+button.getText().toString());
                popupWindow.dismiss();//关闭窗口
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();//关闭窗口
            }
        });

        popupWindow.showAtLocation(this.popbut, Gravity.CENTER,0,0);

    }
}
