package me.denmau.foodfit.Screens;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import me.denmau.foodfit.R;
import me.denmau.foodfit.adapter.RecipesAdapter;
import me.denmau.foodfit.model.Recipe;

public class SearchActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    /*
     * Created by Dennis Kamau
     * website: https://www.denmau.me
     */

    private final String TAG = "SearchActivity";
    private List<Recipe> searchRecipe = new ArrayList<>();
    private JSONArray testArr;
    private Button searchBtn;
    private Button breakfastBtn, lunchBtn, dinnerBtn;
    private TextView searchTv, emptyView;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        //getting bottom navigation view and attaching the listener
        BottomNavigationView bottomNav = findViewById(R.id.navigation);
        bottomNav.setOnNavigationItemSelectedListener((BottomNavigationView.OnNavigationItemSelectedListener) this);
        // Identify Views
        recyclerView = findViewById(R.id.recyclerviewForSearchResults);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        searchTv = findViewById(R.id.search_recipes);
        searchBtn = findViewById(R.id.search_button);
        breakfastBtn = findViewById(R.id.home_breakfast_filter);
        lunchBtn = findViewById(R.id.home_lunch_filter);
        dinnerBtn = findViewById(R.id.home_dinner_filter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        breakfastBtn.setOnClickListener(this);
        lunchBtn.setOnClickListener(this);
        dinnerBtn.setOnClickListener(this);
        searchBtn.setOnClickListener(this);
    }

    public void onClick(View v) {
        if (v == breakfastBtn) {
            searchRecipe("breakfast");
        } else if (v == lunchBtn) {
            searchRecipe("lunch");
        } else if (v == dinnerBtn) {
            searchRecipe("dinner");
        } else if (v == searchBtn) {
            if (!searchTv.getText().toString().toString().equals("")) {
                searchRecipe(searchTv.getText().toString());
            } else
                Toast.makeText(SearchActivity.this, "Type something...", Toast.LENGTH_LONG).show();
        }
    }

    private void searchRecipe(String search) {
        searchRecipe = new ArrayList<Recipe>();
        // Add your API key
        String URL = "https://api.spoonacular.com/recipes/complexSearch?query=" + search + "&number=10&addRecipeInformation=true&instructionsRequired=true&apiKey=";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                URL,
                null,
                response -> {
                    try {
                        testArr = (JSONArray) response.get("results");
                        Log.i(TAG, "search results:" + String.valueOf(testArr));
                        for (int i = 0; i < testArr.length(); i++) {
                            JSONObject jsonObject1;
                            jsonObject1 = testArr.getJSONObject(i);
                            searchRecipe.add(new Recipe(jsonObject1.optString("id"), jsonObject1.optString("title"), jsonObject1.optString("image"),
                                    Integer.parseInt(jsonObject1.optString("servings")), Integer.parseInt(jsonObject1.optString("readyInMinutes")),
                                    Double.parseDouble(jsonObject1.optString("healthScore")), Double.parseDouble(jsonObject1.optString("spoonacularScore"))));
                        }

                        if (searchRecipe.isEmpty()) {
                            Log.d(TAG, "Recipe information was downloaded but couldn't be stored in the Recipes List");
                            new SweetAlertDialog(SearchActivity.this, SweetAlertDialog.ERROR_TYPE)
                                    .setTitleText("Oops...")
                                    .setContentText("Problem saving data onto Recipes List")
                                    .show();
                        } else {
                            RecipesAdapter myAdapter = new RecipesAdapter(SearchActivity.this, searchRecipe);
                            recyclerView.setAdapter(myAdapter);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(SearchActivity.this, "Error: " + e.toString(), Toast.LENGTH_SHORT).show();
                    }
                },
                error -> {
                    Log.d(TAG, "Response was actually an error:" + error.toString());
                    new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("We cannot load data at this point, please try later")
                            .show();
                }
        );
        requestQueue.add(jsonObjectRequest);
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

            case R.id.navigation_account:
                activity = new AccountActivity();
                break;
        }
        startActivity(new Intent(this, activity.getClass()));
        return true;
    }
}