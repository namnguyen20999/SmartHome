package com.example.myapplication;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ImageView;

public class lightSensor extends AppCompatActivity {
    SwitchCompat switchCompat;
    SwitchCompat switchCompat1;
    ImageView   imageView;
    ImageView   imageView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light_sensor);

        switchCompat = findViewById(R.id.switchButton);
        imageView = findViewById(R.id.bulb);
        imageView.setImageDrawable(getDrawable(R.drawable.lbulboff));

        switchCompat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (switchCompat.isChecked()){
                    imageView.setImageDrawable(getDrawable(R.drawable.lbulbon));
                } else {
                    imageView.setImageDrawable(getDrawable(R.drawable.lbulboff));
                }
            }
        });

        switchCompat1 = findViewById(R.id.switchButton1);
        imageView2 = findViewById(R.id.bulb1);
        imageView2.setImageDrawable(getDrawable(R.drawable.lbulboff1));

        switchCompat1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (switchCompat1.isChecked()){
                    imageView2.setImageDrawable(getDrawable(R.drawable.lbulbon1));
                } else {
                    imageView2.setImageDrawable(getDrawable(R.drawable.lbulboff1));
                }
            }
        });
    }

}