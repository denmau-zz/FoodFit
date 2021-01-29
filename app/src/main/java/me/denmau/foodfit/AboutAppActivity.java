package me.denmau.foodfit;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import me.denmau.foodfit.model.AboutAppModel;

public class AboutAppActivity extends AppCompatActivity {
    private static final String TAG = "AboutAppActivity";
    // This activity should display sets of data meant akin to 3 screens
    int currentScreen = 1;

    // this arrayList contains images and details for each screen
    List<AboutAppModel> aboutAppDetails = new ArrayList<>();

    // declare views
    ImageView aboutAppBanner;
    TextView aboutAppTitle;
    TextView aboutAppDesc;
    ImageView aboutAppSlider;

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
        String aboutAppDesc1 = "We compute the nutritional information for recipes automatically using a powerful algorithm. With this information, you can find individual recipes or even create entire meal plans that satisfy your dietary goals";
        String aboutAppDesc2 = "We automatically analyze recipes to check for ingredients that contain common allergens, such as wheat, dairy, eggs, soy, nuts, etc. We also determine whether a recipe is vegan, vegetarian, Paleo friendly, Whole30 compliant, and more";
        String aboutAppDesc3 = "We are more than just a recipe. We provide solutions to make any food-related vision a reality.";
        // AboutApp Slider Images
        int aboutAppSlider1 = R.drawable.about_app_position_1;
        int aboutAppSlider2 = R.drawable.about_app_position_2;
        int aboutAppSlider3 = R.drawable.about_app_position_3;

        // identify views
        aboutAppBanner = findViewById(R.id.bannerImage);
        aboutAppTitle = findViewById(R.id.txtAboutAppTitle);
        aboutAppDesc = findViewById(R.id.txtAboutAppDesc);
        aboutAppSlider = findViewById(R.id.aboutAppSliderImage);

        // Load the array List with about App Details
        aboutAppDetails.add(new AboutAppModel(aboutAppImage1, aboutAppTitle1, aboutAppDesc1, aboutAppSlider1));
        aboutAppDetails.add(new AboutAppModel(aboutAppImage2, aboutAppTitle2, aboutAppDesc2, aboutAppSlider2));
        aboutAppDetails.add(new AboutAppModel(aboutAppImage3, aboutAppTitle3, aboutAppDesc3, aboutAppSlider3));
    }

    @Override
    protected void onStart() {
        super.onStart();
        // the first screen (screen 1) is at index zero in the aboutAppDetails arrayList, so we pass currentScreen-1
        populateAboutAppDetails((currentScreen - 1));

        // Display app details for the second and third screen, each for 5 seconds
        for (int i = 0; i <= 1; i++) {
            int finalI = i;
            if (finalI < (aboutAppDetails.size() - 1)) {
                // the second screen has already been opened, so we start from screen 2
                Log.d(TAG, "opening screen " + (finalI + 2));
                // screen 2 is actually index 1 in the array, so we pass finalI + 1, wh
                new Handler(Looper.getMainLooper()).postDelayed(() -> populateAboutAppDetails(finalI + 1), 5000 + i * 5000);
            }
        }
    }

    private void populateAboutAppDetails(int currentScreen) {
        aboutAppBanner.setImageResource(aboutAppDetails.get(currentScreen).getAboutAppBanner());
        aboutAppTitle.setText(aboutAppDetails.get(currentScreen).getAboutAppTitle());
        aboutAppDesc.setText(aboutAppDetails.get(currentScreen).getAboutAppDesc());
        aboutAppSlider.setImageResource(aboutAppDetails.get(currentScreen).getAboutAppSliderImage());
    }


}
