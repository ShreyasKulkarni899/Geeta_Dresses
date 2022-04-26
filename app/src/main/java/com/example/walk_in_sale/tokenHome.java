package com.example.walk_in_sale;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class tokenHome extends AppCompatActivity {
    TextView userName, userDescription;
    SharedPreferences userSP;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_token_home);

        userName = findViewById(R.id.userName);
        userDescription = findViewById(R.id.userDescription);

        userSP = getSharedPreferences("userMetadata",MODE_PRIVATE);
        userName.setText(userSP.getString("userName",""));
        userDescription.setText(userSP.getString("userEmail",""));

    }
}