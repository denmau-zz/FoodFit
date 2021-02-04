package me.denmau.foodfit.spoonacularapi;

import android.content.Context;
import android.content.Intent;
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
import me.denmau.foodfit.recipedetails.RecipeDetailsActivity;
import me.denmau.foodfit.spoonacularapi.model.Recipe;

public class DataProcessor extends RecyclerView.Adapter<DataProcessor.RecipeViewHolder> {

    /*
     * Created by Dennis Kamau
     * website: https://www.denmau.me
     */

    /*
    This class should process data and pass to all activities that need it
     Responsible for communicating with API and adding results to arrayList
    Then passing the arrayList to a recyclerView
    */

    private Context mContext;
    private List<Recipe> mData;


    public DataProcessor(Context mContext, List<Recipe> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.cardview_item_search_result, parent, false);
        return new RecipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DataProcessor.RecipeViewHolder holder, int position) {
        holder.recipe_title_result.setText(mData.get(position).getRecipeTitle());
        if (mData.get(position).getRecipeImage().isEmpty()) {
            holder.recipe_image_result.setImageResource(R.drawable.food_fit_logo);
        } else {
            Picasso.get().load(mData.get(position).getRecipeImage()).into(holder.recipe_image_result);
        }
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, RecipeDetailsActivity.class);
                intent.putExtra("id", mData.get(position).getId());
                intent.putExtra("title", mData.get(position).getRecipeTitle());
                intent.putExtra("img", mData.get(position).getRecipeImage());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class RecipeViewHolder extends RecyclerView.ViewHolder {
        public TextView recipe_title_result;
        public ImageView recipe_image_result;
        public CardView cardView;

        public RecipeViewHolder(View itemView) {
            super(itemView);
            recipe_title_result = itemView.findViewById(R.id.recipe_title_result);
            recipe_image_result = itemView.findViewById(R.id.recipe_image_result);
            cardView = itemView.findViewById(R.id.search_result_cardview);
        }
    }
}