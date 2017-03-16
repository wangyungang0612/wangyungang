package com.example.administrator.wangyungang;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ContextMenuActivity extends Activity {

    private String data[] = {"wang yun gang","nsjnndnnnd","nihaoa","ndsbsjb"};
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.listView = new ListView(this);
        listView.setAdapter(new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,this.data));
        setContentView(this.listView);
        registerForContextMenu(this.listView);
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu,v,menuInfo);
        menu.setHeaderTitle("信息操作");
        menu.add(Menu.NONE,Menu.FIRST+1,1,"删除");
        menu.add(Menu.NONE,Menu.FIRST+2,2,"保存");
        menu.add(Menu.NONE,Menu.FIRST+3,3,"帮助");
        menu.add(Menu.NONE,Menu.FIRST+4,4,"添加");
        menu.add(Menu.NONE,Menu.FIRST+5,5,"详细");


    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {
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
        }
        return false;
    }

    @Override
    public void onContextMenuClosed(Menu menu){
        Toast.makeText(this,"关闭",Toast.LENGTH_SHORT).show();
    }
}
