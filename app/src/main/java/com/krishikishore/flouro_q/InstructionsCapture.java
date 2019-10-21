package com.krishikishore.flouro_q;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class InstructionsCapture extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions_capture);
    }

    public void moveToLightSensor(View view) {
        Intent intent = new Intent(InstructionsCapture.this, MeasureActivity.class);
        startActivity(intent);
    }

}
