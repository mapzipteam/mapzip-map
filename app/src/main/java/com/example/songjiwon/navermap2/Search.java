//package com.example.songjiwon.navermap2;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.StringReader;
//import java.io.UnsupportedEncodingException;
//import java.lang.ref.PhantomReference;
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.net.URLEncoder;
//import java.util.ArrayList;
//import java.util.HashMap;
//
//import org.xmlpull.v1.XmlPullParser;
//import org.xmlpull.v1.XmlPullParserException;
//import org.xmlpull.v1.XmlPullParserFactory;
//
//import android.content.Intent;
//import android.os.NetworkOnMainThreadException;
//import android.util.Log;
//import android.widget.Toast;
//import android.content.Context;
//
//import com.android.volley.Request;
//import com.android.volley.RequestQueue;
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.StringRequest;
//
//public class RestaurantParsing {
//
//    private Restaurant restaurant = new Restaurant();
//    private ArrayList<Restaurant> restaurants = new ArrayList<Restaurant>();
//
//    private String response;
//    private Context context;
//
//
//    private boolean tagIdentifier0 = false;
//    private boolean tagIdentifier1 = false;
//    private boolean tagIdentifier2 = false;
//    private boolean tagIdentifier3 = false;
//    private boolean tagIdentifier4 = false;
//    private boolean tagIdentifier5 = false;
//    private boolean tagIdentifier6 = false;
//    private boolean tagIdentifier7 = false;
//    private boolean tagIdentifier8 = false;
//
//
//
////    private static final String TAG = "DaumSearch";
////    private static final String BASE_URL = "https://apis.daum.net/local/geo/transcoord?";
////    private static final String API_KEY = "a29a5d0645f164527a11f285f4c5f1ae";
////    private static final String FROM_COORD = "KTM";
////    private static final String TO_COORD = "WGS84";
////    private static final String OUTPUT = "xml";
////
////    private static final class PARAM {
////        private static final String API_KEY = "apikey=";
////        private static final String FROM_COORD = "&fromCoord=";
////        private static final String Y = "&y=";
////        private static final String X = "&x=";
////        private static final String TO_COORD = "&toCoord=";
////        private static final String OUTPUT = "&output=";
////    }
//
//    private int katecX;//경도, X, longtitude
//    private int katecY;//위도, Y, latitude
//
//    private double lng;
//    private double lat;
//
//    public Search( String response, Context context) {
//
//        this.response = response;
//        this.context = context;
//
//        startParsing();
//    }
//
//    public void startParsing() {
//
//        try {
//
//            final XmlPullParserFactory parserCreator = XmlPullParserFactory.newInstance();
//
//            final XmlPullParser parser = parserCreator.newPullParser();
//
//            StringReader stringReader = new StringReader(response);
//
//
//            parser.setInput(stringReader);
//
//
//            int parserEvent = parser.getEventType();
//
//            String tag;
//
//            while (parserEvent != XmlPullParser.END_DOCUMENT) {
//
//              switch (parserEvent) {
//
//                    case XmlPullParser.START_DOCUMENT:
//                        break;
//
//                    case XmlPullParser.END_DOCUMENT:
//                        break;
//
//                    case XmlPullParser.START_TAG:
//
//                        tag = parser.getName();
//
//                        if (tag.equals("title")) {
//                            tagIdentifier0 = true;
//                        } else if (tag.equals("link")) {
//                            tagIdentifier1 = true;
//                        } else if (tag.equals("category")) {
//                            tagIdentifier2 = true;
//                        } else if (tag.equals("description")) {
//                            tagIdentifier3 = true;
//                        } else if (tag.equals("telephone")) {
//                            tagIdentifier4 = true;
//                        } else if (tag.equals("address")) {
//                            tagIdentifier5 = true;
//                        } else if (tag.equals("roadAddress")) {
//                            tagIdentifier6 = true;
//                        } else if (tag.equals("mapx")) {
//                            tagIdentifier7 = true;
//                        } else if (tag.equals("mapy")) {
//                            tagIdentifier8 = true;
//                        }
//                        break;
//
//                    case XmlPullParser.END_TAG:
//                        break;
//
//                    case XmlPullParser.TEXT:
//
//                        if (tagIdentifier0 == true) {
//
//                            Log.d("volley", "흠 getTitle();" + parser.getText().trim());
//
//                            restaurant.setTitle(parser.getText().trim());
//                            setTagIdentifierFalse();
//                            //Log.d("volley", "0~>: " + parser.getText().trim());
//
//                        } else if (tagIdentifier1 == true) {
//
//                            restaurant.setLink(parser.getText().trim());
//                            setTagIdentifierFalse();
//                            //Log.d("volley", "1: " + parser.getText().trim());
//
//                        } else if (tagIdentifier2 == true) {
//
//                            restaurant.setCategory(parser.getText().trim());
//                            setTagIdentifierFalse();
//                            //Log.d("volley", "2: " + parser.getText().trim());
//
//                        } else if (tagIdentifier3 == true) {
//
//                            restaurant.setDescription(parser.getText().trim());
//                            setTagIdentifierFalse();
//                            //Log.d("volley", "3: " + parser.getText().trim());
//
//                        } else if (tagIdentifier4 == true) {
//
//                            restaurant.setTelephone(parser.getText().trim());
//                            setTagIdentifierFalse();
//                            //Log.d("volley", "4: " + parser.getText().trim());
//
//                        } else if (tagIdentifier5 == true) {
//
//                            restaurant.setAdress(parser.getText().trim());
//                            setTagIdentifierFalse();
//                            //Log.d("volley", "5: " + parser.getText().trim());
//
//                        } else if (tagIdentifier6 == true) {
//
//                            restaurant.setRoadadress(parser.getText().trim());
//                            setTagIdentifierFalse();
//                            //Log.d("volley", "6: " + parser.getText().trim());
//
//                        } else if (tagIdentifier7 == true) {
//
//                            katecX = Integer.parseInt(parser.getText());
//
//                            Log.d("volley", "흠 outputPoint.getX();"+katecX);
//
//                            restaurant.setMapx(katecX);
//                            setTagIdentifierFalse();
//                            //Log.d("volley", "7: " + parser.getText().trim());
//
//                        } else if (tagIdentifier8 == true) {
//
//                            katecY = Integer.parseInt(parser.getText());
//
//                            Log.d("volley", "흠 outputPoint.getY();" + katecY);
//
//                            //////////////////////toLngLat();
//                            KatecConvert katecConvert = new KatecConvert(context, katecX, katecY);
//
//                            double[] latlng =  katecConvert.getLatLng();
//
//                            Log.d("kang",""+latlng[0]);
//                            Log.d("kang",""+latlng[1]);
//
//                            //restaurant.setMapy(katecY);
//                            setTagIdentifierFalse();
//                            //Log.d("volley", "7: " + parser.getText().trim());
//
//
//                            restaurant = addRestaurant(restaurant);
//                            /*restaurants.add(restaurant);
//                            restaurant = new Restaurant();*/
//                        }
//
//
//                        break;
//                }
//
//                parserEvent = parser.next();
//            }
//        } catch (Exception e) {
//            System.out.print(e.getMessage());
//        }
//    }
//
//    public void setTagIdentifierFalse() {
//
//        tagIdentifier0 = false;
//        tagIdentifier1 = false;
//        tagIdentifier2 = false;
//        tagIdentifier3 = false;
//        tagIdentifier4 = false;
//        tagIdentifier5 = false;
//        tagIdentifier6 = false;
//        tagIdentifier7 = false;
//        tagIdentifier8 = false;
//    }
//
//
//  /* public void toLngLat(){
//
//        HashMap<String, String> map = new HashMap<String, String>();
//
//        try{
//            URL url = new URL(BASE_URL
//                    +PARAM.API_KEY+API_KEY
//                    +PARAM.X+katecX
//                    +PARAM.Y+katecY
//                    +PARAM.FROM_COORD+FROM_COORD
//                    +PARAM.TO_COORD+TO_COORD
//                    +PARAM.OUTPUT+OUTPUT);
//
//            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
//            XmlPullParser parser = factory.newPullParser();
//
//            Log.d("volley", url.toString());
//            parser.setInput(url.openStream(), "utf-8");
//
//            int parserEvent = parser.getEventType();
//
//
//            *//*while(parserEvent != XmlPullParser.END_DOCUMENT) {
//
//                switch(parserEvent){
//
//                    case XmlPullParser.START_TAG:
//
//                        String tag = parser.getName();
//
//                        //if(tag.compareTo()==0)
//                }
//            }*//*
//        } catch(Exception e) {
//            Log.e("volley","toLngLat() 파트 Error!"+e.getMessage());
//        }
//
//    }
//
//*/
//
//
//    // restaurants.add(restaurant);
//    // restaurant = new Restaurant(); 이 두 부분을 메소드화 시킨 메소드
//    public Restaurant addRestaurant(Restaurant restaurant) {
//
//        restaurants.add(restaurant);
//        return new Restaurant();
//    }
//
//    public ArrayList<Restaurant> getRestaurants()
//    {
//        return restaurants;
//    }
//}
