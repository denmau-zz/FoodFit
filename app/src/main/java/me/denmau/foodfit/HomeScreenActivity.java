package me.denmau.foodfit;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

import me.denmau.foodfit.reciperecycler.RecipeModel;
import me.denmau.foodfit.ui.home.RecipesFragment;
import me.denmau.foodfit.ui.random.RandomFragment;
import me.denmau.foodfit.ui.search.SearchFragment;

public class HomeScreenActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    /*
     * Created by Dennis Kamau
     * website: https://www.denmau.me
     */

    private ArrayList<RecipeModel> recipes = new ArrayList<>();
    private final String TAG = "HomeScreenActivity";
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        user = FirebaseAuth.getInstance().getCurrentUser();

        //getting bottom navigation view and attaching the listener
        BottomNavigationView bottomNav = findViewById(R.id.navigation);
        bottomNav.setOnNavigationItemSelectedListener((BottomNavigationView.OnNavigationItemSelectedListener) this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Load data into the ArrayList
        recipes.add(new RecipeModel(R.drawable.app_logo, "Chocolate Cakes", 30, 4.5, 2.5, "Dessert"));
        recipes.add(new RecipeModel(R.drawable.splash_screen_image, "Broiled double-thick Lamb chops", 45, 9.5, 1.5, "Appetizer"));

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


    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        switch (item.getItemId()) {
            case R.id.navigation_home:
                fragment = RecipesFragment.newInstance(recipes);
                break;

            case R.id.navigation_search:
                fragment = new SearchFragment();
                break;








            case R.id.navigation_random:
                fragment = new RandomFragment();
                break;
        }
        return loadFragment(fragment);
    }
}