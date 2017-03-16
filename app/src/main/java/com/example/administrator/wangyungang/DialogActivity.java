package com.example.administrator.wangyungang;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class DialogActivity extends AppCompatActivity {

    @InjectView(R.id.TextView6)
    TextView TextView6;

    @InjectView(R.id.delete)
    Button delete;
    @InjectView(R.id.exit)
    ImageButton exit;
    @InjectView(R.id.choice)
    Button choice;
    @InjectView(R.id.TextView)
    android.widget.TextView TextView;
    @InjectView(R.id.TextView1)
    android.widget.TextView TextView1;
    @InjectView(R.id.login)
    Button login;
    @InjectView(R.id.date)
    Button date;
    @InjectView(R.id.dateTextView)
    android.widget.TextView dateTextView;
    @InjectView(R.id.time)
    Button time;
    @InjectView(R.id.timeTextView)
    android.widget.TextView timeTextView;
    @InjectView(R.id.progress)
    Button progress;

    private int chNum = 0;
    private static final int maxProgress = 100;
    private boolean chFruit[] = new boolean[]{false, false, false};
    private String fruit[] = new String[]{"苹果", "西瓜", "水蜜桃"};
    private String fruitDesc[] = new String[]{"苹果,植物类水果，具有丰富营养成分",
            "西瓜，属葫芦科，原产于非洲",
            "水蜜桃，属蔷薇科，为落叶小乔木"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        ButterKnife.inject(this);

        Dialog dialog = new AlertDialog.Builder(this)
                .setTitle("库存报警请查看")
                .setMessage("显示提示信息")
                .setIcon(R.drawable.people)
                .create();
        dialog.show();
    }

    @OnClick({R.id.TextView6, R.id.delete, R.id.exit, R.id.choice, R.id.login, R.id.date, R.id.time,R.id.progress})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.TextView6:
                break;
            case R.id.delete:
                Dialog dialog = new AlertDialog.Builder(this)
                        .setIcon(R.drawable.people1)
                        .setTitle("确定删除？")
                        .setMessage("您确定要删除该条信息吗?")
                        .setPositiveButton("删除", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .setNeutralButton("查看详情", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .create();
                dialog.show();
                break;
            case R.id.exit:
                DialogActivity.this.exitDialog();  //退出对话框
                break;
            case R.id.choice:
                fruitDialog();
                break;
            case R.id.login:
                loginDialog();
                break;
            case R.id.date:
                dateDialog();
                break;
            case R.id.time:
                timeDialog();
                break;
            case R.id.progress:
               // progressDialog();
             //   progressDialog2();
                progressDialog3();
                break;
        }
    }

    //按键盘上的返回键弹出退出对话框
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            this.exitDialog();
        }
        return false;
    }

    //退出对话框
    private void exitDialog() {
        Dialog dialog = new AlertDialog.Builder(this)
                .setIcon(R.drawable.people1)
                .setTitle("程序退出")
                .setMessage("您确定要退出本程序吗?")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        DialogActivity.this.finish();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .create();
        dialog.show();
    }

    //水果的对话框
    private void fruitDialog() {
        Dialog dialog = new AlertDialog.Builder(this)
                .setIcon(R.drawable.people1)
                .setTitle("请选择您喜欢的水果")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // TextView.setText("您选择的水果是:"+fruit[chNum]);
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })

                //使用listview的形式
//              //  .setItems(fruit, new DialogInterface.OnClickListener() {
//                .setItems(R.array.city_spinner, new DialogInterface.OnClickListener() { //通过资源文件读取数据
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                       // TextView.setText("您选择的水果是:"+fruit[i]);
//                        TextView.setText("您选择的城市是:"+DialogActivity.this.getResources().getStringArray(R.array.city_spinner)[i]);
//                    }
//                })

