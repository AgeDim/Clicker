package com.example.clicker;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioGroup;

public class SettingsActivity extends AppCompatActivity {

    ImageButton back;
    Button click;
    RadioGroup colorGroup, sizeGroup, positionGroup;
    Intent answerIntent = new Intent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        positionGroup = findViewById(R.id.positionGroup);
        sizeGroup = findViewById(R.id.sizeGroup);
        colorGroup = findViewById(R.id.colorGroup);
        click = findViewById(R.id.click);
        back = findViewById(R.id.back);
        back.setOnClickListener(view -> {
            setResult(RESULT_OK, answerIntent);
            finish();
        });

    }

    @SuppressLint("NonConstantResourceId")
    public void onSize(View v) {

        int id = sizeGroup.getCheckedRadioButtonId();
        switch (id) {
            case R.id.big:
                answerIntent.putExtra("size", "big");
            case R.id.small:
                answerIntent.putExtra("size", "small");
        }
    }

    @SuppressLint("NonConstantResourceId")
    public void onPosition(View v) {

        int id = positionGroup.getCheckedRadioButtonId();
        switch (id) {
            case R.id.right:
                answerIntent.putExtra("position", "right");
            case R.id.left:
                answerIntent.putExtra("position", "left");
        }
    }

    @SuppressLint("NonConstantResourceId")
    public void onColor(View v) {
        int id = colorGroup.getCheckedRadioButtonId();
        if (id == R.id.radio_white) {
            answerIntent.putExtra("color", "white");
        } else {
            if (id == R.id.radio_grey) {
                answerIntent.putExtra("color", "gray");
            } else {
                if (id == R.id.radio_black) {
                    answerIntent.putExtra("color", "black");
                }
            }

        }
    }
}
