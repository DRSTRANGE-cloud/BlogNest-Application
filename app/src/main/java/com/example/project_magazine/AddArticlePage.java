package com.example.project_magazine;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class AddArticlePage extends AppCompatActivity {
    public static final String PREF_NAME = "MY_PREFS";
    public static final String PREF_EMAIL = "";
    private static final int GALLERY_REQUEST_CODE = 100;
    private Uri photoFilePath;
    private Bitmap photoToStore;
    private byte[] byteArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_article_page);

        LinearLayout goToBackToHomeHandler = findViewById(R.id.goBackToHome);
        goToBackToHomeHandler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddArticlePage.this, MainActivity.class);
                startActivity(intent);
            }
        });

        LinearLayout chooseImageHandler = findViewById(R.id.addImage);
        chooseImageHandler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, GALLERY_REQUEST_CODE);
            }
        });

        LinearLayout addArticle = findViewById(R.id.addArticle);
        addArticle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    SharedPreferences sharedPreferences = getSharedPreferences("eco", Context.MODE_PRIVATE);
                    String email = sharedPreferences.getString("email", "");
                    Log.d("EMAIL", email);

                    ECO_ECO_DB DatabaseObject = new ECO_ECO_DB(getApplicationContext());
                    Cursor cur = DatabaseObject.fetchUser(email);
                    cur.moveToNext();
                    String fetchedUsername = cur.getString(0);
                    Log.d("Username: ", fetchedUsername);

                    EditText articleTitleEditText = findViewById(R.id.articleTitle);
                    EditText articleParaAEditText = findViewById(R.id.articleParaA);
                    EditText articleParaBEditText = findViewById(R.id.articleParaB);
                    Spinner articleTypeSpinner = findViewById(R.id.articleType);

                    String articleTitle = articleTitleEditText.getText().toString();
                    String articleParaA = articleParaAEditText.getText().toString();
                    String articleParaB = articleParaBEditText.getText().toString();
                    String articleType = articleTypeSpinner.getSelectedItem().toString();

                    boolean result = DatabaseObject.insertArticle(fetchedUsername, articleTitle, articleParaA, articleParaB, byteArray, articleType);
                    if (result) {
                        Toast.makeText(getApplicationContext(), "Article Stored Successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Failed To Store Article", Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Select a smaller resolution image please...", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALLERY_REQUEST_CODE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            photoFilePath = data.getData();
            try {
                photoToStore = MediaStore.Images.Media.getBitmap(getContentResolver(), photoFilePath);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                photoToStore.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byteArray = stream.toByteArray();
            } catch (IOException e) {
                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}