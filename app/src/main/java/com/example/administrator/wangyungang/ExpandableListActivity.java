package com.example.administrator.wangyungang;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import Adapter.MyExapableListAdapter;
import butterknife.ButterKnife;
import butterknife.InjectView;

public class ExpandableListActivity extends Activity {

    @InjectView(R.id.eListView)
    ExpandableListView eListView;

    private ExpandableListAdapter adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandable_list);
        ButterKnife.inject(this);

        adapter = new MyExapableListAdapter(this);
        eListView.setAdapter(adapter);

        super.registerForContextMenu(this.eListView);//注册上下文菜单
        eListView.setOnChildClickListener(new OnChildClickListenerImpl());//设置子项单击事件
        eListView.setOnGroupClickListener(new OnGroupClickListenerImpl());//设置组项单击事件
        eListView.setOnGroupCollapseListener(new OnGroupCollapseListenerImpl());//关闭分组事件
        eListView.setOnGroupExpandListener(new OnGroupExpandListenerImpl());//展开分组事件
    }

    private class OnChildClickListenerImpl implements ExpandableListView.OnChildClickListener{

        @Override
        public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
            Toast.makeText(ExpandableListActivity.this,"子选项被选中，i="+i+",i1="+i1,Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private class OnGroupClickListenerImpl implements ExpandableListView.OnGroupClickListener{

        @Override
        public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {
            Toast.makeText(ExpandableListActivity.this,"分组被选中，i="+i,Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private class OnGroupCollapseListenerImpl implements ExpandableListView.OnGroupCollapseListener{

        @Override
        public void onGroupCollapse(int i) {
            Toast.makeText(ExpandableListActivity.this,"关闭分组，i="+i,Toast.LENGTH_SHORT).show();
        }
    }

    private class OnGroupExpandListenerImpl implements ExpandableListView.OnGroupExpandListener{

        @Override
        public void onGroupExpand(int i) {
            Toast.makeText(ExpandableListActivity.this,"展开分组，i="+i,Toast.LENGTH_SHORT).show();
        }
    }

    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu, view,menuInfo);
        ExpandableListView .ExpandableListContextMenuInfo info = (ExpandableListView.ExpandableListContextMenuInfo) menuInfo;
        int type = ExpandableListView.getPackedPositionType(info.packedPosition);
        int group = ExpandableListView.getPackedPositionGroup(info.packedPosition);
        int child = ExpandableListView.getPackedPositionChild(info.packedPosition);

        Toast.makeText(ExpandableListActivity.this,"type="+type+"group="+group+"child="+child,Toast.LENGTH_SHORT).show();

    }

}
