package com.example.geeta_dresses;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.util.Log;
import android.view.MenuItem;

import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.geeta_dresses.constants.Constant;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Array;
import java.util.ArrayList;

public class Dashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    //variable
    private RecyclerView courseRV;
    Constant constant;
    JsonObjectRequest jsonObjectRequest;
    JSONObject object;
    RequestQueue requestQueue;
    JSONObject response_object;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    ExtendedFloatingActionButton addTokenbtn;
    SharedPreferences spLogin, spToken, spUserData,userSP;
    TextView inquiryCount;
    private long pressedTime;

    // Arraylist for storing data
    private ArrayList<inquiryModel> inquiryModelArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_dashboard);
        Window window = Dashboard.this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(ContextCompat.getColor(Dashboard.this, R.color.black));

        //hocks
        courseRV = findViewById(R.id.idInquiryRV);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbarHere);
        addTokenbtn = findViewById(R.id.addTokenbtn);
        inquiryCount = findViewById(R.id.textView4);
        //User data part with SP
        userSP = getSharedPreferences("userMetadata", MODE_PRIVATE);

        //Tool bar as action bar
        setSupportActionBar(toolbar);

        //navigation drawer menu
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navi_drawer_open, R.string.navi_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        toolbar.setNavigationIcon(R.drawable.menu);


        navigationView.setNavigationItemSelectedListener(this);

        // here we have created new array list and added data to it.
        inquiryModelArrayList = new ArrayList<>();

        // Using Constants
        constant = new Constant();
        // URL
        String url = constant.getURL() + constant.getPORT() + constant.getUPDATE_TOKEN();
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
                    //Log.d("Data_araay herer", String.valueOf(data_array));

                    //JSONObject data = (JSONObject) data_array.get(0);
                    //Log.d("Token Data",data.toString());
                    // Log.d("Product Names", String.valueOf(data.getJSONArray("productName")));
                    //JSONArray product_array = data.getJSONArray("product");
                    //Log.d("Product Array",product_array.toString());
                    inquiryCount.setText("Total inquiries - " + data_array.length());
                    for(int i = 0; i < data_array.length(); i++)
                    {
                        //JSONObject product = product_array.getJSONObject(i);
                        JSONObject data = (JSONObject) data_array.get(i);
                        String inquiryNo = data.getString("tokenNumber");
                        String inquiryUser = data.getString("username");
                        String rawDay = data.getString("createdAt");
                        String inquiryDay = rawDay.substring(0,10);

                        String[] mobileArray = {};//= {"Android","IPhone","WindowsMobile","Blackberry",
//                                "WebOS","Ubuntu","Windows7","Max OS X"};
                        ArrayList<String> productArray = new ArrayList<String>();
                        JSONArray product_array = data.getJSONArray("product");
                        Log.d("Product_array_dialog",String.valueOf(product_array));
                        for(int j=0;j< product_array.length();j++){
                            JSONObject product = product_array.getJSONObject(j);
                            String product_name = product.getString("productName");
                            String qty = product.getString("quantity");
                            productArray.add(product_name +"                 "+ qty);
                        }
                        mobileArray = productArray.toArray(mobileArray);
                        Boolean isInquired = data.getBoolean("isEnquired");
                        Boolean isPurchased = data.getBoolean("isPurchased");
                        String userRole = userSP.getString("userRole","");
                        Boolean isManger;
                        if(userRole.equals("manager")){
                            isManger = true;
                        }else{
                            isManger = false;
                        }
                        Log.d("UserRolle", String.valueOf(isManger));
                        inquiryModelArrayList.add(new inquiryModel(inquiryNo,inquiryUser,inquiryDay,mobileArray,isInquired,isPurchased,isManger));
                        courseRV.setAdapter(new inquiryAdapter(Dashboard.this, inquiryModelArrayList));

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


        Log.d("Product Array List",inquiryModelArrayList.toString());


        //        String productName = "Mens formal shirts";
//        String qty = "4";
//        for(int i=1;i<=200;i++){
//            productsModelArrayList.add(new productsModel(productName,qty));
//        }

//        for(int i=1; i<= 200;i++){
//            inquiryModelArrayList.add(new inquiryModel(String.valueOf(i),"Smith Johnson","2022/05/07 14:10:07"));
//        }

        // we are initializing our adapter class and passing our arraylist to it.
        inquiryAdapter inquiryAdapter = new inquiryAdapter(this, inquiryModelArrayList);

        // below line is for setting a layout manager for our recycler view.
        // here we are creating vertical list so we will provide orientation as vertical
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        // in below two lines we are setting layoutmanager and adapter to our recycler view.
        courseRV.setLayoutManager(linearLayoutManager);
        courseRV.setAdapter(inquiryAdapter);

        //Floating button implementation
        addTokenbtn.setOnClickListener(view -> {
            Intent addOrderIntent = new Intent(getApplicationContext(), tokenHome.class);
            startActivity(addOrderIntent);
            //Toast.makeText(getApplicationContext(), "Clicked on add order", Toast.LENGTH_SHORT).show();
        });


    }

    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            //super.onBackPressed();
            if (pressedTime + 2000 > System.currentTimeMillis()) {
                super.onBackPressed();
                finish();
                finishAffinity();
            } else {
                Toast.makeText(getBaseContext(), "Press back again to exit", Toast.LENGTH_SHORT).show();
            }
            pressedTime = System.currentTimeMillis();
        }

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.nav_token:
                startActivity(new Intent(this, tokenHome.class));
                return true;
            case R.id.logout:
                spLogin = getSharedPreferences("login", MODE_PRIVATE);
                spLogin.edit().clear().apply();

                spToken = getSharedPreferences("tokenSharedPreferences", MODE_PRIVATE);
                spToken.edit().clear().apply();

                spUserData = getSharedPreferences("userMetadata",MODE_PRIVATE);
                spUserData.edit().clear().apply();

                Toast.makeText(this, "Logged Out Successfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, MainActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}