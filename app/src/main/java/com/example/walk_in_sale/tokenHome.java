package com.example.walk_in_sale;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class tokenHome extends AppCompatActivity {
    //variables
    TextView userName, userDescription;
    SharedPreferences userSP;
    Button backBTN, createTokenBTN, existingBTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_token_home);
        Window window = tokenHome.this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(ContextCompat.getColor(tokenHome.this, R.color.black));
        //hocks
        userName = findViewById(R.id.userName);
        userDescription = findViewById(R.id.userDescription);
        backBTN = findViewById(R.id.backButtonTokenHome);
        createTokenBTN = findViewById(R.id.createTokenBTN);
        existingBTN = findViewById(R.id.existingTokenBTN);

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
        createTokenBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent createTokenIntent = new Intent(getApplicationContext(), createToken.class);
                startActivity(createTokenIntent);
            }
        });
        //existinTokenBTN code
        existingBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent createTokenIntent = new Intent(getApplicationContext(), crateToken.class);
                //startActivity(createTokenIntent);
                Toast.makeText(getApplicationContext(), "Clicked on Existing Token", Toast.LENGTH_SHORT).show();
            }
        });
    }
}