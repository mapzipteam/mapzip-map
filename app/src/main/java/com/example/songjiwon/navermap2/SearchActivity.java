package com.example.songjiwon.navermap2;


import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

/**
 * Created by Song  Ji won on 2015-08-23.
 */
public class SearchActivity extends ActionBarActivity {


    private RestaurantResult restaurants;
    private String query = "우마이도";
    private Context context;


    private RestaurantSearcher restaurantSearcher;


    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_search);

        context = getApplicationContext();

        restaurants = new RestaurantResult();

        restaurantSearcher = new RestaurantSearcher(restaurants, query, context);
        restaurantSearcher.UrlRequest();
    }


    public void checkSearch(View v) {

        restaurants.showMinimumInfo();
    }

}
