package com.example.as1;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.viewpager2.widget.ViewPager2;

public class CounterActivity extends AppCompatActivity {
    private CardView roundedCard;
    private ImageView cardImage; // Add ImageView reference
    private ViewPager2 viewPager;
    private TextView textViewForever;
    private TextView textViewNightTapes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter);

        // Initialize your variables by finding the corresponding XML elements
        roundedCard = findViewById(R.id.roundedCard);
        cardImage = findViewById(R.id.cardImage); // Initialize the ImageView
        viewPager = findViewById(R.id.viewPager);
        textViewForever = findViewById(R.id.textViewForever);
        textViewForever = findViewById(R.id.textViewNightTapes);

        // You can now manipulate these elements in your code as needed
    }
}
