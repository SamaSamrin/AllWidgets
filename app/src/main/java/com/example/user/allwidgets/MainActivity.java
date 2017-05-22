package com.example.user.allwidgets;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends Activity {

    private static final String TAG = "*MainActivity*";
    Spinner spinner ;
    Spinner spinner_months;
    ProgressBar circularProgressBar;//mainly used during thread works
    private int circularProgressStatus = 0;
    private Handler handler = new Handler();
    /*
    * Handlers are the best way of communication between the background and UI thread. Generally Handlers are associated with message Queue of a Thread and they are used to
    * send messages and runnable to the Message.
    USE:
    Thread: To do tasks in separate(Background) thread than UI thread. (helps to unblock the UI thread)
    Handler Used to communicate between the UI and Background thread.
    */
    private ProgressBar horizontalProgressBar ;
    private int horizontalProgressStatus = 0;
    private SeekBar seekbar;
    private TextView seekbarStatusDisplay;
    int progressThis = 0;
    private SeekBar discreteSeekbar;
    private TextView discreteSeekbarStatusDisplay;
    private RatingBar ratingBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.spinner_options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner_months = (Spinner) findViewById(R.id.spinner_month);
        ArrayAdapter<CharSequence> monthAdapter = ArrayAdapter.createFromResource(this, R.array.spinner_months, android.R.layout.simple_spinner_item);
        monthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_months.setAdapter(monthAdapter);

        circularProgressBar = (ProgressBar) findViewById(R.id.circularProgressBar);
        horizontalProgressBar = (ProgressBar) findViewById(R.id.horizontalProgressBar) ;
        handleProgressBar();

        seekbar = (SeekBar) findViewById(R.id.seekBar);
        handleSeekbar();
        seekbarStatusDisplay = (TextView) findViewById(R.id.seekbarStatusText);
        seekbarStatusDisplay.setText("SeekBar status: Max="+seekbar.getMax());

        discreteSeekbar = (SeekBar) findViewById(R.id.discreteSeekBar);
        discreteSeekbarStatusDisplay = (TextView) findViewById(R.id.discreteSeekbarStatus);
        discreteSeekbarStatusDisplay.setText("Discrete SeekBar status: Max="+discreteSeekbar.getMax());
        handleDiscreteSeekbar();

        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        handleRatingBar();
    }

    private void handleProgressBar(){
        //Thread for circular progress bar
        Thread circularThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while(circularProgressStatus <= 500){
                    //Log.e(TAG, String.valueOf(circularProgressStatus));
                    circularProgressStatus++ ;//the lengthy work that the progress bar will show progress of

                    //updating the progress bar in UI
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            circularProgressBar.setProgress(circularProgressStatus);
                            if (circularProgressStatus == 500)
                                circularProgressBar.setVisibility(View.GONE);//keeps spinning even after reaching 500
                        }
                    });
                }
            }
        });
        circularThread.start();

        //Thread for horizontal progress bar
        final Thread horizontalThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (horizontalProgressStatus <= 1000){
                    horizontalProgressStatus++;
                    //Log.e(TAG, String.valueOf(horizontalProgressStatus));
                    horizontalProgressBar.setProgress(horizontalProgressStatus);//shows filled up progress bar from the beginning
                }
            }
        });
        horizontalThread.start();
    }

    /*  a SeekBar is a ProgressBar element’s extension that allows the selection of integer values using a natural user interface. SeekBar has a thumb that can be
        slided in order to choose a value between 0 and some maximum that can be set from the developer.
    */
    private void handleSeekbar(){
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressThis = progress;
                //Log.e(TAG, String.valueOf(progressThis));
                seekbarStatusDisplay.setText("SeekBar status: Changed, Current Progress="+progressThis);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                seekbarStatusDisplay.setText("SeekBar status: Started,  Current Progress="+progressThis);
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                seekbarStatusDisplay.setText("SeekBar status: Stopped,  Current Progress="+progressThis);
            }
        });
    }

    private void handleDiscreteSeekbar(){
        discreteSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressThis = progress;
                //Log.e(TAG, String.valueOf(progressThis));
                discreteSeekbarStatusDisplay.setText("Discrete SeekBar status: "+progressThis);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                discreteSeekbarStatusDisplay.setText("Discrete SeekBar status: "+progressThis);
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                discreteSeekbarStatusDisplay.setText("Discrete SeekBar status: "+progressThis);
            }
        });
    }

    private void handleRatingBar(){
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {

            }
        });
    }

    public void goToNext(View view){
        Intent i = new Intent(MainActivity.this, GridViewDemo.class);
        startActivity(i);
    }



}
