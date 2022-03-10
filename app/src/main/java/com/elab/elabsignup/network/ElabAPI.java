package com.elab.elabsignup.network;

import org.json.JSONException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

@SuppressWarnings("SpellCheckingInspection")
public class ElabAPI {
    private static final String urlRoot = "http://120.25.202.230:3007";

    public static String[] reguserAPI(String username, String password) throws IOException, JSONException {
        String urlParameters = "username=" + username + "&password=" + password;
        byte[] postData = urlParameters.getBytes(StandardCharsets.UTF_8);
        URL url = new URL(urlRoot + "/api/reguser");
        String outputString = ApiFetcherForTest.getUrlOutput(url, postData);
        JsonParser outputJson = new JsonParser(outputString);
        return new String[]{
                String.valueOf(outputJson.getIntProperty("status")),
                outputJson.getStringProperty("message")
        };
    }

    public static String[] loginAPI(String username, String password) throws IOException, JSONException {
        String urlParameters = "username=" + username + "&password=" + password;
        byte[] postData = urlParameters.getBytes(StandardCharsets.UTF_8);
        URL url = new URL(urlRoot + "/api/login");
        String outputString = ApiFetcherForTest.getUrlOutput(url, postData);
        JsonParser outputJson = new JsonParser(outputString);
        return new String[]{
                String.valueOf(outputJson.getIntProperty("status")),
                outputJson.getStringProperty("message"),
                outputJson.getStringProperty("token")
        };
    }

    public static String[] signupAPI(String token, String time, double longitude, double latitude) throws IOException, JSONException {
        String urlParameters = "signin_time=" + time + "&longitude=" + longitude + "&latitude=" + latitude;
        byte[] postData = urlParameters.getBytes(StandardCharsets.UTF_8);
        URL url = new URL(urlRoot + "/my/signin");
        String outputString = ApiFetcherForTest.getUrlOutput(url, postData, token);
        JsonParser outputJson = new JsonParser(outputString);
        return new String[]{
                String.valueOf(outputJson.getIntProperty("status")),
                outputJson.getStringProperty("message"),
                String.valueOf(outputJson.getIntProperty("signid"))
        };
    }

    public static String[] signoutAPI(String signinID, String time, String token) throws IOException, JSONException {
        String urlParameters = "id=" + signinID + "&signout_time=" + time;
        byte[] postData = urlParameters.getBytes(StandardCharsets.UTF_8);
        URL url = new URL(urlRoot + "/my/signin");
        String outputString = ApiFetcherForTest.getUrlOutput(url, postData, token);
        JsonParser outputJson = new JsonParser(outputString);
        return new String[]{
                String.valueOf(outputJson.getIntProperty("status")),
                outputJson.getStringProperty("message")
        };
    }
}
