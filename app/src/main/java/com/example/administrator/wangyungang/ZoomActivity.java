package com.example.administrator.wangyungang;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.ZoomControls;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class ZoomActivity extends Activity {

    @InjectView(R.id.zoomText)
    TextView zoomText;
    @InjectView(R.id.zoomContor)
    ZoomControls zoomContor;

    private int size = 10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoom);
        ButterKnife.inject(this);

        this.zoomContor.setOnZoomInClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ZoomActivity.this.size = size + 3;
                ZoomActivity.this.zoomText.setTextSize(size);
            }
        });

        this.zoomContor.setOnZoomOutClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ZoomActivity.this.size = size  - 3;
                ZoomActivity.this.zoomText.setTextSize(size);
            }
        });
    }
}
