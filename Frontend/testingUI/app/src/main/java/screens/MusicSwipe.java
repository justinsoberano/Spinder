package screens;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.palette.graphics.Palette;

import com.android.volley.toolbox.JsonArrayRequest;
import com.example.as1.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.android. volley.Request;
import com.android. volley. RequestQueue;
import com.android.volley. Response;
import com.android.volley.VolleyError;
import com.android.volley. toolbox. Volley;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MusicSwipe extends AppCompatActivity {
    private ImageView cardImage;
    private TextView songNameView;
    private TextView artistNameView;
    RelativeLayout relativeLayout;
    private MediaPlayer mediaPlayer;
    private int currentSongIndex = 0;
    private final ArrayList<String> songNames = new ArrayList<>();
    private final ArrayList<String> artistNames = new ArrayList<>();
    private final ArrayList<String> songImages = new ArrayList<>();
    private final ArrayList<String> songSnippets = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_musicswipe);
        SwipeListener swipeListener;
        cardImage = findViewById(R.id.cardImage);
        songNameView = findViewById(R.id.songNameView);
        artistNameView = findViewById(R.id.artistNameView);
        relativeLayout = findViewById(R.id.relativeLayout);

        swipeListener = new SwipeListener(relativeLayout);
        makeJSONRequest();
        setUpNavBar();
    }



    private void setUpNavBar(){
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.menu_profile) {
                    Intent profileIntent = new Intent(MusicSwipe.this, ProfileScreen.class);
                    startActivity(profileIntent);
                    return true;
                }
                return false;
            }
        });
    }

    private void makeJSONRequest(){
        RequestQueue requestQueue = Volley.newRequestQueue(MusicSwipe.this);
        String url = "http://coms-309-056.class.las.iastate.edu:8080/user/1/1";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("Volley Response", response.toString());
                        try {
                            for(int i = 0; i < response.length(); i++){
                                JSONObject songObject = response.getJSONObject(i);
                                String songName = songObject.getString("name");
                                String artistName = songObject.getString("artist");
                                String songImage = songObject.getString("image");
                                String songSnippet = songObject.getString("preview");

                                if(!songSnippet.equals("null")){
                                    songNames.add(songName);
                                    artistNames.add(artistName);
                                    songImages.add(songImage);
                                    songSnippets.add(songSnippet);
                                }

                            }

                            currentSongIndex = 0;
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    getColors();
                                    songNameView.setText(songNames.get(currentSongIndex));
                                    artistNameView.setText(artistNames.get(currentSongIndex));
                                    mediaPlayer = MediaPlayer.create(MusicSwipe.this, Uri.parse(songSnippets.get(currentSongIndex)));
                                    mediaPlayer.start();
                                }
                            });

                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        error.printStackTrace();
                    }
                }
        );

        requestQueue.add(jsonArrayRequest);
    }


    private class SwipeListener implements View.OnTouchListener {
        GestureDetector gestureDetector;

        SwipeListener(View view) {
            int threshold = 100;
            int velocityThreshold = 100;

            GestureDetector.SimpleOnGestureListener listener = new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onDown(MotionEvent e) {
                    return true;
                }

                @Override
                public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                    float xDif = e2.getX() - e1.getX();
                    float yDif = e2.getY() - e1.getY();

                    float absX = Math.abs(xDif);
                    float absY = Math.abs(yDif);

                    try {
                        if (absX > absY) {
                            if (absX > threshold && Math.abs(velocityX) > velocityThreshold) {
                                if (xDif > 0) {
                                    // "Liked the song" and basically goes to the next song in the array
                                    currentSongIndex = (currentSongIndex + 1) % songImages.size();
                                } else {
                                    currentSongIndex = (currentSongIndex - 1 + songImages.size()) % songImages.size();
                                    // change info to say "you did not like this song"
                                }

                                if (mediaPlayer != null) {
                                    mediaPlayer.release();
                                }
                                mediaPlayer = MediaPlayer.create(MusicSwipe.this, Uri.parse(songSnippets.get(currentSongIndex)));

                                getColors();
                                songNameView.setText(songNames.get(currentSongIndex));
                                artistNameView.setText(artistNames.get(currentSongIndex));
                                mediaPlayer.start();

                                return true;
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return false;
                }
            };
            gestureDetector = new GestureDetector(listener);
            view.setOnTouchListener(this);
        }

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            return gestureDetector.onTouchEvent(event);
        }
    }
    @Override
    protected void onResume() {
        super.onResume();

        if (mediaPlayer == null && currentSongIndex >= 0 && currentSongIndex < songSnippets.size()) {
            mediaPlayer = MediaPlayer.create(MusicSwipe.this, Uri.parse(songSnippets.get(currentSongIndex)));
            mediaPlayer.start();
        }
    }
    @Override
    protected void onPause() {
        super.onPause();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    private void updateGradientXML(int startColor, int centerColor, int endColor) {
        GradientDrawable gradientDrawable = (GradientDrawable) getResources().getDrawable(R.drawable.gradient_background);
        gradientDrawable.setColors(new int[]{startColor, centerColor, endColor});
        relativeLayout.setBackground(gradientDrawable);
    }

    private void getColors(){
        Picasso.get().load(songImages.get(currentSongIndex)).into(cardImage, new Callback() {
            @Override
            public void onSuccess() {
                Bitmap bitmap = ((BitmapDrawable) cardImage.getDrawable()).getBitmap();

                Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
                    @Override
                    public void onGenerated(Palette palette) {
                        int startColor = palette.getDarkVibrantColor(Color.BLACK);
                        int centerColor = palette.getDominantColor(Color.BLUE);
                        int endColor = palette.getDarkMutedColor(Color.GRAY);

                        updateGradientXML(startColor, centerColor, endColor);
                    }
                });
            }

            @Override
            public void onError(Exception e) {
                e.printStackTrace();
            }
        });
    }




}
