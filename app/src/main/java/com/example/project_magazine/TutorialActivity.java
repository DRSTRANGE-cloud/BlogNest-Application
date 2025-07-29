package com.example.project_magazine;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TutorialActivity extends AppCompatActivity {

    private final String[] slideDescriptions = {
            "Enter your Email ID and Password in the respective fields.\n\n" +
                    "Click the Login button to access your account.\n\n" +
                    "If you don’t have an account, click Signup to create a new one.\n\n",
            "Enter a valid Email ID and create a Password.\n\n" +
                    "Provide your Phone Number and choose a Username.\n\n" +
                    "Click the Signup button to create your account.\n\n" +
                    "After signing up, you can log in using your credentials.\n\n",
            "This is your main dashboard after logging in.\n\n" +
                    "You can explore different blog categories such as:\n\n" +
                    "- Entertainment News\n" +
                    "- Fashion Trends\n" +
                    "- Gaming News\n" +
                    "- Sports Highlights\n" +
                    "- Business Insights\n\n" +
                    "Click on any category to explore articles.\n\n" +
                    "Use the \"Write a Blog\" button to create a new article.\n\n" +
                    "Click Profile to manage your account details.\n\n" +
                    "Use the Logout option to sign out of your account.",
            "To create a new blog, fill in the required details:\n\n" +
                    "1. Blog Title – Enter a catchy title for your article.\n" +
                    "2. First & Second Paragraphs – Write the content of your blog.\n" +
                    "3. Blog Type – Select a category that best fits your blog.\n" +
                    "4. Upload Blog Image – Add an image related to your blog post.\n\n" +
                    "Click \"Add Article\" to publish your blog on BLOGNEST.",
            "View your Username, Email ID, and Phone Number.\n\n" +
                    "Check the list of articles you have written.\n\n" +
                    "Click on any article to edit or view its details.\n\n" +
                    "Update your profile details as needed.\n\n"
    };

    private int[] slideImages = {
            R.drawable.p1, R.drawable.p2, R.drawable.p4,
            R.drawable.p5, R.drawable.p6, R.drawable.profile
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);

        LinearLayout sliderContainer = findViewById(R.id.sliderContainer);

        int minSlides = Math.min(slideDescriptions.length, slideImages.length);

        for (int i = 0; i < minSlides; i++) {
            View slide = LayoutInflater.from(this).inflate(R.layout.slide_item, null);

            ImageView slideImage = slide.findViewById(R.id.slideImage);
            TextView slideText = slide.findViewById(R.id.slideText);

            slideImage.setImageResource(slideImages[i]);
            slideText.setText(slideDescriptions[i]);

            sliderContainer.addView(slide);
        }

        // Exit button to go back to MainActivity
        Button exitButton = findViewById(R.id.exitButton);
        exitButton.setOnClickListener(v -> {
            Intent intent = new Intent(TutorialActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
