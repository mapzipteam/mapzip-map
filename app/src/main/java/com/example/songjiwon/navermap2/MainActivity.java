package com.example.songjiwon.navermap2;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onButton1Clicked(View v)//"1.강남구"버튼
    {
        Intent intent = new Intent(getApplicationContext(),MapActivity.class);

//준수형이 해주신거 이런식으로 바꿔야해intent.putExtra("location", 1);
/////ㄴㄴ이게 젤좋은데 안되는것같다intent.putExtra("location", (Parcelable)Location.setLocation(1));

        intent.putExtra("LNG", Location.GANGNAMGU_LNG);
        intent.putExtra("LAT", Location.GANGNAMGU_LAT);

        startActivity(intent);
    }

    public void onButton2Clicked(View v)//"2.강동구"버튼
    {
        Intent intent = new Intent(getApplicationContext(), MapActivity.class);

       //잠시만한거 안더ㅣ면 이거해!! intent.putExtra("location", 2 );
        intent.putExtra("LNG", Location.GANGDONGGU_LNG);
        intent.putExtra("LAT", Location.GANGDONGGU_LAT);

        startActivity(intent);
    }

    public void onButton3Clicked(View v)//"5.관악구"버튼
    {
        Intent intent = new Intent(getApplicationContext(),MapActivity.class);

        //intent.putExtra("location", 5);
        intent.putExtra("LNG", Location.GWANAKGU_LNG);
        intent.putExtra("LAT", Location.GWANAKGU_LAT);

        startActivity(intent);
    }

    public void onButton4Clicked(View v)//"6.광진구"버튼
    {
        Intent intent = new Intent(getApplicationContext(),MapActivity.class);

        //intent.putExtra("location", 6);
        intent.putExtra("LNG", Location.GWANGJINGU_LNG);
        intent.putExtra("LAT", Location.GWANGJINGU_LAT);

        startActivity(intent);
    }
}
