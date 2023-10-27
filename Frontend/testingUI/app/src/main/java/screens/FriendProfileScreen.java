package screens;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.as1.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class FriendProfileScreen extends AppCompatActivity {

    String baseUrl = "http://coms-309-056.class.las.iastate.edu:8080/";
    TextView bioF; //set with user/(friends username)/bio
    TextView usernameF; //set with user/(friends username)/username
    ImageView profilePictureF; //set with user/(friends username)/image/url

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);
        bioF = findViewById(R.id.bioF);
        usernameF = findViewById(R.id.usernameF);
        profilePictureF = findViewById(R.id.profilePictureF);


        getUsername();
        getInfo(usernameF.getText().toString());
        navBar();
    }

    private void getUsername() {//will need a get request to retrieve username form profile screen
        RequestQueue requestQueue = Volley.newRequestQueue(FriendProfileScreen.this);//might need to grab request from ProfileScreen directly
        String url = "http://coms-309-056.class.las.iastate.edu:8080/user/";

        StringRequest stringRequest = new StringRequest(
                Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("Volley Response", response);
                        usernameF.setText(response);
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
                    Intent discoverIntent = new Intent(FriendProfileScreen.this, MusicSwipe.class);
                    startActivity(discoverIntent);
                    return true;
                }
                if(item.getItemId() == R.id.menu_profile){
                    Intent discoverIntent = new Intent(FriendProfileScreen.this, ProfileScreen.class);
                    startActivity(discoverIntent);
                    return true;
                }
                return false;
            }
        });
    }

    private void getInfo(String username){
        RequestQueue requestQueue = Volley.newRequestQueue(FriendProfileScreen.this);
        String urlBio = "http://coms-309-056.class.las.iastate.edu:8080/user/"+ username + "/profile";
        String urlImage = "http://coms-309-056.class.las.iastate.edu:8080/user/"+ username + "/image/url";
        StringRequest stringRequestBio = new StringRequest(
                Request.Method.GET, urlBio,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("Volley Response", response);
                        bioF.setText(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                }
        );
        requestQueue.add(stringRequestBio);
        StringRequest stringRequestImage = new StringRequest( //will need to tweak this for image url
                Request.Method.GET, urlImage,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("Volley Response", response);
                        //bioF.setdra(response);//will need a set image
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                }
        );
        requestQueue.add(stringRequestImage);
    }


}
