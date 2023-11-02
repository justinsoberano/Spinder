package screens;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

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
import com.squareup.picasso.Picasso;

import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class ChatScreen extends AppCompatActivity {

    private EditText messageEditText;
    private ImageView sendButton;
    private ImageView sendSong;
    private ScrollView chatScrollView;
    private TextView currentUserTextView;
    private TextView friendUserTextView;
    private TextView songName;
    private TextView artistName;
    private ImageView songView;
    private String songSnippet;
    private MediaPlayer mediaPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        messageEditText = findViewById(R.id.messageEditText);
        sendButton = findViewById(R.id.sendButton);
        chatScrollView = findViewById(R.id.chatScrollView);
        currentUserTextView = findViewById(R.id.currentUserTextView);
        friendUserTextView = findViewById(R.id.friendUserTextView);
        sendSong = findViewById(R.id.sendSong);
        songName = findViewById(R.id.songName);
        artistName = findViewById(R.id.artistName);
        songView = findViewById(R.id.songView);


        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = messageEditText.getText().toString();
                currentUserTextView.append("You: " + "\n" + message + "\n" + "\n");
                messageEditText.setText("");
                chatScrollView.fullScroll(View.FOCUS_DOWN);
            }
        });
        //song / name of song
        sendSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String songNametoSend = messageEditText.getText().toString();
                getSong(songNametoSend);
            }
        });


    }

    private void getSong(String songNameToSend){
        RequestQueue requestQueue = Volley.newRequestQueue(ChatScreen.this);

        String url = "http://coms-309-056.class.las.iastate.edu:8080/" + "song/" + songNameToSend;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Volley Response", response.toString());
                        try {
                            String songName1 = response.getString("name");
                            String artistName1 = response.getString("artist");
                            String songImage = response.getString("image");
                            songSnippet = response.getString("preview");

                            Log.d("Volley Response", "Song Name: " + songName1);
                            Picasso.get().load(songImage).into(songView);
                            songName.setText(songName1);
                            artistName.setText(artistName1);
                            mediaPlayer = MediaPlayer.create(ChatScreen.this, Uri.parse(songSnippet));
                            mediaPlayer.start();
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

}
