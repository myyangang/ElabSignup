package com.elab.elabsignup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.elab.elabsignup.event.SignupEvent;
import com.elab.elabsignup.event.SignupEventList;

public class IndexActivity extends AppCompatActivity {
    // 导入组件
    private TextView mIndexTextViewLatitude;
    private TextView mIndexTextViewLongitude;
    private Button mIndexButtonSignup;

    // 引入实例字段
    private SignupEventList mSignupEventList;
    private LocationClient mLocationClient = null;
    private LocationClientOption mLocationClientOption = new LocationClientOption();
    private BDLocation mBDLocation;
    private final BDAbstractLocationListener mBDAbstractLocationListener = new BDAbstractLocationListener() {
        @Override public void onReceiveLocation(BDLocation bdLocation) {
            if(bdLocation != null && bdLocation.getLocType() != BDLocation.TypeServerError){
                updateLocationTextView(bdLocation.getLatitude(),bdLocation.getLongitude());
                mBDLocation = mLocationClient.getLastKnownLocation();
                mSignupEventList.addSignupEvent(new SignupEvent(mBDLocation));
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



    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);

        mIndexTextViewLatitude = findViewById(R.id.index_TextView_Latitude);
        mIndexTextViewLongitude = findViewById(R.id.index_TextView_Longitude);
        mIndexButtonSignup = findViewById(R.id.index_Button_Signup);
        mSignupEventList = SignupEventList.get(getApplicationContext());

        mIndexButtonSignup.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                startLocate();
            }
        });
    }

    @Override public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.activity_index,menu);
        return true;
    }

    @Override public boolean onOptionsItemSelected(MenuItem menuItem){
        switch (menuItem.getItemId()){
            case R.id.JumpToLogin:
//                Intent intent = new Intent(IndexActivity.this,LoginActivity.class);
//                startActivityForResult(
//                    new Intent()
//                );
                return true;
            case R.id.JumpToLogView:
                startActivity(new Intent(IndexActivity.this,LogViewActivity.class));
                return true;
            default:
                return true;
        }
    }

    /* 开始定位 */
    public void startLocate(){
        // 配置百度地图SDK,发起定位请求
        mLocationClient = new LocationClient(getApplicationContext());
        mLocationClient.registerLocationListener(mBDAbstractLocationListener);
        mLocationClientOption.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        mLocationClientOption.setCoorType("bd0911");
        mLocationClientOption.setScanSpan(1000);
        mLocationClient.start();
    }

    public void updateLocationTextView(double latitude,double longitude){
        mIndexTextViewLatitude.setText(String.valueOf(latitude));
        mIndexTextViewLongitude.setText(String.valueOf(longitude));
    }

    public void makeToast(int stringResId){
        Toast.makeText(this,stringResId,Toast.LENGTH_SHORT);
    }
}