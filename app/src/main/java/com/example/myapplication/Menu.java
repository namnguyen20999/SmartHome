package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Menu extends AppCompatActivity {

   private ImageView feature1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        feature1 = (ImageView) findViewById(R.id.feature1);
        feature1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSensor();
            }
        });
    }
        public void openSensor(){
            Intent intent =  new Intent(this, lightSensor.class);
            startActivity(intent);
        }
    }
