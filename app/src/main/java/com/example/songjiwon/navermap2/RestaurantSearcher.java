package com.example.songjiwon.navermap2;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.lang.ref.PhantomReference;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.content.Intent;
import android.os.NetworkOnMainThreadException;
import android.util.Log;
import android.widget.Toast;
import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

public class RestaurantSearcher extends Thread{

    @Override
    public void run() {

    }

    private Restaurant restaurant = new Restaurant();
    private ArrayList<Restaurant> restaurants = new ArrayList<Restaurant>();

    private String response;
    private Context context;


    private boolean tagId_TITLE = false;
    private boolean tagId_LINK = false;
    private boolean tagId_CATEGORY = false;
    private boolean tagId_DESCRIPTION = false;
    private boolean tagId_TELEPHONE = false;
    private boolean tagId_ADRESS = false;
    private boolean tagId_ROADADRESS = false;
    private boolean tagId_KATECX = false;
    private boolean tagId_KATECY = false;


    private int katecX;//경도, X, longtitude
    private int katecY;//위도, Y, latitude

    private double lng;
    private double lat;

    public RestaurantSearcher(String response, Context context) {

        this.response = response;
        this.context = context;

        startParsing();
    }

    public synchronized void startParsing() {

        try {

            final XmlPullParserFactory parserCreator = XmlPullParserFactory.newInstance();

            final XmlPullParser parser = parserCreator.newPullParser();

            StringReader stringReader = new StringReader(response);


            parser.setInput(stringReader);


            int parserEvent = parser.getEventType();

            String tag;

            while (parserEvent != XmlPullParser.END_DOCUMENT) {

                switch (parserEvent) {

                    case XmlPullParser.START_DOCUMENT:
                        break;

                    case XmlPullParser.END_DOCUMENT:
                        break;

                    case XmlPullParser.START_TAG:

                        tag = parser.getName();

                        if (tag.equals("title")) {
                            tagId_TITLE = true;
                        } else if (tag.equals("link")) {
                            tagId_LINK = true;
                        } else if (tag.equals("category")) {
                            tagId_CATEGORY = true;
                        } else if (tag.equals("description")) {
                            tagId_DESCRIPTION = true;
                        } else if (tag.equals("telephone")) {
                            tagId_TELEPHONE = true;
                        } else if (tag.equals("address")) {
                            tagId_ADRESS = true;
                        } else if (tag.equals("roadAddress")) {
                            tagId_ROADADRESS = true;
                        } else if (tag.equals("mapx")) {
                            tagId_KATECX = true;
                        } else if (tag.equals("mapy")) {
                            tagId_KATECY = true;
                        }
                        break;

                    case XmlPullParser.END_TAG:
                        break;

                    case XmlPullParser.TEXT:

                        if (tagId_TITLE == true) {

                            //Log.d("volley", "흠 getTitle();" + parser.getText().trim());

                            restaurant.setTitle(parser.getText().trim());
                            setTagIdFalse();
                            //Log.d("volley", "0~>: " + parser.getText().trim());

                        } else if (tagId_LINK == true) {

                            restaurant.setLink(parser.getText().trim());
                            setTagIdFalse();
                            //Log.d("volley", "1: " + parser.getText().trim());

                        } else if (tagId_CATEGORY == true) {

                            restaurant.setCategory(parser.getText().trim());
                            setTagIdFalse();
                            //Log.d("volley", "2: " + parser.getText().trim());

                        } else if (tagId_DESCRIPTION == true) {

                            restaurant.setDescription(parser.getText().trim());
                            setTagIdFalse();
                            //Log.d("volley", "3: " + parser.getText().trim());

                        } else if (tagId_TELEPHONE == true) {

                            restaurant.setTelephone(parser.getText().trim());
                            setTagIdFalse();
                            //Log.d("volley", "4: " + parser.getText().trim());

                        } else if (tagId_ADRESS == true) {

                            restaurant.setAdress(parser.getText().trim());
                            setTagIdFalse();
                            //Log.d("volley", "5: " + parser.getText().trim());

                        } else if (tagId_ROADADRESS == true) {

                            restaurant.setRoadadress(parser.getText().trim());
                            setTagIdFalse();
                            //Log.d("volley", "6: " + parser.getText().trim());

                        } else if (tagId_KATECX == true) {

                            katecX = Integer.parseInt(parser.getText());

                            //Log.d("volley", "흠 outputPoint.getX();" + katecX);

                            restaurant.setKatecX(katecX);
                            setTagIdFalse();
                            //Log.d("volley", "7: " + parser.getText().trim());

                        } else if (tagId_KATECY == true) {

                            katecY = Integer.parseInt(parser.getText());

                            //Log.d("volley", "흠 outputPoint.getY();" + katecY);

                            restaurant.setKatecY(katecY);
                            setTagIdFalse();
                            //Log.d("volley", "8: " + parser.getText().trim());

                            Log.d("volley", "카텍 값 뽑아 오는 것까지 성공 katecX: "+restaurant.getKatecX()+", katecY: "+restaurant.getKatecX());



                            Log.d("volley", "이제 카텍 컴버트 하러간다!!");

                            KatecConvertThread katecConvertThread = new KatecConvertThread(context, katecX, katecY);

                            synchronized (katecConvertThread){

                                katecConvertThread.start();
                            }
                            //katecConvertThread.start();


//                            try{
//
//                                katecConvertThread.join();
//
//                            } catch(InterruptedException e) {
//                                e.printStackTrace();
//                            }
                            Log.d("volley", "이제 카텍 컴버트 끝났다!!!!!!!try-catch 나왔다");
                            Log.d("volley", "LNG :"+katecConvertThread.getLngX()+ ",  LAT : "+katecConvertThread.getLatY());

                            restaurant.setLngX(katecConvertThread.getLngX());

                            restaurant.setLatY((katecConvertThread.getLatY()));

                            restaurant = addRestaurant(restaurant);
                            /*restaurants.add(restaurant);
                            restaurant = new Restaurant();*/
                        }
                        break;
                }
                parserEvent = parser.next();
            }
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }

    public void setTagIdFalse() {

        tagId_TITLE = false;
        tagId_LINK = false;
        tagId_CATEGORY = false;
        tagId_DESCRIPTION = false;
        tagId_TELEPHONE = false;
        tagId_ADRESS = false;
        tagId_ROADADRESS = false;
        tagId_KATECX = false;
        tagId_KATECY = false;
    }


    // restaurants.add(restaurant);
    // restaurant = new Restaurant(); 이 두 부분을 메소드화 시킨 메소드
    public Restaurant addRestaurant(Restaurant restaurant) {

        restaurants.add(restaurant);
        return new Restaurant();
    }

    public ArrayList<Restaurant> getRestaurants() {
        return restaurants;
    }
}

