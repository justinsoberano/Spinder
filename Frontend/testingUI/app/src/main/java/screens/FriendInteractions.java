package screens;

import android.content.Intent;
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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.as1.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONObject;

public class FriendInteractions extends AppCompatActivity {

    EditText friendText;
    Button friendList;
    Button openChat;
    Button addFriend;
    Button removeFriend;
    TextView fullFriendList;
    String baseUrl = "http://coms-309-056.class.las.iastate.edu:8080/";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_interactions);
        friendList = findViewById(R.id.friendList);
        openChat = findViewById(R.id.openChat);
        addFriend = findViewById(R.id.addFriend);
        removeFriend = findViewById(R.id.removeFriend);
        friendText = findViewById(R.id.friendUsername);
        fullFriendList = findViewById(R.id.fullFriendList);

        openChat.setOnClickListener(new View.OnClickListener() {//open a chat with the inputted username
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FriendInteractions.this, ChatScreen.class);
                startActivity(intent);
                setContentView(R.layout.activity_friends);
            }
        });
        friendList.setOnClickListener(new View.OnClickListener() {//want this to send a username to friendprofilescreen for it to build other user profile
            @Override   //if i want all friends friends/Global.username  will grab the list of all users added to friendslist
            public void onClick(View v) {
                GlobalVariables.friendUserName = friendText.getText().toString();
                if (!GlobalVariables.friendUserName.isEmpty()) {

                    Toast.makeText(FriendInteractions.this, "Request sent successfully open friend", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(FriendInteractions.this, FriendProfileScreen.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(FriendInteractions.this, "Enter Friend Username", Toast.LENGTH_SHORT).show();
                }
            }
        });
        addFriend.setOnClickListener(new View.OnClickListener() {//add the submitted username into users friend list
            @Override
            public void onClick(View v) {
                String editFriendString = friendText.getText().toString();
                if (!editFriendString.isEmpty()) {
                    String url = baseUrl + "add/" + GlobalVariables.userName + "/" + editFriendString;
                    JSONObject requestBody = new JSONObject();
                    JsonObjectRequest request = new JsonObjectRequest(
                            Request.Method.PUT, url, requestBody, null, null
                    );

                    Volley.newRequestQueue(FriendInteractions.this).add(request);

                    Toast.makeText(FriendInteractions.this, "Request sent successfully"+ editFriendString, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(FriendInteractions.this, "Enter Friend Username", Toast.LENGTH_SHORT).show();
                }
            }
        });
        removeFriend.setOnClickListener(new View.OnClickListener() {//remove a friend from the user repository
            @Override
            public void onClick(View v) {
                String editFriendString = friendText.getText().toString();
                if (!editFriendString.isEmpty()) {
                    String url = baseUrl + "remove/" + GlobalVariables.userName + "/" + editFriendString;
                    JSONObject requestBody = new JSONObject();
                    JsonObjectRequest request = new JsonObjectRequest(
                            Request.Method.PUT, url, requestBody, null, null
                    );

                    Volley.newRequestQueue(FriendInteractions.this).add(request);

                    Toast.makeText(FriendInteractions.this, "Request sent successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(FriendInteractions.this, "Enter Friend Username", Toast.LENGTH_SHORT).show();
                }
            }
        });
        getFriendList();
    }

    private void getFriendList() {
        RequestQueue requestQueue = Volley.newRequestQueue(FriendInteractions.this);
        String org = "http://coms-309-056.class.las.iastate.edu:8080/friends/" + GlobalVariables.userName;

        StringRequest stringRequest = new StringRequest(
                Request.Method.GET, org,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("Volley Response", response);
                        fullFriendList.setText(response);
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
                    Intent discoverIntent = new Intent(FriendInteractions.this, MusicSwipe.class);
                    startActivity(discoverIntent);
                    return true;
                }else if(item.getItemId() == R.id.menu_profile){
                    Intent profileIntent = new Intent(FriendInteractions.this, ProfileScreen.class);
                    startActivity(profileIntent);
                }
                return false;
            }
        });
    }

}
