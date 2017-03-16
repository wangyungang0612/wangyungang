package com.example.administrator.wangyungang;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class MainActivity extends Activity {


    @InjectView(R.id.TextView6)
    TextView TextView6;
    @InjectView(R.id.paoma)
    TextView paoma;
    @InjectView(R.id.TextView1)
    TextView TextView1;
    @InjectView(R.id.button1)
    Button button1;
    @InjectView(R.id.button0)
    Button button0;
    @InjectView(R.id.Time)
    Button Time;
    @InjectView(R.id.Draw)
    Button Draw;
    @InjectView(R.id.dialog)
    Button dialog;
    @InjectView(R.id.Auto1)
    Button Auto1;
    @InjectView(R.id.chart)
    Button chart;
    private static final int msgKey1 = 1;
    @InjectView(R.id.Slidong)
    Button Slidong;
    @InjectView(R.id.zoom)
    Button zoom;
    @InjectView(R.id.popup)
    Button popup;
    @InjectView(R.id.expandable)
    Button expandable;
    @InjectView(R.id.SharedPreferences)
    Button SharedPreferences;
    @InjectView(R.id.file)
    Button file;
    @InjectView(R.id.dom)
    Button dom;
    @InjectView(R.id.sax)
    Button sax;
    @InjectView(R.id.listview12)
    Button listview12;
    @InjectView(R.id.SQlite)
    Button SQlite;
    @InjectView(R.id.guanji)
    Button guanji;
    @InjectView(R.id.chongqi)
    Button chongqi;
    @InjectView(R.id.seekBar11)
    SeekBar seekBar11;
    @InjectView(R.id.yinyue)
    Button yinyue;
    private int i = 9;

    private static final String TAG = MainActivity.class.getSimpleName();

    private static final String FILENAME = "mldn";//文件名称

    private Button reboot = null;
    private Button shutdown = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        paoma.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG); //下划线
//        getScreenMode();
//        setScreenMode(1);
//        saveScreenBrightness(200);
//        setScreenBrightness(200);

        //***********************************//
        //下面两个函数可进行系统屏幕亮度调节
        screenBrightness_check();
        setScreenBritness(222);
        //***********************************//
        // setScreenBrightness(1);//计算出当前值
        //new TimeThread().start();
        if (savedInstanceState != null) {
            Log.d(TAG, "onCreate() Restoring previous state");
            /* restore state */
        } else {
            Log.d(TAG, "onCreate() No saved state available");
            /* initialize app */
        }
        timer2.schedule(task, 1000, 1000);

        SharedPreferences shared = super.getSharedPreferences(FILENAME, Activity.MODE_PRIVATE);//指定操作的文件名称
        SharedPreferences.Editor editor = shared.edit();//编辑文件
        editor.putString("autor", "wangyungang");//保存字符串
        editor.putInt("age", 20);//保存整型
        editor.commit();//提交更新

        f1();
        f2();
        // setScreenBrightness(100);//计算出当前值

    }

    private void setScreenBrightness(float num) {
        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();//取得Window属性
        layoutParams.screenBrightness = num;//num已经除以100
        super.getWindow().setAttributes(layoutParams);// 0到1之间
        Log.e("qqq", "111");
    }
