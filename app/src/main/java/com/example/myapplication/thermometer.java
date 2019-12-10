package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.*;


public class thermometer extends AppCompatActivity {

    private TextView Date;
    private TextView Temp;
    private String tempValue;
    private String timeData;
    DatabaseReference tempData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thermometer);
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        Temp = findViewById(R.id.temperature);
        Date = findViewById (R.id.date);
        //Create data

        String path = "/userdata/"+ mAuth.getUid() + "/temperature";
        tempData = FirebaseDatabase.getInstance().getReference(path);

        // Obtaining data from firebase and push to app
        tempData.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange (@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child ("temperature").exists ()) {
                    tempValue = dataSnapshot.child("temperature").getValue().toString();
                    Temp.setText(tempValue);
                } else {
                    addValue();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        tempData.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                tempValue = dataSnapshot.child("temperature").getValue().toString();
                Temp.setText(tempValue);
                addTime();
                timeData = dataSnapshot.child("date").getValue ().toString ();
                Date.setText (timeData);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    // Adding value to Firebase
    public void addValue() {
        Temp = findViewById(R.id.temperature);
        String tempValue = Temp.getText().toString().trim();
        String id = tempData.push().getKey();
        String date = DateFormat.getDateTimeInstance ().format(new Date ());
        temperature Temperature = new temperature(tempValue, id, date);
        tempData.setValue(Temperature);
    }

    public void addTime() {
        Temp = findViewById(R.id.temperature);
        String date = DateFormat.getDateTimeInstance ().format(new Date ());
        //tempTime temptime = new tempTime(tempValue, date);

        Map<String, Object> data = new HashMap<>();
        data.put("temperature", tempValue);
        data.put("date", date);

        tempData.updateChildren(data);
    }
}
