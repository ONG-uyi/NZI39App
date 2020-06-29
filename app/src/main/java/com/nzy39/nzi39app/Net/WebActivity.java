package com.nzy39.nzi39app.Net;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.nzy39.nzi39app.R;

public class WebActivity extends AppCompatActivity {
    public  static final String WEB_URL = "webUrl";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        String webUrl = getIntent().getStringExtra(WEB_URL);


        WebView webView = findViewById(R.id.web_content);
        WebSettings settings = webView.getSettings();

        settings.setJavaScriptEnabled(true);
        webView.loadUrl(webUrl);
    }

}
