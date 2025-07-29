package com.example.project_magazine;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class ProfilePage extends AppCompatActivity {
    LinearLayout articleContainerLayout;
    RelativeLayout ProfileMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile_page);

        ProfileMain = findViewById(R.id.ProfileMain);
        articleContainerLayout = findViewById(R.id.articleContainerLayout);

        SharedPreferences sharedPreferences = getSharedPreferences("eco", Context.MODE_PRIVATE);
        String email = sharedPreferences.getString("email", "");
        Log.d("EMAIL", email);

        if (email != "") {
            ECO_ECO_DB DatabaseObject = new ECO_ECO_DB(getApplicationContext());
            Cursor cur = DatabaseObject.fetchUser(email);
            cur.moveToNext();
            String fetchedUsername = cur.getString(0);
            String fetchedPhoneNumber = cur.getString(1);

            EditText editTextUsername = findViewById(R.id.editTextUsername);
            EditText editTextPhoneNumber = findViewById(R.id.editTextPhoneNumber);
            EditText editTextEmail = findViewById(R.id.editTextEmail);

            editTextUsername.setText(fetchedUsername);
            editTextPhoneNumber.setText(fetchedPhoneNumber);
            editTextEmail.setText(email);
        }
        displayArticles();
    }

    protected void onResume() {
        super.onResume();
    }

    private void displayArticles() {
        SharedPreferences sharedPreferences = getSharedPreferences("eco", Context.MODE_PRIVATE);
        String email = sharedPreferences.getString("email", "");
        Log.d("EMAIL", email);

        if (email != "") {
            ECO_ECO_DB DatabaseObject = new ECO_ECO_DB(getApplicationContext());
            Cursor cur = DatabaseObject.fetchUser(email);
            cur.moveToNext();
            String fetchedUsername = cur.getString(0);

            Cursor result = DatabaseObject.fetchUsersArticles(fetchedUsername);

            while (result.moveToNext()) {
                RelativeLayout articleCard = (RelativeLayout) getLayoutInflater().inflate(R.layout.activity_article_card, null);
                ImageView articleImage = articleCard.findViewById(R.id.articleImage);
                TextView articleTitle = articleCard.findViewById(R.id.articleTitle);
                TextView articleParaA = articleCard.findViewById(R.id.articleParaA);
                TextView articleParaB = articleCard.findViewById(R.id.articleParaB);
                TextView articleAuthor = articleCard.findViewById(R.id.articleAuthor);

                articleTitle.setText(result.getString(result.getColumnIndexOrThrow("ARTICLE_TITLE")));
                articleParaA.setText(result.getString(result.getColumnIndexOrThrow("ARTICLE_PARA_A")));
                articleParaB.setText(result.getString(result.getColumnIndexOrThrow("ARTICLE_PARA_B")));
                articleAuthor.setText("Article Author : " + result.getString(result.getColumnIndexOrThrow("ARTICLE_AUTHOR")));

                byte[] imageData = result.getBlob(result.getColumnIndexOrThrow("ARTICLE_IMAGE"));
                Bitmap bitmap = BitmapFactory.decodeByteArray(imageData, 0, imageData.length);
                articleImage.setImageBitmap(bitmap);

                articleContainerLayout.addView(articleCard);
            }
        }
    }
}