package com.elab.elabsignup;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;

public class LocationListener extends BDAbstractLocationListener {
    public double latitude; // 纬度
    public double longitude; // 经度
    public float radius; // 定位精确度半径
    public int errorCode; // 错误代码
    @Override public void onReceiveLocation(BDLocation location){
        this.latitude = location.getLatitude();
        this.longitude = location.getLongitude();
        this.radius = location.getRadius();
        this.errorCode = location.getLocType();
    }
}
