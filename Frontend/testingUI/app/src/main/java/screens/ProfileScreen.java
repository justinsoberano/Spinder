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
    EditText friendText;
    Button setBio;
    Button friendList;
    Button addFriend;
    String baseUrl = "http://coms-309-056.class.las.iastate.edu:8080/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        bio = findViewById(R.id.bio);
        editBio = findViewById(R.id.editBio);
        setBio = findViewById(R.id.setBio);
        friendList = findViewById(R.id.friendList);
        addFriend = findViewById(R.id.addFriend);
        friendText = findViewById(R.id.friendText);

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

        friendList.setOnClickListener(new View.OnClickListener() {//want this to send a username to friendprofile for it to build other user profile
            @Override
            public void onClick(View v) {
                setContentView(R.layout.activity_friends);
            }
        });
        addFriend.setOnClickListener(new View.OnClickListener() {//add the submitted username into users friend list
            @Override
            public void onClick(View v) {
                String editFriendString = friendText.getText().toString();
                if (!editFriendString.isEmpty()) {
                    String url = baseUrl + "user/friends/" + editFriendString;//may need to be user/friend/ or however it is named
                    JSONObject requestBody = new JSONObject();
                    JsonObjectRequest request = new JsonObjectRequest(
                            Request.Method.PUT, url, requestBody, null, null
                    );

                    Volley.newRequestQueue(ProfileScreen.this).add(request);

                    Toast.makeText(ProfileScreen.this, "Request sent successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ProfileScreen.this, "Enter Friend Username", Toast.LENGTH_SHORT).show();
                }
            }
        });

        getBio();
        navBar();
    }

    private void getBio(){
        RequestQueue requestQueue = Volley.newRequestQueue(ProfileScreen.this);
        String url = "http://coms-309-056.class.las.iastate.edu:8080/user/1/profile";

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
