package com.example.walk_in_sale;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class createToken extends AppCompatActivity {
    TextView userName, userDescription;
    SharedPreferences userSP;
    Button backBTN, newCustomerBTN, loyalCustomerBTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_token);
        //hocks
        userName = findViewById(R.id.userNameCreate);
        userDescription = findViewById(R.id.userDescriptionCreate);
        backBTN = findViewById(R.id.backButtonCreateToken);
        newCustomerBTN = findViewById(R.id.newCustomerBTN);
        loyalCustomerBTN = findViewById(R.id.loyalCustomerBTN);

        //User data part with SP
        userSP = getSharedPreferences("userMetadata", MODE_PRIVATE);
        userName.setText(userSP.getString("userName", ""));
        userDescription.setText(userSP.getString("userEmail", ""));

        //backBTN code
        backBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                onBackPressed();
            }
        });
        //createTokenBTN code
        newCustomerBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent tokenNumberIntent = new Intent(getApplicationContext(), tokenNumberCreation.class);
                startActivity(tokenNumberIntent);
                //Toast.makeText(getApplicationContext(), "Clicked on new customer", Toast.LENGTH_SHORT).show();
            }
        });
        //existinTokenBTN code
        loyalCustomerBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent createTokenIntent = new Intent(getApplicationContext(), crateToken.class);
                //startActivity(createTokenIntent);
                Toast.makeText(getApplicationContext(), "Clicked on loyal customer", Toast.LENGTH_SHORT).show();
            }
        });
    }
}