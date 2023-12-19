package com.example.allos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.allos.controllers.ScanController;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class ProfilePage extends AppCompatActivity implements View.OnClickListener{

    String currentUser;
    ImageView exitButton;
    LinearLayout editProfileButton;
    LinearLayout helpButton;
    LinearLayout privacyPolicyButton;
    LinearLayout termOfServiceButton;
    LinearLayout rateAppButton;
    ImageView homeButton;
    ImageView scanButton;
    ImageView profileButton;
    TextView profileText;
    TextView emailText;

    ScanController scanController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);

        exitButton = findViewById(R.id.exitButton);
        editProfileButton = findViewById(R.id.editProfileButton);
        helpButton = findViewById(R.id.helpButton);
        privacyPolicyButton = findViewById(R.id.privacyPolicyButton);
        termOfServiceButton = findViewById(R.id.termOfServiceButton);
        rateAppButton = findViewById(R.id.rateAppButton);
        homeButton = findViewById(R.id.homeButton);
        scanButton = findViewById(R.id.scanButton);
        profileButton = findViewById(R.id.profileButton);
        profileText = findViewById(R.id.profileText);
        emailText= findViewById(R.id.emailText);

        exitButton.setOnClickListener(this);
        editProfileButton.setOnClickListener(this);
        helpButton.setOnClickListener(this);
        privacyPolicyButton.setOnClickListener(this);
        termOfServiceButton.setOnClickListener(this);
        rateAppButton.setOnClickListener(this);
        homeButton.setOnClickListener(this);
        scanButton.setOnClickListener(this);
        profileButton.setOnClickListener(this);

        scanController = new ScanController();

        currentUser = getIntent().getStringExtra("username");

        setProfile();

    }

    private void setProfile() {
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("User");
        database.child(currentUser).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    profileText.setText("Hello, " + snapshot.child("Name").getValue().toString());
                    emailText.setText("@" + snapshot.child("Username").getValue().toString());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        if(view == exitButton){
            Intent loginIntent = new Intent(this, LoginPage.class);
            loginIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(loginIntent);
        }
        if(view == editProfileButton){
            Intent profileIntent = new Intent(this, EditProfile.class);
            profileIntent.putExtra("username", currentUser);
            startActivity(profileIntent);
        }
        if(view == helpButton){
            Intent helpIntent = new Intent(this, HelpPage.class);
            startActivity(helpIntent);
        }
        if(view == privacyPolicyButton){
            Intent privacyIntent = new Intent(this, PrivacyPage.class);
            startActivity(privacyIntent);
        }
        if(view == termOfServiceButton){
            Intent termIntent = new Intent(this, TermPage.class);
            startActivity(termIntent);
        }
        if(view == rateAppButton){
            Intent rateIntent = new Intent(this, RatePage.class);
            startActivity(rateIntent);
        }
        if(view == homeButton){
            Intent homeIntent = new Intent(this, HomePage.class);
            homeIntent.putExtra("username", currentUser);
            startActivity(homeIntent);
        }
        if(view == scanButton){
            scanController.scanCode(this);
        }
        if(view == profileButton){
            Intent profileIntent = new Intent(this, ProfilePage.class);
            startActivity(profileIntent);
        }
    }

//    private void scanCode(){
//
//        IntentIntegrator integrator = new IntentIntegrator(this);
//        integrator.setCaptureActivity(ScanPage.class);
//        integrator.setOrientationLocked(false);
//        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
//        integrator.setPrompt("Scanning Code");
//        integrator.initiateScan();
//    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if(result != null){
            if(result.getContents() != null){
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(result.getContents());
                builder.setTitle("Scanning Result");
                builder.setPositiveButton("Scan Again", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        scanController.scanCode(ProfilePage.this);
                    }
                });
//                        setNegativeButton("Finish", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        finish();
//                    }
//                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
            else{
                Toast.makeText(this, "No Results", Toast.LENGTH_LONG).show();
            }
        }
        else{
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}

