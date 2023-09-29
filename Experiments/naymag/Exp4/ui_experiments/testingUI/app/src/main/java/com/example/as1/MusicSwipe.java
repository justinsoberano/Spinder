package com.example.as1;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MusicSwipe extends AppCompatActivity {
    private CardView roundedCard;
    private ImageView cardImage;
    private TextView textViewForever;
    private TextView textViewNightTapes;
    RelativeLayout relativeLayout;
    private MediaPlayer mediaPlayer;

    private int currentSongIndex = 0;
    private int[] songImages = {R.drawable.modest_mouse, R.drawable.nightapes, R.drawable.nightapes};
    private String[] songTitles = {"Float on", "Dress", "So it goes..."};
    private String[] artistNames = {"Modest Mouse", "Taylor Swift", "Taylor Swift"};
    private int[] songSnippets = {R.raw.floaton, R.raw.dress, R.raw.soitgoes};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_musicswipe);
        SwipeListener swipeListener;

        roundedCard = findViewById(R.id.roundedCard);
        cardImage = findViewById(R.id.cardImage);
        textViewForever = findViewById(R.id.textViewForever);
        textViewNightTapes = findViewById(R.id.textViewNightTapes);
        relativeLayout = findViewById(R.id.relativeLayout);

        swipeListener = new SwipeListener(relativeLayout);

        currentSongIndex = 0;
        cardImage.setImageResource(songImages[currentSongIndex]);
        textViewForever.setText(songTitles[currentSongIndex]);
        textViewNightTapes.setText(artistNames[currentSongIndex]);
        MediaPlayer mediaPlayer = MediaPlayer.create(this, songSnippets[currentSongIndex]);
        mediaPlayer.start();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.menu_profile) {
                    Intent profileIntent = new Intent(MusicSwipe.this, ProfileScreen.class);
                    startActivity(profileIntent);
                    return true;
                }
                // Add additional if statements for other menu items if needed in the future
                return false;
            }
        });


    }

    private class SwipeListener implements View.OnTouchListener {
        GestureDetector gestureDetector;

        SwipeListener (View view){
            int threshold = 100;
            int velocityThreshold = 100;

            GestureDetector.SimpleOnGestureListener listener = new GestureDetector.SimpleOnGestureListener(){
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

                    try{
                        if(absX > absY){
                            if(absX > threshold && Math.abs(velocityX) > velocityThreshold){
                                if(xDif > 0){
                                    //"Liked the song" and basically goes to the next song in the array
                                    currentSongIndex = (currentSongIndex + 1) % songImages.length;
                                }else{
                                    currentSongIndex = (currentSongIndex - 1 + songImages.length) % songImages.length;
                                    //change info to say "you did not like this song"
                                    textViewForever.setText("You dont like this song");
                                }
                                if (mediaPlayer != null) {
                                    mediaPlayer.release();
                                    mediaPlayer = null;
                                }

                                mediaPlayer = MediaPlayer.create(MusicSwipe.this, songSnippets[currentSongIndex]);

                                // Start playback
                                mediaPlayer.start();
                                cardImage.setImageResource(songImages[currentSongIndex]);
                                textViewForever.setText(songTitles[currentSongIndex]);
                                textViewNightTapes.setText(artistNames[currentSongIndex]);
                                return true;
                            }
                        }
                    }catch (Exception e){
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
}
