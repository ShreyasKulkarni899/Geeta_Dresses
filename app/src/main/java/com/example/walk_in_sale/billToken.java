package com.example.walk_in_sale;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.walk_in_sale.constants.Constant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class billToken extends AppCompatActivity {
    private RecyclerView courseRV;
    Constant constant;
    RequestQueue requestQueue;
    JsonObjectRequest jsonObjectRequest;
    JSONObject response;
    Button backBTN, printBTN;
    TextView userName, currentTokenNumber;
    SharedPreferences userSP;


    //this is for the bill list
    private ArrayList<productsListModel> listModelArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_token);
        Window window = billToken.this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(ContextCompat.getColor(billToken.this, R.color.black));
        //hocks
        userName = findViewById(R.id.userNameBillToken);
        currentTokenNumber = findViewById(R.id.userCurrentBillToken);
        //User data part with SP
        userSP = getSharedPreferences("userMetadata", MODE_PRIVATE);
        userName.setText(userSP.getString("userName", ""));
        currentTokenNumber.setText("Token No - "+userSP.getString("tokenNumber",""));

        Intent intent = getIntent();
        try {
            response =  response = new JSONObject(intent.getStringExtra("response"));
            Log.d("Response From Intent",response.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //hocks
        courseRV = findViewById(R.id.idBillToken);
        backBTN = findViewById(R.id.backButtonBillToken);
        printBTN = findViewById(R.id.printToDaBTN);

        // here we have created new array list and added data to it.
        listModelArrayList = new ArrayList<>();


        JSONArray data_array = null;
        try {
            data_array = response.getJSONArray("data");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject data = null;
        try {
            data = (JSONObject) data_array.get(0);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //Log.d("Token Data",data.toString());
        // Log.d("Product Names", String.valueOf(data.getJSONArray("productName")));
        JSONArray product_array = null;
        try {
            product_array = data.getJSONArray("product");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("Product Array",product_array.toString());
        for(int i = 0; i < product_array.length(); i++)
        {
            try {
                JSONObject product = product_array.getJSONObject(i);
                String product_name = product.getString("productName");
                String qty = product.getString("quantity");
                String price = product.getString("price");
                Log.d("Product Name", product_name);
                Log.d("Quantity", qty);
                listModelArrayList.add(new productsListModel(product_name, qty,price));
                //courseRV.setAdapter(new productAdapter(billToken.this, listModelArrayList));
            }catch (Exception e){

            }
        }
        // Some Changes


//        String productName = "Mens formal shirts";
//        String qty = "4";
//        String price ="500";
//        for(int i=1;i<=200;i++){
//            listModelArrayList.add(new productsListModel(productName,qty,price));
//        }


        // we are initializing our adapter class and passing our arraylist to it.
        billAdaptor billAdaptor = new billAdaptor(this, listModelArrayList);

        // below line is for setting a layout manager for our recycler view.
        // here we are creating vertical list so we will provide orientation as vertical
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        // in below two lines we are setting layoutmanager and adapter to our recycler view.
        courseRV.setLayoutManager(linearLayoutManager);
        courseRV.setAdapter(billAdaptor);

        //backBTN code
        backBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                onBackPressed();
            }
        });
        //printBTN code
        printBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent Intent = new Intent(getApplicationContext(), billToken.class);
                //startActivity(Intent);
                Toast.makeText(getApplicationContext(), "Clicked on Print", Toast.LENGTH_SHORT).show();
            }
        });


    }
}