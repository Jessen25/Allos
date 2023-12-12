package com.example.allos.controllers;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.example.allos.HomePage;
import com.example.allos.ScanPage;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class ScanController {

    public void scanCode(Activity activity){

        IntentIntegrator integrator = new IntentIntegrator(activity);
        integrator.setCaptureActivity(ScanPage.class);
        integrator.setOrientationLocked(false);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        integrator.setPrompt("Scanning Product's Barcode");
        integrator.initiateScan();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data, Activity activity, Activity sourceActivity){
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if(result != null){
            if(result.getContents() != null){
                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                builder.setMessage(result.getContents());
                builder.setTitle("Scanning Result");
                builder.setPositiveButton("Scan Again", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        scanCode(sourceActivity);
                    }
                }).setNegativeButton("Finish", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        activity.finish();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
            else{
                Toast.makeText(activity, "No Results", Toast.LENGTH_LONG).show();
            }
        }
        else{
            onActivityResult(requestCode, resultCode, data, activity, sourceActivity);
        }
    }
}
