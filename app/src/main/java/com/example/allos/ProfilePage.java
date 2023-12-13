package com.example.allos;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class ProfilePage extends AppCompatActivity implements View.OnClickListener{

    ImageView exitButton;
    LinearLayout editProfileButton;
    LinearLayout helpButton;
    LinearLayout privacyPolicyButton;
    LinearLayout termOfServiceButton;
    LinearLayout rateAppButton;
    ImageView homeButton;
    ImageView scanButton;
    ImageView profileButton;

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

        exitButton.setOnClickListener(this);
        editProfileButton.setOnClickListener(this);
        helpButton.setOnClickListener(this);
        privacyPolicyButton.setOnClickListener(this);
        termOfServiceButton.setOnClickListener(this);
        rateAppButton.setOnClickListener(this);
        homeButton.setOnClickListener(this);
        scanButton.setOnClickListener(this);
        profileButton.setOnClickListener(this);

    }
    @Override
    public void onClick(View view) {
        if(view == exitButton){
            Intent loginIntent = new Intent(this, LoginPage.class);
            startActivity(loginIntent);
        }
        if(view == editProfileButton){
            Intent profileIntent = new Intent(this, EditProfile.class);
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
            startActivity(homeIntent);
        }
        if(view == scanButton){
//            Intent homeIntent = new Intent(this, ScanPage.class);
//            startActivity(homeIntent);
            scanCode();
        }
        if(view == profileButton){
            Intent profileIntent = new Intent(this, ProfilePage.class);
            startActivity(profileIntent);
        }
    }

    private void scanCode(){

        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setCaptureActivity(ScanPage.class);
        integrator.setOrientationLocked(false);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        integrator.setPrompt("Scanning Code");
        integrator.initiateScan();
    }

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
                        scanCode();
                    }
                }).setNegativeButton("Finish", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
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

