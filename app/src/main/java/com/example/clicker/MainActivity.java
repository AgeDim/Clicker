package com.example.clicker;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    TextView counter, time;
    ImageButton click,settings;
    Button startBtn;
    Handler customHandler = new Handler();
    LinearLayout l;
    ViewGroup.LayoutParams last;

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
            time.setText("Время : " + String.format("%2d", secs) + ":" + String.format("%3d", milliseconds) + " c");
            customHandler.postDelayed(this, 0);
        }
    };
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        l = findViewById(R.id.buttonlayout);
        time = findViewById(R.id.time);
        counter = findViewById(R.id.counter);
        settings = findViewById(R.id.settings);
        click = findViewById(R.id.click);
        last = click.getLayoutParams();
        startBtn = findViewById(R.id.start);
        final long[] start = new long[1];
        startBtn.setEnabled(true);
        click.setEnabled(false);

        startBtn.setOnClickListener(view -> {
            startBtn.setEnabled(false);
            click.setEnabled(true);
            time.setText("Время : 0 c");

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
            counter.setText("Клик : " + c);
            c++;
        });
        settings.setOnClickListener(view -> {
            Intent intent;
            intent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivityForResult(intent, 0);
        });


    }

    @SuppressLint("RtlHardcoded")
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String color = data.getExtras().getString("color");
        String size = data.getExtras().getString("size");
        String position = data.getExtras().getString("position");
        String form = data.getExtras().getString("form");
        if(form.equals("circle")){
                if(color.equals("white"))click.setBackgroundResource(R.drawable.whitecircle);
            if(color.equals("gray"))click.setBackgroundResource(R.drawable.graycircle);
            if(color.equals("black"))click.setBackgroundResource(R.drawable.blackcircle);
            }
        else{
        if(color.equals("white"))click.setBackgroundResource(R.drawable.whitesquare);
            if(color.equals("gray"))click.setBackgroundResource(R.drawable.graysquare);
            if(color.equals("black"))click.setBackgroundResource(R.drawable.blacksquare);
            }
        ViewGroup.LayoutParams k = click.getLayoutParams();
        if (size.equals("big")) {
            k.height = 300;
            k.width = 300;
        } else {
            if (size.equals("small")) {
                k.height = 200;
                k.width = 200;
            }
        }
        click.setLayoutParams(k);
        if (position.equals("left")) {
            l.setGravity(Gravity.LEFT | Gravity.CENTER_HORIZONTAL);
        } else {
            if (position.equals("right")) {
                l.setGravity(Gravity.RIGHT | Gravity.CENTER_HORIZONTAL);

            }
        }
    }
}
