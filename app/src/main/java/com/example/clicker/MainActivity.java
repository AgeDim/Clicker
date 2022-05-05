package com.example.clicker;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView time, counter;
    ImageButton settings;
    Button click, startBtn;

    int c = 0;

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
            }

            if(c == 40){
                time.setText("Time : "+ String.format("%.3f",(float)(System.currentTimeMillis() -  start[0])/1000) + " c");
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