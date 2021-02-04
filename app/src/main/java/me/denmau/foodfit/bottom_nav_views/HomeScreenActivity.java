package me.denmau.foodfit.bottom_nav_views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import me.denmau.foodfit.R;
import me.denmau.foodfit.reciperecycler.RecipesAdapter;
import me.denmau.foodfit.spoonacularapi.model.Recipe;

public class HomeScreenActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    /*
     * Created by Dennis Kamau
     * website: https://www.denmau.me
     * This class should get/Recipes and add them into the arrayList before attaching the recipes fragment
     */

    private List<Recipe> lstRecipe = new ArrayList<>();
    private final String TAG = "HomeScreenActivity";
    private JSONArray testArr;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        //getting bottom navigation view and attaching the listener
        recyclerView = findViewById(R.id.recyclerView);
        BottomNavigationView bottomNav = findViewById(R.id.navigation);
        bottomNav.setOnNavigationItemSelectedListener((BottomNavigationView.OnNavigationItemSelectedListener) this);
        loadRandomDataIntoTheArrayList();
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
        if (activity == null)
            return false;

        startActivity(new Intent(this, activity.getClass()));
        return true;
    }

    public void loadRandomDataIntoTheArrayList() {
        String URL = " https://api.spoonacular.com/recipes/random?number=5&instructionsRequired=true&apiKey=c957b6816ba048139fbc25a67d2cff33";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                URL,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            testArr = (JSONArray) response.get("recipes");
                            Log.i("the res is:", String.valueOf(testArr));
                            for (int i = 0; i < testArr.length(); i++) {
                                JSONObject jsonObject1;
                                jsonObject1 = testArr.getJSONObject(i);
                                lstRecipe.add(new Recipe(jsonObject1.optString("id"), jsonObject1.optString("title"), "https://spoonacular.com/recipeImages/" + jsonObject1.optString("image"),
                                        Integer.parseInt(jsonObject1.optString("servings")), Integer.parseInt(jsonObject1.optString("readyInMinutes")),
                                        Double.parseDouble(jsonObject1.optString("healthScore")), Double.parseDouble(jsonObject1.optString("spoonacularScore"))));
                                // Once we call RecipesFragment it should call the Adapter and it should set the Recycler View by itself
                                Log.d(TAG, "Displaying Ingredients");
                                DisplayIngredients();
                            }
                        } catch (JSONException e) {
                            Toast.makeText(HomeScreenActivity.this, "Couldn't attach Recepes!", Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }
                    }
                },
                error -> Log.d("the volleyError:", error.toString())
        );
        requestQueue.add(jsonObjectRequest);
    }

    public void DisplayIngredients() {
        recyclerView.setHasFixedSize(true);
        int numberOfColumns = 2;
        recyclerView.setLayoutManager(new GridLayoutManager(this, numberOfColumns));
        mAdapter = new RecipesAdapter(this, lstRecipe);
        recyclerView.setAdapter(mAdapter);
    }
}