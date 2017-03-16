package com.example.administrator.wangyungang;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class OptionsMenuActivity extends Activity {

    @InjectView(R.id.text00)
    TextView text00;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options_menu);
        ButterKnife.inject(this);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        menu.add(Menu.NONE,Menu.FIRST+1,5,"删除").setIcon(R.drawable.a5);
        menu.add(Menu.NONE,Menu.FIRST+2,2,"保存").setIcon(R.drawable.a3);
        menu.add(Menu.NONE,Menu.FIRST+3,6,"帮助").setIcon(R.drawable.a6);
        menu.add(Menu.NONE,Menu.FIRST+4,1,"添加").setIcon(R.drawable.a1);
        menu.add(Menu.NONE,Menu.FIRST+5,4,"详细").setIcon(R.drawable.a4);
        menu.add(Menu.NONE,Menu.FIRST+6,7,"发送").setIcon(R.drawable.a5);
        menu.add(Menu.NONE,Menu.FIRST+7,3,"编辑").setIcon(R.drawable.a2);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case Menu.FIRST+1:
                Toast.makeText(this,"您选的是删除",Toast.LENGTH_SHORT).show();
                break;
            case Menu.FIRST+2:
                Toast.makeText(this,"您选的是保存",Toast.LENGTH_SHORT).show();
                break;
            case Menu.FIRST+3:
                Toast.makeText(this,"您选的是帮助",Toast.LENGTH_SHORT).show();
                break;
            case Menu.FIRST+4:
                Toast.makeText(this,"您选的是添加",Toast.LENGTH_SHORT).show();
                break;
            case Menu.FIRST+5:
                Toast.makeText(this,"您选的是发送",Toast.LENGTH_SHORT).show();
                break;
            case Menu.FIRST+6:
                Toast.makeText(this,"您选的是详细",Toast.LENGTH_SHORT).show();
                break;
            case Menu.FIRST+7:
                Toast.makeText(this,"您选的是编辑",Toast.LENGTH_SHORT).show();
                break;
        }
        return false;
    }
    @Override
    public void onOptionsMenuClosed(Menu menu){
        Toast.makeText(this,"关闭",Toast.LENGTH_SHORT).show();
    }
    @Override
    public boolean onPrepareOptionsMenu(Menu menu){
        Toast.makeText(this,"预处理",Toast.LENGTH_SHORT).show();
        return true;
    }



}
