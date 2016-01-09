package com.dreamteam.snapichat.utils;

public class Utils {

    private static final String BASE_URL = "snapichat-dreamteamteicm.rhcloud.com";
    private static final String APPLICATION_PATH = "/SnapiChat/";
    private static final String WEBSOCKET_PORT = ":8000";
    private static final String HTTP_PORT = "";

    public static String getHttpURL() {
        return "http://"
                .concat(BASE_URL)
                .concat(HTTP_PORT)
                .concat(APPLICATION_PATH);
    }

    public static String getWebSocketURL() {
        return "ws://"
                .concat(BASE_URL)
                .concat(WEBSOCKET_PORT)
                .concat(APPLICATION_PATH);
    }
}
