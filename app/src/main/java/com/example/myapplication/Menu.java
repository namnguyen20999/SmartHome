package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Menu extends AppCompatActivity {

    private ImageView feature1;
    private ImageView helpcntr;
    private ImageView ventilation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        feature1 = (ImageView) findViewById(R.id.feature1);
        helpcntr = (ImageView) findViewById(R.id.helpcntr);
        ventilation = (ImageView) findViewById(R.id.ventilation);

        feature1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSensor();
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
        public void openSensor(){
            Intent intent =  new Intent(this, lightSensor.class);
            startActivity(intent);
        }
    public void openVentilation(){
        Intent intent =  new Intent(this, ventControl.class);
        startActivity(intent);
    }
}
