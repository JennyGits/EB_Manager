package com.example.my_eb_manager;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;

import java.util.List;

public class SessionControl {
    static public DefaultHttpClient httpClient = null;
    static public List cookies;

    public static HttpClient getHttpclient() {
        if (httpClient == null) {
            SessionControl.setHttpClient(new DefaultHttpClient());
        }
        return httpClient;
    }

    public static void setHttpClient(DefaultHttpClient httpClient) {
        SessionControl.httpClient = httpClient;
    }
}
