package com.example.sumon.androidvolley;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.JsonWriter;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.sumon.androidvolley.app.AppController;
import com.example.sumon.androidvolley.utils.Const;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class JsonRequestActivity extends Activity implements OnClickListener {

    private String TAG = JsonRequestActivity.class.getSimpleName();
    private Button btnJsonObj, btnJsonArray, customArrayBtn;
    private TextView msgResponse;
    private ProgressDialog pDialog;

    // These tags will be used to cancel the requests
    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.json_request);

        btnJsonObj = (Button) findViewById(R.id.btnJsonObj);
        btnJsonArray = (Button) findViewById(R.id.btnJsonArray);
        customArrayBtn = (Button) findViewById(R.id.customArray);
        msgResponse = (TextView) findViewById(R.id.msgResponse);

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.setCancelable(false);

        btnJsonObj.setOnClickListener(this);
        btnJsonArray.setOnClickListener(this);
        customArrayBtn.setOnClickListener(this);
    }

    private void showProgressDialog() {
        if (!pDialog.isShowing())

            pDialog.show();
    }

    private void hideProgressDialog() {
        if (pDialog.isShowing())
            pDialog.hide();
    }

    /**
     * Making json object request
     * */
    private void makeJsonObjReq() {
        showProgressDialog();
        Map<String,String> newObj = new HashMap<>();

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Method.GET,
                Const.URL_JSON_OBJECT,null ,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());
                        msgResponse.setText(response.toString());
                        hideProgressDialog();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                hideProgressDialog();
            }
        }) {

            /**
             * Passing some request headers
             * */
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                return headers;
            }

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("name", "Androidhive");
                params.put("email", "abc@androidhive.info");
                params.put("pass", "password123");

                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq,
                tag_json_obj);

        // Cancelling request
        // ApplicationController.getInstance().getRequestQueue().cancelAll(tag_json_obj);
    }

    /**
     * Making json array request
     * */
    private void makeJsonArryReq() {
        showProgressDialog();
        JsonArrayRequest req = new JsonArrayRequest(Const.URL_JSON_ARRAY,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());
                        msgResponse.setText(response.toString());
                        hideProgressDialog();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                hideProgressDialog();
            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(req,
                tag_json_arry);

        // Cancelling request
        // ApplicationController.getInstance().getRequestQueue().cancelAll(tag_json_arry);
    }

    public void createJsonObject() throws JSONException {
//        Object yo = new Object();
        JSONArray array = new JSONArray();

//        JSONArray child = new JSONArray();
//        child.put(new JSONObject("One"));
//        child.put(new JSONObject("Three"));
//        child.put(new JSONObject("Five"));
//        child.put(new JSONObject("Seven"));
//        child.put(new JSONObject("Nine"));
//        for(int i=0;i<5;i++) {
//            array.put(child);
//        }
        String[] odds = {"one","three","five", "seven", "nine"};

        for(int i=0;i<5;i++) {
            array.put(odds[i]);
        }

        JSONObject json = new JSONObject();
        json.put("mine",array);
        msgResponse.setText(array.toString());
//        new Response.Listener<JSONArray>() {
//            @Override
//            public void onResponse(JSONArray response) {
//                Log.d(TAG, response.toString());
//                msgResponse.setText(response.toString());
//                msgResponse.setText(array.toString());
//                hideProgressDialog();
//            }
//        };
//        yo = {"title ":"STARTING JSON", "coins" : 150 };

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnJsonObj:
                makeJsonObjReq();
                break;
            case R.id.btnJsonArray:
                makeJsonArryReq();
                break;
            case R.id.customArray:
                try {
                    createJsonObject();
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
                break;
        }

    }

}
