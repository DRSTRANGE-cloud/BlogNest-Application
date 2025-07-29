package com.example.project_magazine;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Find the Tutorial button by its ID
        LinearLayout goToTutorial = findViewById(R.id.tutorial);

        // Existing code for other buttons
        LinearLayout goToEntertainmentHandler = findViewById(R.id.goToEntertainment);
        goToEntertainmentHandler.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, EntertainmentPage.class);
            startActivity(intent);
        });

        LinearLayout goToBusinessHandler = findViewById(R.id.goToBusiness);
        goToBusinessHandler.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, BusinessPage.class);
            startActivity(intent);
        });

        LinearLayout goToSportsHandler = findViewById(R.id.goToSports);
        goToSportsHandler.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SportsPage.class);
            startActivity(intent);
        });

        LinearLayout goToGamesHandler = findViewById(R.id.goToGames);
        goToGamesHandler.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, GamesPage.class);
            startActivity(intent);
        });

        LinearLayout goToFashionHandler = findViewById(R.id.goToFashion);
        goToFashionHandler.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, FashionPage.class);
            startActivity(intent);
        });

        LinearLayout goToAddArticleHandler = findViewById(R.id.goToAddArticle);
        goToAddArticleHandler.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddArticlePage.class);
            startActivity(intent);
        });

        LinearLayout goToProfileHandler = findViewById(R.id.goToProfile);
        goToProfileHandler.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ProfilePage.class);
            startActivity(intent);
        });

        LinearLayout goToAuthHandler = findViewById(R.id.goToAuth);
        goToAuthHandler.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AuthPage.class);
            startActivity(intent);
        });

        // Set the OnClickListener for the Tutorial button
        goToTutorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the Tutorial Activity when the button is clicked
                Intent intent = new Intent(MainActivity.this,TutorialActivity.class);
                startActivity(intent);
            }
        });
    }
}
