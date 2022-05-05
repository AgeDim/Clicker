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
    Button click;

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
        final long[] start = new long[1];
        click.setEnabled(true);
        click.setOnClickListener(view -> {
            if(c == 0){
                start[0] = System.currentTimeMillis();
            }
            c++;
            if(c == 41){
                time.setText("Time : "+ String.format("%.3f",(float)(System.currentTimeMillis() -  start[0])/1000) + " c");
                c=0;
                start[0] = System.currentTimeMillis();
            }
            counter.setText("Click : "+ c);
        });
        settings.setOnClickListener(view -> {
            Intent intent;
            intent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(intent);
        });
    }
}