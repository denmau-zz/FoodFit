package me.denmau.foodfit.Screens;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

import me.denmau.foodfit.R;

public class AccountActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    /*
     * Created by Dennis Kamau
     * website: https://www.denmau.me
     */
    private final String TAG = "AccountActivity";
    private FirebaseUser user;
    private ImageView userProfileImg;
    private TextView txtUserEmail;
    private Button btnSignOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        //getting bottom navigation view and attaching the listener for nav button clicks
        BottomNavigationView bottomNav = findViewById(R.id.navigation);
        bottomNav.setOnNavigationItemSelectedListener((BottomNavigationView.OnNavigationItemSelectedListener) this);

        user = FirebaseAuth.getInstance().getCurrentUser();
        userProfileImg = findViewById(R.id.userProfileImg);
        txtUserEmail = findViewById(R.id.txtUserEmail);
        btnSignOut = findViewById(R.id.btnSignOut);
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Load Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Picasso.get().load(user.getPhotoUrl())
                .into(userProfileImg);
        Log.d(TAG, "Picasso is attempting to draw: " + user.getPhotoUrl());

        txtUserEmail.setText(user.getEmail());

        String userEmail = user.getEmail();

        btnSignOut.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
        });

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

        FirebaseAuth.AuthStateListener authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() == null) {
                    startActivity(new Intent(AccountActivity.this, MainActivity.class));
                }
            }
        };
        firebaseAuth.addAuthStateListener(authStateListener);
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

            case R.id.navigation_account:
                activity = new AccountActivity();
                break;
        }
        startActivity(new Intent(this, activity.getClass()));
        return true;
    }
}