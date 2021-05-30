package com.example.homepage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

public class Chat extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        WebView mywebview = (WebView) findViewById(R.id.webView);
        mywebview.loadUrl("https://tanjiros-chat-app.herokuapp.com/");
    }
}