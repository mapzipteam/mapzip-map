//package com.example.songjiwon.navermap2;
//
//import android.graphics.Rect;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.MotionEvent;
//import android.widget.LinearLayout;
//import android.widget.RelativeLayout;
//
//import com.nhn.android.maps.NMapActivity;
//import com.nhn.android.maps.NMapController;
//import com.nhn.android.maps.NMapOverlay;
//import com.nhn.android.maps.NMapOverlayItem;
//import com.nhn.android.maps.NMapView;
//import com.nhn.android.maps.maplib.NGeoPoint;
//import com.nhn.android.maps.nmapmodel.NMapError;
//import com.nhn.android.maps.overlay.NMapPOIdata;
//import com.nhn.android.maps.overlay.NMapPOIitem;
//import com.nhn.android.mapviewer.overlay.NMapCalloutOverlay;
//import com.nhn.android.mapviewer.overlay.NMapOverlayManager;
//
///**
// * Created by Song  Ji won on 2015-09-02.
// */
//public class SearchInLocationActivity extends NMapActivity implements NMapView.OnMapStateChangeListener, NMapView.OnMapViewTouchEventListener, /*이제부턴 오버레이 아이콘*/NMapOverlayManager.OnCalloutOverlayListener {
//
//    private NMapView mMapView = null;
//    private NGeoPoint centerPoint = Location.SEOUL;
//    private NMapController mMapController = null;
//    private LinearLayout mapContainer;
//
//    private NMapViewerResourceProvider nMapViewerResourceProvider = null;
//
//
//    public void onCreate(Bundle savedInstanceState){
//
//        super.onCreate(savedInstanceState);
//
//        setContentView(R.layout.activity_search_in_location);
//
//        mapContainer = (LinearLayout)findViewById(R.id.search_in_location_map);
//
//        mMapView = new NMapView(this);
//
//        mMapView.setApiKey(MapActivity.API_KEY);//API키 설정
//
//        mapContainer.addView(mMapView);
//
//
//        //mMapView 갖 가지 설정
//        mMapView.setClickable(true);//터치 되게 만든다
//
//        mMapView.setOnMapStateChangeListener(this);//맵 상태 변화 리스너 셑팅
//        mMapView.setOnMapViewTouchEventListener(this);
//
//
//        //mMapView.setLogoImageOffset(-100, -500);//네이버 로고 안보이제 하는 부분
//
//        mMapView.setBuiltInZoomControls(true, null);//좀 컨트롤 가능 하게 만들고, 위치는 원애있던곳에 박아놓음
//
//        mMapView.displayZoomControls(true);//줌컨트롤 보이게 만듬
//
//        mMapView.setAutoRotateEnabled(false, false);//지도 회전(세로보기) 불가
//
//        centerPoint = mMapView.getMapProjection().fromPixels(mMapView.getWidth()/2, mMapView.getHeight()/2);//현재 지도의 중심 좌쵸 뽑음
//
//        //Log.d("위치지정","centerPoint - getLongitude() : "+centerPoint.getLongitude()+", getLatitude() : "+centerPoint.getLatitude());
//
//        mMapController = mMapView.getMapController();
//
//
//    }
//
//
//     
//    public NMapCalloutOverlay onCreateCalloutOverlay(NMapOverlay nMapOverlay, NMapOverlayItem nMapOverlayItem, Rect rect) {
//        return null;
//    }
//
//     
//    public void onMapCenterChange(NMapView nMapView, NGeoPoint nGeoPoint) {
//
//        centerPoint = nGeoPoint;
//
//    }
//
//
//
//
//    /* */
//    public void onMapInitHandler(NMapView nMapView, NMapError nMapError) {
//        if (nMapError == null) {
//            mMapController.setMapCenter(Location.SEOUL);
//        } else {
//            Log.d("위치지정", "onMapInitHandler error " + nMapError.toString());
//        };
//    }
//    /* */
//    public void onMapCenterChangeFine(NMapView nMapView) {
//
//    }
//
//    /* */
//    public void onZoomLevelChange(NMapView nMapView, int i) {
//
//    }
//
//    /* */
//    public void onAnimationStateChange(NMapView nMapView, int i, int i1) {
//
//    }
//
//    /* */
//    public void onLongPress(NMapView nMapView, MotionEvent motionEvent) {
//
//    }
//
//    /* */
//    public void onLongPressCanceled(NMapView nMapView) {
//
//    }
//
//    /* */
//    public void onTouchDown(NMapView nMapView, MotionEvent motionEvent) {
//
//    }
//
//    /* */
//    public void onTouchUp(NMapView nMapView, MotionEvent motionEvent) {
//
//    }
//
//    /* */
//    public void onScroll(NMapView nMapView, MotionEvent motionEvent, MotionEvent motionEvent1) {
//
//    }
//
//    /* */
//    public void onSingleTapUp(NMapView nMapView, MotionEvent motionEvent) {
//
//    }
//}
//

