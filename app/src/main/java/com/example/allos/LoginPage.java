package com.example.allos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.example.allos.controllers.UserController;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class LoginPage extends AppCompatActivity implements View.OnClickListener{

    private Button loginButton;
    private EditText userEdit, passEdit;
    private DatabaseReference database;
    private TextView registerText, errorMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        loginButton = findViewById(R.id.loginButton);
        userEdit = findViewById(R.id.usernameEdit);
        passEdit = findViewById(R.id.passEdit);
        registerText = findViewById(R.id.registerText);

        SpannableString spannableString = new SpannableString("Don't have an account? Sign Up");

        int signUpStartIndex = spannableString.toString().indexOf("Sign Up");
        int signUpEndIndex = signUpStartIndex + "Sign Up".length();
        spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#6F12E7")),
                signUpStartIndex, signUpEndIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        errorMsg = findViewById(R.id.errorMsg);



        loginButton.setOnClickListener(this);
        registerText.setOnClickListener(this);

        registerText.setText(spannableString);

    }

    @Override
    public void onClick(View v) {
        if(v == loginButton){
            String username = userEdit.getText().toString();
            String password = passEdit.getText().toString();

            String validate = "false";
            validate = UserController.validateLoginUser(username, password);
            if (validate.equals("username")){
                errorMsg.setText("Username must not be empty");
            }
            if (validate.equals("alphanumeric")){
                errorMsg.setText("Username must only contain Alphabet or Numeric");
            }
            if (validate.equals("password")){
                errorMsg.setText("Password must not be empty");
            }
            if (validate.equals("success")){
                readData(username, password);
            }
        }

        if (v == registerText){
            Intent registerIntent = new Intent(this, RegisterPage1.class);
            startActivity(registerIntent);
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
                        Intent homeIntent = new Intent(LoginPage.this, HomePage.class);
                        homeIntent.putExtra("username", username);
                        homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(homeIntent);
                    } else {
                        errorMsg.setText("Password is incorrect");
                    }
                }else{
                    errorMsg.setText("Username not found");
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                errorMsg.setText("Database error");
            }
        });
    }
}