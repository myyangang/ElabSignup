package com.elab.elabsignup;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

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

            }
        });


    }

}
