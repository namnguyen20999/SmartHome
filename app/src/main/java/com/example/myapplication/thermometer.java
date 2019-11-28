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
import android.widget.TextView;


public class thermometer extends AppCompatActivity {

    private TextView Temp;
    private String tempValue;
    DatabaseReference Temper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thermometer);
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        Temp = findViewById(R.id.temperature);

        //Create data

        String path = "/userdata/"+ mAuth.getUid() + "/temperature";
        Temper = FirebaseDatabase.getInstance().getReference(path);
        addValue();

        // Obtaining data from firebase and push to app

        Temper.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                tempValue = dataSnapshot.child("temperature").getValue().toString();
                Temp.setText(tempValue);
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
        String id = Temper.push().getKey();
        long date = System.currentTimeMillis();
        temperature Temperature = new temperature(tempValue, id, date);
        Temper.setValue(Temperature);

    }
}
