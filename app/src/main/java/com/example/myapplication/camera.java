package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import io.grpc.Context;

public class camera extends AppCompatActivity {


    private FirebaseStorage mStorageRef;
    private DatabaseReference mDatabaseRef;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_camera);
        //mStorageRef = FirebaseStorage.getInstance ().getReference ("userdata");
        // Reference to an image file in Cloud Storage // ImageView in your Activity
        //StorageReference storageReference = FirebaseStorage.getInstance().getReference("users/me");
        /*
        storageRef.child("users/home.jpg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>()
        {
                    @Override
                    public void onSuccess(Uri uri) {
                        // Got the download URL for 'users/me/profile.png'
                    }
                }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            // Handle any errors
                        }
                    });

         */

        /*
        // Reference to an image file in Cloud Storage
        //StorageReference storageReference = FirebaseStorage.getInstance().getReferenceFromUrl("https://firebasestorage.googleapis.com/v0/b/shdatabase-bc9ce.appspot.com/o/200px-Bertha_von_Suttner_nobel.jpg?alt=media&token=66b0b1b3-724d-4792-8ee9-d00ca5f3b056");
        FirebaseStorage storage = FirebaseStorage.getInstance();

        // [START download_create_reference]
        // Create a storage reference from our app
        StorageReference storageRef = storage.getReference();

        // Create a reference with an initial file path and name
        StorageReference pathReference = storageRef.child("users/200px-Bertha_von_Suttner_nobel.jpg");
        */

// ImageView in your Activity
        ImageView imageView = findViewById(R.id.imageView13);

// Download directly from StorageReference using Glide
// (See MyAppGlideModule for Loader registration)
        Glide.with(getApplicationContext ())
                .load("https://firebasestorage.googleapis.com/v0/b/shdatabase-bc9ce.appspot.com/o/home.jpg?alt=media&token=15981c5c-1fb8-4332-846a-7de9d9cb9e2e")
                .into(imageView);
    }
}