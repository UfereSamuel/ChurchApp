package com.example.sammy.churchapp.Streaming;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.sammy.churchapp.R;

public class LiveRadio extends AppCompatActivity {

    private String postUrl = "https://google.com";
    private WebView webView;
    private ProgressBar progressBar;
   // private ImageView imgHeader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_radio);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        webView = findViewById(R.id.webView);
        progressBar = findViewById(R.id.progressBar);
       // imgHeader = findViewById(R.id.backdrop);

        //initializing toolbar
//        initCollapsingToolbar();
         webView.getSettings().setJavaScriptEnabled(true);
         webView.loadUrl(postUrl);
         webView.setHorizontalScrollBarEnabled(false);



    }



//    private void initCollapsingToolbar() {
//        final CollapsingToolbarLayout collapsingToolbarLayout =
//                findViewById(R.id.collapsing_toolbar);
//        collapsingToolbarLayout.setTitle(" ");
//        AppBarLayout appBarLayout = findViewById(R.id.appBar);
//        appBarLayout.setExpanded(true);

        // hiding & showing the txtPostTitle when toolbar expanded & collapsed
//        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
//            boolean isShow = false;
//            int scrollRange = -1;

//            @Override
//            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
//                if (scrollRange == -1) {
//                    scrollRange = appBarLayout.getTotalScrollRange();
//                }
//                if (scrollRange + verticalOffset == 0) {
//                    collapsingToolbarLayout.setTitle("Web View");
//                    isShow = true;
//                } else if (isShow) {
//                    collapsingToolbarLayout.setTitle(" ");
//                    isShow = false;
//                }
//            }
//        });
//
//    }
}
