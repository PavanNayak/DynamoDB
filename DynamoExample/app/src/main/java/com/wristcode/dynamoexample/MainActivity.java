package com.wristcode.dynamoexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.ClasspathPropertiesFileCredentialsProvider;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBScanExpression;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ComparisonOperator;
import com.amazonaws.services.dynamodbv2.model.Condition;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;
import com.wristcode.dynamoexample.adapter.CustomAdapter;
import com.wristcode.dynamoexample.model.Datas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//import com.amazonaws.services.dynamodbv2.model.AmazonDynamoDBClientBuilder;
public class MainActivity extends AppCompatActivity {
    static AmazonDynamoDBClient client;
    private DynamoDBMapper mapper;
    private static int PRODUCT_ID;
    List<Datas> data;
    RecyclerView recyclerView;
    CustomAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=(RecyclerView)findViewById(R.id.recycler);
        data=new ArrayList<>();


        //AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().build();
//
//        ScanRequest scanRequest = new ScanRequest()
//                .withTableName("riyaz_dev_submissions_for_review ");
//
//       // ScanResult result = client.scan(scanRequest);
//        for (Map<String, AttributeValue> item : result.getItems()){
//           // printItem(item);
//            Log.d("dynamoresult",item.toString());
//        }

        init();

        getAllRows();




    }


    private void init() {



        client = new AmazonDynamoDBClient();
        Region usWest2 = Region.getRegion(Regions.AP_SOUTH_1);
        client.setRegion(usWest2);
        mapper = new DynamoDBMapper(client);
    }





    private void getAllRows() {
        ScanRequest scanRequest = new ScanRequest()
                .withTableName("riyaz_dev_submissions_for_review");
        try {
            System.out.println("Scan Request: " + scanRequest);
            ScanResult scanResponse = client.scan(scanRequest);
            for (Map item : scanResponse.getItems()) {
                System.out.println(item.get("userId") + " , " +
                        item.get("title") + " , " +
                        item.get("timeStamp")+ " , " +
                        item.get("isReviewed"));

               // data.add((Datas) item.get("userId"));

            }

            adapter=new CustomAdapter(getApplicationContext(),data);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
            adapter.notifyDataSetChanged();

            System.out.println("Scan Response: " + scanResponse);
            System.out.println("Count: " + scanResponse.getCount());
            System.out.println("Items: " + scanResponse.getItems());
        } catch (AmazonServiceException e) {
            e.printStackTrace();
        } catch (AmazonClientException e) {
            e.printStackTrace();
        }
    }

}
