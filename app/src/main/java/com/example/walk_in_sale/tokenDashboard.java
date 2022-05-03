package com.example.walk_in_sale;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.walk_in_sale.constants.Constant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class tokenDashboard extends AppCompatActivity {
    private RecyclerView courseRV;
    Constant constant;
    RequestQueue requestQueue;
    JsonObjectRequest jsonObjectRequest;
    JSONObject object;
    Button backBTN, barcodeBTN, scanBTN, nameBTN, nextBTN;
    TextView userName, currentTokenNumber;
    SharedPreferences userSP;

    // Arraylist for storing data
    private ArrayList<productsModel> productsModelArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_token_dashboard);
        Window window = tokenDashboard.this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(ContextCompat.getColor(tokenDashboard.this, R.color.black));
        //hocks
        userName = findViewById(R.id.userNameTokenNumber);
        currentTokenNumber = findViewById(R.id.userCurrentTokenNo);
        //User data part with SP
        userSP = getSharedPreferences("userMetadata", MODE_PRIVATE);
        userName.setText(userSP.getString("userName", ""));
        currentTokenNumber.setText("Token No - "+userSP.getString("tokenNumber",""));


        //hocks
        courseRV = findViewById(R.id.idRVCourse);
        backBTN = findViewById(R.id.backButtonTokenNumber);
        barcodeBTN = findViewById(R.id.barcodeBTN);
        scanBTN = findViewById(R.id.scanBTN);
        nameBTN = findViewById(R.id.nameBTN);
        nextBTN = findViewById(R.id.nextToDaBTN);

        // here we have created new array list and added data to it.
        productsModelArrayList = new ArrayList<>();

        // Using Constants
        constant = new Constant();
        // URL
        String url = constant.getURL() + constant.getPORT() + constant.getGET_TOKEN_DETAILS() + "100";
        // Setting up request queue
        requestQueue = Volley.newRequestQueue(this);
        object = new JSONObject();

        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, object, new Response.Listener<JSONObject>() {
            @SuppressLint("LongLogTag")
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Log.d("Token Response",response.toString());
                    JSONArray data_array = response.getJSONArray("data");
                    JSONObject data = (JSONObject) data_array.get(0);
                    Log.d("Token Data",data.toString());
                    Log.d("Product Names", String.valueOf(data.getJSONArray("productName")));
                    //JSONArray product_array = data.getJSONArray("")
                } catch (JSONException e) {
                    Log.d("Failed Token Data Request",e.toString());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue.add(jsonObjectRequest);



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
                Intent Intent = new Intent(getApplicationContext(), billToken.class);
                startActivity(Intent);
                Toast.makeText(getApplicationContext(), "Clicked on next", Toast.LENGTH_SHORT).show();
            }
        });
    }
}