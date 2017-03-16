package com.example.administrator.wangyungang;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import org.xml.sax.SAXException;

import java.io.File;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class MySAXActivity extends Activity {

    @InjectView(R.id.name)
    EditText name;
    @InjectView(R.id.email)
    EditText email;
    @InjectView(R.id.but1)
    Button but1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_sax);
        ButterKnife.inject(this);
    }

    @OnClick(R.id.but1)
    public void onClick() {
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {//如果sdcard不存在
            return;//返回被带调用处
        }
        File file = new File(Environment.getExternalStorageDirectory().toString() + File.separator + "mldndata" + File.separator + "member.xml");
        if (!file.exists()) {//父文件夹不存在
            return;//返回被带调用处
        }
        //1.建立SAX解析工厂
        SAXParserFactory factory = SAXParserFactory.newInstance();
        //2.构建解析器
        SAXParser parser = null;
        MySAX sax = new MySAX();//SAX解析器
        try{
            parser = factory.newSAXParser();//取得SAXParser对象
        }catch (Exception e) {
            e.printStackTrace();
        }
        try{
            parser.parse(file,sax);//解析XML使用DefaultHandler
        }catch (Exception e) {
            e.printStackTrace();
        }
        List<LinkMan> all = sax.getAll();//取得联系人信息
        MySAXActivity.this.name.setText(all.get(0).getName());//设置文本
        MySAXActivity.this.email.setText(all.get(0).getEmail());//设置文本
    }
}
