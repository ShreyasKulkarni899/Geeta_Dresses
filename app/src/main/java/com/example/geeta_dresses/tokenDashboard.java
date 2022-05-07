package com.example.geeta_dresses;

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
import com.example.geeta_dresses.constants.Constant;
import com.example.geeta_dresses.models.InquiryModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class tokenDashboard extends AppCompatActivity {
    private RecyclerView courseRV;
    Constant constant;
    RequestQueue requestQueue;
    JsonObjectRequest jsonObjectRequest;
    JSONObject object;
    Button backBTN, nameBTN, nextBTN;
    TextView userName, currentTokenNumber;
    SharedPreferences userSP;
    JSONObject response_object;
    InquiryModel inquiryModel;


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
        nameBTN = findViewById(R.id.nameBTN);
        nextBTN = findViewById(R.id.nextToDaBTN);



        // here we have created new array list and added data to it.
        productsModelArrayList = new ArrayList<>();

        //Code for the intent from productFinder
        String resultIN , product_nameIN,productIDIN, product_priceIN;
        Bundle extras = getIntent().getExtras();
        if(extras == null){
            resultIN =null;
            product_nameIN = null;
            productIDIN = null;
            product_priceIN= null;
        }else{
            resultIN = extras.getString("result");
            product_nameIN = extras.getString("product_name");
            productIDIN = extras.getString("productId");
            product_priceIN =  "0";
            Log.d("Data here Look",String.valueOf(extras));
            productsModelArrayList.add(new productsModel(product_nameIN,"1",product_priceIN,productIDIN));
        }
        // Using Constants
        constant = new Constant();
        // URL
        String url = constant.getURL() + constant.getPORT() + constant.getGET_TOKEN_DETAILS() + "100";
                //userSP.getString("tokenNumber","");
        // Setting up request queue
        requestQueue = Volley.newRequestQueue(this);
        object = new JSONObject();

        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, object, new Response.Listener<JSONObject>() {
            @SuppressLint("LongLogTag")
            @Override
            public void onResponse(JSONObject response) {
                try {
                    response_object = response;
                    //Log.d("Token Response",response.toString());
                    JSONArray data_array = response.getJSONArray("data");
                    JSONObject data = (JSONObject) data_array.get(0);
                    //Log.d("Token Data",data.toString());
                   // Log.d("Product Names", String.valueOf(data.getJSONArray("productName")));
                    JSONArray product_array = data.getJSONArray("product");
                    Log.d("Product Array",product_array.toString());
                    for(int i = 0; i < product_array.length(); i++)
                    {
                        JSONObject product = product_array.getJSONObject(i);
                        String product_name = product.getString("productName");
                        String qty = product.getString("quantity");
                        String ActualPrice = product.getString("price");
                        String productId = product.getString("productId");
                        Log.d("Product Name",product_name);
                        Log.d("Quantity",qty);
                        productsModelArrayList.add(new productsModel(product_name,qty,ActualPrice,productId));
                        courseRV.setAdapter(new productAdapter(tokenDashboard.this, productsModelArrayList));

                    }
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


    Log.d("Product Array List",productsModelArrayList.toString());

//        String productName = "Mens formal shirts";
//        String qty = "4";
//        for(int i=1;i<=200;i++){
//            productsModelArrayList.add(new productsModel(productName,qty));
//        }


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
        
        //nameBTN code
        nameBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(tokenDashboard.this,ProductFinder.class);
                intent.putExtra("activity","tokenDashboard");
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Clicked on Name", Toast.LENGTH_SHORT).show();
            }
        });
        //nextBTN code
        nextBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Dashboard.class);
                //intent.putExtra("response", response_object.toString());

                inquiryModel = new InquiryModel();
                inquiryModel.setUserId(userSP.getString("userId",""));
                inquiryModel.setUserName(userSP.getString("userName",""));
                inquiryModel.setCustomerId("Customer ID");
                inquiryModel.setTokenNumber(userSP.getString("tokenNumber",""));
                inquiryModel.setReason("Some Reason");
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                Date date = new Date();
                inquiryModel.setDay(dateFormat.format(date).toString());
                inquiryModel.setEnquired(true);
                inquiryModel.setPurchased(true);
                inquiryModel.setProductsModelArrayList(productsModelArrayList);

                //intent.putExtra("productsModelArrayList",productsModelArrayList);
                try {
                    constant = new Constant();
                    object = new JSONObject(inquiryModel.toString());
                    String url = constant.getURL() + constant.getPORT() + constant.getUPDATE_TOKEN();

                    jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, object, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Log.d("InquiryModel",response.toString());
                            Toast.makeText(tokenDashboard.this,"Inquiry Added",Toast.LENGTH_SHORT).show();
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(tokenDashboard.this,"Failed To Add Inquiry",Toast.LENGTH_SHORT).show();
                        }
                    });
                    requestQueue.add(jsonObjectRequest);
                } catch (JSONException e) {
                    e.printStackTrace();
                };



                //Log.d("Inquiry",inquiryModel.toString());
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Clicked on next", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
