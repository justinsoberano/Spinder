package com.example.sumon.androidvolley;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Cache;
import com.android.volley.Cache.Entry;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.sumon.androidvolley.app.AppController;
import com.example.sumon.androidvolley.utils.Const;

import java.io.UnsupportedEncodingException;

public class ImageRequestActivity extends Activity {

    private static final String TAG = ImageRequestActivity.class
            .getSimpleName();
    private Button btnImageReq;
    private ImageView imageView;
    private ProgressBar progressBar;
    private TextView albumnName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_request);

        btnImageReq = (Button) findViewById(R.id.btnImageReq);
        imageView = (ImageView) findViewById(R.id.imgView);
        progressBar = findViewById(R.id.progressBar);
        albumnName = findViewById(R.id.albumnName);

        btnImageReq.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                makeImageRequest();
            }
        });
    }

    private void makeImageRequest() {
        ImageLoader imageLoader = AppController.getInstance().getImageLoader();

        progressBar.setVisibility(View.VISIBLE);
        albumnName.setVisibility(View.GONE);

        // If you are using NetworkImageView

        progressBar.setVisibility(View.GONE);
        // Loading image with placeholder and error image
        imageLoader.get(Const.URL_IMAGE, ImageLoader.getImageListener(
                imageView, R.drawable.ico_loading, R.drawable.ico_error));
        albumnName.setVisibility(View.VISIBLE);
        Cache cache = AppController.getInstance().getRequestQueue().getCache();
        Entry entry = cache.get(Const.URL_IMAGE);
        if(entry != null){
            try {
                String data = new String(entry.data, "UTF-8");
                // handle data, like converting it to xml, json, bitmap etc.,
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }else{
            // cached response doesn't exists. Make a network call here
        }

    }
}