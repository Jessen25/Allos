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

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class HomePage extends AppCompatActivity implements View.OnClickListener{

    ArrayList<Items> itemList;
    ListView itemListView;
    ImageView homeButton;
    ImageView scanButton;
    ImageView profileButton;

    String currentUser, barcodeId;
    ArrayList<String> barcodeIdList = new ArrayList<>();

    ScanController scanController;

    DatabaseReference database, allergenDBList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        currentUser = getIntent().getStringExtra("username");

        itemList = new ArrayList<>();
//        itemList.add(new Items("123123123", "bengbeng","Beng-Beng",true));
//        itemList.add(new Items("123123123", "piattos","Piattos",false));

        readData(currentUser);

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

//        itemListClicked();
    }

    @Override
    public void onClick(View view) {

        if(view == scanButton){
            scanController.scanCode(this);
        }

        if(view == profileButton){
            Intent profileIntent = new Intent(this, ProfilePage.class);
            profileIntent.putExtra("username", currentUser);
            startActivity(profileIntent);
        }
    }

    private void readData(String username){

        database = FirebaseDatabase.getInstance().getReference("Product");

        allergenDBList = FirebaseDatabase.getInstance().getReference("Allergen").child(username);

        HashMap<String, Boolean> allergenList = new HashMap<>();

        allergenDBList.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    for (DataSnapshot snap: snapshot.getChildren()) {
                        allergenList.put(snap.getValue().toString(), false);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    itemList.clear();

                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        // Assuming each child node represents an item in the list
                        // barcodeId buat dapetin jumlah data di db buat jadi posisi listview
                        barcodeId = String.valueOf(dataSnapshot.child("BarcodeID").getValue());
                        String ProductImage = String.valueOf(dataSnapshot.child("Image").getValue());
                        String ProductName = String.valueOf(dataSnapshot.child("Name").getValue());
                        boolean itemStatus = true;
                        for(DataSnapshot snap: dataSnapshot.child("Ingredient").getChildren()){
                            String ingredient = snap.getValue().toString();
                            if (allergenList.containsKey(ingredient)){
                                itemStatus = false;
                            }
                        }

                        Items item = new Items(barcodeId, ProductImage, ProductName, itemStatus);
                        itemList.add(item);
                        barcodeIdList.add(barcodeId);

                        itemListView.setOnItemClickListener((parent, view, position, id) -> {
                            Items clickedItem = itemList.get(position);
                            itemListClicked(clickedItem.barcodeID);
                        });
                    }

                    ((ItemAdapter) itemListView.getAdapter()).notifyDataSetChanged();


                }else{
                    Toast.makeText(HomePage.this, "product doesnt exist", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(HomePage.this, "failed to read data", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // jgn dihapus dulu in case error
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if(result != null){
            if(result.getContents() != null){
                Intent scanDetailIntent = new Intent(this, ScanDetailPage.class);
                scanDetailIntent.putExtra("BarcodeId", result.getContents());
                scanDetailIntent.putExtra("username", currentUser);
                startActivity(scanDetailIntent);
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(result.getContents());
                builder.setTitle("Scanning Result");
                builder.setPositiveButton("Scan Again", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        scanController.scanCode(HomePage.this);
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

    public void itemListClicked(String barcodeId){
//        Toast.makeText(this, "123123123", Toast.LENGTH_SHORT).show();
        Intent scanDetailIntent = new Intent(this, ScanDetailPage.class);

        scanDetailIntent.putExtra("BarcodeId", barcodeId);
        scanDetailIntent.putExtra("username", currentUser);
        startActivity(scanDetailIntent);
    }
}