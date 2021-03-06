package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import android.widget.Toast;


public class Register extends AppCompatActivity {
    FirebaseAuth mAuth;
    EditText mFullName, mEmail, mPassword, mPasswwordConf;
    Button mRegisterBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        mFullName = findViewById(R.id.fullname);
        mEmail = findViewById(R.id.email);
        mPassword = findViewById(R.id.pswRegister);
        mPasswwordConf = findViewById(R.id.pswConfirm);
        mRegisterBtn = findViewById(R.id.button2);
        mAuth = FirebaseAuth.getInstance();

        if(mAuth.getCurrentUser() != null){
            openMainActivity();
            finish();
        }

        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();
                String pswconf = mPasswwordConf.getText().toString().trim();
                String usn = mFullName.getText().toString().trim();


                if (email.isEmpty()) {
                    mEmail.setError("Please enter email id");
                    return;

                } else if (password.isEmpty()) {
                    mPassword.setError("Please enter your password");
                    return;
                } else if (!(password).equals(pswconf)) {
                    mPasswwordConf.setError("Password does not match");
                    return;
                } else if (usn.isEmpty()) {
                    mFullName.setError("Please enter your full name");
                    return;
                }

                // register user with Firebase


                    mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(Register.this, "User Created.", Toast.LENGTH_SHORT).show();
                                Intent intentOnComplete = new Intent(Register.this, MainActivity.class);
                                startActivity(intentOnComplete);
                            } else {
                                Toast.makeText(getApplicationContext(),"Error Occurred, Please try again",Toast.LENGTH_LONG).show();
                            }
                        }
                    });

            }
        });
    }
    public void openMainActivity(){
        Intent intentMain = new Intent(Register.this,MainActivity.class);
        startActivity(intentMain);
    }
}
