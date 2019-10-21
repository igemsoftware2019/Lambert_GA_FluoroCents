package com.krishikishore.flouro_q;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import java.util.Calendar;
import android.os.AsyncTask;
import java.sql.*;
//import com.amazonaws.mobile.client.*;
import com.amazonaws.mobileconnectors.*;
import android.util.Log;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.mobile.client.*;
import com.amazonaws.mobile.client.AWSMobileClient;
import com.amazonaws.mobile.config.AWSConfiguration;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.*;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;

import java.util.concurrent.ThreadLocalRandom;

import java.util.Random;

public class FinalActivity extends AppCompatActivity {

    private String latitude;
    private String longitude;
    String xMean;
    String xVar;
    String xCount;

    private String sourcevalue;
    private String individualvalue;
    private String nearestbodyofwatervalue;
    private String diseasestatusvalue;
    private String notesvalue;
    private String timestamp;

    DynamoDBMapper dynamoDBMapper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);

        AWSMobileClient.getInstance().initialize(this, new AWSStartupHandler() {
            @Override
            public void onComplete(AWSStartupResult awsStartupResult) {
                Log.d("FinalActivity", "AWSMobileClient is instantiated and you are connected to AWS!");
            }
        }).execute();

        // AWSMobileClient enables AWS user credentials to access your table
        AWSMobileClient.getInstance().initialize(this).execute();

        AWSCredentialsProvider credentialsProvider = AWSMobileClient.getInstance().getCredentialsProvider();
        AWSConfiguration configuration = AWSMobileClient.getInstance().getConfiguration();


        // Add code to instantiate a AmazonDynamoDBClient
        AmazonDynamoDBClient dynamoDBClient = new AmazonDynamoDBClient(credentialsProvider);

        this.dynamoDBMapper = DynamoDBMapper.builder()
                .dynamoDBClient(dynamoDBClient)
                .awsConfiguration(configuration)
                .build();

        Intent intentExtras = getIntent();

        latitude = intentExtras.getStringExtra("Latitude");
        longitude = intentExtras.getStringExtra("Longitude");
        xMean = intentExtras.getStringExtra("xMean");
        xVar = intentExtras.getStringExtra("xVar");
        xCount = intentExtras.getStringExtra("xCount");

        sourcevalue = intentExtras.getStringExtra("Source");
        individualvalue = intentExtras.getStringExtra("Individual");
        nearestbodyofwatervalue = intentExtras.getStringExtra("NearestWater");
        diseasestatusvalue = intentExtras.getStringExtra("DiseaseStatus");
        notesvalue = intentExtras.getStringExtra("Notes");
        timestamp = Calendar.getInstance().getTime().toString();

        createNews();

    }

    public void createNews() {
        final FluorocentsdataDO newsItem = new FluorocentsdataDO();

        newsItem.setUserId( 0.0 );

        newsItem.setXVar(xVar);
        newsItem.setXMean(xMean);
        newsItem.setXCount(xCount);
        newsItem.setDiseasestatusvalue(diseasestatusvalue);
        newsItem.setLatitude(latitude);
        newsItem.setLongitude(longitude);
        newsItem.setIndividualvalue(individualvalue);
        newsItem.setNearestbodyofwatervalue(nearestbodyofwatervalue);
        newsItem.setNotesvalue(notesvalue);
        newsItem.setSourcevalue(sourcevalue);
        newsItem.setTimestampvalue(timestamp);

;        new Thread(new Runnable() {
            @Override
            public void run() {
                dynamoDBMapper.save(newsItem);
                // Item saved
            }
        }).start();
    }

    public void returnHome(View view) {
        Intent intent = new Intent(FinalActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void moveToMap(View view) {
        Intent intent = new Intent(FinalActivity.this, CreditsActivity.class);
        startActivity(intent);
    }



}