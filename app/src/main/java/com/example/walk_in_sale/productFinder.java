package com.example.walk_in_sale;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class productFinder extends AppCompatActivity {
    EditText productId;
    TextView userName, currentTokenNumber , finderResult;
    SharedPreferences userSP;
    Button findBTN , nextBTN ,backBTN;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_finder);
        Window window = productFinder.this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(ContextCompat.getColor(productFinder.this, R.color.black));
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
                //Code for product finding herer
            }
        });
        //nextBTN code
        nextBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //next button code here
            }
        });
    }
}