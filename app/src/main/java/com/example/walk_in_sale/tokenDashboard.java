package com.example.walk_in_sale;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class tokenDashboard extends AppCompatActivity {
    private RecyclerView courseRV;
    Button backBTN, barcodeBTN, scanBTN, nameBTN, nextBTN;

    // Arraylist for storing data
    private ArrayList<productsModel> productsModelArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_token_dashboard);
        courseRV = findViewById(R.id.idRVCourse);
        backBTN = findViewById(R.id.backButtonTokenNumber);
        barcodeBTN = findViewById(R.id.barcodeBTN);
        scanBTN = findViewById(R.id.scanBTN);
        nameBTN = findViewById(R.id.nameBTN);
        nextBTN = findViewById(R.id.nextToDaBTN);

        // here we have created new array list and added data to it.
        productsModelArrayList = new ArrayList<>();
        String productName = "Mens formal shirts";
        String qty = "4";
        for(int i=1;i<=200;i++){
            productsModelArrayList.add(new productsModel(productName,qty));
        }


        // we are initializing our adapter class and passing our arraylist to it.
        productAdapter productAdapter = new productAdapter(this, productsModelArrayList);

        // below line is for setting a layout manager for our recycler view.
        // here we are creating vertical list so we will provide orientation as vertical
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        // in below two lines we are setting layoutmanager and adapter to our recycler view.
        courseRV.setLayoutManager(linearLayoutManager);
        courseRV.setAdapter(productAdapter);

        //backBTN code
        backBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                onBackPressed();
            }
        });
        //barcodeBTN code
        barcodeBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Clicked on barcode", Toast.LENGTH_SHORT).show();
            }
        });
        //scanBTN code
        scanBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Clicked on Scan", Toast.LENGTH_SHORT).show();
            }
        });
        //nameBTN code
        nameBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Clicked on Name", Toast.LENGTH_SHORT).show();
            }
        });
        //nextBTN code
        nextBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent createTokenIntent = new Intent(getApplicationContext(), crateToken.class);
                //startActivity(createTokenIntent);
                Toast.makeText(getApplicationContext(), "Clicked on next", Toast.LENGTH_SHORT).show();
            }
        });
    }
}