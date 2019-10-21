package com.krishikishore.flouro_q;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.icu.util.Measure;
import android.os.Bundle;
import android.hardware.SensorManager;
import java.util.ArrayList;
import com.krishikishore.flouro_q.SensorUtils;
import com.krishikishore.flouro_q.RunningStat;

import java.util.Timer;
import java.util.TimerTask;

public class MeasureActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_measure);
        // Calculate statistics of accelerometer values over 300 ms (a blocking method)

        try{
            RunningStat[] stats = SensorUtils.sensorStats(MeasureActivity.this, Sensor.TYPE_LIGHT, 30000);
            double xMean = stats[0].mean();
            double xVar  = stats[0].variance();
            double xCount = stats[0].count();
            Intent intentBundle = new Intent(MeasureActivity.this, ResultsActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("xMean", String.valueOf(xMean));
            bundle.putString("xVar", String.valueOf(xVar));
            bundle.putString("xCount", String.valueOf(xCount));
            intentBundle.putExtras(bundle);
            startActivity(intentBundle);
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }

    }
