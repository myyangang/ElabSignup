package com.elab.elabsignup;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.elab.elabsignup.network.ApiFetcher;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";
    private static final String API_LOGIN_TEST = "http://47.108.215.113/ElabSignup/signup";

    private boolean isLoginSuccess = false;

    //导入组件
    private EditText mLoginEditTextAccount;
    private EditText mLoginEditTextPassword;
    private Button mLoginButtonLogin;

    @Override public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // 绑定组件
        mLoginEditTextAccount = findViewById(R.id.login_EditText_Account);
        mLoginEditTextPassword = findViewById(R.id.login_EditText_Password);
        mLoginButtonLogin = findViewById(R.id.login_Button_Login);

        mLoginButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                String uriSpec = Uri.parse(API_LOGIN_TEST)
                        .buildUpon()
                        .appendQueryParameter("account",mLoginEditTextAccount.getText().toString())
                        .appendQueryParameter("password",mLoginEditTextPassword.getText().toString())
                        .build()
                        .toString();
                new LoginFetcherTask(uriSpec).execute();
            }
        });
    }

/*
* Login预期得到的JSON形如:
* {
*       "status":0,
*       "isSuccessfulLogin":1
* }
* */

//    public void login(String account,String password){
//        try{
//            Uri uri = Uri.parse(API_SUBMIT_URL_TEST)
//                    .buildUpon()
//                    .appendQueryParameter("account",account)
//                    .appendQueryParameter("password",password)
//                    .build();
//            URL url = new URL(uri.toString());
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//            InputStream inputStream = connection.getInputStream();
//            if(connection.getResponseCode() != HttpURLConnection.HTTP_OK){
//                throw new IOException(connection.getResponseMessage() + ": with" + url.toString());
//            }
//            int bytesRead = 0;
//            byte[] buffer = new byte[1024];
//            while ((bytesRead = inputStream.read(buffer)) > 0){
//                outputStream.write(buffer,0,bytesRead);
//            }
//            outputStream.close();
//        } catch (MalformedURLException malformedURLException) {
//            Log.e(TAG,malformedURLException.toString());
//        } catch (IOException ioException){
//            Log.e(TAG,ioException.toString());
//        } finally {
//            connection.disconnect();
//        }
//    }


    public static class LoginFetcherTask extends AsyncTask<Void,Void,Integer>{

        private String url;

        public LoginFetcherTask(String url){
            this.url = url;
        }

        @Override protected Integer doInBackground(Void... params){
            try {
                String result = new ApiFetcher().getUrlString(url);
                Log.d(TAG,"Fetched contents from URL:" + result);
                return new Integer(loginJsonParser(result));
            }catch (IOException ioException){
                Log.e(TAG,"Failed to fetch URL" + ioException);
            }
            return null;
        }

        private int loginJsonParser(String result){
            try {
                JSONObject jsonFile = new JSONObject(result);
                Log.d(TAG,String.valueOf(jsonFile.getInt("status")));
                return jsonFile.getInt("isSuccessfulLogin");
            }catch (JSONException jsonException){
                Log.e(TAG,"Failed to parse JSON: " + result);
            }
            return -1;
        }

    }
}
