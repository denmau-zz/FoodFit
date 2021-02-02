package me.denmau.foodfit;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.List;

import me.denmau.foodfit.model.Recipe;
import me.denmau.foodfit.ui.home.HomeFragment;
import me.denmau.foodfit.ui.home.HomeViewModel;

public class HomeScreenActivity extends AppCompatActivity {
    private ArrayList<Recipe> newRecipes;

    /*
     * Created by Dennis Kamau
     * website: https://www.denmau.me
     */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        newRecipes = new ArrayList<>();
        newRecipes.add(new Recipe(R.drawable.app_logo, "Very Hot Bacons", 20, 3.5, 87, "yuuumy"));

        // push this data to Home Fragment so as to trigger recycler view
        HomeViewModel hommy = new HomeViewModel();
        hommy.getRecipes().setValue(newRecipes);
    }

    public HomeScreenActivity() {


    }

    @Override
    protected void onStart() {
        super.onStart();

        // Instantiate the fragment.
        HomeFragment homeFragment = HomeFragment.newInstance();

        // Get the FragmentManager and start a transaction.
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager
                .beginTransaction();

        // Add the Reset Password Fragment.
        //fragmentTransaction.add(R.id.fragment_container, resetPasswordFrag).addToBackStack(null).commit();\
        fragmentTransaction.add(R.id.fragment_container, homeFragment).addToBackStack(null).commit();

        Toast.makeText(this, "Home fragment is on display", Toast.LENGTH_SHORT).show();
    }
}