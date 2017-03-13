package com.example.user.allwidgets;

import android.os.Bundle;
import android.app.Activity;
import android.os.Handler;
import android.widget.ProgressBar;

public class Widgets2 extends Activity {

    ProgressBar progressBar;//mainly used during thread works
    private int progressStatus = 0;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_widgets2);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        //the lengthy work that the progress bar will show progress of :

        //Thread - needs Runnable object as argument and run method
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                while(progressStatus < 100){
                    progressStatus++ ;//the lengthy work that the progress bar will show progress of

                    //updating the progress bar in UI
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setProgress(progressStatus);
                        }
                    });
                }
            }
        });
        t.start();
    }
}
