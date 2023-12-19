package com.example.allos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class ScanDetailPage extends AppCompatActivity{

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
//        username = "Fatih1";
//        barcodeId = "711844330009";
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
                if (snapshot.exists()) {
                    String name = String.valueOf(snapshot.child("Name").getValue());
                    String image = String.valueOf(snapshot.child("Image").getValue());
                    productName.setText(name);

                    SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
                    SpannableStringBuilder originalListBuilder = new SpannableStringBuilder();
                    SpannableStringBuilder warningListBuilder = new SpannableStringBuilder();

                    int index = 1;

                    for (DataSnapshot snap : snapshot.child("Ingredient").getChildren()) {
                        String ingredient = snap.getValue().toString();
                        String text = index + ". " + ingredient + "\n";
                        String text2 = ", " + ingredient ;

                        originalListBuilder.append(text);

                        if (allergenList.containsKey(ingredient)) {
                            spannableStringBuilder.append(text);
                            warningListBuilder.append(text2);

                            ClickableSpan clickableSpan = createClickableSpan(ingredient, index);

                            int startIndex = spannableStringBuilder.length() - text.length();
                            int endIndex = spannableStringBuilder.length() - 1;
                            spannableStringBuilder.setSpan(clickableSpan, startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        }

                        index++;
                    }

                    listIngredient.setText("List of Ingredients: \n");
                    listIngredientDetail.setText(originalListBuilder);

                    suspectIngridientList.setText("Dangerous Ingredients: \n");


                    if(spannableStringBuilder.length() == 0){
                        warningText.setText("This product is SAFE to consume");
                        suspectIngridientListDetail.setText("None");
                    } else {
                        warningText.setText("WARNING this product can trigger your allergy,\nit contains");
                        warningText.setTextColor(Color.RED);
                        warningText.append(warningListBuilder);
                        suspectIngridientListDetail.setText(spannableStringBuilder);
                        suspectIngridientListDetail.setMovementMethod(LinkMovementMethod.getInstance());
                        suspectIngridientListDetail.setTextColor(Color.RED);
                    }


                }
            }

            private ClickableSpan createClickableSpan(final String ingredient, final int index) {
                return new ClickableSpan() {
                    @Override
                    public void onClick(@NonNull View view) {
                        handleSuspectedIngredientClick(ingredient, index);
                    }
                };
            }

            private void handleSuspectedIngredientClick(String ingredient, int index) {
                Toast.makeText(ScanDetailPage.this, ingredient, Toast.LENGTH_SHORT).show();

//                Intent dangerousItemDetail = new Intent(this, dangerousItemDetail.class);
//                dangerousItemDetail.putExtra("ingredientName", ingredient);
//                startActivity(dangerousItemDetail);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}