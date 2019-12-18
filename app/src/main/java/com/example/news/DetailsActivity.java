package com.example.news;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;


public class DetailsActivity extends AppCompatActivity {

    ImageView new_img ;
    TextView appbar_title ,date , time, title;
    FrameLayout data_behavior ;
    LinearLayout titleAppbar ;
    AppBarLayout appBarLayout;
    boolean isHide;
    String mUrl , mImg ,mDate ,mSource , mAuthor , mTitle ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //add back button in toolbar and action to back to the same fragment
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
            }
        });

        data_behavior=findViewById(R.id.date_behavior);
        titleAppbar = findViewById(R.id.title_appbar);
        new_img = findViewById(R.id.backdrop);
        appbar_title=findViewById(R.id.title_on_appbar);
        date = findViewById(R.id.date);
        time=findViewById(R.id.time);
        title=findViewById(R.id.title);

        initCollapsingToolbar();
        getIntentFromMain();


    }



    private void initWebView(String url){

        WebView webView = findViewById(R.id.webView);
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDisplayZoomControls(false);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);

    }


    private void initCollapsingToolbar() {
        final CollapsingToolbarLayout collapsingToolbarLayout =
                findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle(" ");
        AppBarLayout appBarLayout = findViewById(R.id.appbar);
        appBarLayout.setExpanded(true);

        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbarLayout.setTitle("");
                    data_behavior.setVisibility(View.GONE);
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbarLayout.setTitle(" ");
                    data_behavior.setVisibility(View.VISIBLE);
                    isShow = false;
                }
            }
        });
    }



    public void getIntentFromMain() {

            Intent intent = getIntent();
            mUrl = intent.getStringExtra("url");
            mImg = intent.getStringExtra("img");
            mTitle = intent.getStringExtra("title");
            mDate = intent.getStringExtra("date");
            mSource = intent.getStringExtra("source");

            Glide.with(this)
                    .load(mImg)
                    .placeholder(R.drawable.ic_placeholder)
                    .into(new_img);


            appbar_title.setText(" " +mTitle);
            date.setText(Utils.DateFormat(mDate));
            title.setText(mTitle);
            time.setText(Utils.DateToTimeFormat(mDate));

            initWebView(mUrl);

    }

}
