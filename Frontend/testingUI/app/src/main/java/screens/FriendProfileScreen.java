package screens;
import static android.icu.util.LocaleData.getInstance;
import static okhttp3.internal.Internal.instance;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
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
import org.json.JSONException;

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

        navBar();
        getUser(GlobalVariables.friendUserName);
        Toast.makeText(FriendProfileScreen.this, GlobalVariables.friendUserName, Toast.LENGTH_SHORT).show();
        getInfo(GlobalVariables.friendUserName);
    }


    private void getUser(String username) {//will need a get request to retrieve username form profile screen
        RequestQueue requestQueue = Volley.newRequestQueue(FriendProfileScreen.this);//might need to grab request from ProfileScreen directly
        String url = baseUrl + "user/" + username; //make sure global variable stays the same for each user request
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET, url,null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject res) {
                        try {
                            String yall = res.getString("userName");
                            usernameF.setText(yall);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                }
        );

        requestQueue.add(jsonObjectRequest);
    }

    private void navBar(){
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.menu_profile){
                    Intent profileIntent = new Intent(FriendProfileScreen.this, ProfileScreen.class);
                    startActivity(profileIntent);
                    return true;
                }
                if(item.getItemId() == R.id.menu_discover){
                    Intent profileIntent = new Intent(FriendProfileScreen.this, MusicSwipe.class);
                    startActivity(profileIntent);
                    return true;
                }
                return false;
            }
        });
    }

    private void getInfo(String username){//will eventually get the bio and profile pic from spotify
        RequestQueue requestQueue = Volley.newRequestQueue(FriendProfileScreen.this);
        String urlBio = "http://coms-309-056.class.las.iastate.edu:8080/user/"+ username + "/profile/";
        //String urlImage = "http://coms-309-056.class.las.iastate.edu:8080/user/"+ username + "/image/url/";
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
//        StringRequest stringRequestImage = new StringRequest( //will need to tweak this for image url
//                Request.Method.GET, urlImage,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        Log.d("Volley Response", response);
//
//                        //bioF.setdra(response);//will need a set image
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        error.printStackTrace();
//                    }
//                }
//        );
//        requestQueue.add(stringRequestImage);
    }


}
