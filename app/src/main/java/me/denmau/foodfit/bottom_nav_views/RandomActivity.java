package me.denmau.foodfit.bottom_nav_views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import me.denmau.foodfit.R;

public class RandomActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    private String TAG = "RandomActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random);
        //getting bottom navigation view and attaching the listener
        BottomNavigationView bottomNav = findViewById(R.id.navigation);
        bottomNav.setOnNavigationItemSelectedListener((BottomNavigationView.OnNavigationItemSelectedListener) this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Activity activity = null;
        switch (item.getItemId()) {
            case R.id.navigation_home:
                activity = new HomeScreenActivity();
                break;

            case R.id.navigation_search:
                activity = new SearchActivity();
                break;


            case R.id.navigation_random:
                activity = new RandomActivity();
                break;
        }
        startActivity(new Intent(this, activity.getClass()));
        return true;
    }
}