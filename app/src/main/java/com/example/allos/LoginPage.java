package com.example.allos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginPage extends AppCompatActivity implements View.OnClickListener{

    Button loginButton;
    EditText userEdit, passEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        loginButton = findViewById(R.id.loginButton);
        userEdit = findViewById(R.id.usernameEdit);
        passEdit = findViewById(R.id.passEdit);


        loginButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == loginButton){

        }
    }
}