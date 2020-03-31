package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Menu extends AppCompatActivity {

    private ImageView light;
    private ImageView helpcntr;
    private ImageView ventilation;
    private ImageView Door;
    private ImageView thermometer;
    private ImageView camera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        light = (ImageView) findViewById(R.id.feature1);
        helpcntr = (ImageView) findViewById(R.id.helpcntr);
        thermometer = (ImageView) findViewById(R.id.thermometer);

        light.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLight();
            }
        });
        thermometer.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openTher();
            }
        });
        helpcntr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://cengsmarthome.wordpress.com"));
                startActivity(intent);
            }
        });
        }
        public void openLight(){
            Intent intent =  new Intent(this, lightSensor.class);
            startActivity(intent);
        }
        public void openTher(){
        Intent intent = new Intent(this, com.example.myapplication.thermometer.class);
        startActivity(intent);
        }
}
