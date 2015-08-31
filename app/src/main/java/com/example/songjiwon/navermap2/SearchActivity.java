package com.example.songjiwon.navermap2;


import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

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
public class SearchActivity extends ListActivity {

    Button searchButton;
    TextView textView;
    EditText editText;

    ListView listView;
    ListViewAdapter adapter;

    String[] restaurantName;
    String[] restaurantAdress;

    private RestaurantResult restaurants;
    private String query; /*= "우마이도";*/
    private Context context;


    private RestaurantSearcher restaurantSearcher;


    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_search);

        searchButton = (Button)findViewById(R.id.SearchButton);
        textView = (TextView)findViewById(R.id.TextView1);
        editText = (EditText)findViewById(R.id.Edit1);

        searchButton.setOnClickListener(searchButtonClcikListener);

        /*ListViewAdapter listViewAdapter = new ListViewAdapter(this, R.layout.row, restaurants.getRestaurants());

        setListAdapter(listViewAdapter);*/
       }

    public class ListViewAdapter extends ArrayAdapter<Restaurant> {

        private ArrayList<Restaurant> items;

        public ListViewAdapter(Context context, int textViewResourceId, ArrayList items) {
            super(context, textViewResourceId, items);

            this.items = items;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            View v = convertView;

            if(v == null) {
                LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = vi.inflate(R.layout.row, null);
            }

            Restaurant restaurant = items.get(position);
            if(restaurant != null){
                TextView topText = (TextView)v.findViewById(R.id.toptext);
                TextView bottomText = (TextView)v.findViewById(R.id.bottomtext);

                if(topText != null){
                    topText.setText(restaurant.getTitle());
                }

                if(bottomText != null){
                    bottomText.setText(restaurant.getAdress());
                }
            }

            return v;
        }
    }


    Button.OnClickListener searchButtonClcikListener = new View.OnClickListener(){
        public void onClick(View v){
            query = editText.getText().toString();
            startSearching();
        }
    };



    public void startSearching(){
        context = getApplicationContext();

        restaurants = new RestaurantResult();

        restaurantSearcher = new RestaurantSearcher(restaurants, query, context);
        restaurantSearcher.UrlRequest();

        showSearchongResult();
    }


    public void showSearchongResult() {


        ListViewAdapter listViewAdapter = new ListViewAdapter(this, R.layout.row, restaurants.getRestaurants());

        setListAdapter(listViewAdapter);

        listViewAdapter.notifyDataSetChanged();
    }

}
