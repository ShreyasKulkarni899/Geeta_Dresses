package com.example.walk_in_sale;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.walk_in_sale.constants.Constant;

import org.json.JSONObject;

import java.util.ArrayList;

public class billToken extends AppCompatActivity {
    private RecyclerView courseRV;
    Constant constant;
    RequestQueue requestQueue;
    JsonObjectRequest jsonObjectRequest;
    JSONObject object;
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


        //hocks
        courseRV = findViewById(R.id.idBillToken);
        backBTN = findViewById(R.id.backButtonBillToken);
        printBTN = findViewById(R.id.printToDaBTN);

        // here we have created new array list and added data to it.
        listModelArrayList = new ArrayList<>();






        String productName = "Mens formal shirts";
        String qty = "4";
        String price ="500";
        for(int i=1;i<=200;i++){
            listModelArrayList.add(new productsListModel(productName,qty,price));
        }


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