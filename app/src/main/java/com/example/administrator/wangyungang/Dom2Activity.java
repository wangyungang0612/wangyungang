package com.example.administrator.wangyungang;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class Dom2Activity extends AppCompatActivity {

    @InjectView(R.id.name)
    EditText name;
    @InjectView(R.id.email)
    EditText email;
    @InjectView(R.id.but1)
    Button but1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dom2);
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
        //1.建立 DocumentBuilderFactory，以用于取得DocumentBuilder
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        //2.通过 DocumentBuilderFactory 取得 DocumentBuilder
        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        //3. 定义Document接口对象，通过DocumentBuilder类进行DOM树的转换操作
        Document document = null;
       try {
           document = builder.parse(file);//读取指定路径的XML文件
       } catch (SAXException e) {
           e.printStackTrace();
       } catch (IOException e) {
           e.printStackTrace();
       }
        //4.查找linkman的节点
        NodeList nodeList = document.getElementsByTagName("linkman");
       //5.输出NodeList中第一个子节点中文本节点的内容
        for(int x = 0;x < nodeList.getLength();x++){//循环输出节点内容
            Element element = (Element) nodeList.item(x);//取得每一个元素
            Dom2Activity.this.name.setText(element.getElementsByTagName("name").item(0).getFirstChild().getNodeValue());//设置文本
            Dom2Activity.this.email.setText(element.getElementsByTagName("email").item(0).getFirstChild().getNodeValue());//设置文本

        }
    }
}
