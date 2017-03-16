package com.example.administrator.wangyungang;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class EditTextActivity extends Activity {

    @InjectView(R.id.button2)
    Button button2;
    @InjectView(R.id.button3)
    Button button3;
    @InjectView(R.id.ListView111)
    Button ListView111;
    @InjectView(R.id.ListViewSimpleAdapter)
    Button ListViewSimpleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_text);
        ButterKnife.inject(this);

    }


    @OnClick({R.id.button2, R.id.button3, R.id.ListView111, R.id.ListViewSimpleAdapter})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button2:
                Intent intent = new Intent(this, SpinnerActivity.class);
                startActivity(intent);
                break;
            case R.id.button3:
                Intent intent2 = new Intent(this, LayoutActivity.class);
                startActivity(intent2);
                break;
            case R.id.ListView111:
                Intent intent5 = new Intent(this, Listview3Activity.class);
                startActivity(intent5);
                break;
            case R.id.ListViewSimpleAdapter:
                Intent intent4 = new Intent(this, SimpleAdapterActivity.class);
                startActivity(intent4);
                break;
        }
    }
}