//*******************************************************//

    /**
     * 获得当前屏幕亮度的模式
     * SCREEN_BRIGHTNESS_MODE_AUTOMATIC=1 为自动调节屏幕亮度
     * SCREEN_BRIGHTNESS_MODE_MANUAL=0  为手动调节屏幕亮度
     */
    private int getScreenMode() {
        int screenMode = 0;
        try {
            screenMode = Settings.System.getInt(getContentResolver(), Settings.System.SCREEN_BRIGHTNESS_MODE);
        } catch (Exception localException) {

        }
        return screenMode;
    }

    /**
     * 获得当前屏幕亮度值  0-255
     */
    private int getScreenBrightness() {
        int screenBrightness = 255;
        try {
            screenBrightness = Settings.System.getInt(getContentResolver(), Settings.System.SCREEN_BRIGHTNESS);
        } catch (Exception localException) {

        }
        return screenBrightness;
    }

    /**
     * 设置当前屏幕亮度的模式
     * SCREEN_BRIGHTNESS_MODE_AUTOMATIC=1 为自动调节屏幕亮度
     * SCREEN_BRIGHTNESS_MODE_MANUAL=0  为手动调节屏幕亮度
     */
    private void setScreenMode(int paramInt) {
        try {
            Settings.System.putInt(getContentResolver(), Settings.System.SCREEN_BRIGHTNESS_MODE, paramInt);
        } catch (Exception localException) {
            localException.printStackTrace();
        }
    }

    /**
     * 设置系统级屏幕亮度值  0-255
     * <span style="white-space:pre">    </span>   *
     */
    private void saveScreenBrightness(int paramInt) {
        try {
            Settings.System.putInt(getContentResolver(), Settings.System.SCREEN_BRIGHTNESS, paramInt);
        } catch (Exception localException) {
            localException.printStackTrace();
        }
    }

    /**
     * 设置当前的屏幕亮度值，及时生效 0-255
     * <span style="white-space:pre">    </span>   * 该方法仅对当前应用屏幕亮度生效
     */
    private void setScreenBrightness(int paramInt) {
        Window localWindow = getWindow();
        WindowManager.LayoutParams localLayoutParams = localWindow.getAttributes();
        float f = paramInt / 255.0F;
        localLayoutParams.screenBrightness = f;
        localWindow.setAttributes(localLayoutParams);
    }

