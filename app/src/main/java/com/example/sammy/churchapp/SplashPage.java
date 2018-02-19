package com.example.sammy.churchapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.format.DateUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class SplashPage extends Activity {

    private final int SPLASH_DISPLAY_LENGTH = 3000;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        TextView textView =(TextView) findViewById(R.id.time_date);
        textView.setText(DateUtils.formatDateTime(context,System.currentTimeMillis(),
                DateUtils.FORMAT_SHOW_TIME|DateUtils.FORMAT_SHOW_DATE|DateUtils.FORMAT_NUMERIC_DATE|DateUtils.FORMAT_SHOW_YEAR|DateUtils.FORMAT_12HOUR));


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mainIntent = new Intent(SplashPage.this, WelcomePage.class);
                SplashPage.this.startActivity(mainIntent);
                SplashPage.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_splash_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
