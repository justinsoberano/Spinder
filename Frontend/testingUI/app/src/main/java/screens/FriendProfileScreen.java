package screens;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.as1.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class FriendProfileScreen extends AppCompatActivity {

    String baseUrl = "http://coms-309-056.class.las.iastate.edu:8080/";
    TextView bioF; //set with friend/bio
    TextView usernameF; //set with friend/username
    ImageView profilePictureF; //set with friend/image/url

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);



        navBar();
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
}
