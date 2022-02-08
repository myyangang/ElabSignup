package com.elab.elabsignup.network;

import android.net.Uri;

public class SignupSubmit {
//    private static final String API_SUBMIT_URL = "";
    private static final String API_SUBMIT_URL_TEST = "http://47.108.215.113/";
    private static final String TAG = "SignupSubmit";



    // 最简单的明文传输方法
    public void send(){
        Uri url = Uri.parse(API_SUBMIT_URL_TEST)
                .buildUpon()
                .appendQueryParameter("API")
                .build();
    }

    // 自己设计的加密方法
//    public void sendSignUp(){
//        String
//    }
}
