package com.example.allos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.allos.controllers.ScanController;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;

public class HomePage extends AppCompatActivity implements View.OnClickListener{

    ArrayList<Items> itemList;
    ListView itemListView;
    ImageView homeButton;
    ImageView scanButton;
    ImageView profileButton;

    String currentUser;

    ScanController scanController;

    DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        itemList = new ArrayList<>();
        itemList.add(new Items("bengbeng","Beng-Beng",true));
        itemList.add(new Items("piattos","Piattos",false));

//        readData();

        itemListView = findViewById(R.id.itemListView);

        ItemAdapter itemAdapter = new ItemAdapter(this, itemList);
        itemListView.setAdapter(itemAdapter);

        homeButton = findViewById(R.id.homeButton);
        scanButton = findViewById(R.id.scanButton);
        profileButton = findViewById(R.id.profileButton);

        homeButton.setOnClickListener(this);
        scanButton.setOnClickListener(this);
        profileButton.setOnClickListener(this);

        scanController = new ScanController();

        currentUser = getIntent().getStringExtra("username").toString();
    }

    @Override
    public void onClick(View view) {

        if(view == scanButton){
//            Intent homeIntent = new Intent(this, ScanPage.class);
//            startActivity(homeIntent);
            scanController.scanCode(this);

//            int requestCode = Integer.parseInt(null);
//            int resultCode = Integer.parseInt(null);
//            Intent data = null;
//            scanController.onActivityResult
//                    (requestCode, resultCode, data, this, this);
        }

        if(view == profileButton){
            Intent profileIntent = new Intent(this, ProfilePage.class);
            profileIntent.putExtra("username", currentUser);
            startActivity(profileIntent);
        }
    }

    private void readData(){

        database = FirebaseDatabase.getInstance().getReference("Product");
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    itemList.clear();

                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        // Assuming each child node represents an item in the list
                        String barcodeId = dataSnapshot.getKey();
                        String ProductImage = String.valueOf(dataSnapshot.child("ProductImage").getValue());
                        String ProductName = String.valueOf(dataSnapshot.child("ProductName").getValue());
                        boolean itemStatus = (boolean) dataSnapshot.child("ProductAllowed").getValue();

                        Items item = new Items(ProductImage, ProductName, itemStatus);
                        itemList.add(item);
                    }

                    ((ItemAdapter) itemListView.getAdapter()).notifyDataSetChanged();
                }else{
                    Toast.makeText(HomePage.this, "product doesnt exist", Toast.LENGTH_SHORT).show();
//                    passEdit.setText("user doesnt exist");
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(HomePage.this, "failed to read data", Toast.LENGTH_SHORT).show();
//                passEdit.setText("failed");
            }
        });
    }

    // jgn dihapus dulu in case error
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if(result != null){
            if(result.getContents() != null){

                // pindah page
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(result.getContents());
                builder.setTitle("Scanning Result");
                builder.setPositiveButton("Scan Again", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        scanController.scanCode(HomePage.this);
                    }
                });
//                        setNegativeButton("Finish", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        // do an intent back to home page
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