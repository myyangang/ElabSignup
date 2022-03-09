package com.elab.elabsignup.network;

import android.widget.Toast;

import com.elab.elabsignup.R;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class SignupSubmit {

    public String getUrlOutput(URL url, byte[] postData) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setInstanceFollowRedirects(false);
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        connection.setRequestProperty("charset", "utf-8");
        connection.setRequestProperty("Content-Length", String.valueOf(postData.length));
        connection.setUseCaches(false);
        DataOutputStream dataOutputStream = new DataOutputStream(connection.getOutputStream());
        dataOutputStream.write(postData);

        InputStream inputStream = connection.getInputStream();
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String stringBuffer;
        while ((stringBuffer = bufferedReader.readLine()) != null) {
            stringBuilder.append(stringBuffer);
        }
        String output = stringBuilder.toString();
        // System.out.println(output);

        dataOutputStream.flush();
        dataOutputStream.close();
        return output;
    }
    // TODO:
    public static void main(String[] args) throws MalformedURLException {
        String urlParameters = "username=123123124&password=123123124";
        byte[] postData = urlParameters.getBytes(StandardCharsets.UTF_8);
        URL url = new URL("http://120.25.202.230:3007/api/reguser");
        getUrlOutput(url, postData);

    }
}
