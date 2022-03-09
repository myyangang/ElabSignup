package com.elab.elabsignup.network;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonParser {
    JSONObject jsonBody;

    public JsonParser(String jsonString) throws JSONException {
        jsonBody = new JSONObject(jsonString);
    }

    public int getIntProperty(String propertyName) throws JSONException{
        return jsonBody.getInt(propertyName);
    }

    public String getStringProperty(String propertyName) throws JSONException{
        return jsonBody.getString(propertyName);
    }
}
