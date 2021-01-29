package me.denmau.foodfit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();

        /*
         Open about app screen after few seconds
         I placed this method in on start (instead of onCreate) so that for in case user clicks back
          and gets back to this screen, it will still change screen after few seconds
        */
        openAboutAppActivity();
    }

    private void openAboutAppActivity() {
        int DELAY = 3000;

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, AboutAppActivity.class);
                startActivity(intent);
            }
        }, DELAY);
    }
}