package com.example.walk_in_sale;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class tokenNumberCreation extends AppCompatActivity {

    TextView userName, userDescription, tokenNumber;
    SharedPreferences userSP;
    Button backBTN, attendTokenBTN, leaveTokenBTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_token_number);
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
        tokenNumber.setText("#"+"123");

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
                Toast.makeText(getApplicationContext(), "Clicked on attend", Toast.LENGTH_SHORT).show();
            }
        });
        //existinTokenBTN code
        leaveTokenBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent createTokenIntent = new Intent(getApplicationContext(), crateToken.class);
                //startActivity(createTokenIntent);
                Toast.makeText(getApplicationContext(), "Clicked on leave", Toast.LENGTH_SHORT).show();
            }
        });
    }
}