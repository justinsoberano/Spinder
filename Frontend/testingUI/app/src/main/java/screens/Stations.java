package screens;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.as1.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONObject;

public class Stations extends AppCompatActivity {
    ImageView newStation;
    RelativeLayout relativeLayout;
    CardView station1;
    CardView station2;
    CardView station3;
    String songText;
    String volumeString;
    String tempoString;
    String popularityString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stations);
        newStation = findViewById(R.id.newStation);
        relativeLayout = findViewById(R.id.relativeLayout);
        station1 = findViewById(R.id.station1);
        station2 = findViewById(R.id.station2);
        station3 = findViewById(R.id.station3);

        station1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent createStation1 = new Intent(Stations.this, SeedSetter.class);
//                startActivity(createStation1);
                showSeedSetterDialog();
            }
        });

        station2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               showSeedSetterDialog();
            }
        });

        station3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSeedSetterDialog();
            }
        });

    }

    private void showSeedSetterDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);

        View dialogView = getLayoutInflater().inflate(R.layout.activity_seedsetter, null);
        dialogBuilder.setView(dialogView);

        EditText seed = dialogView.findViewById(R.id.seed);
        Button setSeed = dialogView.findViewById(R.id.seedSetter);
        SeekBar volume = dialogView.findViewById(R.id.volume_slider);
        SeekBar tempo = dialogView.findViewById(R.id.tempo_slider);
        SeekBar popularity = dialogView.findViewById(R.id.popularity_slider);
        Button musicSwipeSend = dialogView.findViewById(R.id.musicSwipeSend);

        setSeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                songText = seed.getText().toString();
                if (!songText.isEmpty()) {
                    Toast.makeText(Stations.this, "Request sent successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Stations.this, "Must Enter A Song Name", Toast.LENGTH_SHORT).show();
                }
            }
        });
        volume.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {}
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                volumeString = Integer.toString(seekBar.getProgress()); //sends the volume as a string
                Toast.makeText(Stations.this, "Volume at " + seekBar.getProgress() + " sent", Toast.LENGTH_SHORT).show();
            }
        });

        tempo.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {}
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                tempoString = Integer.toString(seekBar.getProgress());//sends the volume as a string
                Toast.makeText(Stations.this, "Tempo at " + seekBar.getProgress() + " sent", Toast.LENGTH_SHORT).show();
            }
        });

        popularity.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {}
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                popularityString = Integer.toString(seekBar.getProgress());//sends the volume as a string
                Toast.makeText(Stations.this, "Popularity at " + seekBar.getProgress() + " sent", Toast.LENGTH_SHORT).show();
            }
        });
        musicSwipeSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Stations.this, MusicSwipe.class);
                startActivity(intent);
            }
        });
        AlertDialog dialog = dialogBuilder.create();
        dialog.show();
        SeedSetter seedSetter = new SeedSetter(songText, volumeString, tempoString, popularityString);
    }

}
