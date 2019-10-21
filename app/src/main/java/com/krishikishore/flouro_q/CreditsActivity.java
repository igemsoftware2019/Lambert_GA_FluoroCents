package com.krishikishore.flouro_q;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.amazonaws.mobile.config.AWSConfiguration;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.util.Log;

import com.google.gson.Gson;
import com.amazonaws.auth.*;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;



import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.*;
import com.amazonaws.mobileconnectors.dynamodbv2.document.datatype.Primitive;
import com.amazonaws.mobileconnectors.dynamodbv2.document.datatype.Document;



import java.util.List;

import android.os.AsyncTask;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;

/*
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
*/

import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;
import com.amazonaws.services.dynamodbv2.model.*;
import java.util.Map;
import java.util.Iterator;

import com.amazonaws.mobile.client.*;
import java.util.concurrent.TimeUnit;



public class CreditsActivity extends AppCompatActivity {


    public DynamoDBMapper dynamoDBMapper;
    AmazonDynamoDBClient dynamoDBClient;
    String[] Lat;
    String[] Long;


    static final String LOG_TAG = "CreditsActivity";

    /*
    // Create a new credentials provider
    CognitoCachingCredentialsProvider credentialsProvider = new CognitoCachingCredentialsProvider(getApplicationContext(), "us-east-1:631893bb-f48b-4c67-ad17-73118e2aa25b", Regions.US_EAST_1);

    // Create a connection to DynamoDB
    AmazonDynamoDBClient dbClient = new AmazonDynamoDBClient(credentialsProvider);

// Create a table reference

    Table dbTable = Table.loadTable(dbClient, "fluorocents-mobilehub-1891069775-fluorocentsdata");
    */

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        // Retrieve the content view that renders the map.
        setContentView(R.layout.activity_credits);
        // Get the SupportMapFragment and request notification
        // when the map is ready to be used.

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
         dynamoDBClient = new AmazonDynamoDBClient(credentialsProvider);

        this.dynamoDBMapper = DynamoDBMapper.builder()
                .dynamoDBClient(dynamoDBClient)
                .awsConfiguration(configuration)
                .build();



    queryNews();

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        try {
            TimeUnit.SECONDS.sleep(5);
        }
        catch(Exception e)
        {
            System.out.println("Sleep Failed");
        }
        mapFragment.getMapAsync(new OnMapReadyCallback() { @Override public void onMapReady(GoogleMap googleMap) {
            // Add a marker in Sydney, Australia,
            // and move the map's camera to the same location.

            for (int iCnt = 0; iCnt < Lat.length; iCnt++) {
                LatLng location = new LatLng(Double.valueOf(Lat[iCnt]), Double.valueOf(Long[iCnt]));
                googleMap.addMarker(new MarkerOptions().position(location));
                googleMap.moveCamera(CameraUpdateFactory.newLatLng(location));
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location, 12.0f));
            }
        } });


        /*

        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().build();

        ScanRequest scanRequest = new ScanRequest()
                .withTableName("Reply");

        ScanResult result = client.scan(scanRequest);
      //  for (Map<String, AttributeValue> item : result.getItems()){
      //      printItem(item);
       // }

*/


    }

/*
    public void readNews() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("starting");
                FluorocentsdataDO newsItem = dynamoDBMapper.load(
                        FluorocentsdataDO.class,
                        2695.0,
                        "Sat Oct 19 11:08:24 EDT 2019");

                // Item read
                System.out.println("News Item:"+ newsItem.getLatitude()+ " - "+newsItem.getLongitude()+ " - "+newsItem.getUserId()+ " - "+newsItem.getTimestampvalue());
            }
        }).start();
    }

*/
    public void queryNews() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                FluorocentsdataDO news = new FluorocentsdataDO();
                news.setUserId(0.0);       //partition key
                news.setTimestampvalue("Fri Oct 11 00:08:52 EDT 2019");

                Condition rangeKeyCondition = new Condition()
                        .withComparisonOperator(ComparisonOperator.GE)
                        .withAttributeValueList(new AttributeValue().withS(" "));
                DynamoDBQueryExpression queryExpression = new DynamoDBQueryExpression()
                        .withHashKeyValues(news)
                        .withRangeKeyCondition("timestampvalue", rangeKeyCondition)
                        .withConsistentRead(false);

                PaginatedList<FluorocentsdataDO> result = dynamoDBMapper.query(FluorocentsdataDO.class, queryExpression);

                Gson gson = new Gson();
                StringBuilder stringBuilder = new StringBuilder();

                FluorocentsdataDO myRecord = null;
/*
                Iterator<PaginatedList<FluorocentsdataDO>> iterator = new Iterato<result>;

                while (iterator != null)
                {
                    myRecord = iterator.next();
                    System.out.println("Lat:" + myRecord.getLatitude());
                    System.out.println("Long:" + myRecord.getLongitude());
                }
                */
                Lat = new String[result.size()];
                Long = new String[result.size()];
                // Loop through query results
                for (int i = 0; i < result.size(); i++) {
                    String jsonFormOfItem = gson.toJson(result.get(i));
                    stringBuilder.append(jsonFormOfItem + "\n\n");
                    Lat[i] = result.get(i).getLatitude();
                    Long[i] = result.get(i).getLongitude();
                }

                // Add your code here to deal with the data result
                System.out.println("------------------Query result: "+ stringBuilder.toString());

                if (result.isEmpty()) {
                    // There were no items matching your query.
                }
            }
        }).start();
    }


    /*
    public void readData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                final Expression expression = new Expression();
                expression.setExpressionStatement("userId > :userId");
                expression.withExpressionAttibuteValues(":userId", new Primitive(0.0));
                Table myTable = Table.loadTable(dynamoDBClient,"fluorocentsdata");
                Search searchResult = myTable.scan(expression);
                List<Document> documents = searchResult.getAllResults();
                Iterator<Document> iterator = documents.iterator();
                Document myRecord = null;
                while (iterator.hasNext())
                {
                    myRecord = iterator.next();
                    System.out.println("UserId:" + myRecord.toString());
                }
            }
        });
    }

    */


/*
    public void readData() {
        new Thread(new Runnable() {
            @Override
            public void run() {

                //var docClient = new AWS.DynamoDB.DocumentClient();


                com.krishikishore.flouro_q.FluorocentsdataDO booksItem = dynamoDBMapper.load(com.krishikishore.flouro_q.FluorocentsdataDO.class, 2695.0,  "Sat Oct 19 11:08:24 EDT 2019");
                // Partition key (hash key)

                // Sort key (range key)

                // Item read
                Log.d(LOG_TAG, String.format("Books Item: %s", booksItem.toString()));
            }
        }).start();
    }
*/


    /*
    public List<Document> getAllData() {

        return dbTable.query(new Primitive(credentialsProvider.getCachedIdentityId())).getAllResults();
    }


    private class GetAllItemsAsyncTask extends AsyncTask<Void, Void, List<Document>> {
        @Override
        protected List<Document> doInBackground(Void... params) {
            DatabaseAccess databaseAccess = DatabaseAccess.getInstance(CreditsActivity.this);
            return databaseAccess.getAllData();
        }

        @Override
        protected void onPostExecute(List<Document> documents) {
            if (documents != null) {
                populateMemoList(documents);
            }
        }
    }
    */



}
