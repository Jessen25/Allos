package com.example.allos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class ScanDetailPage extends AppCompatActivity {

    private String barcodeId;
    private String username;
    private ImageView image;
    private TextView productName;
    private TextView warningText;
    private TextView suspectIngridientList;
    private TextView suspectIngridientListDetail;
    private TextView listIngredient;
    private TextView listIngredientDetail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_detail_page);

        barcodeId = getIntent().getStringExtra("BarcodeId");
        username = getIntent().getStringExtra("username");
        productName = findViewById(R.id.productName);
        image = findViewById(R.id.productImage);
        warningText = findViewById(R.id.warningText);
        suspectIngridientList = findViewById(R.id.suspectIngridientList);
        suspectIngridientListDetail = findViewById(R.id.suspectedIngridientListDetail);
        listIngredient = findViewById(R.id.ingredientList);
        listIngredientDetail = findViewById(R.id.ingredientListDetail);

        initialize();
    }

    private void initialize(){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("Product").child(barcodeId);
        DatabaseReference allergenDB = FirebaseDatabase.getInstance().getReference("Allergen").child(username);

        HashMap<String, Boolean> allergenList = new HashMap<>();
        allergenDB.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    for (DataSnapshot snap: snapshot.getChildren()) {
                        allergenList.put(snap.getValue().toString(), true);
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
                if (snapshot.exists()){
                    String name = String.valueOf(snapshot.child("Name").getValue());
                    String image = String.valueOf(snapshot.child("Image").getValue());
                    productName.setText(name);
                    listIngredient.setText("List of Ingridients: \n");
                    suspectIngridientList.setText("List of Suspected\nIngredients: \n");
                    String text = "";
                    String text2 = "";
                    int index = 1;
                    int index2 = 1;
                    for(DataSnapshot snap: snapshot.child("Ingredient").getChildren()){
                        String ingredient = snap.getValue().toString();
                        text += index + ". " + ingredient + "\n";
                        if (allergenList.containsKey(ingredient) == true){
                            text2 += index2 + ". " + ingredient + "\n";
                            index2++;
                        }
                        index++;
                        listIngredientDetail.setText(text);
                        suspectIngridientListDetail.setText(text2);
                    }
                    if (index2 == 1){
                        suspectIngridientList.setText("No suspected ingredients");
                    }
                    suspectIngridientListDetail.setTextColor(Color.RED);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

}