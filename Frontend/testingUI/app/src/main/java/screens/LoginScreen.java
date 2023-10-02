package com.example.screens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.as1.R;

import java.util.HashMap;

public class LoginScreen extends AppCompatActivity {
    EditText username;
    EditText password;
    Button loginButton;
    ImageView logoimg;

    private HashMap<String, String> userCredentials;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        loginButton = findViewById(R.id.loginButton);

        userCredentials = new HashMap<>();
        userCredentials.put("username", "password");

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String enteredUsername = username.getText().toString();
                String enteredPassword = password.getText().toString();

                if (validateLogin(enteredUsername, enteredPassword)) {
                    Intent intent = new Intent(LoginScreen.this, MusicSwipe.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginScreen.this, "User Name or Password is incorrect!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public boolean validateLogin(String enteredUsername, String enteredPassword) {
        if (userCredentials.containsKey(enteredUsername)) {
            String storedPassword = userCredentials.get(enteredUsername);
            return enteredPassword.equals(storedPassword);
        }

        return false;
    }
}
