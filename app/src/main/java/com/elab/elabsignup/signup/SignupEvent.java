package com.elab.elabsignup.signup;

import com.baidu.location.BDLocation;

import java.util.Date;
import java.util.UUID;

public class SignupEvent {

    // TODO:这里的科中经纬度是通过谷歌地图得到的,需要实地再进行校正
    private static final double elabLatitude = 38.8861130410332;
    private static final double elabLongitude = 121.5305215325497;

    public static final int SUBMIT_PENDING = 0;
    public static final int SUBMIT_SUCCESS = 1;
    public static final int SUBMIT_UNKNOWN = 2;

    // 将一次签到事件抽象为实例
    public UUID uuid = UUID.randomUUID();
    public double latitude;
    public double longitude;
    public Date date = new Date();
    public int isValidLocation = 0; // 你可能会问为什么不用boolean,这是因为Android API的数据库类CursorWrapper不支持getBoolean(),能存但不能读,其他类型全支持😅,详见https://www.apiref.com/android/android/database/CursorWrapper.html
    public int isSuccessSubmit = SUBMIT_PENDING; // 0为未上传,1为已上传,2为未知(未收到服务器返回信息)

    public SignupEvent(BDLocation bdLocation){
        this.latitude = bdLocation.getLatitude();
        this.longitude = bdLocation.getLongitude();
        this.isValidLocation =
                Math.pow(latitude - elabLatitude, 2) + Math.pow(longitude - elabLongitude, 2) < Math.pow(0.0017131271215117582, 2) ? 1 : 0;
    }


    // 纯用于测试
    public SignupEvent() {

    }
}
