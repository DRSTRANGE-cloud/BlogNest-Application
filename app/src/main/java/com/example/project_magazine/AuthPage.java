package com.example.project_magazine;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class AuthPage extends AppCompatActivity {

    public String userEmail;
    public String userPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_auth_page);

        LinearLayout goToBackToHomeHandler = findViewById(R.id.goBackToHome);
        goToBackToHomeHandler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ECO_ECO_DB DatabaseObject = new ECO_ECO_DB(getApplicationContext());
                EditText userEmailEditText = findViewById(R.id.userAuthEmail);
                EditText userPasswordEditText = findViewById(R.id.userAuthPassword);
                userEmail = userEmailEditText.getText().toString();
                userPassword = userPasswordEditText.getText().toString();
                boolean success = DatabaseObject.checkIfUserExists(userEmail, userPassword);

                String emailPattern = "[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}";

                if (userEmail.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Email Cant be Empty", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!userEmail.matches(emailPattern)) {
                    Toast.makeText(getApplicationContext(), "Invalid Email Format", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (userPassword.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Password cannot be empty", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (success) {
                    SharedPreferences sharedPreferences = getSharedPreferences("eco", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("email", userEmail);
                    editor.apply();
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    Toast.makeText(getApplicationContext(), "Login success ", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Incorrect Email or Password", Toast.LENGTH_SHORT).show();
                }
            }
        });

        LinearLayout goToSignup = findViewById(R.id.goToSignUp);
        goToSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AuthPage.this, SignupPage.class);
                startActivity(intent);
            }
        });
    }
}