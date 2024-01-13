package com.example.allos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.Firebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.example.allos.controllers.UserController;
import java.util.ArrayList;

public class RegisterPage2 extends AppCompatActivity implements View.OnClickListener {

    private ArrayList<String> allergen;
    private Button regButton;
    private EditText nameEdit, usernameEdit, emailEdit, passwordEdit;
    private TextView errorMsg;

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
        errorMsg = findViewById(R.id.errorMsg);

        allergen = getIntent().getStringArrayListExtra("allergens");
    }

    @Override
    public void onClick(View v) {
        if(v == regButton){
            String name = nameEdit.getText().toString();
            String username = usernameEdit.getText().toString();
            String email = emailEdit.getText().toString();
            String password = passwordEdit.getText().toString();

            String validate = "false";
            validate = UserController.validateRegisterUser(name, username, email, password);
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
            if (validate.equals("password")){
                errorMsg.setText("Password must not be empty");
            }
            if (validate.equals("success")){
                insertNewUser(name, username, email, password);
                insertNewAllergen(username);
                Intent loginIntent = new Intent(this, LoginPage.class);
                startActivity(loginIntent);
            }
        }
    }

    public void insertNewAllergen(String username) {
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("Allergen");
        Integer index = 1;
        for (String aller: allergen) {
            database.child(username).child(index.toString()).setValue(aller);
            index++;
        }
    }

    public void insertNewUser(String name,String username, String email ,String password){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("User");
        database.child(username).child("Name").setValue(name);
        database.child(username).child("UserPassword").setValue(password);
        database.child(username).child("Username").setValue(username);
        database.child(username).child("UserEmail").setValue(email);
    }
}