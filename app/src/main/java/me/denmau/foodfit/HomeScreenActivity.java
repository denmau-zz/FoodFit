package me.denmau.foodfit;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;

import me.denmau.foodfit.reciperecycler.RecipeModel;
import me.denmau.foodfit.reciperecycler.RecipesFragment;

public class HomeScreenActivity extends AppCompatActivity {


    /*
     * Created by Dennis Kamau
     * website: https://www.denmau.me
     */

    private ArrayList<RecipeModel> recipes = new ArrayList<>();
    private final String TAG = "HomeScreenActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Load data into the ArrayList
        recipes.add(new RecipeModel(R.drawable.app_logo, "Mihogo", 30, 4.5, 2.5, "Dessert"));
        recipes.add(new RecipeModel(R.drawable.splash_screen_image, "Cassava", 30, 9.5, 1.5, "Appetizer"));

        // Instantiate Recipes Fragment.
        RecipesFragment recipesFragment = RecipesFragment.newInstance(recipes);

        // Get the FragmentManager and start mount RecipesFragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager
                .beginTransaction();
        Log.d(TAG, "recipesFragment is on display");

        // Add the Recipes Fragment.
        fragmentTransaction.add(R.id.fragment_container, recipesFragment).addToBackStack(null).commit();

        Log.d(TAG, "recipesFragment attached successfully");

        Toast.makeText(this, "recipesFragment is on display", Toast.LENGTH_SHORT).show();
    }
}