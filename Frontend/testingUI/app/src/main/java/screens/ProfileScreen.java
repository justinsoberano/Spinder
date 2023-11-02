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
    EditText editBio;
    EditText friendText;
    Button setBio;
    Button friendList;
    Button openChat;
    Button addFriend;
    Button removeFriend;
    String baseUrl = "http://coms-309-056.class.las.iastate.edu:8080/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {//user:crevice     password:master    for testing
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        bio = findViewById(R.id.bio);
        username = findViewById(R.id.username);
        editBio = findViewById(R.id.editBio);
        setBio = findViewById(R.id.setBio);
        friendList = findViewById(R.id.friendList);
        openChat = findViewById(R.id.openChat);
        addFriend = findViewById(R.id.addFriend);
        removeFriend = findViewById(R.id.removeFriend);
        friendText = findViewById(R.id.friendText);

        setBio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String editBioString = editBio.getText().toString();

                if (!editBioString.isEmpty()) {
                    String url = baseUrl + "user/" + GlobalVariables.userName + "/profile/" + editBioString;

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

        openChat.setOnClickListener(new View.OnClickListener() {//open a chat with the inputted username
            @Override
            public void onClick(View v) {
                String editFriendString = friendText.getText().toString();
                if (!editFriendString.isEmpty()) {
                    String url = baseUrl + "user/" + GlobalVariables.userName + "/" + editFriendString; //user1 + user2
                    JSONObject requestBody = new JSONObject();
                    JsonObjectRequest request = new JsonObjectRequest(//will need to edit this for opening websocket
                            Request.Method.PUT, url, requestBody, null, null
                    );

                    Volley.newRequestQueue(ProfileScreen.this).add(request);

                    Toast.makeText(ProfileScreen.this, "Request sent successfully open friend chat", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ProfileScreen.this, "Enter Friend Username", Toast.LENGTH_SHORT).show();
                }
                //setContentView(R.layout.activity_friends);
            }
        });
        friendList.setOnClickListener(new View.OnClickListener() {//want this to send a username to friendprofilescreen for it to build other user profile
            @Override   //if i want all friends friends/Global.username  will grab the list of all users added to friendslist
            public void onClick(View v) {
                String editFriendString = friendText.getText().toString();
                if (!editFriendString.isEmpty()) {
                    String url = baseUrl + "user/" + editFriendString;//sets the user that is in the friend string
                    JSONObject requestBody = new JSONObject();
                    JsonObjectRequest request = new JsonObjectRequest(
                            Request.Method.PUT, url, requestBody, null, null
                    );

                    Volley.newRequestQueue(ProfileScreen.this).add(request);

                    Toast.makeText(ProfileScreen.this, "Request sent successfully open friend", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ProfileScreen.this, "Enter Friend Username", Toast.LENGTH_SHORT).show();
                }
                setContentView(R.layout.activity_friends);
                }
        });
        addFriend.setOnClickListener(new View.OnClickListener() {//add the submitted username into users friend list
            @Override
            public void onClick(View v) {
                String editFriendString = friendText.getText().toString();
                if (!editFriendString.isEmpty()) {
                    String url = baseUrl + "user/" + GlobalVariables.userName + "/" + editFriendString;
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
        removeFriend.setOnClickListener(new View.OnClickListener() {//remove a friend from the user repository
            @Override
            public void onClick(View v) {
                String editFriendString = friendText.getText().toString();
                if (!editFriendString.isEmpty()) {
                    String url = baseUrl + "user/" + GlobalVariables.userName + "/" + editFriendString;
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


        getBioInfo();
        navBar();
    }

    private void getBioInfo(){
        RequestQueue requestQueue = Volley.newRequestQueue(ProfileScreen.this);
        String base = "http://coms-309-056.class.las.iastate.edu:8080/user/" + GlobalVariables.userName;
        String url =  base + "/profile";


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
                Request.Method.GET, base,
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
