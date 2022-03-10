package com.elab.elabsignup.network;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ApiFetcherForTest {
    public static String getUrlOutput(URL url, byte[] postData) throws IOException {
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
    public static String getUrlOutput(URL url, byte[] postData,String header) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setInstanceFollowRedirects(false);
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        connection.setRequestProperty("charset", "utf-8");
        connection.setRequestProperty("Content-Length", String.valueOf(postData.length));
        connection.setRequestProperty("Authorization",header);
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
}
