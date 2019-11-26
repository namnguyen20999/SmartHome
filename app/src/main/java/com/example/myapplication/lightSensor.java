package com.example.myapplication;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.android.gms.tasks.OnSuccessListener;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import android.view.View;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;

import java.util.HashMap;
import java.util.Map;

public class lightSensor extends AppCompatActivity {
    SwitchCompat switchCompat;
    SwitchCompat switchCompat1;
    ImageView imageView;
    ImageView imageView2;

    private TextView Temp;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private static final String TAG = "lightSensor";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light_sensor);

        switchCompat = findViewById(R.id.switchButton);
        imageView = findViewById(R.id.bulb);
        imageView.setImageDrawable(getDrawable(R.drawable.light_bulboff));

        switchCompat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (switchCompat.isChecked()) {
                    imageView.setImageDrawable(getDrawable(R.drawable.light_bulbon));
                } else {
                    imageView.setImageDrawable(getDrawable(R.drawable.light_bulboff));
                }
            }
        });

        /*
            // Create a light setting
            Map<String, Object> user = new HashMap<>();
            user.put("status", true);
            FirebaseAuth mAuth = FirebaseAuth.getInstance();
            final String userid = mAuth.getUid();
            //Add a new document with a generated ID
            db.collection("light").document(userid).set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    // Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                    Log.d(TAG, "DocumentSnapshot added with ID: " + userid);

                }
            })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w(TAG, "Error adding document", e);
                        }
                    });

            DocumentReference tempValue = db.collection("temperature").document(userid);
            tempValue.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot doc = task.getResult();
                        StringBuilder fields = new StringBuilder("");
                        fields.append("Current Temperature is: ").append(doc.get("temperature"));
                        Temp.setText(fields.toString());

                    }
                }
            })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                        }
                    });
        }
        */
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        final String userid = mAuth.getUid();
        DocumentReference lightValue = db.collection("light").document(userid);
        lightValue.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot doc = task.getResult();
                    imageView.setImageDrawable(getDrawable(R.drawable.light_bulbon));

                }
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
    }
}



































































        switchCompat1 = findViewById(R.id.switchButton1);
        imageView2 = findViewById(R.id.bulb1);
        imageView2.setImageDrawable(getDrawable(R.drawable.light_bulboff1));

        switchCompat1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (switchCompat1.isChecked()){
                    imageView2.setImageDrawable(getDrawable(R.drawable.light_bulbon_1));
                } else {
                    imageView2.setImageDrawable(getDrawable(R.drawable.light_bulboff1));
                }
            }
        });
    }

}