//****************************************************************//

    private void screenBrightness_check() {
        //先关闭系统的亮度自动调节
        try {
            if (Settings.System.getInt(getContentResolver(), Settings.System.SCREEN_BRIGHTNESS_MODE) == Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC) {
                Settings.System.putInt(getContentResolver(),
                        Settings.System.SCREEN_BRIGHTNESS_MODE,
                        Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL);
            }
        } catch (Settings.SettingNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //获取当前亮度,获取失败则返回255
        int ScreenBrightness = (Settings.System.getInt(getContentResolver(),
                Settings.System.SCREEN_BRIGHTNESS, 255));
        //文本、进度条显示
        seekBar11.setProgress(ScreenBrightness);
        //mTextView_light.setText(""+intScreenBrightness*100/255);
    }

    //屏幕亮度
    private void setScreenBritness(int brightness) {
        //不让屏幕全暗
        if (brightness <= 1) {
            brightness = 1;
        }
        //设置当前activity的屏幕亮度
        WindowManager.LayoutParams lp = this.getWindow().getAttributes();
        //0到1,调整亮度暗到全亮
        lp.screenBrightness = Float.valueOf(brightness / 255f);
        this.getWindow().setAttributes(lp);
        //保存为系统亮度方法1
        Settings.System.putInt(getContentResolver(),
                Settings.System.SCREEN_BRIGHTNESS,
                brightness);
        //保存为系统亮度方法2 //
        //    Uri uri = android.provider.Settings.System.getUriFor("screen_brightness");
        //        android.provider.Settings.System.putInt(getContentResolver(), "screen_brightness", brightness);
        //        // resolver.registerContentObserver(uri, true, myContentObserver);
        //        getContentResolver().notifyChange(uri, null);
    }

    public static void f1() {
        System.out.println("1");
        System.out.println("2");
        System.out.println("3");
    }

    public static void f2() {
        for (char c = 0; c < 128; c++) {
            if (Character.isLowerCase(c)) {
                System.out.println("value" + (int) c + "character" + c);
                Log.e("WWW", "111");
            }
        }
        f1();
    }


    //    public class TimeThread extends Thread {
//                @Override
//              public void run () {                   
//                     do {
//                            try {
//                                      Thread.sleep(1000);
//                                      Message msg = new Message();
//                                        msg.what = msgKey1;
//                                         mHandler.sendMessage(msg);
//                                     }
//                               catch (InterruptedException e) {
//                                       e.printStackTrace();
//                                     }
//                             } while(true);
//                   }
//            }
//
//                private Handler mHandler = new Handler() {
//               @Override
//               public void handleMessage (Message msg) {
//                       super.handleMessage(msg);
//                       switch (msg.what) {
//                              case msgKey1:
//                                  TextView1.setText(""+i);
//                                  i++;
//                                       break;
//                                default:
//                                    break;
//                             }
//                    }
//          };
    Timer timer2 = new Timer();
    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:

                    TextView1.setText("" + i);
                    i++;

                    break;
            }
            super.handleMessage(msg);
        }

    };

    TimerTask task = new TimerTask() {
        public void run() {
            Message message = new Message();
            message.what = 1;
            handler.sendMessage(message);
        }
    };

    @OnClick({R.id.TextView6, R.id.button1, R.id.Time, R.id.button0,
            R.id.Draw, R.id.dialog, R.id.Auto1, R.id.chart, R.id.Slidong, R.id.zoom,
            R.id.popup, R.id.expandable, R.id.SharedPreferences, R.id.file, R.id.dom, R.id.sax, R.id.SQlite,
            R.id.guanji, R.id.chongqi, R.id.yinyue})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.TextView6:
                break;
            case R.id.button1:
                Intent intent = new Intent(this, EditTextActivity.class);
                startActivity(intent);
                break;
            case R.id.Time:
                Intent intent2 = new Intent(this, TimeActivity.class);
                startActivity(intent2);
                break;
            case R.id.button0:
                Toast toast = Toast.makeText(MainActivity.this, "自定义Toast提示框", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 60, 30);//定义对齐方式及位置
                LinearLayout myToastView = (LinearLayout) toast.getView();
                ImageView imageView = new ImageView(MainActivity.this);
                imageView.setImageResource(R.drawable.a);
                myToastView.addView(imageView, 0);
                toast.show();
                break;
            case R.id.Draw:
                Intent intent3 = new Intent(this, DrawActivity.class);
                startActivity(intent3);
            case R.id.dialog:
                Intent intent4 = new Intent(this, DialogActivity.class);
                startActivity(intent4);
            case R.id.Auto1:
                Intent intent5 = new Intent(this, AutoCompleteActivity.class);
                startActivity(intent5);
            case R.id.chart:
                Intent intent6 = new Intent(this, ChartActivity.class);
                startActivity(intent6);
            case R.id.Slidong:
                Intent intent7 = new Intent(this, SlidingDrawerActivity.class);
                startActivity(intent7);
            case R.id.zoom:
                Intent intent8 = new Intent(this, ZoomActivity.class);
                startActivity(intent8);
            case R.id.popup:
                Intent intent9 = new Intent(this, PopupWindowActivity.class);
                startActivity(intent9);
            case R.id.expandable:
                Intent intent10 = new Intent(this, ExpandableListActivity.class);
                startActivity(intent10);
            case R.id.SharedPreferences:
                Intent intent11 = new Intent(this, SaveDataActivity.class);
                startActivity(intent11);
            case R.id.file:
                Intent intent12 = new Intent(this, FileActivity.class);
                startActivity(intent12);
            case R.id.dom:
                Intent intent13 = new Intent(this, DomActivity.class);
                startActivity(intent13);
            case R.id.sax:
                Intent intent14 = new Intent(this, MySAXActivity.class);
                startActivity(intent14);
            case R.id.SQlite:
                Intent intent15 = new Intent(this, MySQliteActivity.class);
                startActivity(intent15);
            case R.id.yinyue:
                Intent intent16 = new Intent(this, MediaPlayerActivity.class);
                startActivity(intent16);
            case R.id.guanji:
//                try {
//                    Process process = Runtime.getRuntime().exec("su");
//                    DataOutputStream out = new DataOutputStream(
//                            process.getOutputStream());
//                    out.writeBytes("reboot -p\n");
//                    out.writeBytes("exit\n");
//                    out.flush();
//                } catch (IOException e) {
//                    new AlertDialog.Builder(MainActivity.this).setTitle("Error").setMessage(
//                            e.getMessage()).setPositiveButton("OK", null).show();
//                }

//                try {
//                    Runtime.getRuntime().exec("su -c \"/system/bin/shutdown\"");   }
//                catch (IOException e) {
//                    e.printStackTrace();
//                }


            case R.id.chongqi:
//                String cmd = "su -c reboot";
//
//                try {
//                    Runtime.getRuntime().exec(cmd);
//                } catch (IOException e) {
//
//                    new AlertDialog.Builder(MainActivity.this).setTitle("Error").setMessage(
//                            e.getMessage()).setPositiveButton("OK", null).show();
//                }

        }
    }
}
