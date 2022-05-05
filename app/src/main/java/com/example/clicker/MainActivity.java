package com.example.clicker;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView counter, time;
    ImageButton settings;
    Button click, startBtn;
    Handler customHandler = new Handler();

    int c = 0;
    long startTime = 0L, timeMilliseconds = 0L, timeSwapBuff = 0L, updateTime = 0L;

    Runnable UpdateTimeThread = new Runnable() {
        @Override
        public void run() {
            timeMilliseconds = SystemClock.uptimeMillis() - startTime;
            updateTime = timeSwapBuff+timeMilliseconds;
            int secs = (int) (updateTime/1000);
            secs%=60;
            int milliseconds = (int) (updateTime%1000);
            time.setText("Time : " + String.format("%2d",secs) + ":" + String.format("%3d",milliseconds) + " c");
            customHandler.postDelayed(this,0);
        }
    };

    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        time = findViewById(R.id.time);
        counter = findViewById(R.id.counter);
        settings = findViewById(R.id.settings);
        click = findViewById(R.id.click);
        startBtn = findViewById(R.id.start);
        final long[] start = new long[1];
        startBtn.setEnabled(true);
        click.setEnabled(false);

        startBtn.setOnClickListener(view -> {
            startBtn.setEnabled(false);
            click.setEnabled(true);
            time.setText("Time : 0 c");

        });
        click.setOnClickListener(view -> {
            if(c == 1){
                start[0] = System.currentTimeMillis();

                startTime = SystemClock.uptimeMillis();

                customHandler.postDelayed(UpdateTimeThread,0);

            }

            if(c == 40){
                timeSwapBuff=0;
                customHandler.removeCallbacks(UpdateTimeThread);
                c=0;
                start[0] = System.currentTimeMillis();
                startBtn.setEnabled(true);
                click.setEnabled(false);
            }
            counter.setText("Click : "+ c);
            c++;
        });
        settings.setOnClickListener(view -> {
            Intent intent;
            intent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(intent);
        });

        }
}