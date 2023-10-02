package screens;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
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
    String baseUrl = "http://10.0.2.2:8080/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seedsetter);

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

        musicSwipeSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SeedSetter.this, MusicSwipe.class);
                startActivity(intent);
            }
        });



    }
}
