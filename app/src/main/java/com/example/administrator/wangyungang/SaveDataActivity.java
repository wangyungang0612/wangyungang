package com.example.administrator.wangyungang;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class SaveDataActivity extends Activity {

    private static final String FILENAME = "mldn22";//文件名称
    @InjectView(R.id.auterinfo)
    TextView auterinfo;
    @InjectView(R.id.ageinfo)
    TextView ageinfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_data);
        ButterKnife.inject(this);

        SharedPreferences shared = super.getSharedPreferences(FILENAME, Activity.MODE_PRIVATE);//指定操作的文件名称
        SharedPreferences.Editor editor = shared.edit();//编辑文件
        editor.putString("autor", "1wangyungang");//保存字符串
        editor.putInt("age", 21);//保存整型
        editor.commit();//提交更新

        SharedPreferences sharedPreferences = super.getSharedPreferences(FILENAME,MODE_PRIVATE);
        auterinfo.setText("作者："+sharedPreferences.getString("autor","hh"));
        ageinfo.setText("年龄："+sharedPreferences.getInt("age",1));

    }
}
