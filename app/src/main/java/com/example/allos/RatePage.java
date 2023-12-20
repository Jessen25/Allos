package com.example.allos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class RatePage extends AppCompatActivity implements View.OnClickListener{

    private ImageView backButton;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_page);

        backButton = findViewById(R.id.backButton);
        textView = findViewById(R.id.textView);

        textView.setText("Thank you for choosing Allos! We value your feedback, and your opinion matters to us. If you enjoy using Allos, please take a moment to rate and review the app. Your feedback helps us improve and enhances the experience for everyone.\n" +
                "\n" +
                "How to Rate Allos:\n" +
                "\n" +
                "1. Open the [App Store/Google Play Store].\n" +
                "2. Search for \"Allos.\"\n" +
                "3. Click on the Allos app icon.\n" +
                "4. Scroll down to the \"Ratings & Reviews\" section.\n" +
                "5. Tap on \"Write a Review.\"\n\n" +
                "Why Rate Allos:\n" +
                "\n" +
                "- Help Others: Your review can guide other users in making informed decisions about using Allos.\n" +
                "- Continuous Improvement: Your feedback helps us understand what you love and areas where we can improve.\n" +
                "- Support the Community: By sharing your positive experiences, you contribute to a thriving Allos community.\n\n" +
                "We Value Your Feedback:\n" +
                "\n" +
                "Whether it's a suggestion, a bug report, or a positive experience you'd like to share, we want to hear from you. Contact us at allos@gmail.com with your thoughts, and we'll do our best to address any concerns or questions.\n" +
                "\n" +
                "Stay Connected:\n" +
                "\n" +
                "Follow us on social media for updates, tips, and more:\n" +
                "\n" +
                "Facebook: [https://www.facebook.com/AllosApp]\n" +
                "Twitter: [https://twitter.com/AllosApp]\n" +
                "Instagram: [https://www.instagram.com/AllosApp]\n\n" +
                "Thank you for being a part of the Allos community! Your support drives us to continuously enhance your allergy management experience.\n" +
                "\n");

        backButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == backButton){
            Intent profileIntent = new Intent(this, ProfilePage.class);
            startActivity(profileIntent);
        }
    }
}