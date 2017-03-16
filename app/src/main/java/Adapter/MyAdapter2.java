package Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.wangyungang.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static android.support.v7.widget.RecyclerView.Adapter;

/**
 * Created by Administrator on 2016/8/5 0005.
 */
public class MyAdapter2 extends BaseAdapter {
    private LayoutInflater inflater;
    private ArrayList<String> items;
    private Bitmap icon;
    private Context context;
    public Map<Integer,Boolean> checkedMap;   //保存checkbox是否被选中的状态
    public Map<Integer,Integer> colorMap;     //保存textview中文字的状态
    public Map<Integer,Integer> visibleMap;   //保存checkbox是否显示的状态

    public MyAdapter2(ArrayList<String> items, Context context) {
        super();
        this.items = items;
        this.context = context;
        inflater = LayoutInflater.from(context);
        icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.exit);
        checkedMap = new HashMap<Integer, Boolean>();
        colorMap = new HashMap<Integer, Integer>();
        visibleMap = new HashMap<Integer, Integer>();
        for(int i=0;i<items.size();i++){
            checkedMap.put(i, false);
            colorMap.put(i, Color.WHITE);
            visibleMap.put(i, CheckBox.INVISIBLE);
        }
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return items.size();
    }

    @Override
    public Object getItem(int arg0) {
        // TODO Auto-generated method stub
        return arg0;
    }

    @Override
    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return arg0;
    }

    @Override
    public View getView(int position, View view, ViewGroup arg2) {
        view = inflater.inflate(R.layout.listview_item11, null);
        ImageView image = (ImageView) view.findViewById(R.id.icon);
        TextView text = (TextView) view.findViewById(R.id.text);
        CheckBox checkBox = (CheckBox) view.findViewById(R.id.checkbox);
        checkBox.setVisibility(visibleMap.get(position));
        checkBox.setChecked(checkedMap.get(position));
        image.setImageBitmap(icon);
        text.setText(items.get(position));
        text.setTextColor(colorMap.get(position));
        return view;
    }
}
