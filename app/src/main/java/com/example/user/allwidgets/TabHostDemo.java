package com.example.user.allwidgets;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
//import android.view.Menu;
//import android.view.MenuItem;
import android.view.View;
import android.widget.TabHost;
import android.widget.Toast;

public class TabHostDemo extends Activity {

    //http://www.viralandroid.com/2015/09/simple-android-tabhost-and-tabwidget-example.html

    TabHost tabhost ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_host_demo);

        tabhost = (TabHost) findViewById(R.id.tabHost);
        tabhost.setup();

        //setting up the tabs
        TabHost.TabSpec spec = null;
        //tab 1
        spec = tabhost.newTabSpec("course1");
        spec.setContent(R.id.tab1);
        spec.setIndicator("CSE110");
        tabhost.addTab(spec);
        //tab 2
        spec = tabhost.newTabSpec("course2");
        spec.setContent(R.id.tab2);
        spec.setIndicator("MAT110");
        tabhost.addTab(spec);
        //tab 3
        spec = tabhost.newTabSpec("course3");
        spec.setContent(R.id.tab3);
        spec.setIndicator("PHY111");
        tabhost.addTab(spec);
        //tab4
        spec = tabhost.newTabSpec("course4");
        spec.setContent(R.id.tab4);
        spec.setIndicator("ENG101");
        tabhost.addTab(spec);
        //listener
        tabhost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String s) {
                View view = tabhost.getCurrentView();
                if(s.equals("course1"))
                    view.setBackgroundColor(Color.GREEN);
                else if(s.equals("course2"))
                    view.setBackgroundColor(Color.YELLOW);
                else if (s.equals("course3"))
                    view.setBackgroundColor(Color.BLUE);
                else if (s.equals("course4"))
                    view.setBackgroundColor(Color.MAGENTA);
                Toast.makeText(TabHostDemo.this, s, Toast.LENGTH_SHORT).show();
            }
        });
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        return super.onCreateOptionsMenu(menu);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        return super.onOptionsItemSelected(item);
//    }
}
