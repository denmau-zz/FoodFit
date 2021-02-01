package me.denmau.foodfit.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import me.denmau.foodfit.R;
import me.denmau.foodfit.model.Recipe;

public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.RecipeViewHolder> {

    /*
     * Created by Dennis Kamau
     * website: https://www.denmau.me
     */

    /* instance variables for RecipeAdapter*/
    private final ArrayList<Recipe> recipes;


    /* inner class : RecipeViewHolder */
            public class RecipeViewHolder extends RecyclerView.ViewHolder {

                // declare views
                public ImageView recipeImage;
                public TextView recipeTitle;
                public TextView prepTime;
                public TextView foodFitScore;
                public TextView healthScore;
                public TextView dishType;

                // Constructor to this inner class
                public RecipeViewHolder(@NonNull View itemView) {
                    super(itemView);

                    // assign views
                    recipeImage = itemView.findViewById(R.id.recipeImage);
                    recipeTitle = itemView.findViewById(R.id.recipeTitle);
                    prepTime = itemView.findViewById(R.id.prep_time);
                    foodFitScore = itemView.findViewById(R.id.foodFitScore);
                    healthScore = itemView.findViewById(R.id.health_score);
                    dishType = itemView.findViewById(R.id.dish_type);
                }
            }
    /* out of inner class : Viewholder */


    public RecipesAdapter(ArrayList<Recipe> recipes) {
        this.recipes = recipes;
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecipeViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
        Recipe currentItem = recipes.get(position);

        // Bind the Data Received to the Views
        holder.recipeImage.setImageResource(currentItem.getRecipeImage());
        holder.recipeTitle.setText(currentItem.getRecipeTitle());
        holder.prepTime.setText(currentItem.getPreptime());
        holder.healthScore.setText((int) currentItem.getHealthscore());
        holder.foodFitScore.setText((int) currentItem.getFoodFitScore());
        holder.dishType.setText(currentItem.getDishtype());
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }
}