package com.example.songjiwon.navermap2;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.nhn.android.maps.NMapActivity;
import com.nhn.android.maps.NMapController;
import com.nhn.android.maps.NMapOverlay;
import com.nhn.android.maps.NMapOverlayItem;
import com.nhn.android.maps.NMapProjection;
import com.nhn.android.maps.NMapView;
import com.nhn.android.maps.maplib.NGeoPoint;
import com.nhn.android.maps.nmapmodel.NMapError;
import com.nhn.android.maps.overlay.NMapPOIdata;
import com.nhn.android.maps.overlay.NMapPOIitem;
import com.nhn.android.mapviewer.overlay.NMapCalloutOverlay;
import com.nhn.android.mapviewer.overlay.NMapOverlayManager;
import com.nhn.android.mapviewer.overlay.NMapPOIdataOverlay;


import com.nhn.android.maps.NMapView.OnMapStateChangeListener;
import com.nhn.android.mapviewer.overlay.NMapOverlayManager.OnCalloutOverlayListener;



public class SearchInLocationActivity extends NMapActivity implements OnMapStateChangeListener{

 
    //default Member variable declare
    public static final String API_KEY = MapActivity.API_KEY;
    NMapView mMapView = null;
    NMapController mMapController = null;
    LinearLayout MapContainer;
    //finish


    //current location
    NGeoPoint currentPoint;
    private double currentLNG; //경도, X
    private double currentLAT; //위도, Y

    int displayCenterX;
    int displayCenterY;



    private Button goBackButton;
    private Button makeReviewButton;





