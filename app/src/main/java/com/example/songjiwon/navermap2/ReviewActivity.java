package com.example.songjiwon.navermap2;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

/**
 * Created by Song  Ji won on 2015-07-31.
 */
public class ReviewActivity extends Activity
{
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
    }

    public void onButton1Clicked(View v)
    {
        Toast.makeText(getApplicationContext(), "���� �������� ���ư����", Toast.LENGTH_LONG).show();

        finish();
   }
}
