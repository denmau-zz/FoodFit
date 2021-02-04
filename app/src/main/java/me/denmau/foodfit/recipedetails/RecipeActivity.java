package me.denmau.foodfit.recipedetails;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import me.denmau.foodfit.R;
import me.denmau.foodfit.spoonacularapi.model.Ingredient;

public class RecipeActivity extends AppCompatActivity {

    private JSONArray ingredientsArr;

    private TextView title, ready_in, servings, healthy, instructions;
    private ImageView img, vegeterian;

    private List<Ingredient> ingredientsLst = new ArrayList<Ingredient>();
    private RecyclerView recyclerView;
    private boolean like = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_recipe_details);

        final Intent intent = getIntent();
        final String recipeId = Objects.requireNonNull(intent.getExtras()).getString("id");

        img = findViewById(R.id.recipe_img);
        title = findViewById(R.id.recipeTitle);
        ready_in = findViewById(R.id.recipe_ready_in);
        servings = findViewById(R.id.servings);
        healthy = findViewById(R.id.recipe_healthy);
        vegeterian = findViewById(R.id.recipe_vegetarian);
        instructions = findViewById(R.id.recipe_instructions);

        // Load current Recipe Data
        getRecipeData(recipeId);

        recyclerView = findViewById(R.id.recipe_ingredients_rv);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
    }

    private void getRecipeData(final String recipeId) {
        // URL as in Spoonacular Docs
        String URL = " https://api.spoonacular.com/recipes/" + recipeId + "/information?apiKey=a1d1fd312d514991be23ea34891aaef9";


    }
}