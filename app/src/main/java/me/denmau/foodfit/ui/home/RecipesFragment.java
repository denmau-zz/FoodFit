package me.denmau.foodfit.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import me.denmau.foodfit.R;
import me.denmau.foodfit.reciperecycler.RecipeModel;
import me.denmau.foodfit.reciperecycler.RecipesAdapter;

public class RecipesFragment extends Fragment {
    private final String TAG = "RecipesFragment";

    private ArrayList<RecipeModel> recipes;
    RecipesAdapter recipesAdapter;

    public void setRecipes(ArrayList<RecipeModel> recipes) {
        this.recipes = recipes;
    }

    public static RecipesFragment newInstance(ArrayList<RecipeModel> recipes) {
        RecipesFragment fragment = new RecipesFragment();
        fragment.setRecipes(recipes);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recipes, container, false);
        int numberOfColumns = 2;

        RecyclerView mRecyclerView = view.findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(false);

        mRecyclerView.setLayoutManager(new GridLayoutManager(view.getContext(), numberOfColumns));
        recipesAdapter = new RecipesAdapter(recipes);
        mRecyclerView.setAdapter(recipesAdapter);
        return view;
    }
}