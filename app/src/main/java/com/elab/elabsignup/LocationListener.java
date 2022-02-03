package com.elab.elabsignup;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;

public class LocationListener extends BDAbstractLocationListener {
    Double latitude; // 纬度
    Double longitude; // 经度
    Float radius; // 定位精确度半径
    int errorCode; // 错误代码
    @Override public void onReceiveLocation(BDLocation location){
        this.latitude = location.getLatitude();
        this.longitude = location.getLongitude();
        this.radius = location.getRadius();
        this.errorCode = location.getLocType();
    }
}
