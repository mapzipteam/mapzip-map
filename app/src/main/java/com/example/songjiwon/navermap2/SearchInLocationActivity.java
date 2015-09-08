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

import org.xmlpull.v1.XmlPullParser;


public class SearchInLocationActivity extends NMapActivity implements NMapView.OnMapStateChangeListener, NMapView.OnMapViewTouchEventListener {

 
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



    //overlay
//    NMapViewerResourceProvider nMapViewerResourceProvider;
//    NMapOverlayManager nMapOverlayManager;
//    int markerId;
//    NMapPOIdata nMapPOIdata;
//    NMapPOIdataOverlay nMapPOIdataOverlay;


    public void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);

        //////mapContainer = (LinearLayout)findViewById(R.id.search_in_location_map);
      ////이건 뭐하는앨까?//  mapContainer = (LinearLayout)findViewById(R.id.search_in_location_map2);
        MapContainer = (LinearLayout)findViewById(R.id.search_in_location_map_2);



        mMapView = new NMapView(this);


        mMapView.setApiKey(MapActivity.API_KEY);


        mMapView.setScalingFactor(2.0f, true);


        setContentView(R.layout.activity_search_in_location2);
        ////////////////////////////////////////////////////////////////////////setContentView(mMapView);

     /*   Window window = getWindow();
        window.setContentView(R.layout.activity_search_in_location2);

        LayoutInflater layoutInflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout linearLayout = (LinearLayout)layoutInflater.inflate(R.layout.over_search_in_location, null);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.FILL_PARENT,
                LinearLayout.LayoutParams.FILL_PARENT);

        window.addContentView(linearLayout, layoutParams);*/



        mMapView.setClickable(true);


        mMapView.setOnMapStateChangeListener(this);
        mMapView.setOnMapViewTouchEventListener(this);


        mMapView.setBuiltInZoomControls(true, null);



        mMapController = mMapView.getMapController();


        //오버레이 아이콘!!!!!!1


        /*NMapProjection nMapProjection = mMapView.getMapProjection();

        NGeoPoint LT = nMapProjection.fromPixels(0,0);
        Toast.makeText(getApplicationContext(), "LT"+LT.getLongitude()+"  "+LT.getLatitude(),Toast.LENGTH_LONG);*/


        //NMapOverlayItem nMapOverlayItem = new NMapOverlayItem()




//        nMapViewerResourceProvider = new NMapViewerResourceProvider(this);
//        nMapOverlayManager = new NMapOverlayManager(this, mMapView, nMapViewerResourceProvider);
//
//        //markerId = NMapPOIflagType.CLICKABLE_ARROW;
//        markerId = NMapPOIflagType.PIN;
//
//        nMapPOIdata = new NMapPOIdata(1, nMapViewerResourceProvider);
//        nMapPOIdata.beginPOIdata(1);
//        //Log.d("SIL", "OnCreate에서의 currentLng : "+currentLNG+", currentLAT : "+currentLAT);
//        nMapPOIdata.addPOIitem(new NGeoPoint(126.97837569120298, 37.565942095656254/*currentLNG, currentLAT*/), "위치 가져오기", markerId, "SIL",0);
//        nMapPOIdata.endPOIdata();
//
//        nMapPOIdataOverlay = nMapOverlayManager.createPOIdataOverlay(nMapPOIdata, null);
//        /////필요없을듯////nMapPOIdataOverlay.showAllPOIdata(0);
//        ///nMapPOIdataOverlay.showAllPOIdata(0);
//
//        /*1*/nMapPOIdataOverlay.setOnStateChangeListener(new SearchInLocationOnPOIdataStateChangeListener());
//
//        /*2*/nMapOverlayManager.setOnCalloutOverlayListener(new SearchInLocationOnCalloutOverlayListener());
//
//        /*3*/nMapOverlayManager.setOnCalloutOverlayViewListener(new SearchInLocationOnCalloutOverlayViewListener());


       /* MapContainer.addView(mMapView);*/

//        Window window = getWindow();
//        //window.setContentView(R.layout.activity_search_in_location2);
//
//        LayoutInflater layoutInflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        LinearLayout linearLayout = (LinearLayout)layoutInflater.inflate(R.layout.over_search_in_location, null);
//        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
//                LinearLayout.LayoutParams.FILL_PARENT,
//                LinearLayout.LayoutParams.FILL_PARENT);
//
//        window.addContentView(linearLayout, layoutParams);

       // MapContainer.addView(mMapView);

    }

