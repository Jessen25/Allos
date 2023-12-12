package com.example.allos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RegisterPage1 extends AppCompatActivity implements View.OnClickListener{


    Button registerButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page1);


        registerButton = findViewById(R.id.registerButton);
        registerButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v == registerButton){
//            Intent nextRegPage = new Intent(this, RegisterPage2.class);
//            nextRegPage.putStringArrayListExtra("allergens", )
//            startActivity(nextRegPage);
        }
    }
}