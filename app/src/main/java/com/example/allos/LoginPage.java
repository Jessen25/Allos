package com.example.allos;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class LoginPage extends AppCompatActivity implements View.OnClickListener{

    Button loginButton;
    EditText userEdit, passEdit;

    private DatabaseReference root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        loginButton = findViewById(R.id.loginButton);
        userEdit = findViewById(R.id.usernameEdit);
        passEdit = findViewById(R.id.passEdit);


        loginButton.setOnClickListener(this);

        root = FirebaseDatabase.getInstance().getReference();
    }

    @Override
    public void onClick(View v) {
        if(v == loginButton) {
//            Toast.makeText(this, "COk", Toast.LENGTH_SHORT).show();
            root.child("User").orderByChild("UserName").equalTo("Jessen").addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                    if (snapshot.exists()){
                        String data1 = snapshot.getValue().toString();
                        Toast.makeText(LoginPage.this, data1, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(LoginPage.this, "Null", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

//            root.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
//                @Override
//                public void onComplete(@NonNull Task<DataSnapshot> task) {
//                    if (task.isSuccessful()){
//                        DataSnapshot doc = task.getResult();
//                        if (doc.exists()){
//
//                        }
//                    }
//                }
//            });
        }
    }
}