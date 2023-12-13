package com.example.allos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.Firebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterPage2 extends AppCompatActivity implements View.OnClickListener {


    Button regButton;
    EditText nameEdit, usernameEdit, emailEdit, passwordEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page2);

        regButton = findViewById(R.id.registerButton);

        regButton.setOnClickListener(this);
        nameEdit = findViewById(R.id.nameEdit);
        usernameEdit = findViewById(R.id.usernameEdit);
        emailEdit = findViewById(R.id.emailEdit);
        passwordEdit = findViewById(R.id.passwordEdit);
    }

    @Override
    public void onClick(View v) {
        if(v == regButton){
            String name = nameEdit.getText().toString();
            String username = usernameEdit.getText().toString();
            String email = emailEdit.getText().toString();
            String password = passwordEdit.getText().toString();

            //todo: bikin controller buat validasi



            insertNewUser(name, username, email, password);

        }
    }

    public void insertNewUser(String name,String username, String email ,String password){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("User");
        database.child(username).child("Name").setValue(name);
        database.child(username).child("UserPassword").setValue(password);
        database.child(username).child("Username").setValue(username);
        database.child(username).child("UserEmail").setValue(email);
        // todo: pasing intent ke login
    }
}