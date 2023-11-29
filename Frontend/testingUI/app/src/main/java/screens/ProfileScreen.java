package screens;
import static android.icu.util.LocaleData.getInstance;
import static okhttp3.internal.Internal.instance;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
import screens.GlobalVariables;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ProfileScreen extends AppCompatActivity {
    TextView bio;
    TextView username;
    ImageView profileSettings;
    Button friends;
    String baseUrl = "http://coms-309-056.class.las.iastate.edu:8080/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {//user:crevice     password:master    for testing
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        bio = findViewById(R.id.bio);
        username = findViewById(R.id.username);
        friends = findViewById(R.id.friends);
        profileSettings = findViewById(R.id.profileSettings);

        profileSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent profSettings = new Intent(ProfileScreen.this, ProfileSettings.class);
                startActivity(profSettings);
            }
        });

        friends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent friendStuff = new Intent(ProfileScreen.this, FriendInteractions.class);
                startActivity(friendStuff);
            }
        });

        getBioInfo();
        navBar();
    }

    private void getBioInfo(){
        RequestQueue requestQueue = Volley.newRequestQueue(ProfileScreen.this);
        String org = "http://coms-309-056.class.las.iastate.edu:8080/user/" + GlobalVariables.userName;
        String base = org + "/username";
        String url =  org + "/profile";

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
        StringRequest stringRequest2 = new StringRequest(
                Request.Method.GET, base,//change to base
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("Volley Response", response);
                        username.setText(response);
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
        requestQueue.add(stringRequest2);

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
