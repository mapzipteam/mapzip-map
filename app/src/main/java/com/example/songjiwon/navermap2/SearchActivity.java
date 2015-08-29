package com.example.songjiwon.navermap2;


import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.Toast;

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

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;


import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

/**
 * Created by Song  Ji won on 2015-08-23.
 */
public class SearchActivity extends ActionBarActivity{

    private String url;

    private static final String TAG = "NaverSearch";
    private static final String BASE_URL = "http://openapi.naver.com/search?";
    private static final String API_KEY = "9d62aa9c56b4ed922537ad43a5d29004";
    private static final String TARGET = "local";
    private static final int START = 1;
    private static final int DISPLAY = 10;

    private static final class PARAM {
        private static final String API_KEY = "key=";
        private static final String QUERY = "&query=";
        private static final String TARGET = "&target=";
        private static final String START = "&start=";
        private static final String DISPLAY = "&display=";
    }

    private ArrayList<Restaurant> restaurants;

    private String query = "부엉이 돈까스";





    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_search);

        try {
                url = BASE_URL +
                    PARAM.API_KEY + API_KEY +
                    PARAM.QUERY +URLEncoder.encode(query,"utf-8")+
                    PARAM.TARGET + TARGET  +
                    PARAM.START + START +
                    PARAM.DISPLAY + DISPLAY;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        Log.d("volley", "****1)네이버 url : "+url);
        RequestQueue queue = MyVolley.getInstance(getApplicationContext()).getRequestQueue();

        StringRequest sr = new StringRequest(Request.Method.GET, url, successListener(), errorListener());

        queue.add(sr);

    }


    private Response.Listener<String> successListener(){
        return new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                try{
                    RestaurantSearcher restaurantSearcher = new RestaurantSearcher(response, getApplicationContext());

                    Log.d("volley", "*****제어가 RestaurantSearcher로 부터 다시 SearchActivity 로갔다");

                   // search.startParsing();

                   restaurants = restaurantSearcher.getRestaurants();

                    for (int i = 0; i < restaurants.size(); i++) {
                        Log.d("volley", "이름 : " + restaurants.get(i).getTitle());
                        Log.d("volley", "주소 : " + restaurants.get(i).getAdress());
                        Log.d("volley", "카텍X : " + restaurants.get(i).getKatecX());
                        Log.d("volley", "경도X : " + restaurants.get(i).getLngX());
                    }
                }
                catch (Exception e) {
                   System.out.print(e.getMessage());
               }

            }

        };
    }

    private Response.ErrorListener errorListener(){
        return new Response.ErrorListener(){
            public void onErrorResponse(VolleyError e){
                //Log.d("volley", "error : "+e.toString());
                //Log.d("volley", "error : " + e.getMessage());

                if (e instanceof TimeoutError)
                {
                    Log.d("volley","error : "+"TimeOutError");
                }
                // 네트워크 연결이 모두 끊어진 경우
                else if (e instanceof NoConnectionError)
                {
                    Log.d("volley", "error : " + "NoConnectionError");
                }
                else if (e instanceof AuthFailureError)
                {
                    Log.d("volley", "error : " + "AuthFailureError");
                }
                // 서버에러, URL에 해당 자료가 없어도 이곳이 불린다.
                else if (e instanceof ServerError)
                {
                    Log.d("volley", "error : " + "ServerError");
                }
                else if (e instanceof NetworkError)
                {
                    Log.d("volley", "error : " + "NetworkError");
                }
                else if (e instanceof ParseError)
                {
                    Log.d("volley", "error : " + "ParseError");
                }
            }
        };
    }



}
