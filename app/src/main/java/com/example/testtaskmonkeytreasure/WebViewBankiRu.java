package com.example.testtaskmonkeytreasure;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class WebViewBankiRu extends AppCompatActivity {

    private WebView web;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_banki_ru);

        Intent intent = getIntent();

        web = (WebView) findViewById(R.id.webViewXML);
        WebSettings webSettings = web.getSettings();
        webSettings.setJavaScriptEnabled(true);

        web.setWebViewClient(new WebViewClient());
        web.loadUrl("http://www.banki.ru/products/currency/cash/moskva/");
    }
}
