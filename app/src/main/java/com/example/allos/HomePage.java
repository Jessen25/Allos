package com.example.allos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

public class HomePage extends AppCompatActivity implements View.OnClickListener{

    ArrayList<Items> itemList;
    ListView itemListView;
    ImageView homeButton;
    ImageView scanButton;
    ImageView profileButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        itemList = new ArrayList<>();
        itemList.add(new Items("bengbeng","Beng-Beng",true));
        itemList.add(new Items("piattos","Piattos",false));

        itemListView = findViewById(R.id.itemListView);

        ItemAdapter itemAdapter = new ItemAdapter(this, itemList);
        itemListView.setAdapter(itemAdapter);

        homeButton = findViewById(R.id.homeButton);
        scanButton = findViewById(R.id.scanButton);
        profileButton = findViewById(R.id.profileButton);

        homeButton.setOnClickListener(this);
        scanButton.setOnClickListener(this);
        profileButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == homeButton){
            Intent homeIntent = new Intent(this, HomePage.class);
            startActivity(homeIntent);
        }
//        if(view == scanButton){
////            Intent homeIntent = new Intent(this, ScanPage.class);
////            startActivity(homeIntent);
//            scanCode();
//        }
        if(view == profileButton){
            Intent profileIntent = new Intent(this, ProfilePage.class);
            startActivity(profileIntent);
        }
    }
}