package com.example.allos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class TermPage extends AppCompatActivity implements View.OnClickListener{

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

        textView.setText("1. Acceptance of Terms\n" +
                "\n" +
                "Welcome to Allos! By using our app, you agree to comply with and be bound by these Terms of Service. If you do not agree with any part of these terms, please do not use the app.\n" +
                "\n" +
                "2. Use of the App\n" +
                "\n" +
                "- You must be at least 13 years old to use Allos.\n" +
                "- You are responsible for maintaining the confidentiality of your account information and password.\n" +
                "- You agree to use Allos for lawful purposes only.\n\n" +
                "3. User Content\n" +
                "\n" +
                "By using Allos, you may submit content, including but not limited to text, images, and other materials. You retain ownership of your content, but by submitting, you grant Allos a worldwide, non-exclusive, royalty-free license to use, reproduce, modify, distribute, and display the content.\n" +
                "\n" +
                "4. Prohibited Conduct\n" +
                "\n" +
                "You agree not to:\n" +
                "\n" +
                "Violate any applicable laws or regulations.\n" +
                "Infringe upon the rights of others.\n" +
                "Use Allos for any unlawful or fraudulent purpose.\n" +
                "Interfere with the operation of the app.\n\n" +
                "5. Intellectual Property\n" +
                "\n" +
                "Allos and its original content, features, and functionality are owned by Allos and are protected by international copyright, trademark, patent, trade secret, and other intellectual property or proprietary rights laws.\n" +
                "\n" +
                "6. Termination of Services\n" +
                "\n" +
                "Allos reserves the right to terminate or suspend access to Allos at any time, without prior notice, for any reason, including, but not limited to, a breach of these Terms.\n" +
                "\n" +
                "7. Disclaimer of Warranties\n" +
                "\n" +
                "Allos is provided \"as is,\" without any warranties of any kind, whether express or implied. Allos does not warrant that the app will be error-free or uninterrupted.\n" +
                "\n" +
                "8. Limitation of Liability\n" +
                "\n" +
                "Allos shall not be liable for any indirect, incidental, special, consequential, or punitive damages, or any loss of profits or revenues, whether incurred directly or indirectly.\n" +
                "\n" +
                "9. Changes to Terms\n" +
                "\n" +
                "Allos reserves the right to update or modify these Terms of Service at any time without prior notice. The latest version will be posted on our website.\n" +
                "\n" +
                "10. Contact Us\n" +
                "\n" +
                "If you have any questions or concerns about these Terms of Service, please contact us at allos@gmail.com.");

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