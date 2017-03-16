package com.example.administrator.wangyungang;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Environment;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class FileActivity extends Activity {

    private static final String FILENAME = "mldn3.txt";
    private static final String FILENAME2 = "/mnt/sdcard/mldndata/mymldn.txt";

    private static final String FILENAME3 = "mymldn2.txt";//设置文件名称
    private static final String DIR = "mldndata";//设置保存文件夹
    @InjectView(R.id.msg)
    TextView msg;
    @InjectView(R.id.msg2)
    TextView msg2;
    @InjectView(R.id.msg3)
    TextView msg3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);
        ButterKnife.inject(this);

        /**************************************************************/
        //文件输出的操作
        FileOutputStream outputStream = null;//文件输出流
        try {
            outputStream = super.openFileOutput(FILENAME, MODE_PRIVATE);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        PrintStream out = new PrintStream(outputStream);
        out.println("姓名：王运刚");
        out.println("年龄：20");
        out.close();

        /**************************************************************/
        //文件读取的操作
        FileInputStream inputStream = null;
        try {//找到指定文件的输入流对象
            inputStream = super.openFileInput(FILENAME);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Scanner scanner = new Scanner(inputStream);
        while (scanner.hasNext()) {//循环读取
            msg.append(scanner.next() + "\n");//设置文本
        }
        scanner.close();

        /**************************************************************/
        //向sdcard上保存文件
        File file = new File(FILENAME2);
        if (!file.getParentFile().exists()) {//父文件夹不存在
            file.getParentFile().mkdirs();//创建文件夹
        }
        PrintStream out2 = null;//打印流对象用于输出
        try {
            out2 = new PrintStream(new FileOutputStream(file));
            out2.println("王运刚 王运刚 王运刚");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (out2 != null) {
                out2.close();
            }
        }

        /**************************************************************/
        //完善向sdcard上保存文件
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {//如果sdcard存在
            File file2 = new File(Environment.getExternalStorageDirectory().toString() + File.separator + DIR + File.separator + FILENAME3);
            if (!file2.getParentFile().exists()) {//父文件夹不存在
                file2.getParentFile().mkdirs();//创建文件夹
            }
            PrintStream out3 = null;//打印流对象用于输出
            try {
                out3 = new PrintStream(new FileOutputStream(file2));
                out3.println("111王运刚 111王运刚 111王运刚");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (out3 != null) {
                    out3.close();
                }
            }
        } else {//如果sdcard不存在
            Toast.makeText(this, "保存失败，sdcard不存在", Toast.LENGTH_SHORT).show();
        }

        /**************************************************************/
        //从sdcard上读取文件
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {//如果sdcard存在
            File file3 = new File(Environment.getExternalStorageDirectory().toString() + File.separator + DIR + File.separator + FILENAME3);
            if (!file3.getParentFile().exists()) {//父文件夹不存在
                file3.getParentFile().mkdirs();//创建文件夹
            }
            Scanner scanner2 = null;//扫描输入
            try {
                scanner2 = new Scanner(new FileInputStream(file3));
                while (scanner2.hasNext()) {//循环读取
                    msg2.append(scanner2.next() + "\n");//设置文本
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (scanner2 != null) {
                    scanner2.close();
                }
            }
        } else {//如果sdcard不存在
            Toast.makeText(this, "保存失败，sdcard不存在", Toast.LENGTH_SHORT).show();
        }
        /**************************************************************/
        //读取资源文件

        Resources resources = super.getResources();//操作资源
        InputStream input = resources.openRawResource(R.raw.mybook);//读取资源ID
        Scanner scan = new Scanner(input);
        StringBuffer buffer = new StringBuffer();//接收数据
        while (scan.hasNext()){
            buffer.append(scan.next()).append("\n");//保存数据
        }
        scan.close();
        try {
            input.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
        msg3.setText(buffer.toString());

    }
}
