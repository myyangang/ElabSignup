package com.elab.elabsignup;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;

public class IndexActivity extends AppCompatActivity {
    public TextView mIndexTextViewLatitude;
    public TextView mIndexTextViewLongitude;

    public LocationListener locationListener = new LocationListener();
    public LocationClient mLocationClient = null;
    public LocationClientOption mLocationClientOption = new LocationClientOption();
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);

        // 绑定组件
        mIndexTextViewLatitude = (TextView) findViewById(R.id.index_TextView_Latitude);
        mIndexTextViewLongitude = (TextView) findViewById(R.id.index_TextView_Longitude);

        mLocationClient = new LocationClient(getApplicationContext());
        mLocationClient.registerLocationListener(locationListener);
        mLocationClientOption.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        mLocationClientOption.setCoorType("bd0911");
        mLocationClientOption.setScanSpan(1000);
        mLocationClient.start();
//        updateLocationTextView();
    }
    public void updateLocationTextView(){
        mIndexTextViewLongitude.setText(locationListener.longitude.toString());
        mIndexTextViewLatitude.setText(locationListener.latitude.toString());
    }

}