package com.example.geeta_dresses;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.view.MenuItem;

import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.navigation.NavigationView;

public class Dashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    //variable
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    ExtendedFloatingActionButton addTokenbtn;
    SharedPreferences spLogin, spToken, spUserData;
    private long pressedTime;


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
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbarHere);
        addTokenbtn = findViewById(R.id.addTokenbtn);


        //Tool bar as action bar
        setSupportActionBar(toolbar);

        //navigation drawer menu
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navi_drawer_open, R.string.navi_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        toolbar.setNavigationIcon(R.drawable.menu);


        navigationView.setNavigationItemSelectedListener(this);

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