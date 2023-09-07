package com.example.sumon.androidvolley;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {
    private Button btnJson, btnString, btnImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnString = (Button) findViewById(R.id.btnStringRequest);
        btnJson = (Button) findViewById(R.id.btnJsonRequest);
        btnImage = (Button) findViewById(R.id.btnImageRequest);

        // button click listeners
        btnString.setOnClickListener(this);
        btnJson.setOnClickListener(this);
        btnImage.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnStringRequest) {
            startActivity(new Intent(MainActivity.this, StringRequestActivity.class));
        } else if (v.getId() == R.id.btnJsonRequest) {
            startActivity(new Intent(MainActivity.this, JsonRequestActivity.class));
        } else if (v.getId() == R.id.btnImageRequest) {
            startActivity(new Intent(MainActivity.this, ImageRequestActivity.class));
        }
    }

}
