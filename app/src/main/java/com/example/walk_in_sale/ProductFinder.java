package com.example.walk_in_sale;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.walk_in_sale.constants.Constant;

import org.json.JSONException;
import org.json.JSONObject;

public class ProductFinder extends AppCompatActivity {
    EditText productId;
    TextView userName, currentTokenNumber , finderResult;
    SharedPreferences userSP;
    Button findBTN , nextBTN ,backBTN;
    RequestQueue requestQueue;
    JsonObjectRequest jsonObjectRequest;
    JSONObject object;
    Constant constant;
    String product_name="",product_price = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_finder);
        Window window = ProductFinder.this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(ContextCompat.getColor(ProductFinder.this, R.color.black));
        //hocks
        userName = findViewById(R.id.userNamePF);
        currentTokenNumber = findViewById(R.id.userCurrentPF);
        productId = findViewById(R.id.prouctIdPF);
        finderResult = findViewById(R.id.finderResult);
        findBTN = findViewById(R.id.findPFBTN);
        nextBTN = findViewById(R.id.nextPFBTN);
        backBTN = findViewById(R.id.backBTNPF);
        //User data part with SP
        userSP = getSharedPreferences("userMetadata", MODE_PRIVATE);
        userName.setText(userSP.getString("userName", ""));
        currentTokenNumber.setText("Token No - "+userSP.getString("tokenNumber",""));

        // Initializing Request Queue
        requestQueue = Volley.newRequestQueue(this);


        //backBTN code
        backBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                onBackPressed();
            }
        });
        //findBTN code
        findBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Code for product finding here
                constant = new Constant();
                // URL
                String url = constant.getURL() + constant.getPORT() + constant.getPRODUCT_FINDER() + productId.getText().toString();

                // Dummy JSON Object
                object = new JSONObject();

                // Setting Up JSON Object Request
                jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, object, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("RPF",response.toString());
                        try {
                                // Fetching Data Object
                                JSONObject data = response.getJSONObject("data");
                                product_name = data.getString("department")+" "+data.getString("productItem")+" "+data.getString("productType");
                                product_price = data.getString("purchasePrice");
                                finderResult.setText("Name:"+product_name+" \nPrice:"+product_price);

                        } catch (JSONException e) {
                            finderResult.setText("No Data Found!");
                            //Toast.makeText(ProductFinder.this,"Something Went Wrong!",Toast.LENGTH_LONG).show();
                            //e.printStackTrace();
                        }
                        //Toast.makeText(ProductFinder.this,response.toString(),Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ProductFinder.this,"Volley Error",Toast.LENGTH_LONG).show();
                    }
                });

                requestQueue.add(jsonObjectRequest);
            }
        });

        //nextBTN code
        nextBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProductFinder.this,tokenDashboard.class);
                intent.putExtra("activity", "ProductFinder");
                if (!product_name.isEmpty() && !product_price.isEmpty() && !productId.getText().toString().isEmpty()) {
                    intent.putExtra("result","OK");
                    intent.putExtra("product_name", product_name);
                    intent.putExtra("product_price", product_price);
                    intent.putExtra("productId", productId.getText().toString());
                }
                else{
                    intent.putExtra("result","NOK");
                }
                startActivity(intent);

            }
        });
    }
}