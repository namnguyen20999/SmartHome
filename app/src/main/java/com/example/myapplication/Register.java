package com.example.myapplication;

import android.app.Activity;
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

import androidx.annotation.NonNull;

public class Register extends Activity {
    FirebaseAuth mAuth;
    EditText mFullName, mEmail, mPassword, mPasswwordConf;
    Button mRegisterBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        mFullName = findViewById(R.id.userName2);
        mEmail = findViewById(R.id.editText);
        mPassword = findViewById(R.id.pswRegister);
        mPasswwordConf = findViewById(R.id.pswConfirm);
        mRegisterBtn = findViewById(R.id.button2);
        mAuth = FirebaseAuth.getInstance();

        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();
                String pswconf = mPasswwordConf.getText().toString().trim();
                String usn = mFullName.getText().toString().trim();

                if (email.isEmpty()) {
                    mEmail.setError("Please enter email id");
                    mEmail.requestFocus();
                } else if (password.isEmpty()) {
                    mPassword.setError("Please enter your password");
                    mPassword.requestFocus();
                } else if (!(password).equals(pswconf)) {
                    mPasswwordConf.setError("Password does not match");
                    mPasswwordConf.requestFocus();
                } else if (usn.isEmpty()) {
                    mFullName.setError("Please enter your full name");
                    mFullName.requestFocus();
                }

                // register user with Firebase
                else {


                    mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(Register.this, "User Created.", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(Register.this, Menu.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(Register.this, "Error occurred", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }
}
