package me.denmau.foodfit;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    /*
     * Created by Dennis Kamau
     * website: https://www.denmau.me
     */

    FirebaseUser user;
    private final int DELAY = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user = FirebaseAuth.getInstance().getCurrentUser();
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (user != null) {
            Toast.makeText(this, "Hello, welcome back", Toast.LENGTH_LONG).show();
            goToHomeScreen();
        } else
            goToLoginScreen();
    }

    private void goToHomeScreen() {
        new Handler(Looper.getMainLooper()).postDelayed(() -> {

            startActivity(new Intent(MainActivity.this, HomeScreenActivity.class));
        }, DELAY);
    }

    private void goToLoginScreen() {
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            startActivity(new Intent(MainActivity.this, AboutAppActivity.class));
        }, DELAY);
    }

}
