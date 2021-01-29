package me.denmau.foodfit;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import me.denmau.foodfit.model.AboutAppModel;

public class AboutAppActivity extends AppCompatActivity {
    // Member Variables
    ImageView aboutAppBanner;
    TextView aboutAppTitle;
    TextView aboutAppDesc;
    ImageView aboutAppSlider;

    // About App Images
    int aboutAppImage1 = R.drawable.about_app_1;
    int aboutAppImage2 = R.drawable.about_app_2;
    int aboutAppImage3 = R.drawable.about_app_3;
    // About App Titles
    String aboutAppTitle1 = "Nutritional Information";
    String aboutAppTitle2 = "Special Diets / Intolerance";
    String aboutAppTitle3 = "Towards a healthy you!";
    // About App Descriptions
    String aboutAppDesc1 = "We compute the nutritional information for recipes automatically using a powerful algorithm. With this information, you can find individual recipes or even create entire meal plans that satisfy your users' dietary goals";
    String aboutAppDesc2 = "We automatically analyze recipes to check for ingredients that contain common allergens, such as wheat, dairy, eggs, soy, nuts, etc. We also determine whether a recipe is vegan, vegetarian, Paleo friendly, Whole30 compliant, and more";
    String aboutAppDesc3 = "We are more than just a recipe. We provide solutions to make any food-related vision a reality.";
    // About App Slider Images
    int aboutAppSlider1 = R.drawable.about_app_position_1;
    int aboutAppSlider2 = R.drawable.about_app_position_2;
    int aboutAppSlider3 = R.drawable.about_app_position_3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_app);

        // identify views
        aboutAppBanner = findViewById(R.id.bannerImage);
        aboutAppTitle = findViewById(R.id.txtAboutAppTitle);
        aboutAppDesc = findViewById(R.id.txtAboutAppDesc);
        aboutAppSlider = findViewById(R.id.aboutAppSliderImage);

        // Load the array List with about App Details
        List<AboutAppModel> aboutAppDetails = new ArrayList<>();
        aboutAppDetails.add(new AboutAppModel(aboutAppImage1, aboutAppTitle1, aboutAppDesc1, aboutAppSlider1));
        aboutAppDetails.add(new AboutAppModel(aboutAppImage2, aboutAppTitle2, aboutAppDesc2, aboutAppSlider2));
        aboutAppDetails.add(new AboutAppModel(aboutAppImage3, aboutAppTitle3, aboutAppDesc3, aboutAppSlider3));
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Show about App Details

    }
}
