package com.example.clicker;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView time, counter;
    ImageButton settings;
    Button click;


    int t = 40;
    int c = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        time = (TextView)findViewById(R.id.time);
        counter = (TextView)findViewById(R.id.counter);
        settings = (ImageButton)findViewById(R.id.settings);
        click = (Button)findViewById(R.id.click);
        final long[] start = new long[1];
        click.setEnabled(true);
        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(c == 0){
                    start[0] = System.currentTimeMillis();
                }
                c++;
                if(c == 41){
                    time.setText("Time : "+ (System.currentTimeMillis() - start[0]));
                    c=0;
                    start[0] = System.currentTimeMillis();
                }
                counter.setText("Click : "+ c);
            }
        });

    }
}