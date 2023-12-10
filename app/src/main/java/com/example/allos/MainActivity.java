package com.example.allos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    ImageView exitButton;
    LinearLayout editProfileButton;
    LinearLayout helpButton;
    LinearLayout privacyPolicyButton;
    LinearLayout termOfServiceButton;
    LinearLayout rateAppButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        exitButton = findViewById(R.id.exitButton);
        editProfileButton = findViewById(R.id.editProfileButton);
        helpButton = findViewById(R.id.helpButton);
        privacyPolicyButton = findViewById(R.id.privacyPolicyButton);
        termOfServiceButton = findViewById(R.id.termOfServiceButton);
        rateAppButton = findViewById(R.id.rateAppButton);

        exitButton.setOnClickListener(this);
        editProfileButton.setOnClickListener(this);
        helpButton.setOnClickListener(this);
        privacyPolicyButton.setOnClickListener(this);
        termOfServiceButton.setOnClickListener(this);
        rateAppButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == exitButton){
            Toast.makeText(this, "exit", Toast.LENGTH_SHORT).show();
        }
        if(view == editProfileButton){
            Toast.makeText(this, "edit", Toast.LENGTH_SHORT).show();
        }
        if(view == helpButton){
            Toast.makeText(this, "help", Toast.LENGTH_SHORT).show();
        }
        if(view == privacyPolicyButton){
            Toast.makeText(this, "privacy", Toast.LENGTH_SHORT).show();
        }
        if(view == termOfServiceButton){
            Toast.makeText(this, "term", Toast.LENGTH_SHORT).show();
        }
        if(view == rateAppButton){
            Toast.makeText(this, "rate", Toast.LENGTH_SHORT).show();
        }
    }
}