package com.example.projectmcs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class AboutUs extends AppCompatActivity {
    private Double latitudes=6.171296;
    private Double longitudes=106.753855;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        Intent intentLoc = new Intent(AboutUs.this,MapsActivity.class);
        intentLoc.putExtra("latitude", latitudes);
        intentLoc.putExtra("longitude",longitudes);
        startActivity(intentLoc);
    }
}