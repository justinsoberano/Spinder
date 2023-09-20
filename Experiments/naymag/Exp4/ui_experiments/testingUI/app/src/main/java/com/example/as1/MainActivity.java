package com.example.as1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    EditText username;
    EditText password;
    Button loginButton;
    ImageView logoimg;

    private HashMap<String, String> userCredentials;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        loginButton = findViewById(R.id.loginButton);
        logoimg = findViewById(R.id.logoimg);

        userCredentials = new HashMap<>();
        userCredentials.put("naymag", "minecraftluvr");
        userCredentials.put("justin", "steveissocool");
        userCredentials.put("qusai", "embeddedsystemsluvr");
        userCredentials.put("brandon", "brandonissocool");

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String enteredUsername = username.getText().toString();
                String enteredPassword = password.getText().toString();

                if (validateLogin(enteredUsername, enteredPassword)) {
                    Intent intent = new Intent(MainActivity.this, MusicSwipe.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "User Name or Password is incorrect!", Toast.LENGTH_SHORT).show();
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
