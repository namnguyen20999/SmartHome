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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        light = (ImageView) findViewById(R.id.feature1);
        helpcntr = (ImageView) findViewById(R.id.helpcntr);
        ventilation = (ImageView) findViewById(R.id.ventilation);
        Door = (ImageView) findViewById(R.id.door);

        light.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLight();
            }
        });
        Door.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openDoor();
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
        ventilation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openVentilation();
            }
        });
        }
        public void openLight(){
            Intent intent =  new Intent(this, lightSensor.class);
            startActivity(intent);
        }
        public void openVentilation(){
        Intent intent =  new Intent(this, ventControl.class);
        startActivity(intent);
        }
        public void openDoor(){
        Intent intent = new Intent(this, doorSensor.class);
        startActivity(intent);
        }

}
