package com.example.administrator.wangyungang;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class SimpleAdapterActivity extends AppCompatActivity {

    @InjectView(R.id.contact_people)
    TextView contactPeople;
    @InjectView(R.id.ListView1)
    ListView ListView1;
    @InjectView(R.id.info)
    TextView info;
    private int[] pic = new int[]{R.drawable.people1, R.drawable.people2, R.drawable.people3,
            R.drawable.people4, R.drawable.people5, R.drawable.people6,};

    private String contact[][] = new String[][]{{"王运刚", "18256068586"}, {"刘云龙", "18759658562"}, {"肖江峰", "18563524586"},
            {"朱    晨", "15456356352"}, {"钟方宝", "18256068586"}, {"周伟杰", "18256068586"},};
    private List<Map<String, String>> list = new ArrayList<Map<String, String>>();
    private SimpleAdapter simpleAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_adapter);
        ButterKnife.inject(this);

        for (int i = 0; i < contact.length; i++) {
            Map<String, String> map = new HashMap<String, String>();
            map.put("pic", String.valueOf(this.pic[i]));
            map.put("name", contact[i][0]);
            map.put("number", contact[i][1]);
            list.add(map);
        }
        simpleAdapter = new SimpleAdapter(this, this.list, R.layout.listview_item, new String[]{"pic", "name", "number"}, new int[]{R.id.people, R.id.name, R.id.number});
        ListView1.setAdapter(simpleAdapter);

        ListView1.setOnItemClickListener(new OnItemClickListenerImpl());
    }

    public class OnItemClickListenerImpl implements AdapterView.OnItemClickListener {


        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
            Map<String, String> map = (Map<String, String>)simpleAdapter.getItem(position);
            String name = map.get("name");
            String number = map.get("number");
            info.setText("姓名"+name+"号码"+number);
        }
    }

}
