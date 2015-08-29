//package com.example.songjiwon.navermap2;
//
//import android.content.Context;
//import android.util.Log;
//
//import com.android.volley.AuthFailureError;
//import com.android.volley.NetworkError;
//import com.android.volley.NoConnectionError;
//import com.android.volley.ParseError;
//import com.android.volley.Request;
//import com.android.volley.RequestQueue;
//import com.android.volley.Response;
//import com.android.volley.ServerError;
//import com.android.volley.TimeoutError;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.StringRequest;
//
//import org.xmlpull.v1.XmlPullParser;
//import org.xmlpull.v1.XmlPullParserFactory;
//
//import java.io.StringReader;
//import java.net.URL;
//import java.util.ArrayList;
//import java.util.HashMap;
//
///**
// * Created by Song  Ji won on 2015-08-28.
// */
//public class KatecConvert extends Thread{
//
//    private Context context;
//    private int katecX;
//    private int katecY;
//
//    private double lngX;
//    private double latY;
//
//
//
//    private static final String TAG = "DaumSearch";
//    private static final String BASE_URL = "https://apis.daum.net/local/geo/transcoord?";
//    private static final String API_KEY = "f1c9190bf38205e7915bb350c8f0f90e";
//    private static final String FROM_COORD = "KTM";
//    private static final String TO_COORD = "WGS84";
//    private static final String OUTPUT = "xml";
//
//    private static final class PARAM {
//        private static final String API_KEY = "apikey=";
//        private static final String FROM_COORD = "&fromCoord=";
//        private static final String Y = "&y=";
//        private static final String X = "&x=";
//        private static final String TO_COORD = "&toCoord=";
//        private static final String OUTPUT = "&output=";
//    }
//
//    private String url;
//
//    private boolean tagId_KATECX = false;
//    private boolean tagId_KATECY = false;
//
//
//    public KatecConvert(Context context, int katecX, int katecY){
//
//        this.context = context;
//        this.katecX = katecX;
//        this.katecY = katecY;
//
//        startConvert();
//    }
//
//    public void startConvert(){
//
//      try{
//            url = BASE_URL
//                    +PARAM.API_KEY+API_KEY
//                    +PARAM.X+katecX
//                    +PARAM.Y+katecY
//                    +PARAM.FROM_COORD+FROM_COORD
//                    +PARAM.TO_COORD+TO_COORD
//                    +PARAM.OUTPUT+OUTPUT;
//
//        } catch(Exception e) {
//            Log.e("volley","KatecConvert startConvert 파트 Error!"+e.getMessage());
//        }
//
//        Log.d("volley", url);
//
//        RequestQueue queue = MyVolley.getInstance(context).getRequestQueue();
//
//        StringRequest sr = new StringRequest(Request.Method.GET, url, successListener(), errorListener());
//
//        queue.add(sr);
//
//    }
//
//
//    private Response.Listener<String> successListener(){
//        return new Response.Listener<String>() {
//
//            @Override
//            public void onResponse(String response) {
//
//                try{
//                    final XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
//                    final XmlPullParser parser = factory.newPullParser();
//
//                    StringReader stringReader = new StringReader(response);
//
//                    parser.setInput(stringReader);
//
//
//                    int parserEvent = parser.getEventType();
//
//                    String tag;
//
//                    while(parserEvent != XmlPullParser.END_DOCUMENT) {
//
//                        switch(parserEvent) {
//
//                            case XmlPullParser.START_DOCUMENT:
//                                break;
//
//
//                            case XmlPullParser.END_DOCUMENT:
//                                break;
//
//
//                            case XmlPullParser.START_TAG:
//
//                                tag = parser.getName();
//
//                                if(tag.equals("result")){
//
//                                    lngX = Double.parseDouble(parser.getAttributeValue(null,"x"));
//
//                                    Log.d("volley", "어우어워워우어ㅜ어ㅜ어워: "+lngX);
//                                    latY = Double.parseDouble(parser.getAttributeValue(null,"y"));
//                                }
//
//                                break;
//                            }
//
//                        Log.d("volley", "456");
//                        parserEvent = parser.next();
//                        Log.d("volley", "789");
//
//                    }
//
//                    notifyAll();
//                }
//                catch (Exception e) {
//                    System.out.print(e.getMessage());
//                }
//
//            }
//        };
//    }
//
//
//    public void setTagIdFalse(){
//
//        tagId_KATECX = false;
//        tagId_KATECY = false;
//    }
//
//
//    private Response.ErrorListener errorListener(){
//        return new Response.ErrorListener(){
//            public void onErrorResponse(VolleyError e){
//                Log.d("volley", "error : "+e.toString());
//                Log.d("volley", "error : " + e.getMessage());
//                }
//        };
//    }
//
//
//    public double getLngX() {
//        return lngX;
//    }
//
//    public double getLatY() {
//        return latY;
//    }
//
//
//
//
//}
