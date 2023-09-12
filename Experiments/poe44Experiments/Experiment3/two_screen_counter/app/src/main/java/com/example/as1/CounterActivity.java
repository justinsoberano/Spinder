package com.example.as1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class CounterActivity extends AppCompatActivity {

    Button increaseBtn;

    Button decreaseBtn;
    Button backBtn;
    TextView numberTxt;

    int currCounter = 0;
    int totalCounter = 0;
    Random rand = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter);

        increaseBtn = findViewById(R.id.increaseBtn);
        decreaseBtn = findViewById(R.id.decreaseBtn);
        backBtn = findViewById(R.id.backBtn);
        numberTxt = findViewById(R.id.number);

        increaseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                currCounter = rand.nextInt(6);//random number 0-5
                totalCounter = currCounter + totalCounter;
                if (totalCounter >= 15){
                    numberTxt.setText("Wow you made it over 15 congrats!");
                }
                else {
                    numberTxt.setText("Increased by " + currCounter + " to now: " + totalCounter);
                }
            }
        });
        decreaseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                currCounter = rand.nextInt(6);//random number 0-5
                totalCounter = totalCounter - currCounter;
                if (totalCounter <= -15){
                    numberTxt.setText("Wow you made it under -15 you're crazy!");
                }
                else {
                    numberTxt.setText("Decreased by " + currCounter + " to now: " + totalCounter);
                }
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(CounterActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });


    }
}