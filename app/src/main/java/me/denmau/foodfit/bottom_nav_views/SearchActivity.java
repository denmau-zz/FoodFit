package me.denmau.foodfit.bottom_nav_views;

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
import com.android.volley.Response;
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
import me.denmau.foodfit.reciperecycler.RecipesAdapter;
import me.denmau.foodfit.spoonacularapi.model.Recipe;

public class SearchActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    private String TAG = "Search Activity";

    private List<Recipe> lstRecipe = new ArrayList<>();
    private List<Recipe> searchRecipe;
    private JSONArray testArr;
    private Button searchBtn;
    private Button breakfastBtn, lunchBtn, dinnerBtn;
    private TextView searchTv, emptyView;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        // Identify Views
        BottomNavigationView bottomNav = findViewById(R.id.navigation);

        //getting bottom navigation view and attaching the listener
        bottomNav.setOnNavigationItemSelectedListener((BottomNavigationView.OnNavigationItemSelectedListener) this);

        searchTv = findViewById(R.id.search_recipes);
        searchBtn = findViewById(R.id.search_btn);
        breakfastBtn = findViewById(R.id.home_breakfast_filter);
        lunchBtn = findViewById(R.id.home_lunch_filter);
        dinnerBtn = findViewById(R.id.home_dinner_filter);
        breakfastBtn.setOnClickListener(this);
        lunchBtn.setOnClickListener(this);
        dinnerBtn.setOnClickListener(this);
        searchBtn.setOnClickListener(this);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

    }

    private void searchRecipe(String search) {
        searchRecipe = new ArrayList<Recipe>();
        String URL = "https://api.spoonacular.com/recipes/search?query=" + search + "&number=15&instructionsRequired=true&apiKey=a1d1fd312d514991be23ea34891aaef9";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                URL,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            testArr = (JSONArray) response.get("results");
                            Log.i("search results:", String.valueOf(testArr));
                            for (int i = 0; i < testArr.length(); i++) {
                                JSONObject jsonObject1;
                                jsonObject1 = testArr.getJSONObject(i);
                                searchRecipe.add(new Recipe(jsonObject1.optString("id"), jsonObject1.optString("title"), "https://spoonacular.com/recipeImages/" + jsonObject1.optString("image"),
                                        Integer.parseInt(jsonObject1.optString("servings")), Integer.parseInt(jsonObject1.optString("readyInMinutes")),
                                        Double.parseDouble(jsonObject1.optString("healthScore")), Double.parseDouble(jsonObject1.optString("spoonacularScore"))));
                            }

                            if (searchRecipe.isEmpty()) {
                                recyclerView.setAlpha(0);
                                new SweetAlertDialog(SearchActivity.this, SweetAlertDialog.ERROR_TYPE)
                                        .setTitleText("Oops...")
                                        .setContentText("No Data")
                                        .show();
                            } else {

                                RecipesAdapter myAdapter = new RecipesAdapter(SearchActivity.this, searchRecipe);
                                recyclerView.setAdapter(myAdapter);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(SearchActivity.this, "Error: " + e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                error -> Log.d("the res is error:", error.toString())
        );
        requestQueue.add(jsonObjectRequest);
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Identify Views

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
}