//    public void addPOIItem(double lng, double lat){
//
//        nMapPOIdata.removePOIitem("SIL");
//
//        nMapPOIdata.beginPOIdata(0);
//        nMapPOIdata.addPOIitem(new NGeoPoint(lng, lat), "위치 가져오기", markerId, "SIL", 0);
//        nMapPOIdata.endPOIdata();
//
//        nMapController.reload();
//
//
//    }


    ////*ok*/class SearchInLocationOnMapStateChangeListener implements NMapView.OnMapStateChangeListener{

         
        public void onMapInitHandler(NMapView nMapView, NMapError nMapError) {

            if(nMapError == null){
                mMapController.setMapCenter(Location.SEOUL, 11);
            } else{
                Log.e("SearchInLocation","SearchInMocationOnMapStateChangeListener : error = "+nMapError.toString());
            }

        }

         
        public void onMapCenterChange(NMapView nMapView, NGeoPoint nGeoPoint) {

            currentLNG = nGeoPoint.getLongitude();
            currentLAT = nGeoPoint.getLatitude();


            NMapProjection nMapProjection = mMapView.getMapProjection();

            //NGeoPoint LT = nMapProjection.fromPixels(0,0);
            //Log.d("SIL", "0,0 "+LT.getLongitude()+"  "+LT.getLatitude());

            DisplayMetrics displayMetrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

            displayCenterX = displayMetrics.widthPixels/2;
            displayCenterY = displayMetrics.heightPixels/2;


            currentPoint = nMapProjection.fromPixels(displayCenterX, displayCenterY);

            currentLNG = currentPoint.getLongitude();
            currentLAT = currentPoint.getLatitude();

//            Log.d("SIL", "currentLNG = " + currentLNG + ", currentLAT = " + currentLAT);
//
//            Log.d("SIL", "addPOIItem(currentLNG, currentLAT); is called");
//            addPOIItem(currentLNG, currentLAT);
//            Log.d("SIL", "addPOIItem(currentLNG, currentLAT); is returned");

            //nMapPOIdata.addPOIitem(new NGeoPoint(currentLNG, currentLAT), "위치 가져오기", markerId, 0);

           // nMapPOIdataOverlay.showAllPOIdata(11);



//            NGeoPoint AAA = new NGeoPoint(currentLNG, currentLAT);
//
//            int markedID = NMapPOIflagType.
//
//            String BBB = new String("지도 중심!!!");
//
//            String CCC = new String("이게 뭘까??");
//
//            Drawable DDD =
//
//            NMapOverlayItem centerOverlayItem = new NMapOverlayItem(AAA,BBB, CCC , DDD);
//
//            centerOverlayItem.setVisibility(NMapOverlayItem.VISIBLE);

           /* NMapViewerResourceProvider nMapViewerResourceProvider = new NMapViewerResourceProvider(SearchInLocationActivity.this);
            NMapOverlayManager nMapOverlayManager = new NMapOverlayManager(SearchInLocationActivity.this, nMapView, nMapViewerResourceProvider);
            int markerId = NMapPOIflagType.SINGLE_MARKER_END;
            NMapPOIdata nMapPOIdata = new NMapPOIdata(1, nMapViewerResourceProvider);
            nMapPOIdata.beginPOIdata(1);
            nMapPOIdata.addPOIitem(new NGeoPoint(currentLNG, currentLAT), "위치 가져오기", markerId, 0);
            nMapPOIdata.endPOIdata();
            NMapPOIdataOverlay nMapPOIdataOverlay = nMapOverlayManager.createPOIdataOverlay(nMapPOIdata, null);
            nMapPOIdataOverlay.showAllPOIdata(0);

            nMapPOIdataOverlay.setOnStateChangeListener(new SearchInLocationOnPOIdataStateChangeListener());

            nMapOverlayManager.setOnCalloutOverlayListener((NMapOverlayManager.OnCalloutOverlayListener) SearchInLocationActivity.this);

            nMapOverlayManager.setOnCalloutOverlayViewListener( new SearchInLocationOnCalloutOverlayViewListener());
*/


        }

         
        public void onMapCenterChangeFine(NMapView nMapView) {

        }

         
        public void onZoomLevelChange(NMapView nMapView, int i) {

        }

         
        public void onAnimationStateChange(NMapView nMapView, int i, int i1) {

        }
    ///}




    ////*ok*/class SearchInLocationOnMapViewTouchEventListener implements NMapView.OnMapViewTouchEventListener{

         
        public void onLongPress(NMapView nMapView, MotionEvent motionEvent) {

        }

         
        public void onLongPressCanceled(NMapView nMapView) {

        }

         
        public void onTouchDown(NMapView nMapView, MotionEvent motionEvent) {

        }

         
        public void onTouchUp(NMapView nMapView, MotionEvent motionEvent) {

        }

         
        public void onScroll(NMapView nMapView, MotionEvent motionEvent, MotionEvent motionEvent1) {

        }

         
        public void onSingleTapUp(NMapView nMapView, MotionEvent motionEvent) {

        }
    ///}




    ////Overlay Part
    /*1*/
   ///class SearchInLocationOnPOIdataStateChangeListener implements NMapPOIdataOverlay.OnStateChangeListener{

         
        public void onFocusChanged(NMapPOIdataOverlay nMapPOIdataOverlay, NMapPOIitem nMapPOIitem) {

        }

         
        public void onCalloutClick(NMapPOIdataOverlay nMapPOIdataOverlay, NMapPOIitem nMapPOIitem) {

            //필요한부분!!!!
            Log.d("SIL", "POI data 눌렸다!!");
        }
    ///}




    /*2*/
    ///class SearchInLocationOnCalloutOverlayListener implements NMapOverlayManager.OnCalloutOverlayListener{

         
        public NMapCalloutOverlay onCreateCalloutOverlay(NMapOverlay nMapOverlay, NMapOverlayItem nMapOverlayItem, Rect rect) {

            return new NMapCalloutBasicOverlay(nMapOverlay, nMapOverlayItem, rect);
        }
    ///}






    /*3*/
    ///class SearchInLocationOnCalloutOverlayViewListener implements  NMapOverlayManager.OnCalloutOverlayViewListener{

         
        public View onCreateCalloutOverlayView(NMapOverlay nMapOverlay, NMapOverlayItem nMapOverlayItem, Rect rect) {

            if(nMapOverlayItem != null){

                String title = nMapOverlayItem.getTitle();

                if(title != null && title.length() > 0){
                    return new NMapCalloutCustomOverlayView(SearchInLocationActivity.this, nMapOverlay, nMapOverlayItem, rect);
                }
            }
            return null;
        }
    ///}






}




