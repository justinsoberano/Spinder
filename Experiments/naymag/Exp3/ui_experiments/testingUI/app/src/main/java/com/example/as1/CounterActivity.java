package com.example.as1;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.viewpager2.widget.ViewPager2;

public class CounterActivity extends AppCompatActivity {
    private CardView roundedCard;
    private ImageView cardImage;
    private TextView textViewForever;
    private TextView textViewNightTapes;
    private ImageButton playButton;
    private ImageButton pauseButton;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter);

        // Initialize your variables by finding the corresponding XML elements
        roundedCard = findViewById(R.id.roundedCard);
        cardImage = findViewById(R.id.cardImage);
        textViewForever = findViewById(R.id.textViewForever);
        textViewNightTapes = findViewById(R.id.textViewNightTapes);
        playButton = findViewById(R.id.playButton);
        pauseButton = findViewById(R.id.pauseButton);

        mediaPlayer = MediaPlayer.create(this, R.raw.nightapes);

    }

    public void startMusic(){
        mediaPlayer.start();
    }

    public void stopMusic(){
        mediaPlayer.stop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
