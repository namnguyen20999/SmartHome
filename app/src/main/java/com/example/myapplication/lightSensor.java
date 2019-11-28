package com.example.myapplication;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import android.widget.TextView;

import android.view.View;
import android.widget.ImageView;

public class lightSensor extends AppCompatActivity {
    SwitchCompat light1;
    SwitchCompat light2;
    ImageView imageView1;
    ImageView imageView2;

    private DatabaseReference lightDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light_sensor);

        light1 = findViewById(R.id.switchButton);
        imageView1 = findViewById(R.id.bulb);
        imageView1.setImageDrawable(getDrawable(R.drawable.light_bulboff));
        lightDatabase = FirebaseDatabase.getInstance().getReference("light");
        light1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (light1.isChecked()) {

                    imageView1.setImageDrawable(getDrawable(R.drawable.light_bulbon));
                } else {
                    imageView1.setImageDrawable(getDrawable(R.drawable.light_bulboff));
                }
            }
        });


        light2 = findViewById(R.id.switchButton1);
        imageView2 = findViewById(R.id.bulb1);
        imageView2.setImageDrawable(getDrawable(R.drawable.light_bulboff1));

        light2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (light2.isChecked()){
                    imageView2.setImageDrawable(getDrawable(R.drawable.light_bulbon_1));
                } else {
                    imageView2.setImageDrawable(getDrawable(R.drawable.light_bulboff1));
                }
            }
        });
    }

}