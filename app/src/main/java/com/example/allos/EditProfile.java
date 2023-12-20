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
    private EditText usernameText;
    private EditText emailText;
    private String currentUser;
    private String password;
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
        usernameText = findViewById(R.id.usernameEditText);
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
                    usernameText.setText(snapshot.child("Username").getValue().toString());
                    emailText.setText(snapshot.child("UserPassword").getValue().toString());
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
            String username = usernameText.getText().toString();
            String email = emailText.getText().toString();

            String validate = UserController.validateUpdateProfile(name, username, email);
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
                updateProfile(name, username, email);
                errorMsg.setText("Successful");
                Intent profileIntent = new Intent(this, ProfilePage.class);
                profileIntent.putExtra("username", currentUser);
                startActivity(profileIntent);
            }
        }
    }

    private void updateProfile(String name, String username, String email) {
        ArrayList<String> allergen = new ArrayList<>();
        DatabaseReference user = FirebaseDatabase.getInstance().getReference("User").child(currentUser);
        user.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    password = snapshot.child("UserPassword").getValue().toString();
                    snapshot.getRef().removeValue();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        insertNewUser(name, username, email, password);

        DatabaseReference allegenUser = FirebaseDatabase.getInstance().getReference("Allergen").child(currentUser);
        allegenUser.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    for (DataSnapshot snap: snapshot.getChildren()) {
                        allergen.add(snap.getValue().toString());
                    }
                    snapshot.getRef().removeValue();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        insertNewAllergen(username, allergen);

    }
    public void insertNewUser(String name,String username, String email ,String password){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("User");
        database.child(username).child("Name").setValue(name);
        database.child(username).child("UserPassword").setValue(password);
        database.child(username).child("Username").setValue(username);
        database.child(username).child("UserEmail").setValue(email);

    }
    public void insertNewAllergen(String username, ArrayList<String> allergen) {
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("Allergen");
        Integer index = 1;
        for (String aller: allergen) {
            database.child(username).child(index.toString()).setValue(aller);
            index++;
        }
    }
}