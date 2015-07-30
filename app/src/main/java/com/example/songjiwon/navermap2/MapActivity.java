package com.example.songjiwon.navermap2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.nhn.android.maps.NMapActivity;
import com.nhn.android.maps.NMapController;
import com.nhn.android.maps.NMapView;
import com.nhn.android.maps.NMapView.OnMapStateChangeListener;
import com.nhn.android.maps.NMapView.OnMapViewTouchEventListener;
import com.nhn.android.maps.maplib.NGeoPoint;
import com.nhn.android.maps.nmapmodel.NMapError;

import static com.example.songjiwon.navermap2.Location.GANGNAMGU;
import static com.example.songjiwon.navermap2.Location.SEOUL;


public class MapActivity extends NMapActivity implements NMapView.OnMapStateChangeListener, NMapView.OnMapViewTouchEventListener {
    public static final String API_KEY = "d38869cf3ca862bf9e45d02b6ec3faeb";
    NMapView mMapView = null;
    NMapController mMapController = null;
    LinearLayout MapContainer;

    NGeoPoint current_point = SEOUL;

   /////////////////////// private int GU_NUM = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        ////////////////////GU_NUM = getIntent().getExtras().getInt("location");
        //ㄴㄴ이건 안될것같다........으어current_point =(NGeoPoint)(getIntent().getExtras().getInt("location"));

        //이거 해보자!!!
        current_point = new NGeoPoint(getIntent().getExtras().getDouble("LNG"), getIntent().getExtras().getDouble("LAT"));

        //g확인용 토스트
        Toast.makeText(getApplicationContext(),"지도화면에서 받은 LNG : "+getIntent().getExtras().getDouble("LNG"),Toast.LENGTH_LONG).show();


        Toast.makeText(getApplicationContext(),"지도화면에서 받은 LAT : "+getIntent().getExtras().getDouble("LAT"),Toast.LENGTH_LONG).show();

        //////////////////////////////current_point.setCurrent_point(GU_NUM);

        super.onCreate(savedInstanceState);

        MapContainer = (LinearLayout) findViewById(R.id.map);

        mMapView = new NMapView(this);

        mMapView.setApiKey(API_KEY);

        setContentView(mMapView);

        mMapView.setClickable(true);

        /****/mMapView.setOnMapStateChangeListener(this);

        /****/mMapView.setOnMapViewTouchEventListener(this);

        mMapView.setBuiltInZoomControls(true, null);

        mMapController = mMapView.getMapController();

        ///int b = getIntent().getExtras().getInt("location");


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onMapInitHandler(NMapView mapview, NMapError errorInfo) {
        if (errorInfo == null) {
            mMapController.setMapCenter(current_point, 9);
        } else {
            android.util.Log.e("NMAP", "onMapInitHandler : error=" + errorInfo.toString());
        }
    }

    @Override
    public void onMapCenterChange(NMapView nMapView, NGeoPoint nGeoPoint) {

    }

    @Override
    public void onMapCenterChangeFine(NMapView nMapView) {

    }

    @Override
    public void onZoomLevelChange(NMapView nMapView, int i) {

    }

    @Override
    public void onAnimationStateChange(NMapView nMapView, int i, int i1) {

    }

    @Override
    public void onLongPress(NMapView nMapView, MotionEvent motionEvent) {

    }

    @Override
    public void onLongPressCanceled(NMapView nMapView) {

    }

    @Override
    public void onTouchDown(NMapView nMapView, MotionEvent motionEvent) {

    }

    @Override
    public void onTouchUp(NMapView nMapView, MotionEvent motionEvent) {

    }

    @Override
    public void onScroll(NMapView nMapView, MotionEvent motionEvent, MotionEvent motionEvent1) {

    }

    @Override
    public void onSingleTapUp(NMapView nMapView, MotionEvent motionEvent) {

    }

//    public NGeoPoint setCurrent_point(int gu_num)
////    {
////
//////        if(gu_num==1)   {   current_point=GANGNAMGU;    }
////////        else if(gu_num==)   {   current_point=; }
//////////    }


}
