package com.example.my_eb_manager;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.CookieManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.apache.http.cookie.Cookie;

import java.util.List;

public class WebViewActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        Intent intent = getIntent();

        String myUrlAddress = intent.getStringExtra("myurl");
        webView = findViewById(R.id.webView);
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);

        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);
        List<Cookie> cookies = SessionControl.cookies;
        cookieManager.removeAllCookie();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                String cookieString = cookie.getName() + "=" + cookie.getValue() + "; Domain=" + cookie.getDomain();
                cookieManager.setCookie(cookie.getDomain(), cookieString);
            }
        }

        webView.loadUrl(myUrlAddress);
    }

    public void onResume() {
        super.onResume();
    }

    public void onPause() {
        super.onPause();
    }
}
