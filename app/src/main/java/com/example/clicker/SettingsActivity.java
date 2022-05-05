package com.example.clicker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

public class SettingsActivity extends AppCompatActivity {

    ImageButton smallLeft;
    ImageButton smallRight;
    ImageButton bigLeft;
    ImageButton bigRight;
    Button click;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        click = findViewById(R.id.click);
        smallLeft = findViewById(R.id.smallLeft);
        smallRight = findViewById(R.id.smallRight);
        bigRight= findViewById(R.id.bigRight);
        bigLeft = findViewById(R.id.bigLeft);

        smallRight.setOnClickListener(view -> {
            Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
            startActivity(intent);

        });
        smallLeft.setOnClickListener(view -> {
            Intent intent;
            intent = new Intent(SettingsActivity.this, MainActivity.class);
            startActivity(intent);

        });
        bigLeft.setOnClickListener(view -> {
            Intent intent;
            intent = new Intent(SettingsActivity.this, MainActivity.class);
            startActivity(intent);

        });
        bigRight.setOnClickListener(view -> {
            Intent intent;
            intent = new Intent(SettingsActivity.this, MainActivity.class);
            startActivity(intent);

        });
    }
}