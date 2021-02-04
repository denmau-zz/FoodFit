package me.denmau.foodfit.adapter;

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
import me.denmau.foodfit.Screens.RecipeDetails;
import me.denmau.foodfit.model.Recipe;

public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.RecipeViewHolder> {

    /*
     * Created by Dennis Kamau
     * website: https://www.denmau.me
     */

    /* inner ViewHolder class */
    public static class RecipeViewHolder extends RecyclerView.ViewHolder {
        // declare views
        public ImageView recipeImage;
        public TextView recipeTitle;
        public CardView cardView;

        // Constructor to this inner class
        public RecipeViewHolder(@NonNull View itemView) {
            super(itemView);
            // assign views
            recipeImage = itemView.findViewById(R.id.image_of_recipe);
            recipeTitle = itemView.findViewById(R.id.title_of_recipe);
            cardView = (CardView) itemView.findViewById(R.id.cardView);
        }
    }
    /* out of inner class : ViewHolder */

    private String TAG = "RecipesAdapter";
    private Context mContext;
    private final List<Recipe> mData;

    public RecipesAdapter(Context mContext, List<Recipe> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_recipes_adapter, parent, false);
        return new RecipeViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
        Recipe currentRecipe = mData.get(position);
        // Bind Recipe Data to the Card Views

        if (currentRecipe.getRecipeImage().isEmpty()) {
            // No image
            holder.recipeImage.setImageResource(R.drawable.nopicture);
        } else {
            Picasso.get().load(currentRecipe.getRecipeImage())
                    .into(holder.recipeImage);
        }
        Log.d(TAG, "Recipe Thumbnail: " + currentRecipe.getRecipeImage());
        holder.recipeTitle.setText(currentRecipe.getRecipeTitle());
        // Ensure CardView is clickable
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, RecipeDetails.class);
                intent.putExtra("id", mData.get(position).getId());
                intent.putExtra("title", mData.get(position).getRecipeTitle());
                intent.putExtra("img", mData.get(position).getRecipeImage());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

}