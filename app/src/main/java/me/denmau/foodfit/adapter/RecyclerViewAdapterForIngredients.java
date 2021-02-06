package me.denmau.foodfit.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import me.denmau.foodfit.R;
import me.denmau.foodfit.model.Ingredient;

public class RecyclerViewAdapterForIngredients extends RecyclerView.Adapter<RecyclerViewAdapterForIngredients.MyViewHolder> {
    private final Context mContext;
    private final List<Ingredient> mData;
    public static List<String> ingredientsList;

    public RecyclerViewAdapterForIngredients(Context mContext, List<Ingredient> mData) {
        this.mContext = mContext;
        this.mData = mData;
        ingredientsList = new ArrayList<>();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.item_ingredient, parent, false);
        return new RecyclerViewAdapterForIngredients.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapterForIngredients.MyViewHolder holder, int position) {
        // Load the image
        holder.ingredient_name.setText(mData.get(position).getName());
        Picasso.get().load(mData.get(position).getThumbnail()).into(holder.ingredient_image);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView ingredient_name;
        public ImageView ingredient_image;
        // Constructor
        public MyViewHolder(View itemView) {
            super(itemView);
            ingredient_name = itemView.findViewById(R.id.ingredient_name);
            ingredient_image = itemView.findViewById(R.id.ingredient_image);
        }
    }
}
