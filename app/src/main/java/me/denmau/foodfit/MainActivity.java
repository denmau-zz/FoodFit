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

        /*
        TODO
         if user is signed in, go to home screen
         else show about app screen, that leads to login/ create account
        */

//        if (mAuth != null) {
//            Toast.makeText(this, "Welcome, You are already signed in", Toast.LENGTH_LONG).show();
//            FirebaseUser currentUser = mAuth.getCurrentUser();
//            openHomeScreenActivity(currentUser);
//        }

        /*
         Open about app screen after few seconds
         I placed this method in on start (instead of onCreate) so that for in case user clicks back
          and gets back to this screen, it will still change screen after few seconds
        */
        openAboutAppActivity();
    }

    private void openAboutAppActivity() {
        int DELAY = 3000;

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, AboutAppActivity.class);
                startActivity(intent);
            }
        }, DELAY);
    }

    private void openHomeScreenActivity(FirebaseUser currentUser) {
        int DELAY = 3000;

        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            Intent intent = new Intent(MainActivity.this, HomeScreenActivity.class);
            intent.putExtra("currentUser", currentUser);
            startActivity(intent);
        }, DELAY);
    }
}