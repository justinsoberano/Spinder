package screens;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.as1.R;

import org.json.JSONObject;

public class SeedSetter extends AppCompatActivity {
    EditText seed;
    Button setSeed;
    Button musicSwipeSend;
    Button preferences;
    SeekBar volume;
    SeekBar test;
    SeekBar tempo;
    SeekBar popularity;
    String baseUrl = "http://coms-309-056.class.las.iastate.edu:8080/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seedsetter);

        volume = findViewById(R.id.volume_slider);
        test = findViewById(R.id.test);
        tempo = findViewById(R.id.tempo_slider);
        popularity = findViewById(R.id.popularity_slider);
        preferences = findViewById(R.id.preferences);
        seed = findViewById(R.id.seed);
        setSeed = findViewById(R.id.seedSetter);
        musicSwipeSend = findViewById(R.id.musicSwipeSend);

        setSeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String songText = seed.getText().toString();

                if (!songText.isEmpty()) {
                    String url = baseUrl + "user/1/station/" + songText;

                    JSONObject requestBody = new JSONObject();

                    JsonObjectRequest request = new JsonObjectRequest(
                            Request.Method.PUT,
                            url,
                            requestBody,
                            null,
                            null
                    );

                    Volley.newRequestQueue(SeedSetter.this).add(request);

                    Toast.makeText(SeedSetter.this, "Request sent successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(SeedSetter.this, "Must Enter A Song Name", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //volume.setOnSeekBarChangeListener();
        //this works with java page open
        //ask if sending slider data while sliding would be too many requests or if it would be after stop touching
        preferences.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tempo = seed.getText().toString();
                String popularity = seed.getText().toString();
                String volume = baseUrl + "user/1/volume/" + test.toString();

                    JSONObject requestBody = new JSONObject();

                    JsonObjectRequest reqTempo = new JsonObjectRequest(
                            Request.Method.PUT, volume, requestBody, null, null
                    );
                JsonObjectRequest reqVolume = new JsonObjectRequest(
                        Request.Method.PUT, tempo, requestBody, null, null
                );
                JsonObjectRequest reqPopularity = new JsonObjectRequest(
                        Request.Method.PUT, popularity, requestBody, null, null
                );

                Volley.newRequestQueue(SeedSetter.this).add(reqVolume);
                Volley.newRequestQueue(SeedSetter.this).add(reqPopularity);
                Volley.newRequestQueue(SeedSetter.this).add(reqTempo);

                    Toast.makeText(SeedSetter.this, "Requests sent successfully", Toast.LENGTH_SHORT).show();
            }
        });

        musicSwipeSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SeedSetter.this, MusicSwipe.class);
                startActivity(intent);
            }
        });




    }
}
