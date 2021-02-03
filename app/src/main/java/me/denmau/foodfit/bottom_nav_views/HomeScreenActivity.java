package me.denmau.foodfit.bottom_nav_views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import me.denmau.foodfit.R;
import me.denmau.foodfit.reciperecycler.RecipeModel;
import me.denmau.foodfit.ui.home.RecipesFragment;

public class HomeScreenActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    /*
     * Created by Dennis Kamau
     * website: https://www.denmau.me
     * This class should get/Recipes and add them into the arrayList before attaching the recipes fragment
     */

    private ArrayList<RecipeModel> recipes = new ArrayList<>();
    private final String TAG = "HomeScreenActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        //getting bottom navigation view and attaching the listener
        BottomNavigationView bottomNav = findViewById(R.id.navigation);
        bottomNav.setOnNavigationItemSelectedListener((BottomNavigationView.OnNavigationItemSelectedListener) this);
        loadDataIntoTheArrayList();
        addTheRecipesFragment();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Activity activity = null;
        switch (item.getItemId()) {
            case R.id.navigation_home:
                activity = new HomeScreenActivity();
                break;

            case R.id.navigation_search:
                activity = new SearchActivity();
                break;

            case R.id.navigation_random:
                activity = new RandomActivity();
                break;
        }
        startActivity(new Intent(this, activity.getClass()));
        return true;
    }

    public void loadDataIntoTheArrayList() {
        // Load data into the ArrayList
        recipes.add(new RecipeModel(R.drawable.app_logo, "Chocolate Cakes", 30, 4.5, 2.5, "Dessert"));
        recipes.add(new RecipeModel(R.drawable.splash_screen_image, "Broiled double-thick Lamb chops", 45, 9.5, 1.5, "Appetizer"));
    }

    public void addTheRecipesFragment() {
        // Instantiate Recipes Fragment.
        RecipesFragment recipesFragment = RecipesFragment.newInstance(recipes);

        // Get the FragmentManager and start mount RecipesFragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager
                .beginTransaction();

        // Add the Recipes Fragment.
        fragmentTransaction.add(R.id.fragment_container, recipesFragment).commit();

        Log.d(TAG, "recipesFragment attached successfully");
    }
}