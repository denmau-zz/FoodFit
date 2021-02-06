package me.denmau.foodfit.Screens;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import me.denmau.foodfit.R;
import me.denmau.foodfit.model.AboutAppModel;

public class AboutAppActivity extends AppCompatActivity {

    /*
     * Created by Dennis Kamau
     * website: https://www.denmau.me
     */

    /* This activity should display sets of data that illustrate 3 screens, each showing what the app is about */

    private static final String TAG = "AboutAppActivity";
    int currentScreen = -1;
    private final Handler handler = new Handler(Looper.getMainLooper());

    /*
    if user clicked skip button in screen 1 and goes to screen 2
    prevent handler.postDelayed from setting content to back to screen 2
   */
    boolean skipButtonClicked = false;

    // this arrayList contains images and details for each screen
    List<AboutAppModel> aboutAppDetails = new ArrayList<>();

    // declare views
    private ImageView aboutAppBanner;
    private TextView aboutAppTitle;
    private TextView aboutAppDesc;
    private ImageView aboutAppSlider;
    private TextView btnSkip;
    private TextView btnNext;
    private Button btnGetStarted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_app);

        // AboutApp Images
        int aboutAppImage1 = R.drawable.about_app_1;
        int aboutAppImage2 = R.drawable.about_app_2;
        int aboutAppImage3 = R.drawable.about_app_3;
        // AboutApp Titles
        String aboutAppTitle1 = "Nutritional Information";
        String aboutAppTitle2 = "Special Diets / Intolerance";
        String aboutAppTitle3 = "Towards a healthy you!";
        // AboutApp Descriptions
        String aboutAppDesc1 = getString(R.string.description1);
        String aboutAppDesc2 = getString(R.string.description2);
        String aboutAppDesc3 = getString(R.string.description3);
        // AboutApp Slider Images
        int aboutAppSlider1 = R.drawable.about_app_position_1;
        int aboutAppSlider2 = R.drawable.about_app_position_2;
        int aboutAppSlider3 = R.drawable.about_app_position_3;

        // Load the array List with about App Details
        aboutAppDetails.add(new me.denmau.foodfit.model.AboutAppModel(aboutAppImage1, aboutAppTitle1, aboutAppDesc1, aboutAppSlider1));
        aboutAppDetails.add(new me.denmau.foodfit.model.AboutAppModel(aboutAppImage2, aboutAppTitle2, aboutAppDesc2, aboutAppSlider2));
        aboutAppDetails.add(new me.denmau.foodfit.model.AboutAppModel(aboutAppImage3, aboutAppTitle3, aboutAppDesc3, aboutAppSlider3));

        // identify views
        aboutAppBanner = findViewById(R.id.bannerImage);
        aboutAppTitle = findViewById(R.id.txtAboutAppTitle);
        aboutAppDesc = findViewById(R.id.txtAboutAppDesc);
        aboutAppSlider = findViewById(R.id.aboutAppSliderImage);
        btnSkip = findViewById(R.id.btnSkip);
        btnNext = findViewById(R.id.btnNext);
        btnGetStarted = findViewById(R.id.search_btn);
    }

    @Override
    protected void onStart() {
        super.onStart();

        toastRunnable.run();

        // when btnSkip is clicked, go to screen 3
        btnSkip.setOnClickListener(v -> {
            skipButtonClicked = true;
            // we are actually going to screen 3, but with index 2 from the arrayList
            updateUI(2);
        });

        // when btnNext is clicked, go to next screen
        btnNext.setOnClickListener(v -> {
            skipButtonClicked = true;
            // if we are in the first screen, go to screen 2
            if (currentScreen == 0) updateUI(1);
                // else if we are in the second screen, go to screen 3
            else if (currentScreen == 1) updateUI(2);
            // the skip button is invisible in screen 3, hence it cannot be clicked
        });
        // when getStarted button is clicked, go to Login screen
        btnGetStarted.setOnClickListener(v -> startActivity(new Intent(AboutAppActivity.this, RegisterActivity.class)));
    }

    private final Runnable toastRunnable = new Runnable() {
        @Override
        public void run() {
            // as long as we are not viewing the last screen; go to the next screen
            if (currentScreen == 2) {
                handler.removeCallbacks(toastRunnable);
            } else updateUI((currentScreen + 1));
            handler.postDelayed(this, 3000);
        }
    };

    private void updateUI(int goToScreen) {
        // prevent the unlikely event of an indexOutOfBoundsException
        if (goToScreen > (aboutAppDetails.size() - 1)) {
            Log.e(TAG, "Trying to access a screen that is not available, we can only access screens 1 through 3, we cannot access screen " + goToScreen);
            return;
        }

        aboutAppBanner.setImageResource(aboutAppDetails.get(goToScreen).getAboutAppBanner());
        aboutAppTitle.setText(aboutAppDetails.get(goToScreen).getAboutAppTitle());
        aboutAppDesc.setText(aboutAppDetails.get(goToScreen).getAboutAppDesc());
        aboutAppSlider.setImageResource(aboutAppDetails.get(goToScreen).getAboutAppSliderImage());
        currentScreen = goToScreen;

        // if our view displays screen 3 (the last screen), make skip and next buttons invisible, but getStarted should be visible
        if (currentScreen == 2) {
            /*
             I chose view.Invisible instead of view.gone, so that the button(although invisible,
             takes up space for layout purposes
            */
            btnSkip.setVisibility(View.INVISIBLE);
            btnNext.setVisibility(View.INVISIBLE);
            aboutAppSlider.setVisibility(View.INVISIBLE);
            slideToRight(btnGetStarted);
        } else {
            btnSkip.setVisibility(View.VISIBLE);
            btnNext.setVisibility(View.VISIBLE);
            btnGetStarted.setVisibility(View.INVISIBLE);
        }
    }

    // To animate view slide out from left to right
    public void slideToRight(View view) {
        TranslateAnimation animate = new TranslateAnimation(-view.getWidth(), 0, 0, 0);
        animate.setDuration(500);
        animate.setFillAfter(true);
        view.startAnimation(animate);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        // Go back to splash screen
        startActivity(new Intent(AboutAppActivity.this, MainActivity.class));
    }
}