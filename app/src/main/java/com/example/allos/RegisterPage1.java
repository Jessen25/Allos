package com.example.allos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

public class RegisterPage1 extends AppCompatActivity implements View.OnClickListener{


    Button registerButton;
    CheckBox[] checkBoxes;

    TextView loginText;

    public ArrayList<String> allergen = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page1);


        registerButton = findViewById(R.id.registerButton);
        registerButton.setOnClickListener(this);

        checkBoxes = new CheckBox[]{
                findViewById(R.id.checkbox1),
                findViewById(R.id.checkbox2),
                findViewById(R.id.checkbox3),
                findViewById(R.id.checkbox4),
                findViewById(R.id.checkbox5),
                findViewById(R.id.checkbox6),
                findViewById(R.id.checkbox7),
                findViewById(R.id.checkbox8),
                findViewById(R.id.checkbox9)
        };

//        checkBox1 = findViewById(R.id.checkbox1);
//        checkBox2 = findViewById(R.id.checkbox2);
//        checkBox3 = findViewById(R.id.checkbox3);
//        checkBox4 = findViewById(R.id.checkbox4);
//        checkBox5 = findViewById(R.id.checkbox5);
//        checkBox6 = findViewById(R.id.checkbox6);
//        checkBox7 = findViewById(R.id.checkbox7);
//        checkBox8 = findViewById(R.id.checkbox8);
//        checkBox9 = findViewById(R.id.checkbox9);

        loginText = findViewById(R.id.loginText);
        loginText.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == registerButton){

            // todo: KALO BISA PAKE LOOPING JADIIN LOOPING AJA THX LOL

            // todo: bikin login here bekerja

            for (CheckBox checkBox : checkBoxes) {
                if (checkBox.isChecked()){
                    allergen.add(checkBox.getText().toString());
                }
            }

//            if (checkBox1.isChecked()){
//                allergen.add(checkBox1.getText().toString());
//            }  if (checkBox2.isChecked()){
//                allergen.add(checkBox2.getText().toString());
//            }  if (checkBox3.isChecked()){
//                allergen.add(checkBox3.getText().toString());
//            }  if (checkBox4.isChecked()){
//                allergen.add(checkBox4.getText().toString());
//            }  if (checkBox5.isChecked()){
//                allergen.add(checkBox5.getText().toString());
//            }  if (checkBox6.isChecked()){
//                allergen.add(checkBox6.getText().toString());
//            }  if (checkBox7.isChecked()){
//                allergen.add(checkBox7.getText().toString());
//            }  if (checkBox8.isChecked()){
//                allergen.add(checkBox8.getText().toString());
//            }  if (checkBox9.isChecked()){
//                allergen.add(checkBox9.getText().toString());
//            }

            Intent nextRegPage = new Intent(this, RegisterPage2.class);
            nextRegPage.putStringArrayListExtra("allergens", allergen);
            startActivity(nextRegPage);
        }

        if (v == loginText){
            Intent loginIntent = new Intent(this, LoginPage.class);
            startActivity(loginIntent);
        }
    }
}