package com.example.clicker;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class SettingsActivity extends AppCompatActivity {

    ImageButton back, click;
    RadioGroup colorGroup, sizeGroup, positionGroup, formGroup;
    RadioButton white, black, gray, big, small, square, circle, left, right;
    Intent answerIntent = new Intent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        String color = getIntent().getExtras().getString("color");
        String size = getIntent().getExtras().getString("size");
        String position = getIntent().getExtras().getString("position");
        String form = getIntent().getExtras().getString("form");
        formGroup = findViewById(R.id.formGroup);
        circle = findViewById(R.id.circle);
        square = findViewById(R.id.square);
        positionGroup = findViewById(R.id.positionGroup);
        left = findViewById(R.id.left);
        right = findViewById(R.id.right);
        sizeGroup = findViewById(R.id.sizeGroup);
        big = findViewById(R.id.big);
        small = findViewById(R.id.small);
        colorGroup = findViewById(R.id.colorGroup);
        white = findViewById(R.id.radio_white);
        black = findViewById(R.id.radio_black);
        gray = findViewById(R.id.radio_gray);
        click = findViewById(R.id.click);
        back = findViewById(R.id.back);
        back.setOnClickListener(view -> {
            setResult(RESULT_OK, answerIntent);
            onColor(view);
            onSize(view);
            onPosition(view);
            onForm(view);
            finish();
        });
        switch(color){
            case "white":
                white.setChecked(true);
                break;
            case "gray":
                gray.setChecked(true);
                break;
            case "black":
                black.setChecked(true);
                break;
        }
        switch (size){
            case "big":
                big.setChecked(true);
                break;
            case "small":
                small.setChecked(true);
                break;
        }
        switch (position){
            case "right":
                right.setChecked(true);
                break;
            case "left":
                left.setChecked(true);
                break;
        }
        switch (form){
            case "circle":
                circle.setChecked(true);
                break;
            case "square":
                square.setChecked(true);
                break;
        }
    }

    @SuppressLint("NonConstantResourceId")
    public void onSize(View v) {

        int id = sizeGroup.getCheckedRadioButtonId();
        sizeGroup.setEnabled(false);
        if (id == R.id.big) {
            answerIntent.putExtra("size", "big");
        } else {
            if (id == R.id.small) {
                answerIntent.putExtra("size", "small");
            }
        }
    }

    @SuppressLint("NonConstantResourceId")
    public void onPosition(View v) {

        int id = positionGroup.getCheckedRadioButtonId();
        if (id == R.id.right) {
            answerIntent.putExtra("position", "right");
        } else {
            if (id == R.id.left) {
                answerIntent.putExtra("position", "left");
            }

        }
    }

    @SuppressLint("NonConstantResourceId")
    public void onColor(View v) {
        int id = colorGroup.getCheckedRadioButtonId();
        colorGroup.setEnabled(false);
        if (id == R.id.radio_white) {
            answerIntent.putExtra("color", "white");
            findViewById(R.id.radio_white).setEnabled(true);
        } else {
            if (id == R.id.radio_gray) {
                answerIntent.putExtra("color", "gray");
                findViewById(R.id.radio_gray).setEnabled(true);
            } else {
                if (id == R.id.radio_black) {
                    answerIntent.putExtra("color", "black");
                    findViewById(R.id.radio_black).setEnabled(true);
                }
            }

        }
    }

    @SuppressLint("NonConstantResourceId")
    public void onForm(View v) {
        int id = formGroup.getCheckedRadioButtonId();
        formGroup.setEnabled(false);
        if (id == R.id.square) {
            answerIntent.putExtra("form", "square");
            findViewById(R.id.square).setEnabled(true);
        } else {
            if (id == R.id.circle) {
                answerIntent.putExtra("form", "circle");
                findViewById(R.id.circle).setEnabled(true);
            }
        }
    }
}
