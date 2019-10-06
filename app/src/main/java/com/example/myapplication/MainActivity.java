package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;


public class MainActivity extends AppCompatActivity {

    private TextView register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        register = (TextView) findViewById(R.id.register);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRegister();
            }
        });
    }

    public void openRegister(){
        Intent intent =  new Intent(this, Register.class);
        startActivity(intent);
    }

    public void OnLoginClick (View v){
        if (v.getId() == R.id.button){
            EditText a = (EditText)findViewById(R.id.userName);
            String str = a.getText().toString();
            Intent i = new Intent(MainActivity.this, Menu.class);
            i.putExtra("username",str);
            startActivity(i);
        }

    }
}
