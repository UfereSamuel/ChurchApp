package com.example.sammy.churchapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

public class WelcomePage extends AppCompatActivity {

    RecyclerView recyclerView;
    WelcomeAdapter adapter;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);

        recyclerView = (RecyclerView) findViewById(R.id.welcomeRecycler);
        layoutManager = new GridLayoutManager(getApplication(),3);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new WelcomeAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);


    }

}
