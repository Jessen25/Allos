package com.example.allos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class ProfilePage extends AppCompatActivity implements View.OnClickListener{

    ImageView exitButton;
    LinearLayout editProfileButton;
    LinearLayout helpButton;
    LinearLayout privacyPolicyButton;
    LinearLayout termOfServiceButton;
    LinearLayout rateAppButton;
    ImageView homeButton;
    ImageView scanButton;
    ImageView profileButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);

        exitButton = findViewById(R.id.exitButton);
        editProfileButton = findViewById(R.id.editProfileButton);
        helpButton = findViewById(R.id.helpButton);
        privacyPolicyButton = findViewById(R.id.privacyPolicyButton);
        termOfServiceButton = findViewById(R.id.termOfServiceButton);
        rateAppButton = findViewById(R.id.rateAppButton);
        homeButton = findViewById(R.id.homeButton);
        scanButton = findViewById(R.id.scanButton);
        profileButton = findViewById(R.id.profileButton);

        exitButton.setOnClickListener(this);
        editProfileButton.setOnClickListener(this);
        helpButton.setOnClickListener(this);
        privacyPolicyButton.setOnClickListener(this);
        termOfServiceButton.setOnClickListener(this);
        rateAppButton.setOnClickListener(this);
        homeButton.setOnClickListener(this);
        scanButton.setOnClickListener(this);
        profileButton.setOnClickListener(this);

    }
    @Override
    public void onClick(View view) {
        if(view == exitButton){
            Toast.makeText(this, "exit", Toast.LENGTH_SHORT).show();
        }
        if(view == editProfileButton){
            Intent profileIntent = new Intent(this, EditProfile.class);
            startActivity(profileIntent);
        }
        if(view == helpButton){
            Intent helpIntent = new Intent(this, HelpPage.class);
            startActivity(helpIntent);
        }
        if(view == privacyPolicyButton){
            Intent privacyIntent = new Intent(this, PrivacyPage.class);
            startActivity(privacyIntent);
        }
        if(view == termOfServiceButton){
            Intent termIntent = new Intent(this, TermPage.class);
            startActivity(termIntent);
        }
        if(view == rateAppButton){
            Intent rateIntent = new Intent(this, RatePage.class);
            startActivity(rateIntent);
        }
        if(view == homeButton){
            Intent homeIntent = new Intent(this, HomePage.class);
            startActivity(homeIntent);
        }
//        if(view == scanButton){
//            Intent homeIntent = new Intent(this, HomePage.class);
//        }
//        if(view == homeButton){
//            Intent homeIntent = new Intent(this, HomePage.class);
//        }
    }
}