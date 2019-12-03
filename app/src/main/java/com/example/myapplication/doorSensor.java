package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.Calendar;

class Settings{
    String settingId;
    boolean frontDoor;
    boolean backDoor;
    boolean garage;

    public Settings() {

    }

    public Settings(String settingId, boolean frontDoor, boolean backDoor, boolean garage) {
        this.settingId = settingId;
        this.frontDoor = frontDoor;
        this.backDoor = backDoor;
        this.garage = garage;
    }

    public String getSettingId() {
        return settingId;
    }

    public boolean isFrontDoor() {
        return frontDoor;
    }

    public boolean isBackDoor() {
        return backDoor;
    }

    public boolean isGarage() {
        return garage;
    }
}

public class doorSensor extends AppCompatActivity implements View.OnClickListener {

    private Switch frontDoor, backdoor, garage;
    private DatabaseReference settingDatabase;
    private Button doneButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_door_sensor);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        settingDatabase = FirebaseDatabase.getInstance().getReference("Door setting");
        doneButton = (Button) findViewById(R.id.doneButton);
        frontDoor = (Switch) findViewById(R.id.front);
        backdoor = (Switch) findViewById(R.id.backdoor);
        garage = (Switch) findViewById(R.id.gar);
        frontDoor.setOnClickListener(this);
        backdoor.setOnClickListener(this);
        garage.setOnClickListener(this);

        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance().format(calendar.getTime());

        TextView textViewDate = findViewById(R.id.view_date);
        textViewDate.setText(currentDate);


    }



    @Override
    public void onClick(View v) {
        boolean frontDoorNoti = frontDoor.isChecked();
        boolean backDoorNoti = backdoor.isChecked();
        boolean garageNoti = garage.isChecked();
        String id = "-Lv41phQ8WYWY0h3-QXv";
        Settings setting = new Settings(id, frontDoorNoti, backDoorNoti, garageNoti);
        settingDatabase.child(id).setValue(setting);
        if (v.getId() == R.id.front) {
            if (frontDoor.isChecked()) {
                Toast.makeText(getApplicationContext(), "Front Door: " + frontDoor.getTextOn().toString(), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "Front Door: " + frontDoor.getTextOff().toString(), Toast.LENGTH_SHORT).show();
            }
        }
        if (v.getId() == R.id.backdoor) {

            if (backdoor.isChecked()) {
                Toast.makeText(getApplicationContext(), "Back Door: " + backdoor.getTextOn().toString(), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "Back Door: " + backdoor.getTextOff().toString(), Toast.LENGTH_SHORT).show();
            }
        }

        if (v.getId() == R.id.gar) {
            if (garage.isChecked()) {
                Toast.makeText(getApplicationContext(), "Garage: " + garage.getTextOn().toString(), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "Garage: " + garage.getTextOff().toString(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        settingDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String id = settingDatabase.push().getKey();
                DataSnapshot settingSnapshot = dataSnapshot;
                Settings setting = new Settings("-Lv41phQ8WYWY0h3-QXv", true, true, true);
                setting = dataSnapshot.child("Door setting").getValue(Settings.class);


                frontDoor.setChecked(setting.isFrontDoor());
                backdoor.setChecked(setting.isBackDoor());
                garage.setChecked(setting.isGarage());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
//Bao did this