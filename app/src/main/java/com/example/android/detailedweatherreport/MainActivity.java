package com.example.android.detailedweatherreport;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {

    EditText city;
    private String url = "http://api.openweathermap.org/data/2.5/weather?q=",appID = "&APPID=1bb89f6a20c24592314448e64d0da4ab",URL;
    private String city_name;
    private Recycler_Adapter la;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        city = (EditText) findViewById(R.id.editText);
        ImageView search = (ImageView) findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                city_name = city.getText().toString();
                URL = url + city_name + appID;

                //Calling the intent to load the search result activity
                Intent intent = new Intent(MainActivity.this,SearchResult.class);
                intent.putExtra("city",city_name);
                intent.putExtra("url",URL);
                startActivity(intent);

            }
        });
    }
}
