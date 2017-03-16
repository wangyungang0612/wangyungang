package com.example.administrator.wangyungang;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.File;

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

public class DomActivity extends AppCompatActivity {

    @InjectView(R.id.name)
    EditText name;
    @InjectView(R.id.email)
    EditText email;
    @InjectView(R.id.but1)
    Button but1;
    @InjectView(R.id.read)
    Button read;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dom);
        ButterKnife.inject(this);
    }

    @OnClick({R.id.but1, R.id.read})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.but1:
                if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {//如果sdcard不存在
                    return;//返回被带调用处
                }
                File file = new File(Environment.getExternalStorageDirectory().toString() + File.separator + "mldndata" + File.separator + "member.xml");
                if (!file.getParentFile().exists()) {//父文件夹不存在
                    file.getParentFile().mkdirs();//创建文件夹
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
                document = builder.newDocument();//创建一个新的文档
                //4.建立各个操作节点
                Element addresslist = document.createElement("addresslist");
                Element linkman = document.createElement("linkman");
                Element name = document.createElement("name");
                Element email = document.createElement("email");
                //5. 设置节点的文本内容，即为每一个节点添加文本节点
                name.appendChild(document.createTextNode(DomActivity.this.name.getText().toString()));
                email.appendChild(document.createTextNode(DomActivity.this.email.getText().toString()));
                //6. 设置节点关系
                linkman.appendChild(name);//子节点
                linkman.appendChild(email);//子节点
                addresslist.appendChild(linkman);//子节点
                document.appendChild(addresslist);//文档上保存节点
                //7.输出文档到文件中
                TransformerFactory tf = TransformerFactory.newInstance();
                Transformer t = null;
                try {
                    t = tf.newTransformer();
                } catch (TransformerConfigurationException e1) {
                    e1.printStackTrace();
                }
                t.setOutputProperty(OutputKeys.ENCODING, "GBK");//设置编码
                DOMSource source = new DOMSource(document);//输出文档
                StreamResult result = new StreamResult(file);//指定输出位置
                try {
                    t.transform(source, result);//输出
                } catch (TransformerException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.read:
                Intent intent= new Intent(this, Dom2Activity.class);
                startActivity(intent);
                break;
        }
    }
}
