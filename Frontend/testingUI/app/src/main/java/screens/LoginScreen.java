package screens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.as1.R;

import org.json.JSONObject;

import java.util.HashMap;

public class LoginScreen extends AppCompatActivity {
    EditText username;
    EditText password;
    Button loginButton;
    LinearLayout logoRelative;
    Button registerButton;
    String baseUrl = "http://10.0.2.2:8080/";


    private HashMap<String, String> userCredentials;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        loginButton = findViewById(R.id.loginButton);
        logoRelative = findViewById(R.id.logoRelative);
        registerButton = findViewById(R.id.signUpButton);
        userCredentials = new HashMap<>();
        userCredentials.put("username", "password");
        String enteredUsername = username.getText().toString();
        String enteredPassword = password.getText().toString();

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateLogin(enteredUsername, enteredPassword)) {
                    Intent intent = new Intent(LoginScreen.this, SeedSetter.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginScreen.this, "User Name or Password is incorrect!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String usernameSend = enteredUsername;
                        String passwordSend = enteredPassword;

                        if (!usernameSend.isEmpty() && !passwordSend.isEmpty()) {
                            String url = baseUrl + "user/" + usernameSend + "/" + passwordSend;

                            JSONObject requestBody = new JSONObject();

                            JsonObjectRequest request = new JsonObjectRequest(
                                    Request.Method.PUT,
                                    url,
                                    requestBody,
                                    null,
                                    null
                            );

                            Volley.newRequestQueue(LoginScreen.this).add(request);

                            Toast.makeText(LoginScreen.this, "Registered Successful", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(LoginScreen.this, "Please Register", Toast.LENGTH_SHORT).show();
                        }

                        Uri uri = Uri.parse("http://10.0.2.2:8080/register");
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                    }
                });
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
