package com.kittu.news;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

public class DetailNews extends AppCompatActivity {
ProgressBar progressBar;
    Toolbar toolbar;
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_news);
        Toast.makeText(this,getIntent().getStringExtra("url"),Toast.LENGTH_SHORT).show();
        progressBar=(ProgressBar)findViewById(R.id.progressBar);
        toolbar=(Toolbar)findViewById(R.id.toolBar);
        webView=(WebView)findViewById(R.id.detailView);
        setSupportActionBar(toolbar);
        webView.setVisibility(View.INVISIBLE);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient() {
             @Override
             public void onPageStarted(WebView view, String url, Bitmap favicon) {
                 super.onPageStarted(view, url, favicon);
                 Toast.makeText(DetailNews.this, "Page loading", Toast.LENGTH_SHORT).show();
             }

             @Override
             public void onPageFinished(WebView view, String url) {
                 super.onPageFinished(view, url);
                 progressBar.setVisibility(View.GONE);
                 webView.setVisibility(View.VISIBLE);
                 Toast.makeText(DetailNews.this, "Page loaded", Toast.LENGTH_SHORT).show();
             }
                                 }
        );
       webView.loadUrl(getIntent().getStringExtra("url"));
    }
}
