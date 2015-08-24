package com.example.songjiwon.navermap2;

import android.app.ActionBar;
import android.app.DownloadManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

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

    private String query = "우마이도";
    //private String query = null;

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

//        } catch (MalformedURLException e) {
//                        e.printStackTrace();
//
//        } catch (UnsupportedEncodingException e) {
//
//            e.printStackTrace();
//
//        }

        //url = "http://openapi.naver.com/search?key=c1b406b32dbbbbeee5f2a36ddc14067f&query=갈비집&target=local&start=1&display=10";
        RequestQueue queue = MyVolley.getInstance(getApplicationContext()).getRequestQueue();

        StringRequest sr = new StringRequest(Request.Method.GET,url,successListener(),errorListener());

        queue.add(sr);

    }


    private Response.Listener<String> successListener(){
        return new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("volley","success : \n"+response);

//                Search search = new Search();
//                Restaurant restaurant = new Restaurant() ;
//                restaurant = search.getRestaurantData("");
//
//                Log.d("volley","식당 이름 : "+restaurant.getTitle());
//                Log.d("volley","식당 전화번호: "+restaurant.getTitle());
//                Log.d("volley","식당 주소: "+restaurant.getTitle());





                Restaurant restaurant = new Restaurant();

                try {

                    final XmlPullParserFactory parserCreator = XmlPullParserFactory.newInstance();

                    final XmlPullParser parser = parserCreator.newPullParser();

                    InputStream A = new ByteArrayInputStream(response.getBytes("utf-8"));


                    parser.setInput(A, "utf-8");



                    int parserEvent = parser.getEventType();

                    boolean tagIdentifier0 = false;
                    boolean tagIdentifier1 = false;
                    boolean tagIdentifier2 = false;
                    boolean tagIdentifier3 = false;
                    boolean tagIdentifier4 = false;
                    boolean tagIdentifier5 = false;
                    boolean tagIdentifier6 = false;
                    boolean tagIdentifier7 = false;
                    boolean tagIdentifier8 = false;

                    String tag;

                    while (parserEvent != XmlPullParser.END_DOCUMENT) {

                        switch (parserEvent) {
                            case XmlPullParser.START_DOCUMENT:
                                break;

                            case XmlPullParser.END_DOCUMENT:
                                break;

                            case XmlPullParser.START_TAG:
                                tag = parser.getName();


                                if (tag.equals("tittle")) {
                                    tagIdentifier0 = true;
                                } else if (tag.equals("link")) {
                                    tagIdentifier1 = true;
                                } else if (tag.equals("category")) {
                                    tagIdentifier2 = true;
                                } else if (tag.equals("description")) {
                                    tagIdentifier3 = true;
                                } else if (tag.equals("telephone")) {
                                    tagIdentifier4 = true;
                                } else if (tag.equals("adress")) {
                                    tagIdentifier5 = true;
                                } else if (tag.equals("roadadress")) {
                                    tagIdentifier6 = true;
                                } else if (tag.equals("mapx")) {
                                    tagIdentifier7 = true;

                                } else if (tag.equals("mapy")) {
                                    tagIdentifier8 = true;
                                }
                                break;

                            case XmlPullParser.END_TAG:
                                break;

                            case XmlPullParser.TEXT:
                                if (tagIdentifier0 == true) {
                                    restaurant.setTitle(parser.getText().trim());
                                    tagIdentifier0 = false;
                                    Log.d("volley", "0: " + parser.getText().trim());
                                } else if (tagIdentifier1 == true) {
                                    restaurant.setLink(parser.getText().trim());
                                    tagIdentifier1 = false;
                                    Log.d("volley", "1: " + parser.getText().trim());
                                } else if (tagIdentifier2 == true) {
                                    restaurant.setCategory(parser.getText().trim());
                                    tagIdentifier2 = false;
                                    Log.d("volley", "2: " + parser.getText().trim());
                                } else if (tagIdentifier3 == true) {
                                    restaurant.setDescription(parser.getText().trim());
                                    tagIdentifier3 = false;
                                    Log.d("volley", "3: " + parser.getText().trim());
                                } else if (tagIdentifier4 == true) {
                                    restaurant.setTelephone(parser.getText().trim());
                                    tagIdentifier4 = false;
                                    Log.d("volley", "4: " + parser.getText().trim());
                                } else if (tagIdentifier5 == true) {
                                    restaurant.setAdress(parser.getText().trim());
                                    tagIdentifier5 = false;
                                    Log.d("volley", "5: " + parser.getText().trim());
                                } else if (tagIdentifier6 == true) {
                                    restaurant.setRoadadress(parser.getText().trim());
                                    tagIdentifier6 = false;
                                    Log.d("volley", "6: " + parser.getText().trim());
                                } else if (tagIdentifier7 == true) {
                                    restaurant.setMapx(Integer.parseInt(parser.getText()));
                                    tagIdentifier7 = false;
                                    Log.d("volley", "7: " + parser.getText().trim());
                                } else if (tagIdentifier8 == true) {
                                    restaurant.setMapy(Integer.parseInt(parser.getText()));
                                    tagIdentifier8 = false;
                                    Log.d("volley", "8: " + parser.getText().trim());
                                }

                                break;
                        }
                        parserEvent = parser.next();
                        }
                    } catch (Exception e) {
                    //throw new RuntimeException(e);
                    //Context context = getApplicationContext();
                    //Toast.makeText(, e.getMessage(), Toast.LENGTH_LONG).show();
                    System.out.print(e.getMessage());
                }




                Log.d("volley","식당 이름 : "+restaurant.getTitle());
                Log.d("volley","식당 전화번호: "+restaurant.getTelephone());
                Log.d("volley","식당 주소: "+restaurant.getAdress());


            }
        };
    }
    private Response.ErrorListener errorListener(){
        return new Response.ErrorListener(){
            public void onErrorResponse(VolleyError e){
                Log.d("volley","error : "+e.getMessage());
            }


        };
    }

}
