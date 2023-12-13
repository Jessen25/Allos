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
            String username = userEdit.getText().toString();
            if(!username.isEmpty()){
                readData(username);
                Intent intent = new Intent(this, HomePage.class);
                startActivity(intent);
            }else{
                passEdit.setText("Fail");
            }

//            database = FirebaseDatabase.getInstance().getReference("anjing");
//            database.setValue("Testing4");
        }
    }

    private void readData(String username){

        database = FirebaseDatabase.getInstance().getReference("User");
        database.child(username).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    String firstname = String.valueOf(snapshot.child("UserName").getValue());
                    passEdit.setText(firstname);
                }else{
                    passEdit.setText("user doesnt exist");
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                passEdit.setText("failed");
            }
        });
    }
}