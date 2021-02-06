package me.denmau.foodfit.Screens;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import me.denmau.foodfit.R;
import me.denmau.foodfit.adapter.RecyclerViewAdapterForIngredients;
import me.denmau.foodfit.model.Ingredient;

import static androidx.core.text.HtmlCompat.FROM_HTML_MODE_COMPACT;
import static androidx.core.text.HtmlCompat.fromHtml;

public class RecipeDetails extends AppCompatActivity {

    private TextView title, ready_in, servings, healthy, instructions;
    private ImageView img, vegetarian;
    private final List<Ingredient> ingredientsLst = new ArrayList<Ingredient>();
    private RecyclerView ingredientsRecyclerView;
    private JSONArray ingredientsArr;
    private String recipeId;
    private final String TAG = "RecipeDetails";
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);
        final Intent intent = getIntent();
        recipeId = (String) Objects.requireNonNull(intent.getExtras()).getString("id");
        fetchAndDisplayRecipeInfo(recipeId);
    }

    @Override
    protected void onStart() {
        super.onStart();
        img = (ImageView) findViewById(R.id.recipeImage);
        title = (TextView) findViewById(R.id.recipeTitle);
        ready_in = (TextView) findViewById(R.id.recipe_ready_in_min);
        servings = (TextView) findViewById(R.id.num_of_serving);
        vegetarian = (ImageView) findViewById(R.id.recipe_vegetarian);
        instructions = (TextView) findViewById(R.id.recipe_instructions);
        healthy = (TextView) findViewById(R.id.is_recipe_healthy);
    }

    private void fetchAndDisplayRecipeInfo(final String recipeId) {
        String URL = "https://api.spoonacular.com/recipes/" + recipeId + "/information?apiKey=a1d1fd312d514991be23ea34891aaef9";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                URL,
                null,
                response -> {
                    try {
                        try {
                            Picasso.get().load((String) response.get("image")).into(img);
                        } catch (Exception e) {
                            Log.d(TAG, e.getMessage());
                            img.setImageResource(R.drawable.nopicture);
                        }
                        title.setText((String) response.get("title"));
                        ready_in.setText(Integer.toString((Integer) response.get("readyInMinutes")));
                        servings.setText(Integer.toString((Integer) response.get("servings")));
                        if ((boolean) response.get("veryHealthy")) {
                            healthy.setText("Healthy");
                        }
                        if ((boolean) response.get("vegetarian")) {
                            vegetarian.setImageResource(R.drawable.vegeterian);
                        }
                        try {
                            if (response.get("instructions").equals("")) {
                                Log.d(TAG, "Found no instructions");
                                throw new Exception("No Instructions");
                            } else
                                instructions.setText(fromHtml((String) response.get("instructions"), FROM_HTML_MODE_COMPACT));
                        } catch (Exception e) {
                            // Display link to user upon failure to get instructions
                            // User can click on link to go to actual website
                            String msg = "Unfortunately, we couldn't fetch the recipe, please this link: " + "<a href=" + response.get("spoonacularSourceUrl") + ">" + response.get("spoonacularSourceUrl") + "</a>";
                            instructions.setMovementMethod(LinkMovementMethod.getInstance());
                            instructions.setText(fromHtml(msg, FROM_HTML_MODE_COMPACT));
                        }
                        ingredientsArr = (JSONArray) response.get("extendedIngredients");
                        for (int i = 0; i < ingredientsArr.length(); i++) {
                            JSONObject jsonObject1;
                            jsonObject1 = ingredientsArr.getJSONObject(i);
                            ingredientsLst.add(new Ingredient(jsonObject1.optString("originalString"), jsonObject1.optString("image")));
                        }

                        ingredientsRecyclerView = (RecyclerView) findViewById(R.id.recipe_ingredients_rv);
                        ingredientsRecyclerView.setHasFixedSize(true);
                        mLayoutManager = new GridLayoutManager(this, 2);
                        mAdapter = new RecyclerViewAdapterForIngredients(getApplicationContext(), ingredientsLst);

                        ingredientsRecyclerView.setLayoutManager(mLayoutManager);
                        ingredientsRecyclerView.setAdapter(mAdapter);
                        ingredientsRecyclerView.setAdapter(mAdapter);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> Log.i("the res is error:", error.toString())
        );
        requestQueue.add(jsonObjectRequest);
    }
}