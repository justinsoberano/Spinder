package screens;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.as1.R;

import org.json.JSONObject;

public class FriendInteractions extends AppCompatActivity {

    EditText friendText;
    Button friendList;
    Button openChat;
    Button addFriend;
    Button removeFriend;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_interactions);
        friendList = findViewById(R.id.friendList);
        openChat = findViewById(R.id.openChat);
        addFriend = findViewById(R.id.addFriend);
        removeFriend = findViewById(R.id.removeFriend);
        friendText = findViewById(R.id.friendUsername);
    }
}
