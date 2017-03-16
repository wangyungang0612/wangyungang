package com.example.administrator.wangyungang;

import android.app.TabActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.widget.TabHost;

public class TabHostActivity extends TabActivity {
    private TabHost myTabHost;
    private int[] IayRes = {R.id.tabedit,R.id.tabclock,R.id.tabsex};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.myTabHost = super.getTabHost();
        LayoutInflater.from(this).inflate(R.layout.activity_tab_host2,this.myTabHost.getTabContentView(),true);

        for(int x= 0; x < this.IayRes.length; x++){
           TabHost.TabSpec myTab = myTabHost.newTabSpec("tab"+x);
            myTab.setIndicator("标签-"+x);
            myTab.setContent(this.IayRes[x]);
            this.myTabHost.addTab(myTab);
        }
    }
}
