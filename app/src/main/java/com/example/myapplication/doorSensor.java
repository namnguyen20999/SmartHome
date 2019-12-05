
package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.Toolbar;


import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.Calendar;

class door{
    String Id;
    String frontDoor;
    String backDoor;
   // String garage;

    public door() {

    }

    public door(String Id, String frontDoor, String backDoor) {
       this.Id = Id;
        this.frontDoor = frontDoor;
        this.backDoor = backDoor;
    }

    public String getId() {
        return Id;
    }

    public String isFrontDoor() {
        return frontDoor;
    }

    public String isBackDoor() {
        return backDoor;
    }

}

public class doorSensor extends AppCompatActivity {

    private Switch frontDoorSwitch;
    private Switch backDoorSwitch;
    private Switch garageSwitch;
    private String frontDoor;
    private String backDoor;
    private String garageButton;
    private String On = "On";
    private String Off = "Off";
    DatabaseReference doorDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_door_sensor);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        // Setting path for Data
        String path = "/userdata/" + mAuth.getUid() + "/door";
        doorDatabase = FirebaseDatabase.getInstance().getReference(path);

        frontDoorSwitch = findViewById(R.id.front);
        backDoorSwitch = findViewById(R.id.backdoor);
        garageSwitch = findViewById(R.id.gar);

        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance().format(calendar.getTime());

        TextView textViewDate = findViewById(R.id.view_date);
        textViewDate.setText(currentDate);

        frontDoorSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (frontDoorSwitch.isChecked()) {
                    Toast.makeText(getApplicationContext(), "Front Door: " + frontDoorSwitch.getTextOn().toString(), Toast.LENGTH_SHORT).show();
                    frontDoor = On;
                    addValue();
                } else {
                    Toast.makeText(getApplicationContext(), "Front Door: " + frontDoorSwitch.getTextOff().toString(), Toast.LENGTH_SHORT).show();
                    frontDoor = Off;
                    addValue();
                }
            }
        });
        backDoorSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( backDoorSwitch.isChecked()) {
                    Toast.makeText(getApplicationContext(), "Back Door: " + backDoorSwitch.getTextOn().toString(), Toast.LENGTH_SHORT).show();
                    backDoor = On;
                    addValue();
                } else {
                    Toast.makeText(getApplicationContext(), "Back Door: " + backDoorSwitch.getTextOff().toString(), Toast.LENGTH_SHORT).show();
                    backDoor = Off;
                    addValue();
                }
            }
        });



  /*      garageSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(garageSwitch.isChecked()){
                    Toast.makeText(getApplicationContext(), "Garage: " + garageSwitch.getTextOn().toString(), Toast.LENGTH_SHORT).show();
                    garageButton = On;
                    addValue();
                }   else {
                    Toast.makeText(getApplicationContext(), "Garage: " + garageSwitch.getTextOff().toString(), Toast.LENGTH_SHORT).show();
                    garageButton = Off;
                    addValue();
                }
            }
        });
*/
        doorDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.child("frontDoor").exists()){
                    frontDoor = dataSnapshot.child("frontDoor").getValue(String.class);
                    backDoor  = dataSnapshot.child("backDoor").getValue(String.class);
                //    garageButton    = dataSnapshot.child("garage door").getValue(String.class);
                    checkValue();
                }   else {
                    frontDoor = Off;
                    backDoor = Off;
                //    garageButton = Off;
                    addValue();
                }
                if(dataSnapshot.child("backDoor").exists()){
                    frontDoor = dataSnapshot.child("frontDoor").getValue(String.class);
                    backDoor  = dataSnapshot.child("backDoor").getValue(String.class);
                //    garageButton    = dataSnapshot.child("garage door").getValue(String.class);
                    checkValue();
                }   else {
                    frontDoor = Off;
                    backDoor = Off;
                //    garageButton = Off;
                    addValue();
                }
                /* if(dataSnapshot.child("garage door").exists()){
                    frontDoorButton = dataSnapshot.child("front door").getValue(String.class);
                    backDoorButton  = dataSnapshot.child("back door").getValue(String.class);
                    garageButton    = dataSnapshot.child("garage door").getValue(String.class);
                    checkValue();
                }   else {
                    frontDoorButton = Off;
                    backDoorButton = Off;
                    garageButton = Off;
                    addValue();
                } */
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void checkValue(){
        if(frontDoor.equals(On)){
            Toast.makeText(getApplicationContext(), "Front Door: " + frontDoorSwitch.getTextOn().toString(), Toast.LENGTH_SHORT).show();
            frontDoorSwitch.setChecked(true);
        }   else {
            Toast.makeText(getApplicationContext(), "Front Door: " + frontDoorSwitch.getTextOff().toString(), Toast.LENGTH_SHORT).show();
            frontDoorSwitch.setChecked(false);
        }
        if(backDoor.equals(On)){
            Toast.makeText(getApplicationContext(), "Back Door: " + backDoorSwitch.getTextOn().toString(), Toast.LENGTH_SHORT).show();
            backDoorSwitch.setChecked(true);
        }   else {
            Toast.makeText(getApplicationContext(), "Back Door: " + backDoorSwitch.getTextOff().toString(), Toast.LENGTH_SHORT).show();
            backDoorSwitch.setChecked(false);
        }
        /* if(garageButton.equals(On)){
            Toast.makeText(getApplicationContext(), "Garage: " + garageSwitch.getTextOn().toString(), Toast.LENGTH_SHORT).show();
            garageSwitch.setChecked(true);
        }   else {
            Toast.makeText(getApplicationContext(), "Garage: " + garageSwitch.getTextOff().toString(), Toast.LENGTH_SHORT).show();
            garageSwitch.setChecked(false);
        } */
    }

    public void addValue(){
       String id = doorDatabase.push().getKey();
        door Door = new door(id,frontDoor, backDoor);
        doorDatabase.setValue(Door);
    }
}
//Bao did this
//