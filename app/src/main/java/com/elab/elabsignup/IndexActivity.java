package com.elab.elabsignup;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;

public class IndexActivity extends AppCompatActivity {
    private TextView mIndexTextViewLatitude;
    private TextView mIndexTextViewLongitude;

    private final BDAbstractLocationListener mBDAbstractLocationListener = new BDAbstractLocationListener() {
        @Override public void onReceiveLocation(BDLocation bdLocation) {
            if(bdLocation != null && bdLocation.getLocType() != BDLocation.TypeServerError){
                updateLocationTextView(bdLocation.getLatitude(),bdLocation.getLongitude());
            }
        }
        @Override public void onLocDiagnosticMessage(int locType,int diagnosticType,String diagnosticMessage){
            super.onLocDiagnosticMessage(locType,diagnosticType,diagnosticMessage);
            switch (locType){
                case BDLocation.TypeNetWorkLocation:
                    if(diagnosticType == 1){
                        makeToast(R.string.index_Toast_SuccessWithoutGPS);
                    }
                    if(diagnosticType == 2){
                        makeToast(R.string.index_Toast_SuccessWithoutWifi);
                    }
                    break;
                case BDLocation.TypeOffLineLocationFail:
                    if(diagnosticType == 3){
                        makeToast(R.string.index_Toast_FailWithoutBase);
                    }
                    break;
                case BDLocation.TypeCriteriaException:
                    if(diagnosticType == 4){
                        makeToast(R.string.index_Toast_FailWithoutData);
                    }
                    if(diagnosticType == 5){
                        makeToast(R.string.index_Toast_FailWithoutBaseAndWifi);
                    }
                    if(diagnosticType == 6){
                        makeToast(R.string.index_Toast_FailWithoutSimAndWifi);
                    }
                    if(diagnosticType == 7){
                        makeToast(R.string.index_Toast_FailWithFlightMode);
                    }
                    if(diagnosticType == 9){
                        makeToast(R.string.index_Toast_FailWithoutAccess);
                    }
                    break;
                case BDLocation.TypeServerError:
                    if(diagnosticType == 8){
                        makeToast(R.string.index_Toast_FailWithoutAccess);
                    }
                    break;
            }
        }
    };
    private LocationClient mLocationClient = null;
    private LocationClientOption mLocationClientOption = new LocationClientOption();
    private BDLocation mBDLocation;
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);

        // 绑定组件
        mIndexTextViewLatitude = (TextView) findViewById(R.id.index_TextView_Latitude);
        mIndexTextViewLongitude = (TextView) findViewById(R.id.index_TextView_Longitude);

        // 配置百度地图SDK,发起定位请求
        mLocationClient = new LocationClient(getApplicationContext());
        mLocationClient.registerLocationListener(mBDAbstractLocationListener);
        mLocationClientOption.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        mLocationClientOption.setCoorType("bd0911");
        mLocationClientOption.setScanSpan(1000);
        mLocationClient.start();
        mLocationClient.requestLocation();
        mBDLocation = mLocationClient.getLastKnownLocation();
    }
    public void updateLocationTextView(double latitude,double longitude){
        mIndexTextViewLatitude.setText(String.valueOf(latitude));
        mIndexTextViewLongitude.setText(String.valueOf(longitude));
    }
    public void makeToast(int stringResId){
        Toast.makeText(this,stringResId,Toast.LENGTH_SHORT);
    }
}