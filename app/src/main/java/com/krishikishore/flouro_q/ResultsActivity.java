package com.krishikishore.flouro_q;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.location.LocationManager;
import android.Manifest;
import androidx.core.app.*;
import android.content.pm.PackageManager;
import android.content.Context;
import android.location.Location;


public class ResultsActivity extends AppCompatActivity {


    static final int REQUEST_LOCATION = 1;

     String xMean;
     String xVar;
     String xCount;

    double latitude;
    double longitude;
    LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        Intent intentExtras = getIntent();
        xMean = intentExtras.getStringExtra("xMean");
        xVar = intentExtras.getStringExtra("xVar");
        xCount = intentExtras.getStringExtra("xCount");

        TextView meanValue = (TextView) findViewById(R.id.meanvalue);
        meanValue.setText("Mean: "+ xMean+" lux");

        TextView varValue = (TextView) findViewById(R.id.variancevalue);
        varValue.setText("Variance: "+ xVar+ " lux");

        TextView countValue = (TextView) findViewById(R.id.countvalue);
        countValue.setText("Count: "+ xCount+ " values");


    }

    public void moveToSave(View view) {
        getLocationInfo();
        Intent intent = new Intent(ResultsActivity.this, SaveActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("xMean", String.valueOf(xMean));
        bundle.putString("xVar", String.valueOf(xVar));
        bundle.putString("xCount", String.valueOf(xCount));
        bundle.putString("Latitude", Double.toString(latitude));
        bundle.putString("Longitude", Double.toString(longitude));
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void getLocationInfo() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);

        } else {
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

            if (location != null) {
                latitude = location.getLatitude();
                longitude = location.getLongitude();

            }

        }

    }


}
