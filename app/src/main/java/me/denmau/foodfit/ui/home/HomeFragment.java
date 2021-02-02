package me.denmau.foodfit.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import me.denmau.foodfit.R;
import me.denmau.foodfit.adapter.RecipesAdapter;
import me.denmau.foodfit.model.Recipe;

public class HomeFragment extends Fragment {

    private HomeViewModel model;

    String TAG = "HomeFragment";

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        model = new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        // Create the observer which updates the UI.
        final Observer<ArrayList<Recipe>> recipesObserver = o -> {
            // update UI by:
            // create instances of Recipe appending them to arrayList
            // Call RecipesAdapter passing in the arrayList
            new RecipesAdapter(model.getRecipes().getValue());
        };

        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        model.getRecipes().observe(getViewLifecycleOwner(), recipesObserver);

        return root;
    }


}