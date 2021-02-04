package me.denmau.foodfit.reciperecycler;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import me.denmau.foodfit.R;
import me.denmau.foodfit.recipedetails.RecipeActivity;
import me.denmau.foodfit.spoonacularapi.model.Recipe;

public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.RecipeViewHolder> {

    /*
     * Created by Dennis Kamau
     * website: https://www.denmau.me
     */
    private Context mContext;
    private List<Recipe> mData;

    public RecipesAdapter(Context mContext, List<Recipe> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.recipe_item, parent, false);
        return new RecipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {

        Recipe currentItem = mData.get(position);
        // Bind the Data Received to the Views

        holder.recipeTitle.setText(currentItem.getRecipeTitle());
        holder.prepTime.setText(String.valueOf(currentItem.getReadyInMins()) + "min");
        holder.healthScore.setText(String.valueOf(currentItem.getHealthScore()));
        holder.foodFitScore.setText(String.valueOf(currentItem.getFoodFitScore()));

        // Set Recipe Image
        if (currentItem.getRecipeImage().isEmpty()) {
            // No image
            holder.recipeImage.setImageResource(R.drawable.nopicture);
        } else {
            Picasso.get().load(mData.get(position).getRecipeImage()).into(holder.recipeImage);
        }

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("CustomTag", "Relax Everything going Well");
                Intent intent = new Intent(mContext, RecipeActivity.class);
                intent.putExtra("id", mData.get(position).getId());
//                intent.putExtra("title", mData.get(position).getRecipeTitle());
//                intent.putExtra("img", mData.get(position).getRecipeImage());
                Log.e("CustomTag", "RecipeActivity called for #" + mData.get(position).getId());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    /* inner ViewHolder class */
    public static class RecipeViewHolder extends RecyclerView.ViewHolder {
        // declare views
        public ImageView recipeImage;
        public TextView recipeTitle;
        public TextView prepTime;
        public TextView foodFitScore;
        public TextView healthScore;
        public CardView cardView;

        // Constructor to this inner class
        public RecipeViewHolder(@NonNull View itemView) {
            super(itemView);
            // assign views
            recipeImage = itemView.findViewById(R.id.recipeImage);
            recipeTitle = itemView.findViewById(R.id.recipeTitle);
            prepTime = itemView.findViewById(R.id.prep_time);
            foodFitScore = itemView.findViewById(R.id.foodFitScore);
            healthScore = itemView.findViewById(R.id.health_score);
            cardView = (CardView) itemView.findViewById(R.id.cardView);
        }
    }
    /* out of inner class : ViewHolder */
}