    public void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_search_in_location);

        MapContainer = (LinearLayout)findViewById(R.id.search_in_location_map);

        mMapView = new NMapView(this);
        Log.d("SIL", "*15@" + mMapView.getHeight());
        mMapController = mMapView.getMapController();
        Log.d("SIL", "*16@" + mMapView.getHeight());
        mMapView.setApiKey(API_KEY);
        Log.d("SIL", "*17@" + mMapView.getHeight());

        MapContainer.addView(mMapView);
        Log.d("SIL", "*18@" + mMapView.getHeight());




        mMapView.setClickable(true);
        Log.d("SIL", "*19@" + mMapView.getHeight());

        mMapView.setBuiltInZoomControls(true, null);
        Log.d("SIL", "*110@" + mMapView.getHeight());

        mMapView.setOnMapStateChangeListener(this);
        //mMapView.setOnMapViewTouchEventListener(this);
        Log.d("SIL", "*111@" + mMapView.getHeight());


        //Button part
        goBackButton = (Button)findViewById(R.id.search_in_location_go_back_button);
        makeReviewButton = (Button)findViewById(R.id.search_in_location_make_review_button);

        goBackButton.setOnClickListener(SearchInLocationActivityOnClickListener);
        makeReviewButton.setOnClickListener(SearchInLocationActivityOnClickListener);
        //fin


    }


    ////*ok*/class SearchInLocationOnMapStateChangeListener implements NMapView.OnMapStateChangeListener{

         
        public void onMapInitHandler(NMapView nMapView, NMapError nMapError) {
            Log.d("SIL", "*21@" + mMapView.getHeight());
            if(nMapError == null){
                Log.d("SIL", "*22@" + mMapView.getHeight());
                mMapController.setMapCenter(Location.SEOUL, 11);
                Log.d("SIL", "*23@" + mMapView.getHeight());
            } else{
                Log.d("SIL", "*24@" + mMapView.getHeight());
                Log.e("SearchInLocation", "SearchInMocationOnMapStateChangeListener : error = " + nMapError.toString());
                Log.d("SIL", "*25@" + mMapView.getHeight());
            }

        }

         
        public void onMapCenterChange(NMapView nMapView, NGeoPoint nGeoPoint) {
            Log.d("SIL", "*31@" + mMapView.getHeight());
            currentLNG = nGeoPoint.getLongitude();
            Log.d("SIL", "*32@" + mMapView.getHeight());
            currentLAT = nGeoPoint.getLatitude();
            Log.d("SIL", "*33@" + mMapView.getHeight());


            NMapProjection nMapProjection = mMapView.getMapProjection();
            Log.d("SIL", "*34@" + mMapView.getHeight());

            //NGeoPoint LT = nMapProjection.fromPixels(0,0);
            Log.d("SIL", "중심좌표의 위도 경도 : "+currentLNG+" ; "+currentLAT);

            DisplayMetrics displayMetrics = new DisplayMetrics();
            Log.d("SIL", "*35@" + mMapView.getHeight());
            getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            Log.d("SIL", "*36@" + mMapView.getHeight());

            displayCenterX = displayMetrics.widthPixels/2;
            Log.d("SIL", "*37@" + mMapView.getHeight());
            displayCenterY = displayMetrics.heightPixels/2;
            Log.d("SIL", "*38@" + mMapView.getHeight());


            currentPoint = nMapProjection.fromPixels(displayCenterX, displayCenterY);
            Log.d("SIL", "*39@" + mMapView.getHeight());

            currentLNG = currentPoint.getLongitude();
            Log.d("SIL", "*310@" + mMapView.getHeight());
            currentLAT = currentPoint.getLatitude();
            Log.d("SIL", "*311@" + mMapView.getHeight());

        }

         
        public void onMapCenterChangeFine(NMapView nMapView) {
            Log.d("SIL", "*41@" + mMapView.getHeight());

        }

         
        public void onZoomLevelChange(NMapView nMapView, int i) {
            Log.d("SIL", "*51@" + mMapView.getHeight());
        }

         
        public void onAnimationStateChange(NMapView nMapView, int i, int i1) {
            Log.d("SIL", "*61@" + mMapView.getHeight());
        }
    ///}




    ////*ok*/class SearchInLocationOnMapViewTouchEventListener implements NMapView.OnMapViewTouchEventListener{

         
        public void onLongPress(NMapView nMapView, MotionEvent motionEvent) {
            Log.d("SIL", "*71@" + mMapView.getHeight());
        }

         
        public void onLongPressCanceled(NMapView nMapView) {
            Log.d("SIL", "*81@" + mMapView.getHeight());
        }

         
        public void onTouchDown(NMapView nMapView, MotionEvent motionEvent) {
            Log.d("SIL", "*91@" + mMapView.getHeight());
        }

         
        public void onTouchUp(NMapView nMapView, MotionEvent motionEvent) {
            Log.d("SIL", "*101@" + mMapView.getHeight());
        }

         
        public void onScroll(NMapView nMapView, MotionEvent motionEvent, MotionEvent motionEvent1) {
            Log.d("SIL", "*111@" + mMapView.getHeight());
        }

         
        public void onSingleTapUp(NMapView nMapView, MotionEvent motionEvent) {

        }
    ///}




    ////Overlay Part
    /*1*/
   ///class SearchInLocationOnPOIdataStateChangeListener implements NMapPOIdataOverlay.OnStateChangeListener{

         
        public void onFocusChanged(NMapPOIdataOverlay nMapPOIdataOverlay, NMapPOIitem nMapPOIitem) {
            Log.d("SIL", "*121@" + mMapView.getHeight());
        }

         
        public void onCalloutClick(NMapPOIdataOverlay nMapPOIdataOverlay, NMapPOIitem nMapPOIitem) {
            Log.d("SIL", "*131@" + mMapView.getHeight());
            //필요한부분!!!!
            Log.d("SIL", "POI data 눌렸다!!");
        }
    ///}




    /*2*/
    ///class SearchInLocationOnCalloutOverlayListener implements NMapOverlayManager.OnCalloutOverlayListener{

         
        public NMapCalloutOverlay onCreateCalloutOverlay(NMapOverlay nMapOverlay, NMapOverlayItem nMapOverlayItem, Rect rect) {
            Log.d("SIL", "*141@" + mMapView.getHeight());
            return new NMapCalloutBasicOverlay(nMapOverlay, nMapOverlayItem, rect);
        }
    ///}






    /*3*/
    ///class SearchInLocationOnCalloutOverlayViewListener implements  NMapOverlayManager.OnCalloutOverlayViewListener{

         
        public View onCreateCalloutOverlayView(NMapOverlay nMapOverlay, NMapOverlayItem nMapOverlayItem, Rect rect) {
            Log.d("SIL", "*151@" + mMapView.getHeight());
            if(nMapOverlayItem != null){
                Log.d("SIL", "*161@" + mMapView.getHeight());
                String title = nMapOverlayItem.getTitle();
                Log.d("SIL", "*171@" + mMapView.getHeight());
                if(title != null && title.length() > 0){
                    Log.d("SIL", "*181@" + mMapView.getHeight());
                    return new NMapCalloutCustomOverlayView(SearchInLocationActivity.this, nMapOverlay, nMapOverlayItem, rect);
                }
            }
            Log.d("SIL", "*191@" + mMapView.getHeight());
            return null;
        }
    ///}





    //////버튼 리스너
    Button.OnClickListener SearchInLocationActivityOnClickListener = new View.OnClickListener(){

        public void onClick(View v){

            switch(v.getId()){

                case R.id.search_in_location_go_back_button:
                    finish();
                    break;

                case R.id.search_in_location_make_review_button:
                    Toast.makeText(getApplicationContext(), "경도 : "+currentLNG+"\n위도 : "+currentLAT+"\n\n make a review??",Toast.LENGTH_LONG).show();
            }
        }
    };

}




