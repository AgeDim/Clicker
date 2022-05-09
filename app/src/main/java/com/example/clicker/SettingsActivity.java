package com.example.clicker;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioGroup;

public class SettingsActivity extends AppCompatActivity {

    ImageButton back, click;
    RadioGroup colorGroup, sizeGroup, positionGroup, formGroup;
    Intent answerIntent = new Intent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        formGroup = findViewById(R.id.formGroup);
        positionGroup = findViewById(R.id.positionGroup);
        sizeGroup = findViewById(R.id.sizeGroup);
        colorGroup = findViewById(R.id.colorGroup);
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
            if (id == R.id.radio_grey) {
                answerIntent.putExtra("color", "gray");
                findViewById(R.id.radio_grey).setEnabled(true);
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
