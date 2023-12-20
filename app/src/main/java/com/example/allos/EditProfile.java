package com.example.allos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.allos.controllers.UserController;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class EditProfile extends AppCompatActivity implements View.OnClickListener{
    private ImageView cancelIcon;
    private ImageView confirmIcon;
    private EditText nameText;
    private EditText emailText;
    private String currentUser;
    private TextView errorMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        cancelIcon = findViewById(R.id.cancelIcon);
        confirmIcon = findViewById(R.id.confirmIcon);
        cancelIcon.setOnClickListener(this);
        confirmIcon.setOnClickListener(this);

        nameText = findViewById(R.id.nameEditText);
        emailText = findViewById(R.id.emailEditText);
        errorMsg = findViewById(R.id.errorMsg);

        currentUser = getIntent().getStringExtra("username").toString();
        setProfile();
    }

    private void setProfile() {
        DatabaseReference user = FirebaseDatabase.getInstance().getReference("User").child(currentUser);
        user.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    nameText.setText(snapshot.child("Name").getValue().toString());
                    emailText.setText(snapshot.child("UserEmail").getValue().toString());
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        if(view == cancelIcon){
            Intent profileIntent = new Intent(this, ProfilePage.class);
            profileIntent.putExtra("username", currentUser);
            startActivity(profileIntent);
        }
        if(view == confirmIcon){
            String name = nameText.getText().toString();
            String email = emailText.getText().toString();

            String validate = UserController.validateUpdateProfile(name, email);
            if (validate.equals("name")){
                errorMsg.setText("Name must not be empty");
            }
            if (validate.equals("username")){
                errorMsg.setText("Username must not be empty");
            }
            if (validate.equals("alphanumeric")){
                errorMsg.setText("Username must only contain Alphabet or Numeric");
            }
            if (validate.equals("email")){
                errorMsg.setText("Email must not be empty");
            }
            if (validate.equals("success")){
                updateProfile(name, email);
                errorMsg.setText("Successful");
                Intent profileIntent = new Intent(this, ProfilePage.class);
                profileIntent.putExtra("username", currentUser);
                startActivity(profileIntent);
            }
        }
    }

    private void updateProfile(String name, String email) {
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("User").child(currentUser);
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                database.child("Name").setValue(name);
                database.child("UserEmail").setValue(email);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}