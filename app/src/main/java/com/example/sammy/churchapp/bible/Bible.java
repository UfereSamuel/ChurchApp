package com.example.sammy.churchapp.bible;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.sammy.churchapp.R;
import com.example.sammy.churchapp.WelcomePage;

public class Bible extends AppCompatActivity {
    private final int SPLASH_DISPLAY_LENGTH = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bible);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mainIntent = new Intent(Bible.this, WelcomePage.class);
                Bible.this.startActivity(mainIntent);
                Bible.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }

}
