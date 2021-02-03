package me.denmau.foodfit;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    /*
     * Created by Dennis Kamau
     * website: https://www.denmau.me
     */

    // Firebase
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    protected void onStart() {
        super.onStart();
        int DELAY = 3000;
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            if (mAuth != null) {
                // user already logged in, go to Home Screeen
                Toast.makeText(this, "Hey " + mAuth.getCurrentUser().getDisplayName() + ", Welcome back", Toast.LENGTH_LONG).show();
                startActivity(new Intent(MainActivity.this, HomeScreenActivity.class));
            }
            startActivity(new Intent(MainActivity.this, AboutAppActivity.class));
        }, DELAY);
    }
}