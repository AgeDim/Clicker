package com.example.clicker;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.Serializable;
import java.util.Locale;

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
            updateTime = timeSwapBuff + timeMilliseconds;
            int secs = (int) (updateTime / 1000);
            secs %= 60;
            int milliseconds = (int) (updateTime % 1000);
            time.setText("Time : " + String.format("%2d", secs) + ":" + String.format("%3d", milliseconds) + " c");
            customHandler.postDelayed(this, 0);
        }
    };

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
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
            if (c == 1) {
                start[0] = System.currentTimeMillis();

                startTime = SystemClock.uptimeMillis();

                customHandler.postDelayed(UpdateTimeThread, 0);

            }

            if (c == 40) {
                timeSwapBuff = 0;
                customHandler.removeCallbacks(UpdateTimeThread);
                c = 0;
                start[0] = System.currentTimeMillis();
                startBtn.setEnabled(true);
                click.setEnabled(false);
            }
            counter.setText("Click : " + c);
            c++;
        });
        settings.setOnClickListener(view -> {
            Intent intent;
            intent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivityForResult(intent, 0);
        });


    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String color = data.getExtras().getString("color");
        if (color.equals("white")) {
            click.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.white));
            findViewById(R.id.click).getBackground().setColorFilter(Color.parseColor("#EDECE6"), PorterDuff.Mode.MULTIPLY);
        } else {
            if (color.equals("gray")) {
                click.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.gray));
                findViewById(R.id.click).getBackground().setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY);
            } else {
                if(color.equals("black")){
                click.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.black));
                findViewById(R.id.click).getBackground().setColorFilter(Color.BLACK, PorterDuff.Mode.MULTIPLY);}
            }
        }
//            switch (data.getStringExtra("position")){
//                case "white":click.setBackgroundColor(Color.parseColor("#FCFCFC"));
//                case "gray":click.setBackgroundColor(Color.parseColor("#808080"));
//                case "black":click.setBackgroundColor(Color.parseColor("#010101"));
//            }
//            switch (data.getStringExtra("size")){
//                case "white":click.setBackgroundColor(Color.parseColor("#FCFCFC"));
//                case "gray":click.setBackgroundColor(Color.parseColor("#808080"));
//                case "black":click.setBackgroundColor(Color.parseColor("#010101"));
//            }
    }
}
