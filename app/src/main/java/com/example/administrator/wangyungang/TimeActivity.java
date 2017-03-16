package com.example.administrator.wangyungang;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.TimePicker;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class TimeActivity extends Activity {

    @InjectView(R.id.time1)
    TextView time1;
    @InjectView(R.id.time2)
    TimePicker time2;
    @InjectView(R.id.time3)
    DatePicker time3;
    @InjectView(R.id.textClock)
    TextClock textClock;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time);
        ButterKnife.inject(this);
        time2.setIs24HourView(true);
        time2.setOnTimeChangedListener(new OnTimeChangedListenerImpl());
        time3.init(time3.getYear(), time3.getMonth(), time3.getDayOfMonth(), new OnDateChangedListenerImpl());
        setDateTime();

        // 设置12时制显示格式
        textClock.setFormat12Hour("yyyy-MM-dd  hh:mm ");
        // 设置24时制显示格式
        textClock.setFormat24Hour("yyyy-MM-dd  kk:mm ");
        //注册EventBu
    }

    public void setDateTime() {
        time1.setText(time3.getYear() + "-" + (time3.getMonth() + 1) + "-" + time3.getDayOfMonth() + " " + time2.getCurrentHour() + ":" + time2.getCurrentMinute());
    }

    public class OnDateChangedListenerImpl implements DatePicker.OnDateChangedListener {

        @Override
        public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {
            setDateTime();
        }
    }

    public class OnTimeChangedListenerImpl implements TimePicker.OnTimeChangedListener {

        @Override
        public void onTimeChanged(TimePicker timePicker, int i, int i1) {
            setDateTime();
        }
    }
}
