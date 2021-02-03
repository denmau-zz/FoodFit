package me.denmau.foodfit.reciperecycler;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import me.denmau.foodfit.R;

public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.RecipeViewHolder> {

    /*
     * Created by Dennis Kamau
     * website: https://www.denmau.me
     */

    /* instance variables for RecipeAdapter*/
    private final ArrayList<RecipeModel> recipes;
    private final String TAG = "RecipesAdapter";

    // Constructor
    public RecipesAdapter(ArrayList<RecipeModel> recipes) {
        this.recipes = recipes;
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecipeViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {

        RecipeModel currentItem = recipes.get(position);
        // Bind the Data Received to the Views
        holder.recipeImage.setImageResource(currentItem.getRecipeImage());
        holder.recipeTitle.setText(currentItem.getRecipeTitle());
        holder.prepTime.setText(String.valueOf(currentItem.getPrepTime()) + "min");
        holder.healthScore.setText(String.valueOf(currentItem.getHealthScore()));
        holder.foodFitScore.setText(String.valueOf(currentItem.getFoodFitScore()));
        holder.dishType.setText(currentItem.getDishType());
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

    /* inner ViewHolder class */
    public static class RecipeViewHolder extends RecyclerView.ViewHolder {
        private final String TAG = "RecipeViewHolder";
        public CardView recipeCardView; // for clickListener purposes
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

            recipeCardView = (CardView) itemView.findViewById(R.id.cardView);

            recipeCardView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    // When a recipe Card is clicked
                    Log.i(TAG, "You clicked on recipe card #" + getAdapterPosition());
                }
            });
        }
    }
    /* out of inner class : ViewHolder */
}
