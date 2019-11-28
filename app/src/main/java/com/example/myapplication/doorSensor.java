package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class doorSensor extends AppCompatActivity implements View.OnClickListener{

    private Switch front1,backdoor,gar;

    private static final String TAG = "Door";
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_door_sensor);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        front1 = findViewById(R.id.front);
        backdoor = findViewById(R.id.backdoor);
        gar = findViewById(R.id.gar);

        front1.setOnClickListener(this);
        backdoor.setOnClickListener(this);
        gar.setOnClickListener(this);

        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance().format(calendar.getTime());

        TextView textViewDate = findViewById(R.id.view_date);
        textViewDate.setText(currentDate);
    }


    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.front){
            if(front1.isChecked()){
                Toast.makeText(getApplicationContext(),"Front Door: " +front1.getTextOn().toString(),Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(getApplicationContext(),"Front Door: " +front1.getTextOff().toString(),Toast.LENGTH_SHORT).show();
            }
        }
        if(v.getId() == R.id.backdoor){

            if(backdoor.isChecked()){
                Toast.makeText(getApplicationContext(),"Back Door: " +backdoor.getTextOn().toString(),Toast.LENGTH_SHORT).show();

            }
            else{
                Toast.makeText(getApplicationContext(),"Back Door: "+backdoor.getTextOff().toString(),Toast.LENGTH_SHORT).show();
            }
        }

        if(v.getId() == R.id.gar){
            if(gar.isChecked()){
                Toast.makeText(getApplicationContext(),"Garage: " +gar.getTextOn().toString(),Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(getApplicationContext(),"Garage: " +gar.getTextOff().toString(),Toast.LENGTH_SHORT).show();
            }
        }

    }
    public void saveState(View v){

        String state = front1.getText().toString();
        Map<String, Object> states = new HashMap<>();
        states.put("value", state );

        db.collection("States").document("state").set(states)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(doorSensor.this,"Note Saved",Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(doorSensor.this,"Note Saved",Toast.LENGTH_SHORT).show();
                        Log.d(TAG, e.toString());
                    }
                });
    }
}
//Bao did this