package com.example.android.detailedweatherreport;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

public class SearchResult extends AppCompatActivity {

    public static Iterator<String> k;
    private RequestQueue mRequestQueue;
    private JsonObjectRequest mRequest;
    RecyclerView recyclerView;
    Recycler_Adapter recycler_adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_result);

        //recycler view
        recyclerView = (RecyclerView)findViewById(R.id.list);

        //List containing the details which to be shown.
        final ArrayList<List_item> list = new ArrayList<>();

        Intent i = getIntent();
        Bundle extras = i.getExtras();
        final String city = extras.getString("city");
        String URL = extras.getString("url");

        mRequestQueue = Volley.newRequestQueue(SearchResult.this);

        mRequest = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {

                    JSONObject obj = response.getJSONObject("main");
                    JSONObject wind = response.getJSONObject("wind");
                    JSONObject sys = response.getJSONObject("sys");

                    //Datas to displayed
                    list.add(new List_item("Place",city));
                    list.add(new List_item("Temperature",obj.getString("temp")));
                    list.add(new List_item("Humidity",obj.getString("pressure")));
                    list.add(new List_item("Pressure",obj.getString("humidity")));
                    list.add(new List_item("Min_Temp",obj.getString("temp_min")));
                    list.add(new List_item("Max_Temp",obj.getString("temp_max")));
                    list.add(new List_item("Wind Speed",wind.getString("speed")));
                    list.add(new List_item("Sunrise",formatter(sys.getLong("sunrise"))));
                    list.add(new List_item("Sunrise",formatter(sys.getLong("sunset"))));

                    //Setting the adapter
                    recyclerView.setLayoutManager(new LinearLayoutManager(SearchResult.this));
                    recycler_adapter = new Recycler_Adapter(list, SearchResult.this);
                    recyclerView.setAdapter(recycler_adapter);

                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(), "Sorry, unable to find the location.", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getApplicationContext(), "Sorry, unable to find the location.", Toast.LENGTH_LONG).show();
                Log.i("MainActivity", "Error :" + error.toString());
            }
        });
        mRequestQueue.add(mRequest);
    }

    //To format the unix timestamp to normal timeformat
    private  String formatter(long time){

        Date date = new java.util.Date(time*1000L);
        SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
        sdf.setTimeZone(java.util.TimeZone.getTimeZone("GMT-4"));
        String formattedDate = sdf.format(date);
        return formattedDate;
    }
}
