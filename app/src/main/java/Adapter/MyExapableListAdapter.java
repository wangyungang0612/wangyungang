package Adapter;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/10/21 0021.
 */
public class MyExapableListAdapter extends BaseExpandableListAdapter {

    public String[] groups = {"我的好友","家人","同事","黑名单"};
    public String[][] children ={{"李明","王浩","长峰","小凯"},{"父亲","母亲","哥哥","姐姐"},
                                   {"小李","小王","小菊","小红"},{"傻逼","二号","二逼"}};
    private Context context = null;

    public MyExapableListAdapter(Context context){
        this.context = context;
    }
   //  1
    @Override
    public Object getChild(int i, int i1) {
        return this.children[i][i1];
    }
    //  2
    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }
    //  3
    @Override
    public int getChildrenCount(int i) {
        return this.children[i].length;
    }
    //  4
    @Override
    public Object getGroup(int i) {
        return this.groups[i];
    }
    //  5
    @Override
    public int getGroupCount() {
        return this.groups.length;
    }
    //  6
    @Override
    public long getGroupId(int i) {
        return i;
    }
    //  7
    @Override
    public boolean hasStableIds() {
        return true;
    }
    //  8
    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }


    public TextView buildTextView(){
        AbsListView.LayoutParams params = new AbsListView.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,105);
        TextView textView = new TextView(this.context);
        textView.setLayoutParams(params);
        textView.setTextSize(20.0f);
        textView.setGravity(Gravity.LEFT);
        textView.setPadding(40,15,8,8);
        return textView;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        TextView textView  = buildTextView();
        textView.setText(this.getGroup(i).toString());//设置文字
        return textView;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        TextView textView  = buildTextView();
        textView.setText(getChild(i,i1).toString());//设置文字
        return textView;
    }


}
