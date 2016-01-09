package com.dreamteam.snapichat.utils;

import android.util.Log;

import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketFactory;

public class WebSocketManager {

    private static WebSocketManager instance;

    private String uid;
    private String username;
    private String activeChatUID;

    private WebSocket ws;

    private WebSocketManager(String uid, String username) {
        this.uid = uid;
        this.username = username;

        initWebSocket();
    }

    public static void initWebSocketManager(String uid, String username) {
        instance = new WebSocketManager(uid, username);
    }

    private void initWebSocket() {
        try {
            String webSocketUri = Utils.getWebSocketURL()
                    .concat("chatApp/")
                    .concat(uid);

            ws = new WebSocketFactory().createSocket(webSocketUri);
        } catch (Exception ex) {
            Log.e("Init WebSocket", ex.toString());
        }
    }

    public static WebSocketManager getWebSocketManager() {
        return instance;
    }

    public void connect() {
        try {
            ws.connect();
        } catch (Exception e) {
            Log.e("WebSocket Connect", e.toString());
        }
    }

    public void disconnect() {
        try {
            ws.disconnect();
        } catch (Exception e) {
            Log.e("WebSocket Disconnect", e.toString());
        }
    }

    public WebSocket getWebSocket() {
        return ws;
    }

    public String getUID() {
        return uid;
    }

    public String getUsername() {
        return username;
    }

    public void setActiveChatUID(String uid) {
        this.activeChatUID = uid;
    }

    public String getActiveChatUID() {
        return activeChatUID;
    }
}
