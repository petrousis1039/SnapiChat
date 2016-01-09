package com.dreamteam.snapichat.utils;

import android.util.Log;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class LocationUpdater {

    private String uid;
    private JSONParser jsonParser;

    public LocationUpdater(String uid) {
        this.uid = uid;
        this.jsonParser = new JSONParser();
    }

    public void update(final String longitude, final String latitude) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<NameValuePair> params = new ArrayList<>();
                params.add(new BasicNameValuePair("longitude", longitude));
                params.add(new BasicNameValuePair("latitude", latitude));
                params.add(new BasicNameValuePair("uid", uid));

                String url = Utils.getHttpURL() + "api/location?";

                JSONObject response = jsonParser.makeHttpRequest(url, "POST", params);
                Log.d("Location Update", response.toString());
            }
        }).start();
    }

}
