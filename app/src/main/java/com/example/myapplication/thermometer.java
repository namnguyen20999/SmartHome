package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
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

public class thermometer extends AppCompatActivity {

    private TextView Temp;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private static final String TAG = "thermometer";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thermometer);

        Temp = findViewById(R.id.temperature);

        // Create a temperature
        Map<String, Object> user = new HashMap<>();
        user.put ("temperature measured" ,25);
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        final String userid = mAuth.getUid();
// Add a new document with a generated ID
        db.collection("temperature").document(userid).set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
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
                        if (task.isSuccessful()){
                            DocumentSnapshot doc = task.getResult();
                            StringBuilder fields = new StringBuilder("");
                            fields.append("Current Temperature is: ").append(doc.get("temperature measured"));
                            Temp.setText(fields.toString());

                        }
                    }
                })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                            }
                        });
        /*

        db.collection("temperature")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                Toast.makeText(getApplicationContext(),"Temperature received successfully",Toast.LENGTH_LONG).show();
                                String temper = document.getString("test");
                                Temp.setText(temper);
                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });
          */



    }
}
//Bao did this