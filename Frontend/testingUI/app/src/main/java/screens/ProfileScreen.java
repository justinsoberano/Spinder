package screens;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.as1.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ProfileScreen extends AppCompatActivity {

    TextView bio;
    EditText editBio;
    Button setBio;
    String baseUrl = "http://10.0.2.2:8080/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        bio = findViewById(R.id.bio);
        editBio = findViewById(R.id.editBio);
        setBio = findViewById(R.id.setBio);

        setBio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String editBioString = editBio.getText().toString();

                if (!editBioString.isEmpty()) {
                    String url = baseUrl + "user/1/profile/" + editBioString;

                    JSONObject requestBody = new JSONObject();

                    JsonObjectRequest request = new JsonObjectRequest(
                            Request.Method.PUT,
                            url,
                            requestBody,
                            null,
                            null
                    );

                    Volley.newRequestQueue(ProfileScreen.this).add(request);

                    Toast.makeText(ProfileScreen.this, "Request sent successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ProfileScreen.this, "Set A Bio", Toast.LENGTH_SHORT).show();
                }
            }
        });

        getBio();
        navBar();
    }

    private void getBio(){
        RequestQueue requestQueue = Volley.newRequestQueue(ProfileScreen.this);
        String url = "http://10.0.2.2:8080/user/1/profile";

        StringRequest stringRequest = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("Volley Response", response);
                        bio.setText(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                }
        );

        requestQueue.add(stringRequest);

    }









    private void navBar(){
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.menu_discover){
                    Intent discoverIntent = new Intent(ProfileScreen.this, MusicSwipe.class);
                    startActivity(discoverIntent);
                    return true;
                }
                return false;
            }
        });
    }

}