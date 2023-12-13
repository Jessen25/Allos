package com.example.allos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


public class LoginPage extends AppCompatActivity implements View.OnClickListener{

    Button loginButton;
    EditText userEdit, passEdit;
    DatabaseReference database;

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
            // TODO: 13/12/2023 bikin controller check user login contoh if empty dan lain lain;
            String username = userEdit.getText().toString();
            String password = passEdit.getText().toString();

            if(!username.isEmpty() && !password.isEmpty()){
                readData(username, password);
            }else{
                Toast.makeText(LoginPage.this, "Failed", Toast.LENGTH_SHORT).show();
            }


        }
    }

    private void readData(String username, String password){

        database = FirebaseDatabase.getInstance().getReference("User");
        database.child(username).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    String databasePass = String.valueOf(snapshot.child("UserPassword").getValue());
                    if (databasePass.equals(password)){
                        Toast.makeText(LoginPage.this, "Success", Toast.LENGTH_SHORT).show();
                        Intent homeIntent = new Intent(LoginPage.this, HomePage.class);
                        homeIntent.putExtra("username", username);
                        startActivity(homeIntent);
                    } else {
                        Toast.makeText(LoginPage.this, "Failed", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(LoginPage.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(LoginPage.this, "Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}