package com.example.myapplication;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


class light {
    String Id;
    String light1;
    String light2;

    public light(String light1State, String light2State) {

    }

    public light(String Id, String light1, String light2){
        this.Id = Id;
        this.light1 = light1;
        this.light2 = light2;
    }

    public String getId(){
        return Id;
    }

    public String getLight1(){
        return light1;
    }

    public String getLight2(){
        return light2;
    }
}


public class lightSensor extends AppCompatActivity {
    private    SwitchCompat light1Switch;
    private    SwitchCompat light2Switch;
    private    ImageView imageView1;
    private    ImageView imageView2;
    private     String light1;
    private     String light2;
    private    String On = "On";
    private    String Off = "Off";
    DatabaseReference lightDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_light_sensor);
        light1Switch = (SwitchCompat) findViewById(R.id.switchButton);
        imageView1 = (ImageView) findViewById(R.id.bulb);
        imageView1.setImageDrawable(getDrawable(R.drawable.light_bulboff));

        // Setting path for Data
        String path = "/userdata/" + mAuth.getUid() + "/light";
        lightDatabase = FirebaseDatabase.getInstance().getReference(path);
        // Adding value to Firebase

        light1Switch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (light1Switch.isChecked()) {
                    imageView1.setImageDrawable(getDrawable(R.drawable.light_bulbon));
                    light1 = On;
                    addValue();
                } else {
                    imageView1.setImageDrawable(getDrawable(R.drawable.light_bulboff));
                    light1 = Off;
                    addValue();
                }
            }
        });
        light2Switch = findViewById(R.id.switchButton1);
        imageView2 = findViewById(R.id.bulb1);
        light2Switch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (light2Switch.isChecked()) {
                    imageView2.setImageDrawable(getDrawable(R.drawable.light_bulbon_1));
                    light2 = On;
                    addValue();
                } else {
                    imageView2.setImageDrawable(getDrawable(R.drawable.light_bulboff1));
                    light2 = Off;
                    addValue();
                }
            }
        });
        lightDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child("light1").exists()) {
                    light1 = dataSnapshot.child("light1").getValue(String.class);
                    light2 = dataSnapshot.child("light2").getValue(String.class);
                    checkValue();
                }
                else {
                    light1 = Off;
                    light2 = Off;
                    addValue();
                }
                if (dataSnapshot.child("light2").exists()){
                    light1 = dataSnapshot.child("light1").getValue(String.class);
                    light2 = dataSnapshot.child("light2").getValue(String.class);
                    checkValue();
                }
                else {
                    light1 = Off;
                    light2 = Off;
                    addValue();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    // Check the value
    public void checkValue() {
            if (light1.equals(On)) {
                imageView1.setImageDrawable(getDrawable(R.drawable.light_bulbon));
                light1Switch.setChecked(true);
            } else {
                imageView1.setImageDrawable(getDrawable(R.drawable.light_bulboff));
                light1Switch.setChecked(false);
            }
            if (light2.equals(On)) {
                imageView2.setImageDrawable(getDrawable(R.drawable.light_bulbon_1));
                light2Switch.setChecked(true);
            } else {
                imageView2.setImageDrawable(getDrawable(R.drawable.light_bulboff1));
                light2Switch.setChecked(false);
            }
    }
    // Adding value to Database
    public void addValue() {
        String id = lightDatabase.push().getKey();
        light Light = new light(id, light1, light2);
        lightDatabase.setValue(Light);
    }
}