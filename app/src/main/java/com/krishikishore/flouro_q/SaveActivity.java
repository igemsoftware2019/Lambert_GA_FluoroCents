package com.krishikishore.flouro_q;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.view.View;
import androidx.appcompat.app.*;
import android.content.DialogInterface;

public class SaveActivity extends AppCompatActivity {

    private String latitude;
    private String longitude;
    String xMean;
    String xVar;
    String xCount;

    private EditText source;
    private String sourcevalue;
    private EditText individual;
    private String individualvalue;
    private EditText nearestbodyofwater;
    private String nearestbodyofwatervalue;
    private EditText diseasestatus;
    private String diseasestatusvalue;
    private EditText notes;
    private String notesvalue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save);

        Intent intentExtras = getIntent();
        xMean = intentExtras.getStringExtra("xMean");
        xVar = intentExtras.getStringExtra("xVar");
        xCount = intentExtras.getStringExtra("xCount");
        latitude = intentExtras.getStringExtra("Latitude");
        longitude = intentExtras.getStringExtra("Longitude");
    }

    public void sendServer(View v) {

        Intent intent = new Intent(SaveActivity.this, FinalActivity.class);
        source = findViewById(R.id.plain_text_input);
        sourcevalue = source.getText().toString();

        individual = (EditText)findViewById(R.id.plain_text_input2);
        individualvalue = individual.getText().toString();

        nearestbodyofwater = (EditText)findViewById(R.id.plain_text_input3);
        nearestbodyofwatervalue = nearestbodyofwater.getText().toString();

        diseasestatus = (EditText)findViewById(R.id.plain_text_input4);
        diseasestatusvalue = diseasestatus.getText().toString();

        notes = (EditText)findViewById(R.id.plain_text_input5);
        notesvalue = notes.getText().toString();

        if (sourcevalue.isEmpty()) {
            AlertDialog alertDialog = new AlertDialog.Builder(SaveActivity.this).create();
            alertDialog.setTitle("Source is empty");
            alertDialog.setMessage("Please enter a value for the source field.");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
        } else if (individualvalue.isEmpty()) {
            AlertDialog alertDialog = new AlertDialog.Builder(SaveActivity.this).create();
            alertDialog.setTitle("Individual is empty");
            alertDialog.setMessage("Please enter a value for the individual field.");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
        } else if (nearestbodyofwatervalue.isEmpty()) {
            AlertDialog alertDialog = new AlertDialog.Builder(SaveActivity.this).create();
            alertDialog.setTitle("Body of water is empty");
            alertDialog.setMessage("Please enter a value for the nearest body of water field.");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
        } else if (diseasestatusvalue.isEmpty()) {
            AlertDialog alertDialog = new AlertDialog.Builder(SaveActivity.this).create();
            alertDialog.setTitle("Disease status is empty");
            alertDialog.setMessage("Please enter a value for the disease status field.");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
        } else {

            if (notesvalue.isEmpty()) {
                notesvalue = "none";
            }

            Bundle bundle = new Bundle();

            bundle.putString("xMean", xMean);
            bundle.putString("xVar", xVar);
            bundle.putString("xCount", xCount);
            bundle.putString("Latitude",latitude);
            bundle.putString("Longitude", longitude);
            bundle.putString("Source", sourcevalue);
            bundle.putString("Individual", individualvalue);
            bundle.putString("NearestWater", nearestbodyofwatervalue);
            bundle.putString("DiseaseStatus", diseasestatusvalue);
            bundle.putString("Notes", notesvalue);

            intent.putExtras(bundle);
            startActivity(intent);
        }

    }

}
