package com.example.vitu.projetotese.activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.vitu.projetotese.R;
import com.example.vitu.projetotese.endpoints.PropostasEndpoint;

public class WebViewActivity extends AppCompatActivity {

    private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        //String URL = PropostasEndpoint.URL_BASE;

        Bundle extra = getIntent().getExtras();
        if(extra != null){
            String id = extra.getString("idProposta");
            //URL = URL + "Proposta/" + id;
        }else{
            //URL = "https://google.com";
        }

        webView = (WebView) findViewById(R.id.Webview_id);
        //webView.getSettings().setJavaScriptEnabled(true);
        webView.setFocusable(true);
        webView.setFocusableInTouchMode(true);

        webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setDatabaseEnabled(true);
        webView.getSettings().setAppCacheEnabled(true);
        webView.setScrollBarStyle(WebView.SCROLLBARS_INSIDE_OVERLAY);

        //webView.loadUrl(URL);
        webView.setWebViewClient(new WebViewClient());
    }


}
