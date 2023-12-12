package com.example.allos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RegisterPage2 extends AppCompatActivity implements View.OnClickListener {


    Button regButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page2);

        regButton = findViewById(R.id.registerButton);

        regButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == regButton){

        }
    }
}