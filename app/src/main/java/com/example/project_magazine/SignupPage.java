package com.example.project_magazine;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class SignupPage extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signup_page);

        LinearLayout goToLogout = findViewById(R.id.goToLogout);
        goToLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignupPage.this, AuthPage.class);
                startActivity(intent);
            }
        });

        LinearLayout goToBackToHomeHandler = findViewById(R.id.goBackToHome);
        goToBackToHomeHandler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ECO_ECO_DB DatabaseObject = new ECO_ECO_DB(getApplicationContext());
                EditText userEmailEditText = findViewById(R.id.userEmail);
                EditText userPasswordEditText = findViewById(R.id.userPassword);
                EditText userPhoneNumberEditText = findViewById(R.id.userPhoneNumber);
                EditText userNameEditText = findViewById(R.id.userName);

                String userEmail = userEmailEditText.getText().toString();
                String userPassword = userPasswordEditText.getText().toString();
                String userPhoneNumber = userPhoneNumberEditText.getText().toString();
                String userName = userNameEditText.getText().toString();

                String emailPattern = "[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}";

                String phonePattern = "^[+]?[0-9]{10,13}$";

                if (!userEmail.isEmpty() && !userPassword.isEmpty() && !userPhoneNumber.isEmpty() && !userName.isEmpty()) {
                    if (userEmail.matches(emailPattern) && userPhoneNumber.matches(phonePattern)) {
                        boolean result = DatabaseObject.insertUser(userEmail, userPassword, userPhoneNumber, userName);
                        if (result) {
                            Intent intent = new Intent(SignupPage.this, AuthPage.class);
                            startActivity(intent);
                            Toast.makeText(getApplicationContext(), "Signed Up Successfully", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "SignUp Failed", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Invalid Email or Phone Number", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Invalid Credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}