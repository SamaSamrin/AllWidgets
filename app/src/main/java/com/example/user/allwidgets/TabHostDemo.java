package com.example.user.allwidgets;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TabHost;

public class TabHostDemo extends Activity {

    //http://www.viralandroid.com/2015/09/simple-android-tabhost-and-tabwidget-example.html

    TabHost tabhost ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_host_demo);

        tabhost = (TabHost) findViewById(R.id.tabHost);
        tabhost.setup();
    }
}