//                  //使用单选按钮的形式
//                .setSingleChoiceItems(fruit, 0, new DialogInterface.OnClickListener() {//0第一个选项默认选中
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        TextView1.setText(fruitDesc[i]);
//                        chNum = i;//修改选中项
//
//                    }
//                })

                //使用checkBox的多选形式
                .setMultiChoiceItems(fruit, chFruit, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean isChecked) {
                        for (int j = 0; j < fruit.length; j++) {
                            if (j == i && isChecked) {//被选中
                                TextView.append(fruit[j] + ".");
                            }
                        }
                    }
                })
                .create();
        dialog.show();
    }

    //登录对话框(自己布局的对话框）
    private void loginDialog() {
        LayoutInflater factory = LayoutInflater.from(this);
        View myView = factory.inflate(R.layout.login, null);
        Dialog dialog = new AlertDialog.Builder(this)
                .setIcon(R.drawable.people1)
                .setTitle("用户登录")
                .setView(myView)//使用自定义布局
                .setPositiveButton("登录", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .create();
        dialog.show();
    }


    //日期对话框
    private void dateDialog() {
//        Dialog dialog = new DatePickerDialog(DialogActivity.this, new DatePickerDialog.OnDateSetListener() {
//            @Override
//            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
//                dateTextView.setText("日期:" + year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
//            }
//        }, 2008, 7, 8);
//        dialog.show();
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("").setMessage("")
                .setPositiveButton("确定", null)
                .setNegativeButton("取消", null)
                .create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    //时间对话框
    private void timeDialog() {
        Dialog dialog = new TimePickerDialog(DialogActivity.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                if (minute >= 0 && minute <= 9) {
                    timeTextView.setText("时间为:" + hourOfDay + ":" + "0" + minute);

                } else {
                    timeTextView.setText("时间为:" + hourOfDay + ":" + minute);
                }
            }
        }, 20, 20, true);
        dialog.show();
    }

    //进度对话框
    private void progressDialog() {
        final ProgressDialog progressDialog = ProgressDialog.show(DialogActivity.this,"搜索网络","请耐心等待......");//对话框显示标题，对话框显示文字
        new Thread(){
            public void run(){
                try {
                    Thread.sleep(5000);//3秒
                }catch (Exception e){
                }finally {
                    progressDialog.dismiss();//关闭对话框
                }
            }
        }.start();//线程启动
        progressDialog.show();//显示对话框
    }

    //进度对话框2  环形进度条
    private void progressDialog2() {
        final ProgressDialog progressDialog = new ProgressDialog(DialogActivity.this);
        progressDialog.setTitle("搜索网络");
        progressDialog.setMessage("请耐心等待......");
        progressDialog.onStart();//启动进度条
        new Thread(){
            public void run(){
                try {
                    Thread.sleep(5000);//3秒
                }catch (Exception e){
                }finally {
                    progressDialog.dismiss();//关闭对话框
                }
            }
        }.start();//线程启动
        progressDialog.show();//显示对话框
    }

    //进度对话框3  水平进度条
    private void progressDialog3() {
        final ProgressDialog progressDialog = new ProgressDialog(DialogActivity.this);
        progressDialog.setTitle("搜索网络");
        progressDialog.setMessage("请耐心等待......");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);//水平进度条
        progressDialog.setMax(maxProgress);
        progressDialog.setProgress(0);  //开始点
        progressDialog.setButton("后台处理", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                progressDialog.dismiss();//关闭对话框
            }
        });
        progressDialog.setButton2("详细信息", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        progressDialog.onStart();//启动进度条
        new Thread(){
            public void run(){
                for(int i = 0;i<maxProgress;i ++){
                    try {
                        Thread.sleep(500);//休眠0.5秒
                    }catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    progressDialog.incrementProgressBy(2);//进度条每次增长2
                }
                progressDialog.dismiss();//关闭对话框

            }
        }.start();//线程启动
        progressDialog.show();//显示对话框
    }

    //自定义文字的颜色和大小的Dialog
    private void Dialog() {
        LayoutInflater factory = LayoutInflater.from(this);
        View myView = factory.inflate(R.layout.dialog1, null);
        ContextThemeWrapper cw = new ContextThemeWrapper(this, R.style.AlertDialogTheme);
        AlertDialog.Builder b = new AlertDialog.Builder(cw);
        b.setIcon(R.drawable.aa);
        b.setTitle("报警信息");
        b.setView(myView);//使用自定义布局
        b.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
//                door_spinner.setClickable(true);
//                door_spinner.setEnabled(true);
//                door_spinner.setSelection(0);
            }
        });
        b.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        b.create();
        b.show();




//        LayoutInflater factory = LayoutInflater.from(this);
//        View myView = factory.inflate(R.layout.dialog1, null);
//        ContextThemeWrapper cw = new ContextThemeWrapper(this, R.style.AlertDialogTheme);
//        AlertDialog dialog = new AlertDialog.Builder(cw)
//                .setView(myView)//使用自定义布局
//                .setPositiveButton("确定", null)
//                .setNegativeButton("取消", null)
//                .create();
//        dialog.setCanceledOnTouchOutside(false);
//        dialog.show();

    }
}
