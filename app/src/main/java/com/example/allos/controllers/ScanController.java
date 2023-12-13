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
}
