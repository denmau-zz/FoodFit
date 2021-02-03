package me.denmau.foodfit.recipedetails;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;

import me.denmau.foodfit.R;

public class RecipeDetailsActivity extends AppCompatActivity {

    CardView cardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);

        cardView = findViewById(R.id.cardView);
        cardView.setBackgroundResource(R.drawable.card_view_bg);
    }
}