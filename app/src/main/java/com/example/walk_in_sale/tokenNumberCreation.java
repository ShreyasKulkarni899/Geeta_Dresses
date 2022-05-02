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

public class tokenNumberCreation extends AppCompatActivity {

    TextView userName, userDescription, tokenNumber;
    SharedPreferences userSP;
    Button backBTN, attendTokenBTN, leaveTokenBTN;
    Constant constant,constant_new;
    RequestQueue requestQueue,requestQueueUpdate;
    JsonObjectRequest tokenRequest,tokenUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_token_number);
        Window window = tokenNumberCreation.this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(ContextCompat.getColor(tokenNumberCreation.this, R.color.black));
        //hocks
        userName = findViewById(R.id.userNameTokenNumber);
        userDescription = findViewById(R.id.userDescriptionTokenNumber);
        tokenNumber = findViewById(R.id.tokenNumberBig);
        backBTN = findViewById(R.id.backButtonTokenNumber);
        attendTokenBTN =findViewById(R.id.attendToken);
        leaveTokenBTN = findViewById(R.id.leaveToken);

        //User data part with SP
        userSP = getSharedPreferences("userMetadata", MODE_PRIVATE);
        userName.setText(userSP.getString("userName", ""));
        userDescription.setText(userSP.getString("userEmail", ""));
        getToken();



        //backBTN code
        backBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                onBackPressed();
            }
        });
        //createTokenBTN code
        attendTokenBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent createTokenIntent = new Intent(getApplicationContext(), createToken.class);
                //startActivity(createTokenIntent);
                //Toast.makeText(getApplicationContext(), "Clicked on attend", Toast.LENGTH_SHORT).show();
                String getTokenText = tokenNumber.getText().toString();
                String userNameText = userName.getText().toString();
                updateToken(getTokenText,userNameText);
                Intent intent = new Intent(tokenNumberCreation.this,tokenDashboard.class);
                startActivity(intent);
            }
        });
        //existinTokenBTN code
        leaveTokenBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent createTokenIntent = new Intent(getApplicationContext(), crateToken.class);
                //startActivity(createTokenIntent);
                finish();
                onBackPressed();
                //Toast.makeText(getApplicationContext(), "Clicked on leave", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getToken(){

        constant = new Constant();
        // Initializing Volley
        requestQueue = Volley.newRequestQueue(this);

        // URL
        String url = constant.getURL() + constant.getPORT() + constant.getGET_TOKEN();
        JSONObject object = new JSONObject();
        // Initializing Object Request
        tokenRequest = new JsonObjectRequest(Request.Method.GET, url,object, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                //Log.d("String Request Response",response.toString());
                try {
                    tokenNumber.setText(response.getString("counter"));
                    Toast.makeText(getApplicationContext(),"Token Created!",Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),"Token Creation Failed!",Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("String Request Error",error.toString());
                tokenNumber.setText("---");
                Toast.makeText(getApplicationContext(),"Token Creation Failed!",Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(tokenRequest);

    }

    private void updateToken(String getTokenText,String userNameText){
        constant_new = new Constant();
        // Initializing Request Queue
        requestQueueUpdate = Volley.newRequestQueue(this);
        // URL
        String update_url = constant_new.getURL() + constant_new.getPORT() + constant_new.getUPDATE_TOKEN();
        // JSON OBJECT FOR POST
        JSONObject updatedData = new JSONObject();
        try {
            updatedData.put("username",userNameText);
            updatedData.put("tokenNumber",getTokenText);
            SharedPreferences.Editor tokenEdit = userSP.edit();
            tokenEdit.putString("tokenNumber",getTokenText);
            tokenEdit.apply();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        tokenUpdate = new JsonObjectRequest(Request.Method.POST, update_url, updatedData, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("Update Token Response",response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Update Token Response",error.toString());
            }
        });
        requestQueueUpdate.add(tokenUpdate);
    }
}