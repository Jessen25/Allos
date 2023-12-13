package com.example.allos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class EditProfile extends AppCompatActivity implements View.OnClickListener{
    ImageView cancelIcon;
    ImageView confirmIcon;

    String currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        cancelIcon = findViewById(R.id.cancelIcon);
        confirmIcon = findViewById(R.id.confirmIcon);

        cancelIcon.setOnClickListener(this);
        confirmIcon.setOnClickListener(this);

        currentUser = getIntent().getStringExtra("username").toString();
    }

    @Override
    public void onClick(View view) {
        if(view == cancelIcon){
            Intent profileIntent = new Intent(this, ProfilePage.class);
            profileIntent.putExtra("username", currentUser);
            startActivity(profileIntent);
        }
        if(view == confirmIcon){
            Intent profileIntent = new Intent(this, ProfilePage.class);
            profileIntent.putExtra("username", currentUser);
            startActivity(profileIntent);
        }
    }
}