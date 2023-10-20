package screens;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.as1.R;

public class Stations extends AppCompatActivity {
    ImageView newStation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stations);
        newStation = findViewById(R.id.newStation);

    }
}
