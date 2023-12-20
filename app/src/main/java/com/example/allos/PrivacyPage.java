package com.example.allos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class PrivacyPage extends AppCompatActivity implements View.OnClickListener{

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

        textView.setText("1. Introduction\n" +
                "\n" +
                "Welcome to Allos! This Privacy Policy explains how we collect, use, and protect your personal information. By using Allos, you agree to the terms outlined in this policy.\n" +
                "\n" +
                "2. Information We Collect\n" +
                "\n" +
                "Personal Information:\n" +
                "We may collect information such as your name, email address, and contact details when you register or use certain features of the app.\n" +
                "\nUsage Information:\n" +
                "We gather data on how you interact with the app, including device information, IP address, and usage patterns.\n\n" +
                "3. How We Use Your Information\n" +
                "\n" +
                "We use the collected information for the following purposes:\n" +
                "\n" +
                "Provide Services:\n" +
                "To deliver the features and services offered by Allos.\n\n" +
                "Communicate:\n" +
                "To send you important information, updates, and notifications.\n\n" +
                "Improve User Experience:\n" +
                "To enhance and personalize your experience within the app.\n\n" +
                "Analytics:\n" +
                "To analyze user behavior and improve our app's functionality.\n\n" +
                "4. Information Sharing\n" +
                "\n" +
                "We do not sell, trade, or otherwise transfer your personal information to third parties. However, we may share information with trusted third parties who assist us in operating our app and servicing you.\n" +
                "\n" +
                "5. Security Measures\n" +
                "\n" +
                "We take appropriate measures to protect your personal information from unauthorized access, alteration, disclosure, or destruction.\n" +
                "\n" +
                "6. Your Choices\n" +
                "\n" +
                "You can manage your account settings and preferences within the app. If you wish to delete your account or have questions about your data, please contact us at [Allos@email.com].\n" +
                "\n" +
                "7. Cookies and Tracking Technologies\n" +
                "\n" +
                "Allos may use cookies and similar tracking technologies to enhance user experience. You can manage cookie preferences through your browser settings.\n" +
                "\n" +
                "8. Changes to this Privacy Policy\n" +
                "\n" +
                "We may update this Privacy Policy from time to time. The latest version will be posted on our website, and we encourage you to review it periodically.\n" +
                "\n" +
                "9. Contact Us\n" +
                "\n" +
                "If you have any questions, concerns, or requests regarding your privacy, please contact us at [Allos@email.com].\n" +
                "\n");

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