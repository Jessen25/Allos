package com.example.allos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class HelpPage extends AppCompatActivity implements View.OnClickListener{

    private ImageView backButton;
    private TextView textView;
    private String currUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_page);

        backButton = findViewById(R.id.backButton);
        textView = findViewById(R.id.textView);
        currUser = getIntent().getStringExtra("username");

        textView.setText
                (
                "Getting Started\n" +
                "1. Welcome to Allos\n\n" +
                "Thank you for choosing Allos to help manage your allergies. This guide will assist you in navigating the app's features and ensuring a smooth experience.\n" +
                "\n" +
                "Features Overview\n" +
                "2. Allergy Detection Scan\n\n" +
                "Use the allergy detection scan to identify potential allergens in food items. Follow these steps:\n" +
                "\n" +
                "Open the app and navigate to the \"Scan\" tab.\n" +
                "Aim your camera at the food item's label or ingredients list.\n" +
                "Wait for the app to analyze the information and provide results.\n\n" +
                "Tips:" +
                "\n" +
                "- Ensure good lighting for accurate scanning.\n" +
                "- Check for updates regularly to enhance allergen detection accuracy.\n\n" +
                "3. Scan Issues\n\n" +
                "If you encounter issues with the allergy detection scan, consider the following:\n" +
                "\n" +
                "- Ensure a stable internet connection.\n" +
                "- Clean your camera lens for optimal scanning.\n" +
                "- Update the app to the latest version for bug fixes.\n\n" +
                "Contact Support:\n" +
                "If problems persist, reach out to our support team at [Allos@email.com].\n" +
                "\n" +
                "FAQs\n" +
                "4. Common Questions\n\n" +
                "Q: Is the app suitable for severe allergies?\n" +
                "A: Allos is designed to assist users with allergies, but always consult with a healthcare professional for severe cases.\n" +
                "\n" +
                "Q: How often should I update the app?\n" +
                "A: We recommend updating the app regularly to access the latest features and improvements.\n"
                );

        backButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == backButton){
            Intent profileIntent = new Intent(this, ProfilePage.class);
            profileIntent.putExtra("username", currUser);
            startActivity(profileIntent);
        }
    }
}