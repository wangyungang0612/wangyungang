package com.example.administrator.wangyungang;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/5 0005.
 */
public class MySAX extends DefaultHandler {//继承DefaultHandler
    private List<LinkMan> all = null;//保存全部元素
    private String elementName = null;//保存元素名称
    private LinkMan man = null;//定义封装对象

    public void startDocument() throws SAXException{//文档开始
        this.all = new ArrayList<LinkMan>();
    }

    public void setElement(String uri, String localName, String name, Attributes attributes) throws SAXException{//元素开始
        if("linkman".equals(localName)){//表示是linkman节点
            this.man = new LinkMan();//实例化LinkMan对象
        }
        this.elementName = localName;//保存元素名称
    }

    public void characters(char[] ch, int start,int length) throws SAXException{//取得元素内容
        if(this.elementName != null){//表示有元素
            String data = new String(ch,start,length);//取得文字信息
            if("name".equals(this.elementName)){//是否是name节点
                this.man.setName(data);//设置name属性
            }else if ("email".equals(this.elementName)){//是否是email节点
                this.man.setEmail(data);//设置email属性
            }
        }
    }

    public void endElement(String uri, String localName, String name) throws SAXException{//元素结束
        if("linkman".equals(localName)){//结尾标记是否是linkman
           this.all.add(this.man);//向集合保存数据
            this.man = null;//清空对象
        }
        this.elementName = null;//清空元素标记
    }

    public List<LinkMan> getAll(){//取得全部集合
        return this.all;
    }
}
