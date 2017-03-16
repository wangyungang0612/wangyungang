package com.example.administrator.wangyungang;

import android.app.Activity;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.io.File;

public class MyJSONActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_json);
        String data[] = {"www.mldnjava.cn","wangyungang","bbs.mldn.cn"};//默认的信息
        JSONObject allData = new JSONObject();//先建立最外围的allData对象
        JSONArray sing = new JSONArray();
        for(int x = 0;x < data.length;x++){
            JSONObject temp = new JSONObject();//创建一个新的JSONObject
            try {
                temp.put("myurl",data[x]);//设置要保存的数据
            } catch (JSONException e) {
                e.printStackTrace();
            }
            sing.put(temp);//保存一个信息
        }

        try {
            allData.put("urldata",sing);//保存所有的数据
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {//如果sdcard不存在
            return;//返回被带调用处
        }
        File file = new File(Environment.getExternalStorageDirectory().toString() + File.separator + "mldndata" + File.separator + "member.xml");
        if (!file.exists()) {//父文件夹不存在
            return;//返回被带调用处
        }



    }
}
