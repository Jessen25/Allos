package com.example.allos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AllergenDetailPage extends AppCompatActivity {

    String ingredientName;

    TextView titleTextView;
    TextView bodyTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allergen_detail_page);

        titleTextView = findViewById(R.id.titleTextView);
        bodyTextView = findViewById(R.id.bodyTextView);

        ingredientName = getIntent().getStringExtra("ingredientName");

        getDataFromDB(ingredientName);


    }

    public void getDataFromDB(String ingredientName){

        DatabaseReference database = FirebaseDatabase.getInstance().getReference("AllergyInfo");

        database.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    String bodyText = String.valueOf(snapshot.child(ingredientName).getValue());
                    titleTextView.setText(ingredientName);
                    bodyTextView.setText(bodyText);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}