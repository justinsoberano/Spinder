package com.example.sumon.androidvolley;

import android.app.Activity;
import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.SearchView; // Import SearchView
import android.widget.Button;

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
import org.json.JSONObject;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonRequestActivity extends Activity implements OnClickListener {

    private String TAG = JsonRequestActivity.class.getSimpleName();
    private Button btnJsonObj, btnJsonArray;
    private TextView msgResponse;
    private ProgressDialog pDialog;
    private List<JSONObject> originalJsonDataList = new ArrayList<>();

    // These tags will be used to cancel the requests
    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.json_request);

        btnJsonObj = findViewById(R.id.btnJsonObj);
        btnJsonArray = findViewById(R.id.btnJsonArray);
        msgResponse = findViewById(R.id.msgResponse);

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Fetching Data...");
        pDialog.setCancelable(false);

        btnJsonObj.setOnClickListener(this);
        btnJsonArray.setOnClickListener(this);


        SearchView searchView = findViewById(R.id.btnSearch);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        searchView.setQueryHint("Search by name");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                performSearch(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return true;
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnJsonObj) {
            makeJsonObjReq();
        } else if (v.getId() == R.id.btnJsonArray) {
            makeJsonArryReq();
        }
    }

    private void showProgressDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideProgressDialog() {
        if (pDialog.isShowing())
            pDialog.hide();
    }


    private void makeJsonObjReq() {
        showProgressDialog();
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Method.GET,
                Const.URL_JSON_OBJECT, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());
                        msgResponse.setText(response.toString());

                        originalJsonDataList.add(response);

                        hideProgressDialog();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                hideProgressDialog();
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                return headers;
            }
        };

        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
    }


    private void makeJsonArryReq() {
        showProgressDialog();
        JsonArrayRequest req = new JsonArrayRequest(Const.URL_JSON_ARRAY,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());
                        msgResponse.setText(response.toString());

                        for (int i = 0; i < response.length(); i++) {
                            try {
                                originalJsonDataList.add(response.getJSONObject(i));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        hideProgressDialog();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                hideProgressDialog();
            }
        });

        AppController.getInstance().addToRequestQueue(req, tag_json_arry);
    }

    private void performSearch(String searchTerm) {
        Log.d(TAG, "Search Term: " + searchTerm);

        List<JSONObject> filteredResults = new ArrayList<>();

        for (JSONObject jsonObject : originalJsonDataList) {
            try {
                String name = jsonObject.getString("name").toLowerCase();

                Log.d(TAG, "Name in JSON: " + name);
                if (name.contains(searchTerm.toLowerCase())) {
                    filteredResults.add(jsonObject);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        updateUIWithFilteredResults(filteredResults);
    }


    private void updateUIWithFilteredResults(List<JSONObject> filteredResults) {
        if (filteredResults.isEmpty()) {
            msgResponse.setText("No matching results found.");
        } else {
            StringBuilder responseText = new StringBuilder();

            for (JSONObject jsonObject : filteredResults) {
                responseText.append(jsonObject.toString()).append("\n");
            }

            msgResponse.setText(responseText.toString());
        }
    }

    @Override
    public boolean onSearchRequested() {
        startSearch(null, false, null, true);
        return true;
    }
}
