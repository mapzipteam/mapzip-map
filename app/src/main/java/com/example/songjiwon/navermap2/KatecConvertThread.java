package com.example.songjiwon.navermap2;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;


public class KatecConvertThread extends Thread {

    private Context context;

    private int katecX;
    private int katecY;

    private double lngX;
    private double latY;


    private static final String TAG = "DaumSearch";
    private static final String BASE_URL = "https://apis.daum.net/local/geo/transcoord?";
    private static final String API_KEY = "f1c9190bf38205e7915bb350c8f0f90e";
    private static final String FROM_COORD = "KTM";
    private static final String TO_COORD = "WGS84";
    private static final String OUTPUT = "xml";

    private static final class PARAM {
        private static final String API_KEY = "apikey=";
        private static final String FROM_COORD = "&fromCoord=";
        private static final String Y = "&y=";
        private static final String X = "&x=";
        private static final String TO_COORD = "&toCoord=";
        private static final String OUTPUT = "&output=";
    }

    private String url;

    private boolean tagId_KATECX = false;
    private boolean tagId_KATECY = false;


    public KatecConvertThread(Context context, int katecX, int katecY) {

        this.context = context;
        this.katecX = katecX;
        this.katecY = katecY;


    }

    public synchronized void run() {
        Log.d("volley", "KatecConvertThread의 run()에 들어와있다");

        try {
            url = BASE_URL
                    + PARAM.API_KEY + API_KEY
                    + PARAM.X + katecX
                    + PARAM.Y + katecY
                    + PARAM.FROM_COORD + FROM_COORD
                    + PARAM.TO_COORD + TO_COORD
                    + PARAM.OUTPUT + OUTPUT;

        } catch (Exception e) {
            Log.e("volley", "KatecConvert startConvert 파트 Error!" + e.getMessage());
        }

        Log.d("volley", "****2)다음  url : "+url);

        RequestQueue queue = MyVolley.getInstance(context).getRequestQueue();

        StringRequest sr = new StringRequest(Request.Method.GET, url,


                new Response.Listener<String>() {
                    public void onResponse(String response) {

                        Log.d("volley", "KatecConvertThread의 successListener 들어옴");

                        try {
                            final XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                            final XmlPullParser parser = factory.newPullParser();

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

                                        if (tag.equals("result")) {

                                            lngX = Double.parseDouble(parser.getAttributeValue(null, "x"));
                                            Log.d("volley", "katecX를 Lng값으로 받아왔고 그값은 ::::: " + lngX);

                                            latY = Double.parseDouble(parser.getAttributeValue(null, "y"));
                                            Log.d("volley", "katecY를 Lat값으로 받아왔고 그값은 ::::: " + latY);
                                        }
                                        break;
                                }

                                parserEvent = parser.next();
                            }

                            notifyAll();
                        } catch (Exception e) {
                            System.out.print(e.getMessage());
                        }

                    }
                }
                , /*errorListener());*/
                new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError e) {
                        Log.d("volley", "error : " + e.toString());
                        Log.d("volley", "error : " + e.getMessage());
                    }

                });

        queue.add(sr);

    }

/*

    private Response.Listener<String> successListener(){
        return new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                Log.d("volley", "KatecConvertThread의 successListener 들어옴");

                try{
                    final XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                    final XmlPullParser parser = factory.newPullParser();

                    StringReader stringReader = new StringReader(response);

                    parser.setInput(stringReader);


                    int parserEvent = parser.getEventType();

                    String tag;

                    while(parserEvent != XmlPullParser.END_DOCUMENT) {

                        switch(parserEvent) {

                            case XmlPullParser.START_DOCUMENT:
                                break;


                            case XmlPullParser.END_DOCUMENT:
                                break;


                            case XmlPullParser.START_TAG:

                                tag = parser.getName();

                                if(tag.equals("result")){

                                    lngX = Double.parseDouble(parser.getAttributeValue(null,"x"));
                                    Log.d("volley", "katecX를 Lng값으로 받아왔고 그값은 ::::: "+lngX);

                                    latY = Double.parseDouble(parser.getAttributeValue(null,"y"));
                                    Log.d("volley", "katecY를 Lat값으로 받아왔고 그값은 ::::: "+latY);
                                }
                                break;
                        }

                        parserEvent = parser.next();
                    }

                    notifyAll();
                }
                catch (Exception e) {
                    System.out.print(e.getMessage());
                }

            }
        };
    }

*/

/*

    private Response.ErrorListener errorListener(){
        return new Response.ErrorListener(){
            public void onErrorResponse(VolleyError e){
                Log.d("volley", "error : "+e.toString());
                Log.d("volley", "error : " + e.getMessage());
            }
        };
    }

*/

    public double getLngX() {
        return lngX;
    }

    public double getLatY() {
        return latY;
    }


}

